package com.atguigu.test;

import com.atguigu.dao.OrderDao;
import com.atguigu.dao.daoImpl.OrderDaoImpl;
import com.atguigu.pojo.Order;
import org.junit.Test;

import java.sql.Date;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @author Mia
 * @create 2022-02-22-15:31
 */
public class OrderDaoTest {
    OrderDao dao = new OrderDaoImpl();
    @Test
    public void saveOrder() {
        Order order = new Order("1213",new Date(System.currentTimeMillis()),123.1,0,1);
        dao.saveOrder(order);
    }

    @Test
    public void queryOrders() {
        List<Order> orders = dao.queryOrders();
        System.out.println(orders);
    }

    @Test
    public void changeOrderStatus() {
        dao.changeOrderStatus("1",1);
    }

    @Test
    public void queryOrderBuUserId() {
        List<Order> orders = dao.queryOrderBuUserId(1);
        System.out.println(orders);
    }
}