/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import dao.CommentDAO;
import dao.PersonDAO;
import dao.RecipeDAO;
import entities.Person;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Jonathan DAH
 */
@WebServlet("/admin/deleteUser")
@SuppressWarnings("serial")
public class DeleteUser extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            int id = Integer.valueOf(req.getParameter("id"));
            PersonDAO pdao = new PersonDAO();
            CommentDAO cdao = new CommentDAO();
            RecipeDAO rdao = new RecipeDAO();

            //Suppression de toute les recettes de la personne(id)
            rdao.deleteallrecipe(id);
            //Remplacement de tous les commentaires de la personne(id)  par la phrase "" et changement du satus
            cdao.updateContent(id);
            //Changement de status de la personne(id)
            Person p = new PersonDAO().read(id);
            p.setStatus(3);
            pdao.update(p);


            resp.sendRedirect(req.getContextPath() + "/admin/user");
        } catch (NumberFormatException ex) {
            resp.sendError(403);
        }
    }

}
