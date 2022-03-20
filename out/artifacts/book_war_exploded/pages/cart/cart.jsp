<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>购物车</title>

    <%-- 静态包含 base标签、css样式、jQuery文件 --%>
    <%@ include file="/pages/common/head.jsp" %>

    <script type="text/javascript">
        $(function () {
            $(".changeCount").change(function () {
                var bookId = $(this).attr("bookId");
                var name = $(this).parent().parent().find("td:first").text();
                var count = $(this).val();
                if (confirm("你确定要将【" + name + "】的数量修改为【" + count + "】吗？"))
                    location.href = "cartServlet?action=updateCount&id=" + bookId+"&count="+count;
                else
                	this.value = this.defaultValue;
            });

            $("a.deleteItem").click(function () {
                return confirm("你确定要删除【" + $(this).parent().parent().find("td:first").text()+"】吗？");
            });

            $("#clearCart").click(function () {
                return confirm("你确定要清空购物车吗？");
            });

        });
    </script>

</head>
<body>

<div id="header">
    <img class="logo_img" alt="" src="static/img/logo.gif">
    <span class="wel_word">购物车</span>

    <%--静态包含，登录 成功之后的菜单 --%>
    <%@ include file="/pages/common/login_success_menu.jsp" %>


</div>

<div id="main">

    <table>
        <tr>
            <td>商品名称</td>
            <td>数量</td>
            <td>单价</td>
            <td>金额</td>
            <td>操作</td>
        </tr>
        <c:if test="${not empty sessionScope.cart.items}">
            <c:forEach items="${sessionScope.cart.items.values()}" var="item">
                <tr>
                    <td>${item.name}</td>
                    <td><input class="changeCount" bookId="${item.id}" type="text" value="${item.count}" style="width: 40px"></td>
                    <td>${item.price}</td>
                    <td>${item.totalPrice}</td>
                    <td><a href="cartServlet?action=deleteItem&id=${item.id}" class="deleteItem">删除</a></td>
                </tr>
            </c:forEach>
        </c:if>
        <c:if test="${empty sessionScope.cart.items}">
            <tr>
                <td colspan="5"><a href="clientBookServlet?action=page"
                                   style="align-content: center;font-size: 20px;color: red">亲！购物车还没有商品呦！快去和小伙伴到商城看看吧!</a>
                </td>
            </tr>
        </c:if>
    </table>
    <c:if test="${not empty sessionScope.cart.items}">
        <div class="cart_info">
            <span class="cart_span">购物车中共有<span class="b_count">${sessionScope.cart.totalCount}</span>件商品</span>
            <span class="cart_span">总金额<span class="b_price">${sessionScope.cart.totalPrice}</span>元</span>
            <span class="cart_span"><a id="clearCart" href="cartServlet?action=clear">清空购物车</a></span>
            <span class="cart_span"><a href="orderServlet?action=createOrder">去结账</a></span>
        </div>
    </c:if>


</div>


<%--静态包含页脚内容--%>
<%@include file="/pages/common/footer.jsp" %>


</body>
</html>