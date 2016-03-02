<%@ tag language="java" pageEncoding="UTF-8" %>
<%@ attribute name="books" type="java.util.List" %>
<%@include file="../jspf/directive/taglib.jspf" %>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="text"/>

<table class="table table-striped">
    <thead>
    <tr>
        <th><a href="controller?command=sortBooks&criteria=bookName"> <fmt:message key="book.name"/></a></th>
        <th><a href="controller?command=sortBooks&criteria=author"> <fmt:message key="book.author"/></a></th>
        <th><a href="controller?command=sortBooks&criteria=publication"> <fmt:message key="book.publication"/></a></th>
        <th><a href="controller?command=sortBooks&criteria=year"> <fmt:message key="book.year"/></a></th>
        <th><fmt:message key="book.inStock"/></th>
        <th><fmt:message key="book.amount"/></th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${books}" var="book">
        <tr>
            <td> ${book.bookName} </td>
            <td> ${book.author} </td>
            <td> ${book.publication} </td>
            <td> ${book.publicationYear} </td>
            <td> ${book.inStock} </td>
            <td> ${book.amount} </td>
            <td><c:if test="${not empty user}">
                <form action="controller" method="post">
                    <input type="hidden" name="command" value="orderBook">
                    <input type="hidden" name="id" value="${book.id}">
                    <input type="submit" name="" value="<fmt:message key="button.order"/>">
                </form>
            </c:if>
            </td>
            <td>
                <c:if test="${user.role=='ADMINISTRATOR'}">
                    <form action="controller" method="post">
                        <input type="hidden" name="command" value="editBook">
                        <input type="hidden" name="bookId" value="${book.id}">
                        <input type="submit" name="" value="<fmt:message key="button.edit"/>">
                    </form>
                </c:if>
            </td>

        </tr>
    </c:forEach>
    </tbody>
</table>