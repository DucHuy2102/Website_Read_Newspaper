package com.messi.king.messinews.controllers;

import com.messi.king.messinews.model.bean.Articles;
import com.messi.king.messinews.model.bean.Categories;
import com.messi.king.messinews.model.bean.Tags;
import com.messi.king.messinews.model.bean.Users;
import com.messi.king.messinews.model.dao.*;
import com.messi.king.messinews.utils.PdfUtils;
import com.messi.king.messinews.utils.ServletUtils;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@WebServlet(name = "AdminArticlesServlet", value = "/Admin/Articles/*")
public class AdminArticlesServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String url = request.getPathInfo();

        switch (url) {
            case "/Upload":
                List<Tags> tagsList = TagsDAO.findAll();
                request.setAttribute("tags", tagsList);
                ServletUtils.forward("/views/vwWriter/Upload.jsp",request,response);
                break;
            case "/List":
                List<Articles> artsComplete = EditorDAO.findAll();
                request.setAttribute("articlesList", artsComplete);
                ServletUtils.forward("/views/vwAdmin/AdminArticleList.jsp", request, response);
                break;
            case "/Accept":
                getAccept(request,response);
                break;
            case "/Deny":
                getDeny(request,response);
                break;
            case "/ViewArticle":
                getViewArticle(request,response);
                break;
            default:
                ServletUtils.forward("/views/404.jsp",request,response);
        }
    }

    private void getAccept(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        List<Tags> tagsList = TagsDAO.findAll();
        Articles article = ArticlesDAO.findById(id);
        List<Categories> catList = CategoriesDAO.findAll();

        request.setAttribute("Categories", catList);
        request.setAttribute("article", article);
        request.setAttribute("tags", tagsList);
        ServletUtils.forward("/views/vwEditor/Accept.jsp", request, response);
    }

    private void getDeny(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Articles article = ArticlesDAO.findById(id);
        request.setAttribute("article", article);
        ServletUtils.forward("/views/vwEditor/Deny.jsp", request, response);
    }
    private void getViewArticle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Articles article = ArticlesDAO.findById(id);
        List<Tags> tags = TagsDAO.findTagByArticle(id);

        request.setAttribute("article", article);
        request.setAttribute("tags", tags);
        ServletUtils.forward("/views/vwEditor/ViewArticle.jsp", request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String url = request.getPathInfo();
        switch (url) {
            case "/Accept":
                int id=0;
                try {
                    id = Integer.parseInt(request.getParameter("id"));
                } catch (NumberFormatException e) {
                    ServletUtils.redirect("/views/204.jsp", request, response);
                }
                request.getParameter("premium");
                request.getParameter("publish_time");
                request.getSession().getAttribute("listTagId");
//                EditorService.acceptArticle();

                Articles art = ArticlesDAO.findById(id);
                PdfUtils.createPdfFile(art, request,response);


                ServletUtils.redirect("/Editor/List", request,response);
                break;
            case "/Deny":
                request.getAttribute("id");
                request.getParameter("reason");

//                EditorService.declineArticle(id,reason);
                ServletUtils.redirect("/Editor/List", request,response);
                break;
            default:
                ServletUtils.forward("/views/404.jsp",request,response);
        }
    }
}
