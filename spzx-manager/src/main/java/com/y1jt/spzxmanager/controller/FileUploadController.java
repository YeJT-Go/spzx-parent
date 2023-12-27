package com.y1jt.spzxmanager.controller;

import com.y1jt.spzxmanager.service.FileUploadService;
import com.y1jt.spzxmodel.vo.common.Result;
import com.y1jt.spzxmodel.vo.common.ResultCodeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author : YeJT
 * @date : 2023-12-08 15:14
 * @Description: TODO
 */
@RestController
@RequestMapping("admin/system")
public class FileUploadController {

    @Autowired
    private FileUploadService fileUploadService;

    @PostMapping("fileUpload")
    public Result fileUpload(@RequestHeader("file")MultipartFile file){
       String url = fileUploadService.upload(file);
        return Result.build(url, ResultCodeEnum.SUCCESS);
    }

}
