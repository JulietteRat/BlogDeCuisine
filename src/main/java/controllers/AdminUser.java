/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import dao.PersonDAO;
import dao.RecipeDAO;
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
@WebServlet("/admin/user")
@SuppressWarnings("serial")
public class AdminUser extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PersonDAO pdao = new PersonDAO();

        req.setAttribute("users", pdao.list());
        req.getRequestDispatcher("/WEB-INF/adminUser.jsp").forward(req, resp);
    }
}
