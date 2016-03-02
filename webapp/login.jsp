<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@include file="WEB-INF/jspf/directive/taglib.jspf" %>
<html>
<%@ include file="WEB-INF/jspf/head.jspf" %>
<body>
<%@include file="WEB-INF/jspf/header.jspf" %>
<div class="container-fluid bottom-right">
    <div class="container col-md-4 col-md-offset-4 well">
        <form action="controller" method="post" accept-charset="UTF-8">
            <h2 class="form-signin-heading"><fmt:message key="login.title"/></h2>
            <input type="hidden" name="command" value="login">
            <input type="email" name="email" class="form-control" placeholder="<fmt:message key="user.email"/>"
                   required>
            <input type="password" name="password" class="form-control"
                   placeholder=""<fmt:message key="user.password"/>"" required>
            <button class="btn btn-lg btn-primary btn-block" type="submit" name="button" value="login"><fmt:message
                    key="login.button.submit"/></button>
        </form>
        <br/>
        <a href="register.jsp">
            <button class="btn btn-lg btn-link btn-block" type="submit" name="button" value="register"><fmt:message
                    key="login.button.register"></fmt:message></button>
        </a>

        <p class="text-danger">${errorMessage}</p>
    </div>
</div>
<%@ include file="WEB-INF/jspf/footer.jspf" %>
</body>
</html>