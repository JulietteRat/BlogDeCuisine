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
 * @author anastasiia
 */
public class UpdatePasswordChecker extends FormChecker<Person> {

    public UpdatePasswordChecker(HttpServletRequest request) {
        super(request);
    }

    @Override
    public Person checkForm() {
        PersonDAO daoP = new PersonDAO();
        Person obj = (Person) request.getSession().getAttribute("user");

        String pwd = getParameter("pwd");
        String pwd1 = getParameter("newPwd");
        String verif = getParameter("verifyPwd");

        if (pwd.trim().length() == 0) {
            setErrors("pwd", "This field has to be completed");
        }
        if (pwd1.trim().length() == 0) {
            setErrors("newPwd", "This field has to be completed");
        }
        if (verif.trim().length() == 0) {
            setErrors("verifyPwd", "This field has to be completed");
        }
        if (pwd.trim().length() != 0 && !pwd.equals(obj.getPwd())) {
            setErrors("pwd", "Wrong password");
        }
        if (!pwd1.equals(verif)) {
            setErrors("verifyPwd", "Passwords don't match");
        }
        if (errors.isEmpty()) {
            obj.setPwd(pwd1);
            daoP.update(obj);
            request.setAttribute("pwdChanged", "Password has been changed");
        }
        request.setAttribute("errors", errors);
        request.setAttribute("bean", obj);

        return obj;

    }

}
