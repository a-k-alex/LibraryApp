<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ include file="../jspf/directive/taglib.jspf" %>
<html>
<%@ include file="../jspf/head.jspf" %>
<body>
<%@include file="../jspf/header.jspf" %>
<div class="container">
    <h2>Result: ${bookNameAuthor}</h2>
    <mtg:listBooks books="${bookList}"/>
    <%--<%@include file="librarian/listBooks.jsp" %>--%>
</div>
<%@ include file="../jspf/footer.jspf" %>
</body>
</html>