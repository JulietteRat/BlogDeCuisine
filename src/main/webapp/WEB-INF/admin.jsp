<%-- 
    Document   : admin
    Created on : 19 mars 2024, 13:22:12
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
        <title>Admin</title>
    </head>
    <body>
        <%@include file="/WEB-INF/jspf/header.jspf" %>
        <main>
            <h1>Choose the section you want to manage</h1>
            <div class="choix">
                <div class="bloc">
                    <a href="<c:url value="/admin/user"/>">Users</a>
                </div>
                    <div class="bloc">
                        <a href="<c:url value="/admin/recipe"/>">Recipe</a>
                </div>
                    <div class="bloc">
                        <a href="<c:url value="/admin/reportedcomment"/>">Reported Comments</a>
                </div>
            </div>
        </main>

        <%@include file="/WEB-INF/jspf/footer.jspf" %>
    </body>
</html>
