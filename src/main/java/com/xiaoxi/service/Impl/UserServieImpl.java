package com.xiaoxi.service.Impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import cn.hutool.core.lang.UUID;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xiaoxi.common.R;
import com.xiaoxi.dto.LoginFormDTO;
import com.xiaoxi.dto.UserDTO;
import com.xiaoxi.entity.Result;
import com.xiaoxi.entity.User;
import com.xiaoxi.mapper.UserMapper;
import com.xiaoxi.service.UserService;
import com.xiaoxi.util.UserHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static com.xiaoxi.util.RedisConstants.LOGIN_USER_KEY;
import static com.xiaoxi.util.RedisConstants.LOGIN_USER_TTL;

@Service
public class UserServieImpl extends ServiceImpl<UserMapper, User> implements UserService {
    @Autowired
    UserMapper userMapper;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public boolean IsExitsUser(User user) {
        JSONObject jsonObject = (JSONObject) JSONObject.toJSON(user);
        return userMapper.IsExitsUser(jsonObject);
    }

    @Override
    public Result login(LoginFormDTO loginFormDTO) {
        //1.查询
        User user = query().eq("userName", loginFormDTO.getUserName()).one();
        //2.为空或与密码不匹配则登录失败
        if(user==null){
            return Result.fail("用户名不存在");
        }
        if(!user.getPassWord().equals(loginFormDTO.getPassword())){
            return Result.fail("密码错误");
        }
        //3.登录成功，那么就生成的令牌和登录信息存到redis，并返回给前台，前台进行存储（下次来访问的时候就就携带令牌，判断是否已经登录）
        //使用uuid生成token，避免重复
        String token = UUID.randomUUID().toString(true);
        // 3.1 将User对象转为HashMap存储
        UserDTO userDTO = BeanUtil.copyProperties(user, UserDTO.class);
        Map<String, Object> userMap = BeanUtil.beanToMap(userDTO, new HashMap<>(),
                CopyOptions.create()
                        .setIgnoreNullValue(true)
                        .setFieldValueEditor((fieldName, fieldValue) -> fieldValue.toString()));
        // 7.3.存储
        String tokenKey = LOGIN_USER_KEY + token;
        stringRedisTemplate.opsForHash().putAll(tokenKey, userMap);
        // 7.4.设置token有效期
        stringRedisTemplate.expire(tokenKey, LOGIN_USER_TTL, TimeUnit.MINUTES);

        return Result.ok(token);
    }

    @Override
    public Result regist(User user) {
        User one = query().eq("userName",user.getUserName()).one();
        //重复
        if(one != null){
            return Result.fail("用户名已存在");
        }
        save(user);
        return Result.ok("创建成功");
    }

    @Override
    public Result logout() {
        String token = UserHolder.getUser().getToken();
        String key  = LOGIN_USER_KEY + token;
        stringRedisTemplate.opsForHash().delete(key);
        return Result.ok("退出成功");
    }
}
