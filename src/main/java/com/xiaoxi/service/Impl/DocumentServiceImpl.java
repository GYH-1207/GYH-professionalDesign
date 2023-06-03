package com.xiaoxi.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xiaoxi.entity.Document;
import com.xiaoxi.mapper.DocumentMapper;
import com.xiaoxi.service.DocumentService;
import org.springframework.stereotype.Service;

@Service
public class DocumentServiceImpl extends ServiceImpl<DocumentMapper, Document> implements DocumentService {
}
