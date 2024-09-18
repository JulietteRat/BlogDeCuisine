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
@WebServlet("/admin/reactivateUser")
@SuppressWarnings("serial")
public class ReactivateUser extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            int id = Integer.valueOf(req.getParameter("id"));
            PersonDAO pdao = new PersonDAO();

            //Changement du status de la personne a desactiver
            Person p = new PersonDAO().read(id);
            p.setStatus(1);
            pdao.update(p);

            resp.sendRedirect(req.getContextPath() + "/admin/user");
        } catch (NumberFormatException ex) {
            resp.sendError(403);
        }
    }

}
