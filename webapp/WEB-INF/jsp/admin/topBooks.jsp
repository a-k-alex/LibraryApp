<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ include file="../../jspf/directive/taglib.jspf" %>
<html>
<%@ include file="../../jspf/head.jspf" %>
<body>
<%@include file="../../jspf/header.jspf" %>
<div class="container">
    <h2>Result:</h2>
    <table class="table table-striped">
        <thead>
        <tr>
            <th><a href="controller?command=sortBooks&criteria=bookName"> <fmt:message key="book.name"/></a></th>
            <th><a href="controller?command=sortBooks&criteria=author"> <fmt:message key="book.author"/></a></th>
            <th><a href="controller?command=sortBooks&criteria=publication"> <fmt:message key="book.publication"/></a>
            </th>
            <th><a href="controller?command=sortBooks&criteria=year"> <fmt:message key="book.year"/></a></th>
            <th><fmt:message key="book.quantity"/></th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${booksMap}" var="book">

        <tr>
            <td> ${book.key.bookName} </td>
            <td> ${book.key.author} </td>
            <td> ${book.key.publication} </td>
            <td> ${book.key.publicationYear} </td>
            <td> ${book.value}</td>
        <tr>
            </c:forEach>
        </tbody>
    </table>
</div>
<%@ include file="../../jspf/footer.jspf" %>
</body>
</html>