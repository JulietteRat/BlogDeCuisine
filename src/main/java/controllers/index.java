package controllers;

import dao.RecipeDAO;
import dao.VoteDAO;
import entities.Person;
import entities.RecipeScoreVotable;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Ralph Lee
 */
@WebServlet("/index")
@SuppressWarnings("serial")
public class index extends HttpServlet {

    Integer NB_BEST_RECIPES = 3;
    Integer NB_LATEST_RECIPES = 10;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Ajout des élements de vote soumis à la condition d'etre connecté et actif
        Person p = (Person) req.getSession().getAttribute("user");
        if (p != null && p.getStatus() == 1) {
            //Ajout des éléments de vote
            req.setAttribute("UserVoter", "1");
        } else {
            req.setAttribute("UserVoter", "0");
        }
        // traitement de la votabilité des recettes en fonction du user
        Collection<RecipeScoreVotable> best = new RecipeDAO().listBest(NB_BEST_RECIPES);
        Collection<RecipeScoreVotable> last = new RecipeDAO().listLast(NB_LATEST_RECIPES);
        if (p != null) {
            VoteDAO vdao = new VoteDAO();

            for (RecipeScoreVotable recipe : best) {
                if (recipe.getVotable() && !vdao.exists(p.getId_person(), recipe.getId_recipe())) {
                    recipe.setVotable(true);
                } else {
                    recipe.setVotable(false);
                }
            }

            for (RecipeScoreVotable recipe : last) {
                if (recipe.getVotable() && !vdao.exists(p.getId_person(), recipe.getId_recipe())) {
                    recipe.setVotable(true);
                } else {
                    recipe.setVotable(false);
                }
            }
        }
        System.out.println(best);
        System.out.println(last);
        req.setAttribute("theBest", best);
        req.setAttribute("latest", last);

        //Affichage de la page
        req.getRequestDispatcher("/WEB-INF/index.jsp").forward(req, resp);
    }

}
