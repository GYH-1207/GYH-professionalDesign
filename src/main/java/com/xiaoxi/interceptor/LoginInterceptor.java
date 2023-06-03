package com.xiaoxi.interceptor;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.util.TokenBuffer;
import com.xiaoxi.common.BaseContext;
import com.xiaoxi.common.R;
import com.xiaoxi.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.servlet.HandlerInterceptor;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.concurrent.TimeUnit;

@Slf4j
@Component
public class LoginInterceptor implements HandlerInterceptor {
    @Autowired
    private RedisTemplate redisTemplate;

    //路径匹配器，支持通配符
    public static final AntPathMatcher PATH_MATCHER = new AntPathMatcher();

    //这个方法是在访问接口之前执行的，我们只需要在这里写验证登陆状态的业务逻辑，就可以在用户调用指定接口之前验证登陆状态了
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        /**（跨域问题已解决）
         * 实际上发送了两次请求，第一次为 OPTIONS 请求，第二次才 GET/POST… 请求
         * 在OPTIONS请求中，不会携带请求头的参数，所以在拦截器上获取请求头为空，自定义的拦截器拦截成功
         * 第一次请求不能通过，就不能获取第二次的请求了 GET/POST…
         * 第一次请求不带参数，第二次请求才带参数
         */
        if (HttpMethod.OPTIONS.toString().equals(request.getMethod())) {
            System.out.println("OPTIONS请求，放行");
            return true;
        }
        String uri = request.getRequestURI();
        log.info("登录拦截: {}",uri);

        //1.设置放行路径
        //不拦截路径（登录路径等等）
        String[] urls= new String[]{
                "/user/login",
                "/user/regist",
                "/user/logout"
        };
        boolean check = check(urls, uri);
        if(check){
            return true;
        }
        response.setContentType("application/json; charset=utf-8");
         //2.拿到请求头里面的token（如果是第一次登录，那么是没有请求头的）
        String token = request.getHeader("u-token");
        if(token==null){
            //2.1 拦截请求并返回信息给前台 （前台后置拦截器就是根据这里面返回的json数据，来判读并跳转到登录界面）
            response.getWriter().print(JSON.toJSONString(R.error("NOTLOGIN")));
            log.info("登录拦截,无token");
            return false;
        }
        //3、如果有token，那么就根据这个token从redis查询登录用户信息，如果redis里面还没过期，那么就正常放行，没有就进行拦截，并返回信息，叫他重新登录
        String tokenUser = (String) redisTemplate.opsForValue().get(token);
        if(tokenUser==null){
            response.getWriter().print(JSON.toJSONString(R.error("NOTLOGIN")));
            log.info("登录拦截,无tokenUser");
            return false;
        }

        //获取user_id,放入线程池
        String substring = tokenUser.substring(tokenUser.indexOf("{"));
        User user = JSONArray.parseObject(substring, User.class);
        log.info("user_id={}",user.getId());
        BaseContext.setCurrentId(user.getId());


        //4.如果没有过期，那么就重新将token和登录用户信息存到redis
        redisTemplate.opsForValue().set(token, tokenUser, 30, TimeUnit.MINUTES);
        log.info("请求符合条件，可以放行");
        return true;
    }

    /**
     * 路径匹配，检查本次请求是否需要放行
     *
     * @param urls
     * @param requestURI
     * @return
     */
    public boolean check(String[] urls, String requestURI) {
        for (String url : urls) {
            boolean match = PATH_MATCHER.match(url, requestURI);
            if (match) {
                return true;
            }
        }
        return false;
    }
}
