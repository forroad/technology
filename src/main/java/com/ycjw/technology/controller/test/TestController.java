package com.ycjw.technology.controller.test;

import com.ycjw.technology.model.user.User;
import com.ycjw.technology.repository.test.TestDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("test")
public class TestController {
    @Autowired
    TestDao testDao;

    @GetMapping("string")
    public String getString(){
        return "test";
    }


}
