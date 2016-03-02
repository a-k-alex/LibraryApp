<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ include file="../../jspf/directive/taglib.jspf" %>
<html>
<%@ include file="../../jspf/head.jspf" %>
<body>
<%@include file="../../jspf/header.jspf" %>
<div class="container">
    <h2><fmt:message key="login"/>${user.email}</h2>

    <form action="controller" method="post">
        <input type="hidden" name="command" value="profile">
        <label for="inputFirstName" class="sr-only"><fmt:message key="user.firstName"/></label>
        <input type="text" id="inputFirstName" name="firstName" class="form-control" value="${user.firstName}" required>
        <label for="inputLastName" class="sr-only"><fmt:message key="user.lastname"/></label>
        <input type="text" id="inputLastName" name="lastName" class="form-control" value="${user.lastName}" required>
        <input type="submit" name="changeInfo" value="<fmt:message key="button.update"/>">
    </form>
</div>
<%@ include file="../../jspf/footer.jspf" %>
</body>
</html>