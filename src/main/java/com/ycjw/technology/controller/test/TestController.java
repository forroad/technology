package com.ycjw.technology.controller.test;

import com.ycjw.technology.model.test.Test;
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
        Test test = testDao.findByName("赵贻成");
        Test test1 = new Test();
        test1.setName("ycjw");
        testDao.save(test1);
        return test.getId() + "";
    }


}
