package com.zhumingbei.babybei_server.controller;

import com.zhumingbei.babybei_server.common.ApiResponse;
import com.zhumingbei.babybei_server.config.UploadConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/**
 * @author fadedfate
 * @date Created at 2019/8/21 10:30
 */
@Controller
@Slf4j
public class UploadController {
    @Autowired
    private UploadConfig uploadConfig;
    @PostMapping("/upload")
    @ResponseBody
    @PreAuthorize("hasAuthority('admin:upload')")
    public ApiResponse fileUpload(@RequestParam("file") MultipartFile file) {
        log.info("start to upload");
        if (file.isEmpty()) {
            return new ApiResponse(400, "文件内容为空");
        }
        String tmp = uploadConfig.getPath();
        File dir = new File(tmp);
        if(!dir.exists()){
            dir.mkdir();
        }
        String filePath = tmp + "/" + file.getOriginalFilename();
        File newFile = new File(filePath);
        try {
            file.transferTo(newFile);
        } catch (IOException e) {
            log.error("文件上传失败," + e.toString());
            return new ApiResponse(500, "文件上次失败");
        }
        return new ApiResponse(200, "文件上传成功", "filepath: " + filePath);
    }

    @GetMapping("/upload")
    public String index() {
        return "upload";
    }
}
