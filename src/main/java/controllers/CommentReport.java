/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import dao.CommentDAO;
import dao.RecipeDAO;
import entities.Comment;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author anastasiia
 */
@WebServlet("/commentReport")

public class CommentReport extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int recipeId = Integer.parseInt(req.getParameter("recipeId"));

        int id = Integer.parseInt(req.getParameter("commentId"));
        Comment cdao = new CommentDAO().read(id);

        if (cdao.getStatus() == 2) {
            req.setAttribute("output", "This comment was already validated");
            req.setAttribute("comments", new CommentDAO().listSortComment(recipeId));
            req.setAttribute("recipe", new RecipeDAO().read(recipeId));
            req.getRequestDispatcher("/WEB-INF/showRecipe.jsp").forward(req, resp);
        }        else if (cdao.getStatus() == 3) {
            req.setAttribute("output", "This comment was deleted");
            req.setAttribute("comments", new CommentDAO().listSortComment(recipeId));
            req.setAttribute("recipe", new RecipeDAO().read(recipeId));
            req.getRequestDispatcher("/WEB-INF/showRecipe.jsp").forward(req, resp);

        } else {
            req.setAttribute("output", "This comment has been successfully reported");
            cdao.setStatus(1);
            new CommentDAO().update(cdao);
            req.setAttribute("comments", new CommentDAO().listSortComment(recipeId));
            req.setAttribute("recipe", new RecipeDAO().read(recipeId));
            req.getRequestDispatcher("/WEB-INF/showRecipe.jsp").forward(req, resp);
        }
//        req.setAttribute("comments", new CommentDAO().list());
//        req.setAttribute("recipe", new RecipeDAO().read(recipeId));
//        resp.sendRedirect(req.getContextPath() + "/showRecipe?id=" + recipeId);
//        req.getRequestDispatcher("/WEB-INF/showRecipe.jsp").forward(req, resp);
    }

}
