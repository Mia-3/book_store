package com.atguigu.dao.daoImpl;

import com.atguigu.dao.OrderDao;
import com.atguigu.pojo.Order;

import java.util.List;

/**
 * @author Mia
 * @create 2022-02-22-15:15
 */
public class OrderDaoImpl extends BaseDao implements OrderDao {
    @Override
    public void saveOrder(Order order) {
        String sql = "INSERT INTO t_order VALUES(?,?,?,?,?)";
        update(sql,order.getOrderId(),order.getCreateTime(),order.getPrice(),order.getStatus(),order.getUserId());
    }

    @Override
    public List<Order> queryOrders() {
        String sql = "SELECT order_id orderId,create_time createTime,price,status,user_id userId FROM t_order";
        return queryForList(Order.class,sql);
    }

    @Override
    public void changeOrderStatus(String orderId, Integer status) {
        String sql = "UPDATE t_order SET status=? WHERE order_id=?";
        update(sql,status,orderId);
    }

    @Override
    public List<Order> queryOrderBuUserId(Integer userId) {
        String sql = "SELECT order_id orderId,create_time createTime,price,status,user_id userId FROM t_order WHERE user_id=? ";
        return queryForList(Order.class,sql,userId);
    }
}
