package com.y1jt.spzxmanager.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * @author : YeJT
 * @date : 2023-12-08 15:23
 * @Description: 文件上传处理
 */
public interface FileUploadService {
    String upload(MultipartFile file);
}
