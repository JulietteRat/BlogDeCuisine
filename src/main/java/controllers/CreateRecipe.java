/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import dao.RecipeDAO;
import entities.Recipe;
import forms.CreateRecipeFormChecker;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

/**
 *
 * @author Jonathan DAH
 */
@WebServlet("/user/createrecipe")
@SuppressWarnings("serial")
@MultipartConfig
//public class CreateRecipe extends HttpServlet {
//
//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//
//        req.getRequestDispatcher("/WEB-INF/createRecipe.jsp")
//                .forward(req, resp);
//    }
//
//    @Override
//    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        CreateRecipeFormChecker fc = new CreateRecipeFormChecker(req);
//        Recipe r = fc.checkForm();
//
//        if (fc.getErrors().isEmpty()) {
//            resp.sendRedirect(req.getContextPath() + "/");
//        } else {
//            req.setAttribute("errorMsg", "Votre formulaire comporte des erreurs.");
//            req.getRequestDispatcher("/WEB-INF/createRecipe.jsp").forward(req, resp);
//        }
//    }
//
//
//
//}

//test modifications by anastasiia
public class CreateRecipe extends HttpServlet {

    private static final String IMAGES_FOLDER = "./Images";
    private String uploadPath;

    @Override
    public void init() throws ServletException {
        // Si le répertoire destination n'existe pas on le créé
        uploadPath = getServletContext().getRealPath(IMAGES_FOLDER);
        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists()) {
            uploadDir.mkdir();
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.getRequestDispatcher("/WEB-INF/createRecipe.jsp")
                .forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RecipeDAO rdao = new RecipeDAO();
        CreateRecipeFormChecker fc = new CreateRecipeFormChecker(req);
        Recipe r = fc.checkForm();
        System.out.println(r);
        if (fc.getErrors().isEmpty()) {
            req.setCharacterEncoding(StandardCharsets.UTF_8.toString());
            Part filePart = req.getPart("file");
            String fileName = getFilename(filePart);
//            save img in bd
            r.setImg_url(fileName);
            new RecipeDAO().update(r);
            String fullPath = uploadPath + File.separator + fileName;
            filePart.write(fullPath);
            req.setAttribute("file", fileName);

            resp.sendRedirect(req.getContextPath() + "/");

        } else {
            req.setAttribute("errorMsg", "Votre formulaire comporte des erreurs.");
            req.getRequestDispatcher("/WEB-INF/createRecipe.jsp").forward(req, resp);
        }
    }

    private String getFilename(Part part) {
        for (String content : part.getHeader("content-disposition").split(";")) {
            if (content.trim().startsWith("filename")) {
                return content.substring(content.indexOf("=") + 2, content.length() - 1);
            }
        }
        return "Default.file";
    }

}
