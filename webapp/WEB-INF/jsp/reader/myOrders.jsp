<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ include file="../../jspf/directive/taglib.jspf" %>
<html>
<%@ include file="../../jspf/head.jspf" %>
<body>
<%@include file="../../jspf/header.jspf" %>
<div class="container">
    <h2><fmt:message key="orders.myOrders"/></h2>
    <table class="table table-striped">
        <thead>
        <tr>
            <th><fmt:message key="order.id"/></th>
            <th><fmt:message key="order.status"/></th>
            <th><fmt:message key="book.name"/></th>
            <th><fmt:message key="book.author"/></th>
            <th><fmt:message key="order.returnDate"/></th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${userBean.orderMap}" var="entry">
            <tr>
                <td> ${entry.key.id} </td>
                <td><fmt:message key="${entry.key.status}"/></td>
                <td> ${entry.value.bookName} </td>
                <td> ${entry.value.author} </td>
                <td> ${entry.key.returnDate} </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
<%@ include file="../../jspf/footer.jspf" %>
</body>
</html>