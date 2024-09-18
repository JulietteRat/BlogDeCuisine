<%-- 
    Document   : showRecipe
    Created on : 20 mars 2024, 09:13:54
    Author     : Le J c'est le S
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="<c:url value="/assets/css/style.css"/>">
        <link rel="stylesheet" href="<c:url value="/assets/css/recipe.css"/>">
        <link rel="stylesheet" href="<c:url value="/assets/css/comment.css"/>">
        <title>showRecipe</title>
    </head>
    <body>
        <%@include file="/WEB-INF/jspf/header.jspf" %>
        <div class="showrecipe">
            <c:if test="${empty recipe}">
                <p>No recipe found</p>
            </c:if>
            <c:if test="${not empty recipe}">
                <h2>${recipe.title}</h2>
                <div class="recipe">
                    <div class="img" id="img-index">
                        <img src="<c:url value="/Images/${recipe.img_url}"/>" alt="alt"/>
                    </div>
                    <div class="text">
                        <div class="appends">
                            <div>
                                <h3> Ingredients </h3>
                                <p> <c:out value="${recipe.ingredients}"/><p>
                            </div>
                            <div>
                                <p> Difficulty : <c:out value="${recipe.difficulty}"/> </p>
                                <p> Score : <c:out value="${score.score}"/> </p>
                            </div>
                        </div>
                        <div>
                            <h3>Content : </h3>
                            <p><c:out value="${recipe.content}"/></p>
                        </div>
                    </div>

                </div>
                <h4>${requestScope.recipe.author.login} le ${recipe.created_r}</h4>
            </div>
        </c:if>
        <c:if test="${not empty recipe}"> 

            <c:if test="${requestScope.UserVoter==1 && recipe.votable==true}">
                <div class="vote">
                    <h2>What did you think?</h2>
                    <form action="<c:url value="/voteFullPage"/>" method="post">
                        <input type="hidden" name="vote" value="true">
                        <input type="hidden" name="id_recipe" value="${recipe.id_recipe}">
                        <input type='submit' value="Tasty">
                    </form>
                    <form action="<c:url value="/voteFullPage"/>" method="post">
                        <input type="hidden" name="vote" value="false">
                        <input type="hidden" name="id_recipe" value="${recipe.id_recipe}">
                        <input type='submit' value="Gross">
                    </form> 
                </div>
            </c:if> 
            <h2>All comments</h2>
            <c:forEach var="comment" items="${requestScope.comments}">
                <div class="comment">
                    <p>${comment.created_c}</p>
                    <p>${comment.id_person.getLogin()}</p>
                    <p>${comment.content}</p>
                    <form action="<c:url value = "/commentReport"/>" method="POST">
                        <input type="hidden" name="commentId" value="${comment.id_comment}">
                        <input type="hidden" name="recipeId" value="${recipe.id_recipe}">
                        <input type="submit" value="report">
                    </form>
                </div>  
            </c:forEach>

            <h2>Comment</h2>
            <div class="addComment">
                <form action="<c:url value='/commentPost'/>" method="POST">
                    <div>
                        <textarea id="content" name="content" rows="5" cols="100" placeholder="Enter your comment here..."></textarea>
                        <span class="error">${requestScope.errors.content}</span>
                    </div>
                    <div>
                        <input type="hidden" name="recipeId" value="${recipe.id_recipe}">
                        <input type="submit" value="Send">
                        <p>${requestScope.notAuthorized}</p>
                    </div>
                    <span>${requestScope.created}</span>
                </form>
            </div>
            <p>${output}</p>

        </c:if>
        <%@include file="/WEB-INF/jspf/footer.jspf" %>
    </body>
</html>
