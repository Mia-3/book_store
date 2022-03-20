package com.atguigu.service;

import com.atguigu.pojo.Cart;
import com.atguigu.pojo.Order;
import com.atguigu.pojo.OrderItem;

import java.util.List;

/**
 * @author Mia
 * @create 2022-02-22-15:55
 */
public interface OrderService {
    /**
     * 生成订单
     * @param cart       购物车
     * @param userId     用户Id
     * @return           返回生成的订单的订单号
     */
    String createOrder(Cart cart,Integer userId);

    /**
     * 查询全部订单
     * @return
     */
    List<Order> showAllOrders();

    /**
     * 发货
     * @param orderId    要发货的订单的Id
     */
    void sendOrder(String orderId);

    /**
     * 查看订单详情
     * @param orderId
     * @return
     */
    List<OrderItem> showOrderDetail(String orderId);

    /**
     * 查看我的订单
     * @param userId
     * @return
     */
    List<Order> showMyOrders(Integer userId);

    /**
     * 签收订单
     * @param orderId
     */
    void receiveOrder(String orderId);
}
