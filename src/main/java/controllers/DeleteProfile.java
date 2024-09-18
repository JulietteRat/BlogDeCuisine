/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import dao.PersonDAO;
import entities.Person;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author stag
 */
@WebServlet("/deleteProfile")

public class DeleteProfile extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        NEEDS TO BE TESTED
        PersonDAO pdao = new PersonDAO();
//        take user from the session
        Person p = (Person) req.getSession().getAttribute("user");
//        find user in db
        Person fromDB = pdao.read(p.getId_person());
//        change status
        fromDB.setStatus(2);
        pdao.update(fromDB);
//        log out from session
        req.getSession().invalidate();
        resp.sendRedirect("/projetJEE/");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getSession().invalidate();
        resp.sendRedirect("/projetJEE/");
    }
}
