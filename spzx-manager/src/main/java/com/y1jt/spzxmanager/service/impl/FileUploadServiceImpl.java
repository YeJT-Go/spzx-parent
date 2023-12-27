package com.y1jt.spzxmanager.service.impl;

import cn.hutool.core.date.DateUtil;
import com.y1jt.commonservice.excetion.GuguiException;
import com.y1jt.spzxmanager.properties.MinioOProperties;
import com.y1jt.spzxmanager.service.FileUploadService;
import com.y1jt.spzxmodel.entity.system.SysRole;
import com.y1jt.spzxmodel.vo.common.ResultCodeEnum;
import io.minio.*;
import io.minio.errors.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * @author : YeJT
 * @date : 2023-12-08 15:23
 * @Description: 文件上传处理
 */
@Service
public class FileUploadServiceImpl implements FileUploadService {

    @Autowired
    private MinioOProperties minioOProperties;

    @Override
    public String upload(MultipartFile file) {
        try {
            MinioClient minioClient = MinioClient.builder().endpoint(minioOProperties.getEndpoint())
                    .credentials(minioOProperties.getAccessKey(), minioOProperties.getSecretKey())
                    .build();

            //判断bucket是否存在
            boolean found = minioClient.bucketExists(BucketExistsArgs.builder().bucket(minioOProperties.getBucketName()).build());
            if (!found) {
                minioClient.makeBucket(MakeBucketArgs.builder().bucket(minioOProperties.getBucketName()).build());
            } else {
                System.out.println("Bucket 'spzx-bucket' already exists");
            }

            //今天日期 例：20231208
            String today = DateUtil.format(new Date(), "yyyyMMdd");
            String uuid = UUID.randomUUID().toString();
            String filename = today + "/" + uuid + file.getOriginalFilename();
            //文件上传
            minioClient.putObject(PutObjectArgs.builder().bucket(minioOProperties.getBucketName())
                    .object(filename)
                    .stream(file.getInputStream(), file.getSize(), -1).build());
            return minioOProperties.getEndpoint() + "/" + minioOProperties.getBucketName() + "/" + filename;
        } catch (Exception e) {
            e.printStackTrace();
            throw new GuguiException(ResultCodeEnum.SYSTEM_ERROR);
        }
    }
}
