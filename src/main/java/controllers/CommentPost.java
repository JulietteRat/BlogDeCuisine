/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import dao.CommentDAO;
import dao.RecipeDAO;
import entities.Comment;
import entities.Person;
import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author anastasiia
 */
@WebServlet("/commentPost")

public class CommentPost extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int recipeId = Integer.parseInt(req.getParameter("recipeId"));

//        if user is not logged in - he cant write comments
        if (req.getSession().getAttribute("user") != null && req.getParameter("content").length() != 0) {
            Comment newComment = new Comment();
            newComment.setContent(req.getParameter("content"));
            newComment.setCreated_c(Timestamp.valueOf(LocalDateTime.now()));
            newComment.setId_person((Person) req.getSession().getAttribute("user"));
            newComment.setId_recipe(new RecipeDAO().read(recipeId));

//        0 - comment without report; 1 - reported; 2 - validated; 3 - deleted;
            newComment.setStatus(0);
            req.setAttribute("created", "Comment added");
            new CommentDAO().create(newComment);
            resp.sendRedirect(req.getContextPath() + "/showRecipe?id=" + recipeId);
        } else {
            req.setAttribute("notAuthorized", "Verify if you are logged in or the field is not empty");
            req.setAttribute("comments", new CommentDAO().listSortComment(recipeId));
            req.setAttribute("recipe", new RecipeDAO().read(recipeId));
            req.getRequestDispatcher("/WEB-INF/showRecipe.jsp").forward(req, resp);
        }
    }

}
