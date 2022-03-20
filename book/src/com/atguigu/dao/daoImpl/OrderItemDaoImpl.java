package com.atguigu.dao.daoImpl;

import com.atguigu.dao.OrderItemDao;
import com.atguigu.pojo.OrderItem;

import java.util.List;

/**
 * @author Mia
 * @create 2022-02-22-15:42
 */
public class OrderItemDaoImpl extends BaseDao implements OrderItemDao {
    @Override
    public void saveOrderItem(OrderItem orderItem) {
        String sql = "INSERT INTO t_order_item(name,count,price,total_price,order_id) VALUES(?,?,?,?,?)";
        update(sql,orderItem.getName(),orderItem.getCount(),orderItem.getPrice(),orderItem.getTotalPrice(),orderItem.getOrderId());
    }

    @Override
    public List<OrderItem> queryOrderItemByOrderId(String orderId) {
        String sql = "SELECT id ,name,count,price,total_price totalPrice,order_id orderId FROM t_order_item WHERE order_id=?";
        return queryForList(OrderItem.class,sql,orderId);
    }
}
