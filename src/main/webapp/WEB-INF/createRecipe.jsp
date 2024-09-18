<%-- 
    Document   : createRecipe
    Created on : 19 mars 2024, 14:20:02
    Author     : Jonathan DAH
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="<c:url value="/assets/css/style.css"/>">
        <link rel="stylesheet" href="<c:url value="/assets/css/form.css"/>">
        <title>Create Recipe</title>
    </head>
    <body>
        <%@include file="/WEB-INF/jspf/header.jspf" %>
        <main>

            <h1>Add new recipe</h1>
            <!--added enctype-->
            <form action="<c:url value="/user/createrecipe" />" method="post"  enctype="multipart/form-data">
                <label for="title">Recipe title :</label><br>
                <input type="text" id="title" name="title"><br><br>
                <div class="error">${requestScope.errors.title}</div>

                <label for="difficulty">Difficulty level :</label><br>
                <select id="difficulty" name="difficulty">
                    <option value="1">1 (Easy)</option>
                    <option value="2">2</option>
                    <option value="3">3</option>
                    <option value="4">4</option>
                    <option value="5">5 (Hard)</option>
                </select><br><br>
                <div class="error">${requestScope.errors.difficulty}</div>

                <label for="ingredients">Ingredients :</label><br>
                <textarea id="ingredients" name="ingredients" rows="4" cols="50"></textarea><br><br>
                <div class="error">${requestScope.errors.ingredients}</div>

                <label for="content">Recipe content :</label><br>
                <textarea id="content" name="content" rows="8" cols="50"></textarea><br><br>
                <div class="error">${requestScope.errors.content}</div>

                <!--put upload of image here-->
                <input type="file" id="file"
                       accept="image/png" name="file">


                <input type="submit" value="Soumettre">
                <div class="ok">${requestScope.created}</div>
            </form>

        </main>
        <%@include file="/WEB-INF/jspf/footer.jspf" %>
    </body>


</html>

