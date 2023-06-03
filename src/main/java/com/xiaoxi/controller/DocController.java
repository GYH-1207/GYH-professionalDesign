package com.xiaoxi.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.xiaoxi.common.BaseContext;
import com.xiaoxi.common.R;
import com.xiaoxi.entity.Document;
import com.xiaoxi.entity.User;
import com.xiaoxi.service.DocumentService;
import com.xiaoxi.service.UserService;
import com.xiaoxi.util.ValidateCodeUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

@RestController
@Slf4j
@RequestMapping("/doc")
@CrossOrigin
public class DocController {
    @Autowired
    private DocumentService documentService;
    @Autowired
    private UserService userService;

    /**
     * 条件查询/查询全部
     * @param type
     * @return
     */
    @GetMapping("/list")
    public R<List<Document>> list(String type){
        LambdaQueryWrapper<Document> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(type!=null,Document::getType,type);
        List<Document> list = documentService.list(queryWrapper);
        return R.success(list);
    }

    @PostMapping("/add")
    public R<String> add(@RequestBody Document document){
        //将参数补全，在添加
        Long userId = BaseContext.getCurrentId();
        document.setUserId(userId);

        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<User>();
        queryWrapper.eq(User::getId,userId);
        User user = userService.getOne(queryWrapper);

        document.setUserName(user.getUserName());
        documentService.save(document);
        return R.success("添加成功");
    }
}
