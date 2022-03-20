package com.atguigu.service.serviceImpl;

import com.atguigu.dao.BookDao;
import com.atguigu.dao.OrderDao;
import com.atguigu.dao.OrderItemDao;
import com.atguigu.dao.daoImpl.BookDaoImpl;
import com.atguigu.dao.daoImpl.OrderDaoImpl;
import com.atguigu.dao.daoImpl.OrderItemDaoImpl;
import com.atguigu.pojo.*;
import com.atguigu.service.OrderService;

import java.sql.Date;
import java.util.List;

/**
 * @author Mia
 * @create 2022-02-22-16:02
 */
public class OrderServiceImpl implements OrderService {
    OrderDao orderDao = new OrderDaoImpl();
    OrderItemDao orderItemDao = new OrderItemDaoImpl();
    BookDao bookDao = new BookDaoImpl();

    @Override
    public String createOrder(Cart cart, Integer userId) {
        //生成订单编号:时间戳+用户Id
        String orderId = String.valueOf(System.currentTimeMillis()) + String.valueOf(userId);
        //创建订单实例
        Order order = new Order(orderId, new Date(System.currentTimeMillis()), cart.getTotalPrice(), 0, userId);
        //调用orderDao将订单存到数据库中
        orderDao.saveOrder(order);
        //将购物车项转换为订单项并存到数据库中
        for (CartItem item : cart.getItems().values()) {
            OrderItem orderItem = new OrderItem(null, item.getName(), item.getCount(), item.getPrice(), item.getTotalPrice(), orderId);
            orderItemDao.saveOrderItem(orderItem);
            //更新库存和销量
            Book book = bookDao.queryBookById(item.getId());
            book.setSales(book.getSales() + item.getCount());
            book.setStock(book.getStock() - item.getCount());
            bookDao.updateBook(book);
        }
        //清空购物车
        cart.clear();
        return orderId;
    }

    @Override
    public List<Order> showAllOrders() {
        return orderDao.queryOrders();
    }

    @Override
    public void sendOrder(String orderId) {
        orderDao.changeOrderStatus(orderId, 1);
    }

    @Override
    public List<OrderItem> showOrderDetail(String orderId) {
        return orderItemDao.queryOrderItemByOrderId(orderId);
    }

    @Override
    public List<Order> showMyOrders(Integer userId) {
        return orderDao.queryOrderBuUserId(userId);
    }

    @Override
    public void receiveOrder(String orderId) {
        orderDao.changeOrderStatus(orderId, 2);
    }
}
