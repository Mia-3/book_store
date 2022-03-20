package com.atguigu.test;

import com.atguigu.dao.daoImpl.BaseDao;
import com.atguigu.pojo.User;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

/**
 * @Author Mia
 * Date  2022/1/31 16:10
 * Description
 */
public class BaseDaoTest {
    private BaseDao dao = new BaseDao();
    @Test
    public void update() {
        String sql = "INSERT INTO t_user(username,password,email) VALUES(?,?,?)";
        dao.update(sql,"zhang","zhang123","zhang@qq.com");
    }

    @Test
    public void queryForOne() {
        String sql = "SELECT * FROM t_user WHERE username=?";
        User user = dao.queryForOne(User.class,sql,"admin");
        System.out.println(user);
    }

    @Test
    public void queryForList() {
        String sql = "SELECT * FROM t_user";
        List<User> list = dao.queryForList(User.class,sql);
        for(User user:list){
            System.out.println(user);
        }
    }

    @Test
    public void queryForSingleValue() {
        String sql = "SELECT COUNT(*) FROM t_user";
        System.out.println(dao.queryForSingleValue(sql));
        sql = "SELECT username FROM t_user WHERE id=?";
        System.out.println(dao.queryForSingleValue(sql,1));
    }
}