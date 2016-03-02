<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ include file="../jspf/directive/taglib.jspf" %>
<html>
<%@ include file="../jspf/head.jspf" %>
<body>
<%@include file="../jspf/header.jspf" %>
<div class="container-fluid bg-3">
    <h2>Categories:</h2><br>
</div>
<div class="list-group">
    <c:forEach items="${categories}" var="category">
        <h4 class="list-group-item">
            <a href="controller?command=showCategory&id=${category.id}">
                <fmt:message key="category.${category.categoryName}"/>
            </a></h4>
    </c:forEach>
</div>
<%@ include file="../jspf/footer.jspf" %>
</body>
</html>