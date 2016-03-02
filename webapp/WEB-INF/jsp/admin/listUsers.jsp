<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>
<html>
<%@ include file="/WEB-INF/jspf/head.jspf" %>
<body>
<%@include file="../../jspf/header.jspf" %>
<div class="container">
    <h2><fmt:message key="user.list"/></h2>
    <table class="table table-striped">
        <thead>
        <tr>
            <th><fmt:message key="user.firstName"/></th>
            <th><fmt:message key="user.lastname"/></th>
            <th><fmt:message key="user.email"/></th>
            <th><fmt:message key="user.role"/></th>
            <th><fmt:message key="user.bannedAt"/></th>
            <th><fmt:message key="user.action"/></th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${users}" var="user">
            <%--hide user from session--%>
            <c:if test="${user.id != sessionScope.user.id}">
                <tr>
                    <td> ${user.firstName} </td>
                    <td> ${user.lastName} </td>
                    <td> ${user.email} </td>
                    <form action="controller">
                        <td>
                            <select name="role">
                                <c:forEach items="${roles}" var="role">
                                    <%--<c:choose>--%>
                                    <%--<c:when test="${user.role==role}">--%>
                                    <%--<option selected>${role}</option>--%>
                                    <%--</c:when>--%>
                                    <%--<c:otherwise>--%>
                                    <%--<option>${role}</option>--%>
                                    <%--</c:otherwise>--%>
                                    <%--</c:choose>--%>
                                    <option value="${role}" ${user.role==role ? 'selected':''}>
                                        <fmt:message key="${role}"/>
                                    </option>
                                </c:forEach>
                            </select>
                        </td>
                        <td> ${user.bannedAt}</td>
                        <td><select name="isBanned">
                                <%--<c:choose>--%>
                                <%--<c:when test="${user.bannedAt==null}">--%>
                                <%--<option selected>Unban</option>--%>
                                <%--<option>Ban</option>--%>
                                <%--</c:when>--%>
                                <%--<c:otherwise>--%>
                                <%--<option selected>Ban</option>--%>
                                <%--<option>Unban</option>--%>
                                <%--</c:otherwise>--%>
                                <%--</c:choose>--%>
                            <option value="Ban" ${user.bannedAt==null ? 'selected':''}>
                                <fmt:message key="BAN"/>
                            </option>
                            <option value="Unban" ${user.bannedAt==null ? 'selected':''}>
                                <fmt:message key="UNBAN"/>
                            </option>


                        </select>
                        </td>
                        <td>
                            <input type="submit" value="<fmt:message key="button.update"/>">
                            <input type="hidden" name="command" value="updateUser">
                            <input type="hidden" name="id" value="${user.id}">
                        </td>
                    </form>
                </tr>
            </c:if>
        </c:forEach>
        </tbody>
    </table>
</div>
<%@ include file="../../jspf/footer.jspf" %>
</body>
</html>