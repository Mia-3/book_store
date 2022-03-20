package com.atguigu.test;

import com.atguigu.dao.UserDao;
import com.atguigu.dao.daoImpl.UserDaoImpl;
import com.atguigu.pojo.User;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @Author Mia
 * Date  2022/1/31 16:42
 * Description
 */
public class UserDaoTest {
    UserDao dao = new UserDaoImpl();
    @Test
    public void queryByUsername() {
        System.out.println(dao.queryByUsername("zhang"));
    }

    @Test
    public void queryByUsernameAndPassword() {
        System.out.println(dao.queryByUsernameAndPassword("admin","admin123"));
    }

    @Test
    public void saveUser() {
        User user = new User(null,"wang","wang123","wang@qq.com",1);
        dao.saveUser(user);
    }
}