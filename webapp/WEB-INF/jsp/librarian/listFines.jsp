<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ include file="../../jspf/directive/taglib.jspf" %>
<html>
<%@ include file="../../jspf/head.jspf" %>
<body>
<%@include file="../../jspf/header.jspf" %>
<div class="container">
    <h2><fmt:message key="fine.fineList"/></h2>
    <table class="table table-striped">
        <thead>
        <tr>
            <th><fmt:message key="fine.id"/></th>
            <th><fmt:message key="user.id"/></th>
            <th><fmt:message key="order.id"/></th>
            <th><fmt:message key="fine.status"/></th>
            <th><fmt:message key="fine.Amount"/></th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${fines}" var="fine">
            <tr>
                <td> ${fine.id} </td>
                <td> ${fine.userId} </td>
                <td> ${fine.orderId} </td>
                <td><fmt:message key="${fine.status}"/></td>
                <td><c:if test="${fine.status=='OPEN'}">
                    <mtg:dpd fineId="${fine.id}"/>
                </c:if>
                </td>
                <td>
                    <form action="controller" method="post">
                        <td>
                            <input type="hidden" name="id" value="${fine.id}">
                            <input type="hidden" name="userId" value="${fine.userId}">
                            <input type="hidden" name="orderId" value="${fine.orderId}">
                            <input type="hidden" name="status" value="${fine.status}">
                            <input type="hidden" name="command" value="paidFine">
                            <c:if test="${user.id!=fine.userId}">
                                <input type="submit" value="<fmt:message key="button.perform"/> ">
                            </c:if>
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