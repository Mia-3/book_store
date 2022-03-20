package com.atguigu.dao.daoImpl;

import com.atguigu.utils.JdbcUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * @Author Mia
 * Date  2022/1/31 11:03
 * Description
 */
public class BaseDao {
    /**
     * 通用增删改操作
     * @param sql       sql语句
     * @param args      填充占位符的参数
     * @return          返回-1操作失败，否则返回影响行数
     */
    public int update(String sql,Object...args){
        //获取连接
        Connection conn = JdbcUtils.getConnection();
        //得到QueryRunner实例
        QueryRunner qr = new QueryRunner();
        try {
            //执行更新操作
            return qr.update(conn,sql,args);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    /**
     * 查询结果为一条记录
     * @param type
     * @param sql
     * @param args
     * @param <T>
     * @return      返回查到的那一条记录
     */
    public <T> T queryForOne(Class<T> type,String sql,Object...args){
        //获取连接
        Connection conn = JdbcUtils.getConnection();
        BeanHandler<T> handler = new BeanHandler<>(type);
        QueryRunner qr = new QueryRunner();
        try {
            return qr.query(conn,sql,handler,args);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    /**
     * 查询结果为多条记录的通用操作
     * @param type     查询的每条结果所属的类
     * @param sql      sql语句
     * @param args     填充展位符的参数
     * @param <T>
     * @return         返回查询结果
     */
    public <T> List<T> queryForList(Class<T> type,String sql, Object...args){
        //获取连接
        Connection conn = JdbcUtils.getConnection();
        //获取QueryRunner对象
        QueryRunner qr = new QueryRunner();
        BeanListHandler<T> handler = new BeanListHandler<T>(type);
        //查询
        try {
            return qr.query(conn, sql,handler,args);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public Object queryForSingleValue(String sql,Object...args){
        Connection conn = JdbcUtils.getConnection();
        QueryRunner qr = new QueryRunner();
        ScalarHandler handler = new ScalarHandler();
        try {
            return qr.query(conn,sql,handler,args);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
