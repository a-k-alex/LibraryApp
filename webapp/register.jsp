<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ include file="WEB-INF/jspf/directive/taglib.jspf" %>
<html>
<%@ include file="WEB-INF/jspf/head.jspf" %>
<body>
<%@include file="WEB-INF/jspf/header.jspf" %>
<div class="container-fluid bottom-right">
    <div class="container col-md-4 col-md-offset-4 well">
        <form action="controller" method="post">
            <h3 class="form-signin-heading"><fmt:message key="regiser.message"/></h3>
            <input type="hidden" name="command" value="register">
            <input type="text" name="firstName" class="form-control" a placeholder="<fmt:message key="user.firstName"/>"
                   pattern="^.{1,250}$" title="Wrong format Length 1-250" required>
            <input type="text" name="lastName" class="form-control" placeholder="<fmt:message key="user.lastname"/>"
                   pattern="^.{1,250}$" title="Wrong format Length 1-250" required>
            <input type="email" name="email" class="form-control" placeholder="<fmt:message key="user.email"/>"
                   pattern="^[_a-z0-9-]+(\.[_a-z0-9-]+)*@[a-z0-9-]+(\.[a-z0-9-]+)*(\.[a-z]{2,4})$"
                   title="Wrong format. Example (aaa@xxx.yy)" required>
            <input type="password" name="password" class="form-control input-sm"
                   pattern="^.{1,50}$" title="Wrong format Length 1-50"
                   placeholder="<fmt:message key="user.password"/>" required>
            <input type="password" name="confirmPassword" class="form-control input-sm"
                   pattern="^.{1,50}$" title="Wrong format Length 1-50"
                   placeholder="<fmt:message key="user.confirmPassword"/>" required>
            <button class="btn btn-lg btn-primary btn-block" type="submit" name="button" value="register">
                <fmt:message key="login.button.register"/>
            </button>
        </form>
        <p class="text-danger">${errorMessage}</p>
    </div>
</div>
<%@include file="WEB-INF/jspf/footer.jspf" %>
</body>
</html>
