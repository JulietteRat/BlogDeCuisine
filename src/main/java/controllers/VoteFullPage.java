/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import dao.VoteDAO;
import entities.Person;
import entities.Vote;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Ralph Lee <your.name at your.org>
 */
@WebServlet("/voteFullPage")
public class VoteFullPage extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        VoteDAO vdao = new VoteDAO();
        Person p = (Person) req.getSession().getAttribute("user");
        Vote vote = new Vote();
        vote.setId_person(p.getId_person());
        Integer id_recipe = Integer.valueOf(req.getParameter("id_recipe"));
        vote.setId_recipe(id_recipe);
        Boolean voteResult = Boolean.valueOf(req.getParameter("vote"));
        vote.setType_vote(voteResult);
        vdao.create(vote);
        
        resp.sendRedirect(req.getContextPath()+"/showRecipe?id="+id_recipe);
    }

}

