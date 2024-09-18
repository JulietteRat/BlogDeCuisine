/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import dao.CommentDAO;
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
@WebServlet("/admin/reportedcomment")
@SuppressWarnings("serial")
public class AdminReportedComment extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        CommentDAO cdao = new CommentDAO();

        System.out.println(cdao.reportedcommentlist());

        req.setAttribute("reportedcomment", cdao.reportedcommentlist());

        req.getRequestDispatcher("/WEB-INF/adminReportedComment.jsp").forward(req, resp);
    }

}
