package com.atguigu.dao;

import com.atguigu.pojo.OrderItem;

import java.util.List;

/**
 * @author Mia
 * @create 2022-02-22-15:37
 */
public interface OrderItemDao {
    //保存订单项
    void saveOrderItem(OrderItem orderItem);

    //根据订单编号查询订单详情
    List<OrderItem> queryOrderItemByOrderId(String orderId);
}
