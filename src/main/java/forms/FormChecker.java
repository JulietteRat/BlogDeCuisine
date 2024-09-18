/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package forms;

import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Jonathan DAH
 */
public abstract class FormChecker<T> {

    protected Map<String, String> errors;
    protected HttpServletRequest request;

    public abstract T checkForm();

    public FormChecker(HttpServletRequest request) {
        this.errors = new HashMap<>();
        this.request = request;
    }

    public Map<String, String> getErrors() {
        return errors;
    }

    protected void setErrors(String k, String v) {
        this.errors.put(k, v);
    }

    protected String getParameter(String k) {
        return request.getParameter(k) == null ? "" : request.getParameter(k);
    }

}
