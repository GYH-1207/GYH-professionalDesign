package com.xiaoxi;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.xiaoxi.common.R;
import com.xiaoxi.entity.Document;
import com.xiaoxi.entity.User;
import com.xiaoxi.service.DocumentService;
import com.xiaoxi.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.Map;

@SpringBootTest
class ApplicationTests {
    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private DocumentService documentService;

    @Autowired
    private UserService userService;

    @Test
    void contextLoads() {
        User user = new User();
        user.setUserName("小汐大人");
        System.out.println("isExitsUser="+userService.IsExitsUser(user));
    }
}
