package com.xiaoxi.controller;

import com.xiaoxi.common.R;
import com.xiaoxi.util.ValidateCodeUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController
@Slf4j
@RequestMapping("/common")
public class CommonController {
    @Value("${ssm_lab.path}")
    private String basePath;
    /**
     * 用post提交，不然url会过长出错
     * @return
     */
    @PostMapping("/upload")
    public R<Map<String,String>> upload(MultipartFile file){
        log.info("文件大小：{}",file.getSize());
        Long fileSize=file.getSize();

        log.info("进入文件上传");
        //初始操作
        String fileName = file.getOriginalFilename().toString();
        log.info("fileName=",fileName);
        //文件后缀
        String suffix = fileName.substring(fileName.lastIndexOf("."));
        //生成6位文件名，可以用uuid，但是因为太长了，不好观察所以没用
        Integer integer = ValidateCodeUtils.generateValidateCode(6);
        fileName=integer+suffix;

        //创建一个目录对象
        File dir = new File(basePath);
        //判断当前目录是否存在
        if (!dir.exists()) {
            //目录不存在，需要创建
            dir.mkdirs();
        }

        try {
            //将临时文件转存到指定位置
            file.transferTo(new File(basePath + fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
        //封装好后传给前端
        Map<String,String> map=new HashMap<>();
        map.put("name",fileName);
        map.put("fileSize",fileSize.toString());
        map.put("suffix",suffix);
        return R.success(map);
    }

    /**
     * 文件下载
     *
     * @param name
     * @param response
     */
    @GetMapping("/download")
    public void download(String name, HttpServletResponse response) {

        try {
            //输入流，通过输入流读取文件内容
            FileInputStream fileInputStream = new FileInputStream(new File(basePath + name));

            if (fileInputStream != null) {
                //输出流，通过输出流将文件写回浏览器
                ServletOutputStream outputStream = response.getOutputStream();

                response.setContentType("image/jpeg");

                int len = 0;
                byte[] bytes = new byte[1024];
                while ((len = fileInputStream.read(bytes)) != -1) {
                    outputStream.write(bytes, 0, len);
                    outputStream.flush();
                }
                //关闭资源
                outputStream.close();
            }
            fileInputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}