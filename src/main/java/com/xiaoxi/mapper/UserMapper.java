package com.xiaoxi.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xiaoxi.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.Map;

@Mapper
public interface UserMapper extends BaseMapper<User> {
    public boolean IsExitsUser(Map<String,Object> userMap);
}
