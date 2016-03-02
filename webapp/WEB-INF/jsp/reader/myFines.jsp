<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ include file="../../jspf/directive/taglib.jspf" %>
<html>
<%@ include file="../../jspf/head.jspf" %>
<body>
<%@include file="../../jspf/header.jspf" %>
<div class="container">
    <h2><fmt:message key="fines.myFines"/></h2>
    <table class="table table-striped">
        <thead>
        <tr>
            <th><fmt:message key="fine.id"/></th>
            <th><fmt:message key="order.id"/></th>
            <th><fmt:message key="fine.status"/></th>
            <th><fmt:message key="fine.Amount"/></th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${fines}" var="fine">
            <tr>
                <td> ${fine.id} </td>
                <td> ${fine.orderId} </td>
                <td><fmt:message key="${fine.status}"/></td>
                <td><mtg:dpd fineId="${fine.id}"/></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
<%@ include file="../../jspf/footer.jspf" %>
</body>
</html>