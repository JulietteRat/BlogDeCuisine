/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import dao.RecipeDAO;
import entities.Person;
import forms.UpdatePasswordChecker;
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
@WebServlet("/user/profile")
public class Profile extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Person p = (Person) req.getSession().getAttribute("user");
        if (p != null && p.getStatus() == 1) {
            System.out.println(new RecipeDAO().listSortId(p.getId_person()));
            req.setAttribute("recipes", new RecipeDAO().listSortId(p.getId_person()));
            req.getRequestDispatcher("/WEB-INF/profile.jsp").forward(req, resp);
        } else {
            resp.sendRedirect("/projetJEE/");
        }
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UpdatePasswordChecker fc = new UpdatePasswordChecker(req);
        Person p = fc.checkForm();
        if (fc.getErrors().isEmpty()) {
            req.setAttribute("pwdChanged", "Your password has been successfully changed");
        }

        Person loggedInUser = (Person) req.getSession().getAttribute("user");
        if (loggedInUser != null && loggedInUser.getStatus() == 1) {
            RecipeDAO recipeDAO = new RecipeDAO();
            req.setAttribute("recipes", recipeDAO.listSortId(loggedInUser.getId_person()));
        }
        req.getRequestDispatcher("/WEB-INF/profile.jsp").forward(req, resp);
    }

}
