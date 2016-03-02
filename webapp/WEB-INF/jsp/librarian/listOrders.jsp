<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>
<html>
<%@ include file="/WEB-INF/jspf/head.jspf" %>
<body>
<%@include file="../../jspf/header.jspf" %>
<div class="container">
    <h2>Order list</h2>
    <table class="table table-striped">
        <thead>
        <tr>
            <th><fmt:message key="order.id"/></th>
            <th><fmt:message key="book.name"/></th>
            <th><fmt:message key="book.author"/></th>
            <th><fmt:message key="user.email"/></th>
            <th><fmt:message key="order.createAt"/></th>
            <th><fmt:message key="order.status"/></th>
            <th><fmt:message key="order.returnDate"/></th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${orderBeans}" var="orderBean">
            <tr>
                <td> ${orderBean.order.id} </td>
                <td> ${orderBean.book.bookName} </td>
                <td> ${orderBean.book.author} </td>
                <td> ${orderBean.user.email} </td>
                <td> ${orderBean.order.createAt} </td>
                <form action="controller" method="post">
                    <td>
                        <input type="hidden" name="id" value="${orderBean.order.id}">
                        <input type="hidden" name="userId" value="${orderBean.user.id}">
                        <input type="hidden" name="bookId" value="${orderBean.book.id}">
                        <input type="hidden" name="createAt" value="${orderBean.order.createAt}">
                        <input type="hidden" name="status" value="${orderBean.order.status}">
                        <select name="newStatus">
                            <c:forEach items="${orderStatuses}" var="status">
                                <option value="${status}" ${orderBean.order.status==status ? 'selected':''}>
                                    <fmt:message key="${status}"/>
                                </option>
                            </c:forEach>
                        </select>
                    </td>
                    <td>
                        <c:choose>
                            <c:when test="${orderBean.order.status=='REQUESTED'}">
                                <input type="date" name="returnDate">
                            </c:when>
                            <c:otherwise>
                                ${orderBean.order.returnDate}
                                <input type="hidden" name="returnDate" value="${orderBean.order.returnDate}">
                            </c:otherwise>
                        </c:choose>
                    </td>
                    <td>
                        <input type="hidden" name="command" value="changeOrderStatus">
                        <input type="submit" value="<fmt:message key="button.perform"/>">
                    </td>
                </form>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
<%@ include file="../../jspf/footer.jspf" %>
</body>
</html>