/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package forms;

import dao.RecipeDAO;
import entities.Person;
import entities.Recipe;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Jonathan DAH
 */
public class CreateRecipeFormChecker extends FormChecker<Recipe> {

    private final String TITLE_FIELD = "title";
    private final String INGREDIENTS = "ingredients";
    private final String CONTENT_FIELD = "content";
    private final String DIFFICULTY = "difficulty";

    private final int MIN_TITLE_LENGTH = 5;
    private final int MIN_CONTENT_LENGTH = 10;

    public CreateRecipeFormChecker(HttpServletRequest request) {
        super(request);
    }

    @Override
    public Recipe checkForm() {
        Recipe obj = new Recipe();
        Person person = (Person) request.getSession().getAttribute("user");
        String title = getParameter(TITLE_FIELD);
        String ingredients = getParameter(INGREDIENTS);
        String content = getParameter(CONTENT_FIELD);
        int difficulty = Integer.parseInt(getParameter(DIFFICULTY));

        obj.setAuthor(person);
        obj.setTitle(title);
        obj.setIngredients(ingredients);
        obj.setContent(content);
        obj.setDifficulty(difficulty);


        obj.setCreated_r(Timestamp.valueOf(LocalDateTime.now()));
        if (title.trim().length() < MIN_TITLE_LENGTH) {
            setErrors(TITLE_FIELD, "This field has a minimum of "
                    + MIN_TITLE_LENGTH + " characters.");
        }
        if (content.trim().length() < MIN_CONTENT_LENGTH) {
            setErrors(CONTENT_FIELD, "This field has a minimum of "
                    + MIN_CONTENT_LENGTH + " characters.");
        }
        if (ingredients == null || ingredients.length() == 0) {
            setErrors("ingredients", "You need to write here");
        }

        if (errors.isEmpty()) {
            RecipeDAO rdao = new RecipeDAO();
            rdao.create(obj);
        }

        request.setAttribute("errors", errors);
        request.setAttribute("bean", obj);
        return obj;
    }

}
