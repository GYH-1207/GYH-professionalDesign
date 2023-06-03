package com.xiaoxi.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Date;

@Data
public class Document {
    private Long id;
    private Long userId;//文件所属人，对应user类
//    private byte[] f;
    private String name; //文件名
    private String realName; //路径名
    private double size; //文件大小
    private String type;   //文件类型
    private String userName;   //用户名

    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime creatTime; //创建时间,自动填充
}
