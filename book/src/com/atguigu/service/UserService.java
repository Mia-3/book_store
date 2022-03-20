package com.atguigu.service;

import com.atguigu.pojo.User;

/**
 * @Author Mia
 * Date  2022/1/31 16:45
 * Description
 */
public interface UserService {
    /**
     * 用户登录
     * @param user
     * @return
     */
    public User login(User user);

    /**
     * 注册用户
     * @param user
     */
    public void register(User user);

    /**
     * 检查用户名是否存在
     * @param username
     * @return   true:存在   false:不存在
     */
    public boolean existsUsername(String username);
}
