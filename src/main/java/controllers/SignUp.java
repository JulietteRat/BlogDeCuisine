/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import entities.Person;
import forms.SignUpFormChecker;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Jonathan DAH
 */
@WebServlet("/signup")
public class SignUp extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/signup.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Instancier le vérificateur de formulaire d'inscription
        SignUpFormChecker fc = new SignUpFormChecker(req);
        // Utiliser le form checker et récupérer le bean
        Person obj = fc.checkForm();
        // Si pas OK => réafficher le formulaire
        if (!fc.getErrors().isEmpty()) {
            req.getRequestDispatcher("/WEB-INF/signup.jsp").forward(req, resp);
        } else { // Sinon on passe à la suite. Ici rentrer en session et afficher la page d'accueild
            HttpSession session = req.getSession();
            session.setAttribute("user", obj);
            resp.sendRedirect(req.getContextPath() + "/");
        }
    }

}
