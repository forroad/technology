package com.ycjw.technology.controller.test;

import com.ycjw.technology.model.test.Test;
import com.ycjw.technology.model.user.User;
import com.ycjw.technology.repository.test.TestDao;
import com.ycjw.technology.repository.user.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("test")
public class TestController {
    @Autowired
    TestDao testDao;
    @Autowired
    UserDao userDao;

    @GetMapping("string")
    public String getString(){
        User user = new User("123456","123456",System.currentTimeMillis()+"","赵贻成");
        userDao.saveAndFlush(user);
        return user.getName();
    }


}
