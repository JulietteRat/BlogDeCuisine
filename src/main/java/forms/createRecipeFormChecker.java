/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package forms;

import dao.PersonDAO;
import entities.Person;
import entities.Recipe;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Jonathan DAH
 */
public class createRecipeFormChecker extends FormChecker<Recipe> {

    private final String TITLE_FIELD = "title";
    private final String INGREDIENTS = "ingredients";
    private final String CONTENT_FIELD = "content";
    private final int DIFFICULTY = 5;
    private final int MIN_TITLE_LENGTH = 5;
    private final int MIN_CONTENT_LENGTH = 10;

    public createRecipeFormChecker(HttpServletRequest request) {
        super(request);
    }

    @Override
    public Recipe checkForm() {
        Recipe obj = new Recipe();
//        Person person = (Person) request.getSession().getAttribute("user");
//        String title = getParameter(TITLE_FIELD);
//        String content = getParameter(CONTENT_FIELD);
//        // int difficulty = getParameter(DIFFICULTY);
//        obj.setId_person(person);
//        obj.setContent(content);
//        obj.setTitle(title);
//        // obj.setDifficulty(difficulty);
//        obj.setIngredients(content);
//
//        obj.setCreated_r(Timestamp.valueOf(LocalDateTime.now()));
//        if (title.trim().length() < MIN_TITLE_LENGTH) {
//            setErrors(TITLE_FIELD, "Ce champ doit faire au moins "
//                    + MIN_TITLE_LENGTH + " lettres.");
//        }
//        if (content.trim().length() < MIN_CONTENT_LENGTH) {
//            setErrors(CONTENT_FIELD, "Ce champ doit faire au moins "
//                    + MIN_CONTENT_LENGTH + " lettres.");
//        }
//        if (errors.isEmpty()) {
//            
//        }
//
//        request.setAttribute("errors", errors);
//        request.setAttribute("bean", obj);
        return obj;
    }

}
