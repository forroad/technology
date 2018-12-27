package com.ycjw.technology.util;

import com.ycjw.technology.exception.ExceptionZyc;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.UUID;

public class ImageUtil {
    public static String saveImg(MultipartFile file) throws ExceptionZyc {
        if (file.isEmpty()) {
            throw ExceptionZyc.IMG_IS_NULL;
        }
        String fileName = file.getOriginalFilename();  // 文件名
        String suffixName = fileName.substring(fileName.lastIndexOf("."));  // 后缀名
        String filePath = "F://img//"; // 上传后的路径
        fileName = UUID.randomUUID() + suffixName; // 新文件名
        File dest = new File(filePath + fileName);
        if (!dest.getParentFile().exists()) {
            dest.getParentFile().mkdirs();
        }
        try {
            file.transferTo(dest);
        } catch (IOException e) {
            throw ExceptionZyc.UPLOAD_FAIL;
        }
        return fileName;
    }
}
