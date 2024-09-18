/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package filters;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

/**
 *
 * @author Jonathan DAH
 */
@WebFilter("/*")
public class utf8Encoding implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        //TRAVAIL A EFFECTUER AVANT D'ARRIVER AU CONTRÔLEUR
        request.setCharacterEncoding(StandardCharsets.UTF_8.toString());
        //APPEL DU FILTRE SUIVANT
        chain.doFilter(request, response);
        //LE TRAVAIL EFFECTUÉ AVANT D'ENVOYER LA RÉPONSE AU CLIENT

    }

    @Override
    public void destroy() {
    }

}
