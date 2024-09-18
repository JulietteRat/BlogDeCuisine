<%-- 
    Document   : adminRecipe
    Created on : 20 mars 2024, 09:27:32
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
            <h1>Recipes Administration</h1>
            <h2>List of our recipes</h2>
            <table>
                <thead>
                    <tr>
                        <th>Id</th>
                        <th>Creation Date</th>
                        <th>Title</th>
                        <th>Author (id)</th>
                        <th>Action</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="recipe" items="${requestScope.recipes}">
                        <tr>
                            <td>${recipe.id_recipe}</td>
                            <td>${recipe.created_r}</td>
                            <td><a href="<c:url value="/showRecipe?id=${recipe.id_recipe}"/>">${recipe.title}</a></td>
                            <td><c:out value="${recipe.author.login} (${recipe.author.id_person})"/></td>
                            <td><a href="<c:url value="/admin/deleteRecipe?id=${recipe.id_recipe}"/>">Delete</td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </main>
        <%@include file="/WEB-INF/jspf/footer.jspf" %>
    </body>
</html>
