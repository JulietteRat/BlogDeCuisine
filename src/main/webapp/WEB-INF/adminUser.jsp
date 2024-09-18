<%-- 
    Document   : adminUser
    Created on : 20 mars 2024, 09:29:22
    Author     : Jonathan DAH
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="<c:url value="/assets/css/style.css"/>">
        <link rel="stylesheet" href="<c:url value="/assets/css/admin.css"/>">
        <title>Administer Recipes</title>
    </head>
    <body>
        <%@include file="/WEB-INF/jspf/header.jspf" %>
        <main>
            <h1>Users Administration</h1>
            <h2>List of current users</h2>
            <table>
                <thead>
                    <tr>
                        <th>Id</th>
                        <th>Login</th>
                        <th>Action</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="user" items="${requestScope.users}">
                        <c:if test="${user.id_person != 1}">
                            <tr>
                                <td>${user.id_person}</td>
                                <td><c:out value="${user.login}"/></td>
                                <c:if test="${user.status != 3}">
                                    <td><a href="<c:url value="/admin/deleteUser?id=${user.id_person}"/>">Delete</td>
                                </c:if>
                                <c:if test="${user.status != 2}">
                                    <td><a href="<c:url value="/admin/deactivateUser?id=${user.id_person}"/>">Deactivate</td>
                                </c:if>
                                <c:if test="${user.status == 2}">
                                    <td><a href="<c:url value="/admin/reactivateUser?id=${user.id_person}"/>">Reactivate</td>
                                </c:if>
                            </tr>
                        </c:if>
                    </c:forEach>
                </tbody>
            </table>
        </main>

        <%@include file="/WEB-INF/jspf/footer.jspf" %>
    </body>
</html>
