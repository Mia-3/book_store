package com.atguigu.test;

import com.atguigu.pojo.Cart;
import com.atguigu.pojo.CartItem;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

/**
 * @author Mia
 * @create 2022-02-20-16:56
 */
public class CartTest {

    @Test
    public void addItem() {
        Cart cart = new Cart();
        cart.addItem(new CartItem(1, "java从入门到精通", 1,1000.0,1000.0));
        cart.addItem(new CartItem(2, "java", 1, 1000.0,1000.0));
        cart.addItem(new CartItem(3, "数据结构与算法", 1, 100.0,100.0));
        System.out.println(cart);
    }

    @Test
    public void deleteItem() {
        Cart cart = new Cart();
        cart.addItem(new CartItem(1, "java从入门到精通", 1,1000.0,1000.0));
        cart.addItem(new CartItem(2, "java", 1, 1000.0,1000.0));
        cart.addItem(new CartItem(3, "数据结构与算法", 1, 100.0,100.0));
        cart.deleteItem(10);
        System.out.println(cart);
    }

    @Test
    public void clear() {
        Cart cart = new Cart();
        cart.addItem(new CartItem(1, "java从入门到精通", 1,1000.0,1000.0));
        cart.addItem(new CartItem(2, "java", 1, 1000.0,1000.0));
        cart.addItem(new CartItem(3, "数据结构与算法", 1, 100.0,100.0));
        cart.clear();
        System.out.println(cart);
    }

    @Test
    public void updateCount() {
        Cart cart = new Cart();
        cart.addItem(new CartItem(1, "java从入门到精通", 1,1000.0,1000.0));
        cart.addItem(new CartItem(2, "java", 1, 1000.0,1000.0));
        cart.addItem(new CartItem(3, "数据结构与算法", 1, 100.0,100.0));
        cart.updateCount(11,10);
        System.out.println(cart);
    }

    @Test
    public void getTotalCount() {
        Cart cart = new Cart();
        cart.addItem(new CartItem(1, "java从入门到精通", 1,1000.0,1000.0));
        cart.addItem(new CartItem(2, "java", 1, 1000.0,1000.0));
        cart.addItem(new CartItem(3, "数据结构与算法", 1, 100.0,100.0));
        System.out.println(cart.getTotalCount());
    }

    @Test
    public void getTotalPrice() {
        Cart cart = new Cart();
        cart.addItem(new CartItem(1, "java从入门到精通", 1,1000.0,1000.0));
        cart.addItem(new CartItem(2, "java", 1, 1000.0,1000.0));
        cart.addItem(new CartItem(3, "数据结构与算法", 1, 100.0,100.0));
        System.out.println(cart.getTotalPrice());
    }
}