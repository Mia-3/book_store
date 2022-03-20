package com.atguigu.test;

import com.atguigu.pojo.User;
import com.atguigu.service.UserService;
import com.atguigu.service.serviceImpl.UserServiceImpl;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @Author Mia
 * Date  2022/1/31 16:50
 * Description
 */
public class UserServiceTest {
    UserService service = new UserServiceImpl();
    @Test
    public void login() {
        User user = new User(null,"admin","admin123","admin@qq.com",1);
        System.out.println(service.login(user));
    }

    @Test
    public void register() {
        User user = new User(null,"li","li123","li@qq.com",1);
        service.register(user);
    }

    @Test
    public void existsUsername() {
        if(service.existsUsername("zhan"))
            System.out.println("用户名已存在！");
        else
            System.out.println("用户名可用！");
    }
}