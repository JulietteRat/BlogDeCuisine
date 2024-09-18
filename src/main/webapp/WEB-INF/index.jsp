<%-- 
    Document   : index
    Created on : 16 mars 2024, 09:24:01
    Author     : Herbert Caffarel
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<link rel="stylesheet" href="<c:url value="/assets/css/recipe.css"/>">
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="<c:url value="/assets/css/style.css"/>">
        <link rel="stylesheet" href="<c:url value="/assets/css/index.css"/>">
        <link rel="stylesheet" href="<c:url value="/assets/css/recipe.css"/>">
        <title>Accueil</title>
    </head>
    <body>
        <%@include file="/WEB-INF/jspf/header.jspf" %>
        <main>
            <h1>Welcome to our super cooking Website!</h1>
            <div class="top">
                <h2>Top recipes of the month</h2>
                <div class="top3">
                    <c:forEach var="recipe" items="${requestScope.theBest}">
                        <div class="winrecipe">
                            <h2><c:out value="${recipe.title}"/></h2>
                            <figure class="img-best">
                                <img src="<c:url value="/Images/${recipe.img_url}"/>" alt="pictureOfRecipe" class="img-best"/>
                            </figure>
                            <div class="bloctext">
                                <div class="gauche">
                                    <div class="ingredients">
                                        <h4>Ingredients</h4>
                                    <c:out value="${recipe.ingredients}"/>
                                    </div>
                                    <!--<h4>Contents</h4>-->
                                    <%--<c:out value="${recipe.content}"/>--%>
                                </div>
                                    <div class="droite">
                                        <p>Score: ${recipe.score}%</p>
                                        <p>Difficulté : ${recipe.difficulty}/5
                                        </p>
                                        &Eacute;crit par <c:out value="${recipe.author.login}"/> le ${recipe.created_r} 
                                        <div class="more"><a href="<c:url value="showRecipe?id=${recipe.id_recipe}" />">En savoir plus</a></div>

                                        <c:if test="${requestScope.UserVoter==1 && recipe.votable==true}">
                                            <div class="margin-voter">
                                                <h5>Voter</h5>
                                            <form action="<c:url value="/tovote"/>" method="post">
                                                <input type="hidden" name="vote" value="true">
                                                <input type="hidden" name="id_recipe" value="${recipe.id_recipe}">
                                                <input type='submit' value="C'est bon">
                                            </form>
                                            <form action="<c:url value="/tovote"/>" method="post">
                                                <input type="hidden" name="vote" value="false">
                                                <input type="hidden" name="id_recipe" value="${recipe.id_recipe}">
                                                <input type='submit' value="C'est dégeulasse">
                                            </form>
                                            </div>
                                        </c:if> 
                                    </div>
                            </div>
                        </div>

                    </c:forEach>
            </div>
            </div>
            <div class="last10">
                <div class="idtitre">
                    <h2>Latest recipes</h2>
                </div>
                <c:forEach var="recipe" items="${requestScope.latest}">
                    <h2><c:out value="${recipe.title}"/></h2>
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
                                    <p>score :"${recipe.score}%"</p>
                                    <c:if test="${requestScope.UserVoter==1 && recipe.votable==true}">
                                        <h5>Voter</h5>
                                        <form action="<c:url value="/tovote"/>" method="post">
                                            <input type="hidden" name="vote" value="true">
                                            <input type="hidden" name="id_recipe" value="${recipe.id_recipe}">
                                            <input type='submit' value="C'est bon">
                                        </form>
                                        <form action="<c:url value="/tovote"/>" method="post">
                                            <input type="hidden" name="vote" value="false">
                                            <input type="hidden" name="id_recipe" value="${recipe.id_recipe}">
                                            <input type='submit' value="C'est dégeulasse">
                                        </form>
                                    </c:if>
                                </div> 
                                <div>
                                    <h3>Content :</h3>
                                    <p>${recipe.content}</p>
                                </div>
                                <div class="more"><a href="<c:url value="showRecipe?id=${recipe.id_recipe}" />">En savoir plus</a></div>

                            </div>
                        </div>
                    </div>
                                    <h4>Created:${recipe.created_r}</h4>
                </c:forEach>
            </div>
        </main>
        <%@include file="/WEB-INF/jspf/footer.jspf" %>
    </body>
</html>
