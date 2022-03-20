package com.atguigu.pojo;

import java.sql.Date;

/**
 * @author Mia
 * @create 2022-02-22-11:31
 */
public class Order {
    private String orderId;
    private Date createTime;
    private Double price;
    private Integer status;
    private Integer userId;

    public Order() {
    }

    public Order(String orderId, Date createTime, Double price, Integer status, Integer userId) {
        this.orderId = orderId;
        this.createTime = createTime;
        this.price = price;
        this.status = status;
        this.userId = userId;
    }

    public String getOrderId() {
        return orderId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public Double getPrice() {
        return price;
    }

    public Integer getStatus() {
        return status;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId='" + orderId + '\'' +
                ", createTime=" + createTime +
                ", price=" + price +
                ", status=" + status +
                ", userId=" + userId +
                '}';
    }
}
