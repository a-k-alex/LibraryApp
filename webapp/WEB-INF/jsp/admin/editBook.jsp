<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ include file="../../jspf/directive/taglib.jspf" %>
<html>
<%@ include file="../../jspf/head.jspf" %>
<body>
<%@include file="../../jspf/header.jspf" %>
<div class="container-fluid bottom-right">
    <div class="container col-md-4 col-md-offset-4 well">
        <form action="controller" method="post">
            <h3 class="form-signin-heading">Please, fill the gaps:</h3>
            <input type="hidden" name="command" value="addBook">
            <input type="hidden" name="id" value="${book.id}">
            <input type="text" name="bookName" class="form-control" placeholder="<fmt:message key="book.name"/>"
                   value="${book.bookName}" pattern="^.{1,250}$" title="Wrong format Length 1-250" required>
            <input type="text" name="author" class="form-control" placeholder="<fmt:message key="book.author"/>"
                   value="${book.author}" pattern="^.{1,250}$" title="Wrong format.Length 1-250">
            <input type="text" name="publication" class="form-control"
                   placeholder="<fmt:message key="book.publication"/>" value="${book.publication}"
                   pattern="^.{1,50}$" title="Wrong format. Length 1-250">
            <input type="text" name="publicationYear" class="form-control input-sm"
                   placeholder="<fmt:message key="book.year"/>" value="${book.publicationYear}" pattern="[0-9]{4}"
                   title="Wrong format. Value must be beetween 1111 and 9999" required>
            <input type="text" id="amount" name="amount" class="form-control input-sm"
                   placeholder="<fmt:message key="book.amount"/>" value="${book.amount}" pattern="[0-9]{1,2}"
                   title="Wrong format. Value must be beetween 1 and 99" required>
            <input type="text" id="inStock" name="inStock" class="form-control input-sm"
                   placeholder="<fmt:message key="book.inStock"/>" value="${book.inStock}" pattern="[0-9]{1,2}"
                   title="Wrong format. Value must be beetween 1 and 99" required>
            <button class="btn btn-lg btn-primary btn-block" type="submit" name="button" value="register"><fmt:message
                    key="button.perform"/></button>
        </form>
        <p class="text-danger">${errorMessage}</p>
    </div>
</div>
<%@include file="../../jspf/footer.jspf" %>
</body>
</html>