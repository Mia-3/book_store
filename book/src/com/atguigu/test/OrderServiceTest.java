package com.atguigu.test;

import com.atguigu.pojo.Cart;
import com.atguigu.pojo.CartItem;
import com.atguigu.pojo.Order;
import com.atguigu.pojo.OrderItem;
import com.atguigu.service.OrderService;
import com.atguigu.service.serviceImpl.OrderServiceImpl;
import org.junit.Test;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * @author Mia
 * @create 2022-02-22-16:28
 */
public class OrderServiceTest {
    OrderService service = new OrderServiceImpl();
    @Test
    public void createOrder() {
        Map<Integer, CartItem> items = new LinkedHashMap<>();
        items.put(1,new CartItem(1,"aaa",1,111.0,111.0));
        items.put(2,new CartItem(2,"bbb",1,111.0,111.0));
        items.put(3,new CartItem(3,"ccc",1,111.0,111.0));
        Cart cart = new Cart(items);
        service.createOrder(cart,1);
    }

    @Test
    public void showAllOrders() {
        List<Order> orders = service.showAllOrders();
        System.out.println(orders);
    }

    @Test
    public void sendOrder() {
        service.sendOrder("1213");
    }

    @Test
    public void showOrderDetail() {
        List<OrderItem> items = service.showOrderDetail("16455189215851");
        System.out.println(items);
    }

    @Test
    public void showMyOrders() {
        List<Order> orders = service.showMyOrders(1);
        System.out.println(orders);
    }

    @Test
    public void receiveOrder() {
        service.receiveOrder("1");
    }
}