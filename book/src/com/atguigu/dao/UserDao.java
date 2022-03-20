package com.atguigu.dao;

import com.atguigu.pojo.User;

/**
 * @Author Mia
 * Date  2022/1/31 11:04
 * Description
 */
public interface UserDao {
    /**
     * 根据用户名查询用户
     * @param username
     * @return
     */
    public User queryByUsername(String username);

    /**
     * 根据用户名和密码查询用户
     * @param usernam
     * @param password
     * @return
     */
    public User queryByUsernameAndPassword(String usernam,String password);

    /**
     * 存储用户
     * @param user
     * @return
     */
    public int saveUser(User user);
}
