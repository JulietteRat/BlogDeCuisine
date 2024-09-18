<%-- 
    Document   : reportedComment
    Created on : 20 mars 2024, 09:51:52
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
        <title>JSP Page</title>
    </head>
    <body>
        <%@include file="/WEB-INF/jspf/header.jspf" %>
        <main>
            <h1>Reported Comments Administration</h1>
            <h2>List of reported comments</h2>
            <table>
                <thead>
                    <tr>
                        <th>Content</th>
                        <th>Posted on </th>
                        <th>Date</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="reportedcomment" items="${requestScope.reportedcomment}">

                        <tr>
                            <td>${reportedcomment.content}</td>
                            <td><c:out value="${reportedcomment.id_recipe.title}"/></td>
                            <td>${reportedcomment.created_c}</td>
                            <td><a href="<c:url value="/admin/deleteComment?id=${reportedcomment.id_comment}"/>">Delete</td>
                            <td><a href="<c:url value="/admin/validateComment?id=${reportedcomment.id_comment}"/>">Validate</td>
                        </tr>

        </c:forEach>
                </tbody>
            </table>
        </main>
        <%@include file="/WEB-INF/jspf/footer.jspf" %>
    </body>
</html>
