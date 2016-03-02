<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ include file="../../jspf/directive/taglib.jspf" %>
<html>
<%@include file="../../jspf/head.jspf" %>
<body>
<%@include file="../../jspf/header.jspf" %>
<div class="container">
    <h3><fmt:message key="welcome.hello"/>${user.firstName} ${user.lastName}</h3>

    <h3><fmt:message key="welcome.welcome"/></h3>
</div>
<%@ include file="../../jspf/footer.jspf" %>
</body>
</html>