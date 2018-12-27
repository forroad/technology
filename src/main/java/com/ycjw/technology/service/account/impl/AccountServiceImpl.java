package com.ycjw.technology.service.account.impl;

import com.ycjw.technology.exception.ExceptionZyc;
import com.ycjw.technology.model.user.Admin;
import com.ycjw.technology.model.user.Mentor;
import com.ycjw.technology.model.user.User;
import com.ycjw.technology.repository.user.AdminDao;
import com.ycjw.technology.repository.user.MentorDao;
import com.ycjw.technology.service.account.AccountService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;
import java.util.Base64;

@Service
@Transactional
public class AccountServiceImpl implements AccountService {
    @Autowired
    AdminDao adminDao;
    @Autowired
    MentorDao mentorDao;
    @Autowired
    HttpSession session;
    @Override
    public User login(String username, String password,User.Role role) throws ExceptionZyc {
        verifyUsername(username);
        //验证密码是否符合规范
        if(StringUtils.isEmpty(password)||password.length() < 6){
            //密码不符合规范，抛出异常
            throw ExceptionZyc.PASSWORD_IS_NOT_TRUE;
        }
        //登录
        if(role.equals(User.Role.admin)){
            //查询数据库
            Admin admin = adminDao.findByUsername(username);
            //判断用户是否存在
            if(admin == null){
                //用户不存在，抛出错误
                throw ExceptionZyc.USER_IS_NOT_EXIST;
            }
            //用户存在，判断密码是否相同
            if(!admin.getPassword().endsWith(encode(password,admin.getSalt()))){
                //密码不同，抛出错误
                throw ExceptionZyc.PASSWORD_IS_WRONG;
            }
            //信息符合，执行登录操作,将用户名,角色存入会话中
            session.setAttribute("username",admin.getUsername());
            session.setAttribute("role",admin.getRole());
            //返回数据
            return admin;
        }else {
            //查询数据库
            Mentor mentor = mentorDao.findByUsername(username);
            //判断用户是否存在
            if(mentor == null){
                //用户不存在，抛出错误
                throw ExceptionZyc.USER_IS_NOT_EXIST;
            }
            //用户存在，判断密码是否相同
            if(!mentor.getPassword().endsWith(encode(password,mentor.getSalt()))){
                //密码不同，抛出错误
                throw ExceptionZyc.PASSWORD_IS_WRONG;
            }
            //信息符合，执行登录操作,将用户名,角色存入会话中
            session.setAttribute("username",mentor.getUsername());
            session.setAttribute("role",mentor.getRole());
            //返回数据
            return mentor;
        }
    }

    @Override
    public User register(String username, String fPassword, String sPassword,String name,User.Role role) throws ExceptionZyc {
        verifyUsername(username);
        //验证密码是否符合规范
        if(StringUtils.isEmpty(fPassword)||fPassword.length() < 6){
            //密码不符合规范，抛出异常
            throw ExceptionZyc.PASSWORD_IS_NOT_TRUE;
        }
        //验证两次密码是否相同
        if(!fPassword.equals(sPassword)){
            //两次密码不相同，抛出异常
            throw ExceptionZyc.PASSWORD_IS_NOT_EQUALS;
        }
        //判断用户是否已存在
        if(adminDao.findByUsername(username) != null|| mentorDao.findByUsername(username) != null){
            //用户已存在
            throw ExceptionZyc.USER_IS_EXIST;
        }
        //信息符合规范，新建用户
        if(role.equals(User.Role.admin)){
            Admin admin = new Admin();
            //设置账号
            admin.setUsername(username);
            //设置盐
            admin.setSalt(System.currentTimeMillis() + "");
            //设置密码
            admin.setPassword(encode(fPassword,admin.getSalt()));
            //设置姓名
            admin.setName(name);
            //设置角色
            admin.setRole(role);
            //保存用户
            adminDao.save(admin);
            //返回相关信息
            return admin;
        }else{
            Mentor mentor = new Mentor();
            //设置账号
            mentor.setUsername(username);
            //设置盐
            mentor.setSalt(System.currentTimeMillis() +"");
            //设置密码
            mentor.setPassword(encode(fPassword,mentor.getSalt()));
            //设置姓名
            mentor.setName(name);
            //设置角色
            mentor.setRole(role);
            //保存用户
            mentorDao.save(mentor);
            //返回相关信息
            return mentor;
        }
    }

    @Override
    @Transactional
    public Boolean deleteUser(String username) throws ExceptionZyc {
        //根据账号查询数据库
        Mentor mentor = mentorDao.findByUsername(username);
        //判断用户是否存在
        if(mentor == null){
            //用户不存在，抛出错误
            throw ExceptionZyc.USER_IS_NOT_EXIST;
        }
        //用户存在，删除用户
        mentorDao.delete(mentor);
        //返回信息
        return true;
    }

    @Override
    public User modifyUser(int id,String username, String name, String password) throws ExceptionZyc {
        //根据账号查询数据库
        Mentor mentor = mentorDao.findById(id).orElse(null);
        //判断用户是否存在
        if(mentor == null){
            //用户不存在，抛出错误
            throw ExceptionZyc.USER_IS_NOT_EXIST;
        }
        //用户存在修改信息
        //验证用户名
        verifyUsername(username);
        //设置新用户名
        mentor.setUsername(username);
        //设置新姓名
        mentor.setName(name);
        //设置新密码
        mentor.setPassword(encode(password,mentor.getSalt()));
        //保存信息
        mentor = mentorDao.save(mentor);
        //返回信息
        return mentor;
    }

    @Override
    public User modifyPassword(String username, String oldPassword, String fPassword, String sPassword) throws ExceptionZyc {
        //根据账号查询数据库
        Mentor mentor = mentorDao.findByUsername(username);
        //判断用户是否存在
        if(mentor == null){
            //用户不存在，抛出错误
            throw ExceptionZyc.USER_IS_NOT_EXIST;
        }
        //用户存在，判断密码是否相同
        if(!mentor.getPassword().endsWith(encode(oldPassword,mentor.getSalt()))){
            //密码不同，抛出错误
            throw ExceptionZyc.PASSWORD_IS_WRONG;
        }
        //验证密码是否符合规范
        if(StringUtils.isEmpty(fPassword)||fPassword.length() < 6){
            //密码不符合规范，抛出异常
            throw ExceptionZyc.PASSWORD_IS_NOT_TRUE;
        }
        //验证两次密码是否相同
        if(!fPassword.equals(sPassword)){
            //两次密码不相同，抛出异常
            throw ExceptionZyc.PASSWORD_IS_NOT_EQUALS;
        }
        //设置新密码
        mentor.setPassword(encode(fPassword,mentor.getSalt()));
        //保存信息
        mentorDao.save(mentor);
        //返回信息
        return mentor;
    }

    /**
     * 验证密码是否符合规范
     * @param username 需要验证的密码
     * @throws ExceptionZyc 可能产生的错误
     */
    private void verifyUsername(String username) throws ExceptionZyc{
        //正则表达式格式，表示账号应有字母和数字组成，且6 < LENGTH < 11
        String regex = "^[0-9A-Za-z]{6,11}$";
        //验证账号是否符合规范
        if(!username.matches(regex)){
            //账号不符合规范，抛出异常
            throw ExceptionZyc.USERNAME_IS_NOT_TRUE;
        }
    }

    /**
     * 加密密码
     * @param password 密码
     * @param salt 盐
     * @return 加密后的密码
     */
    private String encode(String password,String salt){
        Base64.Encoder encoder = Base64.getEncoder();
        byte[] result = encoder.encode((password + salt).getBytes());
        return new String(result);
    }
}
