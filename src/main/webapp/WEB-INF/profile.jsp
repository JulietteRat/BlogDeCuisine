<%-- 
    Document   : profil.jsp
    Created on : 19 mars 2024, 09:47:14
    Author     : anastasiia
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="<c:url value="/assets/css/style.css"/>">
        <link rel="stylesheet" href="<c:url value="/assets/css/form.css"/>">
        <link rel="stylesheet" href="<c:url value="/assets/css/recipe.css"/>">
        <title>JSP Page</title>
    </head>
    <body>
        <%@include file="/WEB-INF/jspf/header.jspf" %>
        <main>
            <!--LIST OF USER'S RECIPIES-->
            <h1>List of your recipes</h1>

            <c:if test="${empty requestScope.recipes}">
                <h1>SADLY YOU DON'T HAVE ANY RECIPES :( </h1>
                <h1><img src="<c:url value="/Images/assiette.png"/>"></h1>
                <h1>CLICK <a href="<c:url value = "/user/createrecipe"/>">HERE </a> TO ADD ONE !  </h1>

            </c:if>

                <c:forEach var="recipe" items="${requestScope.recipes}">

                <h2>${recipe.title}</h2>
                <div class="recipe"> 
                    <div class="img" id="img-index">
                        <img src="<c:url value="/Images/${recipe.img_url}"/>" alt="img error"/>
                    </div>
                    <div class="text">
                        <div class="appends" >
                            <div>
                                <h3>Ingredients</h3>
                                <p>${recipe.ingredients}</p>
                            </div>
                                <div>
                                    <p>Difficulty: ${recipe.difficulty} out of 5</p>
                                    <p>score :"${score.score}"</p>
                                </div> 
                        </div>
                        <div>
                            <h3>Content :</h3>
                            <p>${recipe.content}</p>
                        </div>
                    </div> 
                </div>
                <h4>Created:${recipe.created_r}</h4>
            </c:forEach>
            <!--CHANGE PASSWORD FORM-->

            <form action="<c:url value = '/user/profile'/>" method="POST">
                <h2>Change your password</h2>
                <div>
                <label for="pwd">Old password</label>
                <input id="pwd" type="password" name="pwd">
                <span class="error">${requestScope.errors.pwd}</span>
            </div>
            <div>
                <label for="newPwd">New password</label>
                <input id="newPwd" type="password" name="newPwd">
                <span class="error">${requestScope.errors.newPwd}</span>
            </div>
            <div>
                <label for="verifyPwd">Confirm password</label>
                <input id="verifyPwd" type="password" name="verifyPwd">
                <span class="error">${requestScope.errors.verifyPwd}</span>
            </div>

            <div>
                <input type="submit" value="Envoyer" class="btn input-btn">
                <input type="reset" value="Annuler" class="btn input-btn">
            </div>
        </form>
                <div>${requestScope.pwdChanged}</div>
                <h4>You can delete your account <a href="<c:url value = "/deleteProfile"/>">here </a></h4>
        </main>
                <%@include file="/WEB-INF/jspf/footer.jspf" %>
    </body>
</html>
