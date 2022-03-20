package com.atguigu.test;

import com.atguigu.dao.OrderItemDao;
import com.atguigu.dao.daoImpl.OrderItemDaoImpl;
import com.atguigu.pojo.OrderItem;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

/**
 * @author Mia
 * @create 2022-02-22-15:50
 */
public class OrderItemDaoTest {
    OrderItemDao dao = new OrderItemDaoImpl();
    @Test
    public void saveOrderItem() {
        OrderItem orderItem = new OrderItem(null,"11",1,111.1,111.1,"1");
        dao.saveOrderItem(orderItem);
    }

    @Test
    public void queryOrderItemByOrderId() {
        List<OrderItem> items = dao.queryOrderItemByOrderId("1111");
        System.out.println(items);
    }
}