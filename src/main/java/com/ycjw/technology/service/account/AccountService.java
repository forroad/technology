package com.ycjw.technology.service.account;

import com.ycjw.technology.exception.ExceptionZyc;
import com.ycjw.technology.model.user.User;

public interface AccountService {
    /**
     *登录
     *@param  username 账号
     *@param  password 密码
     * @param role 用户角色
     *@return 登录成功用户相关信息
     */
    User login(String username, String password,User.Role role) throws ExceptionZyc;
    /**
     *注册
     *@param username 账号
     * @param fPassword 密码
     * @param sPassword 验证密码
     * @return 注册成功返回用户相关信息
     */
    User register(String username, String fPassword, String sPassword, String name, User.Role role) throws ExceptionZyc;

    /**
     *  删除导师
     * @param username 导师用户名
     * @return 修改状态
     * @throws ExceptionZyc 可能产生的异常
     */
    Boolean deleteUser(String username) throws ExceptionZyc;

    /**
     *  管理员修改导师信息
     * @param id 导师id
     * @param name 姓名
     * @param username 用户名
     * @param password 密码
     * @return 修改后的导师信息
     */
    User modifyUser(int id,String username,String name,String password) throws ExceptionZyc;

    /**
     *  用户修改密码
     * @param username 用户名
     * @param oldPassword 旧密码
     * @param fPassword  第一次密码
     * @param sPassword 第二次密码
     * @return 修改后的用户信息
     * @throws ExceptionZyc 可能产生的错误
     */
    User modifyPassword(String username,String oldPassword,String fPassword,String sPassword) throws ExceptionZyc;
}
