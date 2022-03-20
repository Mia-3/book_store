package com.atguigu.utils;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

/**
 * @Author Mia
 * Date  2022/1/30 21:43
 * Description  数据库工具类：获取连接和关闭连接
 */
public class JdbcUtils {
    private static DataSource dataSource;
    private static ThreadLocal<Connection> conns = new ThreadLocal<>();
    //获取连接池
    static{
        Properties pro = new Properties();
        InputStream is = JdbcUtils.class.getClassLoader().getResourceAsStream("jdbc.properties");
        try {
            pro.load(is);
            dataSource = DruidDataSourceFactory.createDataSource(pro);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * 从连接池中获取一个连接
     * @return
     */
    public static Connection getConnection(){
        Connection conn = null;
        try {
            conn = dataSource.getConnection();
            conns.set(conn);
            conn.setAutoCommit(false);     //设置手动管理事务
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }

    public static void commitAndClose(){
        Connection conn = conns.get();
        try {
            conn.commit();
        } catch(SQLException e) {
            e.printStackTrace();
        } finally{
                try {
                    if (conn != null)
                        conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        conns.remove();
    }

    public static void rollbackAndClose(){
        Connection conn = conns.get();
        if(conn != null){
            try {
                conn.rollback();
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                //关闭资源
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        conns.remove();
    }
}
