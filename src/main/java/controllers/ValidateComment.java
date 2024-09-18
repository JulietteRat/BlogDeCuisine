/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import dao.CommentDAO;
import dao.PersonDAO;
import entities.Comment;
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
@WebServlet("/admin/validateComment")
@SuppressWarnings("serial")
public class ValidateComment extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            int id = Integer.valueOf(req.getParameter("id"));
            System.out.println(id + "fvfvf");
            CommentDAO cdao = new CommentDAO();
            //Changement du status du commentaire en validé !
            Comment c = new CommentDAO().read(id);
            c.setStatus(2);
            cdao.updatestatusonly(c);

            resp.sendRedirect(req.getContextPath() + "/admin/reportedcomment");
        } catch (NumberFormatException ex) {
            resp.sendError(403);
        }
    }

}
