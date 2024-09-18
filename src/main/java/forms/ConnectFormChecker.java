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
public class ConnectFormChecker extends FormChecker<Person> {

    public ConnectFormChecker(HttpServletRequest request) {
        super(request);
    }

    @Override
    public Person checkForm() {
        Person obj = new Person();
        // hydrater le bean avec les données du formulaire
        String login = getParameter("login");
        String pwd = getParameter("pwd");
        obj.setLogin(login);
        obj.setPwd(pwd);
        // Vérifier les données du formulaire
        // Vérifier si les champs sont remplis
        if (login.trim().length() == 0) {
            setErrors("login", "You need to write here.");
        }
        if (pwd.length() == 0) {
            setErrors("pwd", "You need to write here.");
        }
        // Si formulaire acceptable => vérifier le couple login/pwd
        if (errors.isEmpty()) {
            // Vérifier en DB que cet utilisateur existe
            // et qu'il a donné le bon pwd
            PersonDAO pdao = new PersonDAO();
            Person read = pdao.read(login);
            if (read == null || !pwd.equals(read.getPwd())) {
                setErrors("pwd", "Incorrect informations, Try again.");
            } else if (read.getStatus() == 2 || read.getStatus() == 3) {
                setErrors("pwd", "Your account has been deleted or banned");
            } else {
                obj.setId_person(read.getId_person());
                obj.setStatus(read.getStatus());
            }
        }
        // associer les messages d'erreur et le bean à la requête
        request.setAttribute("errors", errors);
        request.setAttribute("bean", obj);
        return obj;
    }

}
