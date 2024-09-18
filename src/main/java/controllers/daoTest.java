/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import dao.CommentDAO;
import dao.RecipeDAO;
import entities.Comment;
import entities.Person;
import entities.Recipe;
import java.io.IOException;
import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author stag
 */
@WebServlet("/test")

public class daoTest extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        CommentDAO commDAO = new CommentDAO();
        RecipeDAO r = new RecipeDAO();
        Person p = new Person();
//        Recipe r = new Recipe();

//        p.setId_person(2);
//        p.setLogin("loginTest");
//        p.setPwd("sdfsdf");
//        p.setStatus(1);

//        r.setContent("sdfsdfsdfdddsdf");
//        r.setId_recipe(1);
//        r.setCreated_r(new Timestamp(Date.valueOf(LocalDate.now()).getTime()));
//        r.setDifficulty(2);
//        CREATE
//        Comment comm = new Comment();
//        comm.setContent("testContent");
//        comm.setCreated_c(new Timestamp(Date.valueOf(LocalDate.now()).getTime()));
//        comm.setId_person(p);
//        comm.setId_recipe(r);
//        comm.setStatus(1);
//
//        commDAO.create(comm);
//        UPDATE
//        Comment k = commDAO.read(1);
//        Recipe rr = r.read("Le steak d’autruche");
//        Recipe rr = r.read(15);
//

//        System.out.println(rr);
//        k.setContent("testContentUPDATED");
//        k.setCreated_c(Timestamp.valueOf(LocalDateTime.now()));
//        k.setId_person(p);
//        k.setId_recipe(r);
//        k.setStatus(3);

//        commDAO.update(k);
//        k.setContent("Comment deleted");
//        commDAO.updateContent(k);

//        LIST
//        Collection collection = commDAO.list();
//        for (Object object : collection) {
//            System.out.println(object);
//        }
//
//        System.out.println(commDAO.count());
//        DELETE
//        commDAO.delete(1);

    }
    }

