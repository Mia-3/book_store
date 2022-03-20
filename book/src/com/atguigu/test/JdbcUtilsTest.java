package com.atguigu.test;

import com.atguigu.utils.JdbcUtils;
import org.junit.Test;

import java.sql.Connection;

import static org.junit.Assert.*;

/**
 * @Author Mia
 * Date  2022/1/30 22:07
 * Description
 */
public class JdbcUtilsTest {

    @Test
    public void getConnection() {
        Connection conn = JdbcUtils.getConnection();
        System.out.println(conn);
        JdbcUtils.commitAndClose();
    }
}