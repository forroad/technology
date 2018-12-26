package com.ycjw.technology.controller.account;

import com.ycjw.technology.exception.ExceptionZyc;
import com.ycjw.technology.model.Response;
import com.ycjw.technology.model.user.User;
import com.ycjw.technology.service.account.AccountService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("account")
public class AccountController {
    @Autowired
    AccountService accountService;

    @ApiOperation("登录")
    @PostMapping("login")
    public Response login(@RequestParam("username") String username,
                          @RequestParam("password") String password,
                          @RequestParam("role") User.Role role) throws ExceptionZyc {
        return new Response("登录成功",accountService.login(username,password,role));
    }
    @ApiOperation("注册")
    @PutMapping("register")
    public Response register(@RequestParam("username") String username,
                             @RequestParam("fPassword") String fPassword,
                             @RequestParam("sPassword") String sPassword,
                             @RequestParam("name") String name,
                             @RequestParam("role") User.Role role) throws ExceptionZyc {
        return new Response("注册成功",accountService.register(username,fPassword,sPassword,name,role));
    }

    @ApiOperation(("删除导师"))
    @DeleteMapping("delete")
    public Response delete(@RequestParam("username") String usernamr) throws ExceptionZyc{
        return new Response("删除成功",accountService.deleteUser(usernamr));
    }

    @ApiOperation(("管理员修改导师信息"))
    @PostMapping("modifyUser")
    public Response modifyUser(@RequestParam("id") int id,
                               @RequestParam("username") String username,
                               @RequestParam("password") String password,
                               @RequestParam("name") String name) throws ExceptionZyc{
        return new Response("修改成功",accountService.modifyUser(id, username, name, password));
    }

    @ApiOperation(("导师修改密码"))
    @PostMapping("modifyPassword")
    public Response modifyPassword(@RequestParam("username") String username,
                               @RequestParam("oldPassword") String oldPassword,
                               @RequestParam("fPassword") String fPassword,
                               @RequestParam("sPassword") String sPassword) throws ExceptionZyc{
        return new Response("修改成功",accountService.modifyPassword(username, oldPassword, fPassword, sPassword));
    }
}
