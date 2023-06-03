package com.xiaoxi.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xiaoxi.dto.LoginFormDTO;
import com.xiaoxi.entity.Result;
import com.xiaoxi.entity.User;

public interface UserService extends IService<User> {
    boolean IsExitsUser(User user);

    Result login(LoginFormDTO loginFormDTO);

    Result regist(User user);

    Result logout();
}
