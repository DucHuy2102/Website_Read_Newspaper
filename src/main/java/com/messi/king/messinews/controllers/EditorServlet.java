package com.messi.king.messinews.controllers;

import com.messi.king.messinews.model.bean.*;
import com.messi.king.messinews.model.dao.*;
import com.messi.king.messinews.utils.PdfUtils;
import com.messi.king.messinews.utils.ServletUtils;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@WebServlet(name = "EditorServlet", value = "/Editor/*")
public class EditorServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String url = request.getPathInfo();
        HttpSession session = request.getSession();
        Users user = (Users) session.getAttribute("authUser");

        switch (url) {
            case "/ArticleList":
                getArticleList(request, response, user);
                break;
            case "/ViewArticle":
                getViewArticle(request, response, user);
                break;
            case "/Accept":
                getAcceptArticle(request, response, user);
                break;
            case "/Deny":
                getDenyArticle(request, response, user);
                break;
            default:
                ServletUtils.forward("/views/404.jsp", request, response);
        }
    }

    private static void getArticleList(HttpServletRequest request, HttpServletResponse response, Users user) throws ServletException, IOException {
        List<Articles> listFull = ArticlesDAO.findByEditorId(user.getId());
        request.setAttribute("articlesList", listFull);
        ServletUtils.forward("/views/vwEditor/EditorArticleList.jsp", request, response);
    }

    private static void getViewArticle(HttpServletRequest request, HttpServletResponse response, Users user) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Articles article2 = ArticlesDAO.findById(id);
        List<Tags> tags = TagsDAO.findTagByArticle(id);

        request.setAttribute("article", article2);
        request.setAttribute("tags", tags);
        ServletUtils.forward("/views/vwEditor/ViewArticle.jsp", request, response);
    }

    private static void getAcceptArticle(HttpServletRequest request, HttpServletResponse response, Users user) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        List<Tags> tagsList = TagsDAO.findAll();
        Articles article = ArticlesDAO.findById(id);
        List<Categories> catList = CategoriesDAO.findAllByEditorId(user.getId());

        request.setAttribute("Categories", catList);
        request.setAttribute("article", article);
        request.setAttribute("tags", tagsList);
        ServletUtils.forward("/views/vwEditor/Accept.jsp", request, response);
    }

    private static void getDenyArticle(HttpServletRequest request, HttpServletResponse response, Users user) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Articles article3 = ArticlesDAO.findById(id);
        request.setAttribute("article", article3);
        ServletUtils.forward("/views/vwEditor/Deny.jsp", request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String url = request.getPathInfo();
        HttpSession session = request.getSession();
        switch (url) {
            case "/Accept":
                acceptArticle(request, response);
                break;
            case "/Deny":
                denyArticle(request, response);
                break;
            default:
                ServletUtils.forward("/views/404.jsp", request, response);
        }
    }

    private static void denyArticle(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Users user = (Users) request.getSession().getAttribute("authUser");

        int id = 0;
        try {
            id = Integer.parseInt(request.getParameter("id"));
        } catch (NumberFormatException e) {
            ServletUtils.redirect("/views/204.jsp", request, response);
        }
        String reason = request.getParameter("reason").trim();
        EditorDAO.declineArticle(id, reason, user.getId());
        if (user.getRole()==3){
            ServletUtils.redirect("/Editor/List", request, response);
        }
        else {
            ServletUtils.redirect("/Admin/Articles/List", request, response);
        }

    }

    public static void acceptArticle(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Users user = (Users) request.getSession().getAttribute("authUser");
        int id = 0, premium = 0, idCat=0;
        try {
            id = Integer.parseInt(request.getParameter("id"));
            idCat = Integer.parseInt(request.getParameter("idCat"));
            premium = Integer.parseInt(request.getParameter("premium"));
        } catch (NumberFormatException e) {
            ServletUtils.redirect("/views/204.jsp", request, response);
        }


        String publish_timeStr = request.getParameter("publish_time") + " 00:00";
        DateTimeFormatter df = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        LocalDateTime publish_time = LocalDateTime.parse(publish_timeStr, df);


        String[] tagsIdStr = request.getParameter("listTagId")
                .split(",");
        int[] tagsId = Arrays
                .stream(tagsIdStr)
                .mapToInt(Integer::parseInt)
                .toArray();

        EditorDAO.acceptArticle(id, publish_time, premium,idCat, tagsId, user.getId());

        Articles art = ArticlesDAO.findById(id);
        PdfUtils.createPdfFile(art, request, response);

        if (user.getRole()==3){
            ServletUtils.redirect("/Editor/List", request, response);
        }
        else {
            ServletUtils.redirect("/Admin/Articles/List", request, response);
        }
    }
}
