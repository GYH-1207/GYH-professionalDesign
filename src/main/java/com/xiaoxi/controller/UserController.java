package com.xiaoxi.controller;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.xiaoxi.common.BaseContext;
import com.xiaoxi.common.R;
import com.xiaoxi.dto.LoginFormDTO;
import com.xiaoxi.entity.Result;
import com.xiaoxi.entity.User;
import com.xiaoxi.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/user")
@Slf4j
@CrossOrigin
public class UserController {
    @Autowired
    private UserService userService;

    /**
     * 1.加上@RequestBody注解后，前端就只能发送post请求，后端才可以接收到
     * 2.因为前端设置了超时时间，所以如果后端打了断点，前端会超时而收不到响应
     * @param loginFormDTO
     * @return
     */
    @PostMapping("/login")
    public Result login(@RequestBody LoginFormDTO loginFormDTO){
        return userService.login(loginFormDTO);


    }
    //注册
    @PostMapping("/regist")
    public Result  regist(@RequestBody User user){
        return userService.regist(user);
    }
    //退出登录
    @GetMapping("/logout")
    public Result  logout(){
        return userService.logout();
    }

    //查询所有用户
    @GetMapping("/list")
    public R<List<User>> list(){
        log.info("查询用户list成功");
        List<User> list = userService.list();
        return R.success(list);
    }

}
