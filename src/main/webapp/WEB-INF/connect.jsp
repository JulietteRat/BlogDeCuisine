<%-- 
    Document   : connect
    Created on : 19 mars 2024, 13:03:54
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
        <title>Connexion</title>
    </head>
    <body>
        <%@include file="/WEB-INF/jspf/header.jspf" %>
        <main>
            <h1>Enter your login and password</h1>
            <div class="error">${requestScope.errorMsg}</div>
            <form action="<c:url value="/connect" />" method="post">
                <fieldset>
                    <legend>Connexion</legend>
                    <div>
                        <label for="login">Login</label>
                        <input id="login" name="login" value="<c:out value="${requestScope.bean.login}"/>">
                        <div class="error">${requestScope.errors.login}</div>
                    </div>
                    <div>
                        <label for="pwd">Password</label>
                        <input id="pwd" name="pwd" type="password">
                        <div class="error">${requestScope.errors.pwd}</div>
                    </div>
                </fieldset>
                <div class="buttons">
                    <input type="submit" value="Envoyer">
                    <input type="reset" value="Annuler">
                </div>
            </form>
        </main>
                        <%@include file="/WEB-INF/jspf/footer.jspf" %>
    </body>
</html>

