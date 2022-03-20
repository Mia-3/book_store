package com.atguigu.dao;

import com.atguigu.pojo.Order;

import java.util.List;

/**
 * @author Mia
 * @create 2022-02-22-15:11
 */
public interface OrderDao {
    //保存订单
    void saveOrder(Order order);

    //查询全部订单
    List<Order> queryOrders();

    //修改订单状态
    void changeOrderStatus(String orderId,Integer status);

    //根据用户编号查询订单信息
    List<Order> queryOrderBuUserId(Integer userId);
}
