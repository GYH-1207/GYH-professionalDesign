package com.xiaoxi.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Data
//与document是一对多关系
public class User implements Serializable {
    private Long id;//编号
    private String userName;//用户名
    private String passWord;//密码
    private int size;//可拥有剩余的文件大小
    private String nickName;//昵称
    private String icon;//用户头像
    private LocalDateTime createTime;
    private String createUser;
    private LocalDateTime updateTime;
    private String updateUser;
}
