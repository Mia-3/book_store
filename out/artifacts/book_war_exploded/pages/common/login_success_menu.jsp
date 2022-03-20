<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2020/2/5
  Time: 10:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div>
    <c:if test="${not empty sessionScope.user}">
    <span>欢迎<span class="um_span">${sessionScope.user.username}</span>光临尚硅谷书城</span>
    <a href="http://localhost:8080/book/orderServlet?action=showMyOrders">我的订单</a>
    <a href="http://localhost:8080/book/pages/cart/cart.jsp">购物车</a>
    <a href="http://localhost:8080/book/userServlet?action=logout">注销</a>&nbsp;&nbsp;
    <a href="../../index.jsp">返回</a>
    </c:if>
    <c:if test="${empty sessionScope.user}">
        <a href="http://localhost:8080/book/pages/user/login.jsp">登录</a> &nbsp;&nbsp;&nbsp;
        <a href="http://localhost:8080/book/pages/user/regist.jsp">注册</a>
    </c:if>
</div>