package com.atguigu.web;

import com.atguigu.pojo.Cart;
import com.atguigu.pojo.Order;
import com.atguigu.pojo.OrderItem;
import com.atguigu.pojo.User;
import com.atguigu.service.OrderService;
import com.atguigu.service.serviceImpl.OrderServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author Mia
 * @create 2022-02-22-16:42
 */
public class OrderServlet extends BaseServlet{
    OrderService service = new OrderServiceImpl();
    //创建订单
    protected void createOrder(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取购物车
        Cart cart = (Cart)req.getSession().getAttribute("cart");
        //获取用户
        User user = (User)req.getSession().getAttribute("user");
        if(user == null) {
            //跳转至登录页面
            req.getRequestDispatcher("/pages/user/login.jsp").forward(req, resp);
            return;
        }
        if(cart != null) {
            String orderId = service.createOrder(cart,user.getId());
            //跳转至/pages/cart/checkout.jsp页面
            req.getRequestDispatcher("/pages/cart/checkout.jsp").forward(req,resp);
        }
    }

    //查询所有订单
    protected void showAllOrders(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        //调用service查询所有订单
        List<Order> orders = service.showAllOrders();
        //将订单存到request域中
        req.setAttribute("orders",orders);
        //跳转至订单管理界面
        req.getRequestDispatcher("/pages/manager/order_manager.jsp").forward(req,resp);
    }

    //查看订单详情
    protected void showOrderDetail(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        //获取请求参数
        String orderId = req.getParameter("orderId");
        //调用service查询订单详情
        List<OrderItem> orderItems = service.showOrderDetail(orderId);
        //将详情存入request域中
        req.setAttribute("orderItems",orderItems);
        //跳转至详情展示页面
        req.getRequestDispatcher("/pages/order/order_detail.jsp").forward(req,resp);
    }

    //发货
    protected void sendOrder(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        //获取请求参数
        String orderId = req.getParameter("orderId");
        //调用service发货
        service.sendOrder(orderId);
        //跳转至刚刚点击的页面
        resp.sendRedirect(req.getHeader("Referer"));
    }

    //查看我的订单
    protected void showMyOrders(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        //获取用户
        User user = (User)req.getSession().getAttribute("user");
        if(user == null) {
            //跳转至登录页面
            req.getRequestDispatcher("/pages/user/login.jsp").forward(req, resp);
            return;
        }
        //调用service查看我的订单
        List<Order> myOrders = service.showMyOrders(user.getId());
        //将我的订单存入request域中
        req.setAttribute("myOrders",myOrders);
        //跳转至我的订单显示页面
        req.getRequestDispatcher("/pages/order/my_order.jsp").forward(req,resp);
    }

    //签收订单
    protected void receiveOrder(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        //获取请求参数
        String orderId = req.getParameter("orderId");
        //调用service签收订单
        service.receiveOrder(orderId);
        //跳转至刚刚点击的页面
        resp.sendRedirect(req.getHeader("Referer"));
    }
}
