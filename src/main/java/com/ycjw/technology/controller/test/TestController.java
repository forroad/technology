package com.ycjw.technology.controller.test;

import com.ycjw.technology.exception.ExceptionZyc;
import com.ycjw.technology.model.user.User;
import com.ycjw.technology.repository.test.TestDao;
import com.ycjw.technology.service.project.impl.ProjectServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


@RestController
@RequestMapping("test")
public class TestController {
    @Autowired
    TestDao testDao;

    @GetMapping("string")
    public String getString(){
        return "test";
    }

    @PutMapping("uploadImg")
    public String uploadImg(@RequestParam(value = "file") MultipartFile multipartFile) throws ExceptionZyc {
        return  ProjectServiceImpl.saveImg(multipartFile);
    }

}
