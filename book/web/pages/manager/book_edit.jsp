<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>编辑图书</title>

    <%-- 静态包含 base标签、css样式、jQuery文件 --%>
    <%@ include file="/pages/common/head.jsp" %>


    <style type="text/css">
        h1 {
            text-align: center;
            margin-top: 200px;
        }

        h1 a {
            color: red;
        }

        input {
            text-align: center;
        }
    </style>
</head>
<body>
<div id="header">
    <img class="logo_img" alt="" src="../../static/img/logo.gif">
    <span class="wel_word">编辑图书</span>

    <%-- 静态包含 manager管理模块的菜单  --%>
    <%@include file="/pages/common/manager_menu.jsp" %>


</div>

<div id="main">

        <table>
            <tr>
                <td>名称</td>
                <td>价格</td>
                <td>作者</td>
                <td>销量</td>
                <td>库存</td>
                <td colspan="2">操作</td>
            </tr>
            <%-- 如果请求参数的id属性不为空，那说明是编辑--%>
            <%-- 如果请求参数的id属性为空，那说明是增加--%>
<%--            <c:if test="${param.action == null}">--%>
            <form action="bookServlet">
                <input type="hidden" name="action" value=${empty param.id? "add":"update"}>
                <input type="hidden" name="id" value="${requestScope.book.id}">
                <input type="hidden" name="pageNo" value="${param.pageNo}">
                <tr>
                        <td><input name="name" type="text" value="${requestScope.book.name}"/></td>
                        <td><input name="price" type="text" value="${requestScope.book.price}"/></td>
                        <td><input name="author" type="text" value="${requestScope.book.author}"/></td>
                        <td><input name="sales" type="text" value="${requestScope.book.sales}"/></td>
                        <td><input name="stock" type="text" value="${requestScope.book.stock}"/></td>
                        <td><input type="submit" value="提交"/></td>
                </tr>
            </form>
        </table>

</div>


<%--静态包含页脚内容--%>
<%@include file="/pages/common/footer.jsp" %>


</body>
</html>