/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import dao.CommentDAO;
import dao.RecipeDAO;
import dao.VoteDAO;
import entities.Person;
import entities.Recipe;
import entities.RecipeScoreVotable;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Le J c'est le S
 */
@WebServlet("/showRecipe")
public class ShowRecipe extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // Ajout des élements de vote soumis à la condition d'etre connecté et actif
        Person p = (Person) req.getSession().getAttribute("user");
        if (p != null && p.getStatus() == 1) {
            //Ajout des éléments de vote
            req.setAttribute("UserVoter", "1");
        } else {
            req.setAttribute("UserVoter", "0");
        }

        try {
            int id = Integer.valueOf(req.getParameter("id"));
            RecipeDAO rdao = new RecipeDAO();
            Recipe recipe = rdao.read(id);
            if (recipe == null) {
                throw new IllegalArgumentException();
            } else {
                // Convertir recipe en RecipeScoreVotable
                RecipeScoreVotable rsv = new RecipeScoreVotable();
                VoteDAO vdao = new VoteDAO();
                rsv.addScore(recipe, vdao.generateScore(recipe.getId_recipe()));
                rsv.setVotable(rdao.checkDate(recipe.getCreated_r()));
                //Gestion du vote en fonction de l'utilisateur
                if(p!=null){
                if (rsv.getVotable() && !vdao.exists(p.getId_person(), rsv.getId_recipe())) {
                    rsv.setVotable(true);
                } else {
                    rsv.setVotable(false);
                }
                }

                req.setAttribute("comments", new CommentDAO().listSortComment(id));
                req.setAttribute("recipe", rsv);
                req.getRequestDispatcher("/WEB-INF/showRecipe.jsp").forward(req, resp);
            }
        } catch (NumberFormatException ex) {
            resp.sendError(400, "Invalid recipe ID");
        } catch (IllegalArgumentException ex) {
            resp.sendError(404);
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    }
}
