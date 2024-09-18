<%-- 
    Document   : signup
    Created on : 19 mars 2024, 11:08:03
    Author     : Jonathan DAH
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="<c:url value="/assets/css/style.css"/>">
        <link rel="stylesheet" href="<c:url value="/assets/css/form.css"/>">
        <title>Sign-up</title>
    </head>
    <body>
        <%@include file="/WEB-INF/jspf/header.jspf" %>
        <main>
            <div class="titre">
                <h1>Please enter your registration details :</h1>
            </div>
            <div class="error">${requestScope.errorMsg}</div>
            <form action="<c:url value="/signup" />" method="post">
                <fieldset>
                    <legend>Sign up</legend>
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
                    <div>
                        <label for="verif">Verification</label>
                        <input id="verif" name="verif" type="password">
                        <div class="error">${requestScope.errors.verif}</div>
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
