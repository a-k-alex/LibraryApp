<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ include file="../jspf/directive/taglib.jspf" %>
<html>
<%@ include file="../jspf/head.jspf" %>
<body>
<%@include file="../jspf/header.jspf" %>
<div class="container">
    <h2><fmt:message key="category.category"/><fmt:message key="category.${category.categoryName}"/></h2>
    <c:if test="${user.role=='ADMINISTRATOR'}">
        <form action="controller">
            <input type="hidden" name="command" value="editBook"/>
            <input type="submit" value="<fmt:message key="button.addBook"/>">
        </form>
    </c:if>
    <mtg:listBooks books="${bookList}"/>
</div>
<%@ include file="../jspf/footer.jspf" %>
</body>
</html>