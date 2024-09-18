/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package forms;

import dao.PersonDAO;
import entities.Person;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Jonathan DAH
 */
public class SignUpFormChecker extends FormChecker<Person> {

    private final int MIN_LOGIN_LENGTH = 3;
    private final int MIN_PWD_LENGTH = 5;
    private final String PASSWORD_FIELD = "pwd";
    private final String LOGIN_FIELD = "login";
    private final String VERIF_FIELD = "verif";

    public SignUpFormChecker(HttpServletRequest request) {
        super(request);
    }

    @Override
    public Person checkForm() {
        Person obj = new Person();
        String login = getParameter(LOGIN_FIELD);
        String pwd = getParameter(PASSWORD_FIELD);
        String verif = getParameter(VERIF_FIELD);
        obj.setLogin(login);
        obj.setPwd(pwd);
        obj.setStatus(1);

        if (login.trim().length() < MIN_LOGIN_LENGTH) {
            setErrors(LOGIN_FIELD, "This field has a minimum of " + MIN_LOGIN_LENGTH + " characters.");
        }
        if (pwd.length() < MIN_PWD_LENGTH) {
            setErrors(PASSWORD_FIELD, "This field has a minimum of " + MIN_PWD_LENGTH + " letters.");
        }
        if (!verif.equals(pwd)) {
            setErrors(VERIF_FIELD, "Passwords don't match.");
        }
        if (((login.indexOf('@')== -1) && (login.indexOf('.') == -1))){
            setErrors(LOGIN_FIELD,"enter a fucking e-mail");
        }    
        if (errors.isEmpty()) {
            PersonDAO pdao = new PersonDAO();
            Person read = pdao.read(login);
            if (read != null) {
                setErrors(LOGIN_FIELD, "This user already exist, please login.");
            } else {
                pdao.create(obj);
            }
        }

        request.setAttribute("errors", errors);
        request.setAttribute("bean", obj);

        return obj;
    }

}
