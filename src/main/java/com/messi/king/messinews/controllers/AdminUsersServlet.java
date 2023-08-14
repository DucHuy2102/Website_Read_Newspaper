package com.messi.king.messinews.controllers;

import at.favre.lib.crypto.bcrypt.BCrypt;
import com.messi.king.messinews.model.bean.Users;
import com.messi.king.messinews.model.dao.CategoriesDAO;
import com.messi.king.messinews.model.dao.EditorDAO;
import com.messi.king.messinews.model.dao.UsersDAO;
import com.messi.king.messinews.utils.ServletUtils;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@WebServlet(name = "AdminUsersServlet", value = "/Admin/Users/*")
public class AdminUsersServlet extends HttpServlet {
    String check = "1";
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String url = request.getPathInfo();
        switch (url) {
            case "/Profile":
                profile(request, response);
                break;
            case "/List":
                request.setAttribute("subs", UsersDAO.findAllByRole(1));
                request.setAttribute("writers", UsersDAO.findAllByRole(2));
                request.setAttribute("editors", UsersDAO.findAllByRole(3));
                request.setAttribute("admins", UsersDAO.findAllByRole(4));
                request.setAttribute("check",check);
                ServletUtils.forward("/views/vwAdmin/PersonnelList.jsp", request, response);
                break;
            case "/ExtendExp":
                getExtendExp(request, response);
                break;
            case "/AssignCategory":
                assignCategory(request, response);
                break;
            case "/Add":
                ServletUtils.forward("/views/vwAdmin/UserAdminAdd.jsp", request, response);
                break;
            default:
                ServletUtils.forward("/views/404.jsp", request, response);
                break;
        }
    }

    private static void profile(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = 0;
        try {
            id = Integer.parseInt(request.getParameter("id"));
        } catch (NumberFormatException e) {
        }
        Users user = UsersDAO.findById(id);
        request.setAttribute("user", user);
        ServletUtils.forward("/views/vwAdmin/ProfileAdmin.jsp", request, response);
    }

    private static void getExtendExp(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = 0;
        try {
            id = Integer.parseInt(request.getParameter("id"));
        } catch (NumberFormatException e) {
        }
        Users user = UsersDAO.findById(id);
        request.setAttribute("user", user);
        ServletUtils.forward("/views/vwAdmin/SubsAdminExtendExpiration.jsp", request, response);
    }

    private static void assignCategory(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int editorId = 0;
        try {
            editorId = Integer.parseInt(request.getParameter("editorId"));
        } catch (NumberFormatException e) {
        }
        Users editor = UsersDAO.findById(editorId);
        if (editor != null) {
            request.setAttribute("editor", editor);
            request.setAttribute("cates", CategoriesDAO.findAllByEditorId(editor.getId()));
            ServletUtils.forward("/views/vwAdmin/CateAdminAssign.jsp", request, response);
        } else {
            ServletUtils.forward("/views/204.jsp", request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String url = request.getPathInfo();
        switch (url) {
            case "/EditProfile":
                adminUpdateUser(request, response);
                break;
            case "/ResetPass":
                adminResetPass(request, response);
                break;
            case "/AssignCategory":
                assignCategories(request, response);
                break;
            case "/Add":
                adminAddUser(request, response);
                break;
            case "/Delete":
                adminDeleteUser(request, response);
                break;
            case "/ExtendExp":
                adminExtendExpUser(request, response);
                break;
            default:
                ServletUtils.forward("/views/404.jsp", request, response);
                break;
        }
    }

    private void adminUpdateUser(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.setCharacterEncoding("UTF-8");

        int id = 0;
        try {
            id = Integer.parseInt(request.getParameter("id"));
        } catch (NumberFormatException e) {
        }
        Users user = UsersDAO.findById(id);
        if (user != null) {
            String fullName = request.getParameter("newFullName");
            String email = request.getParameter("newEmail");
            int role = user.getRole();
            try {
                role = Integer.parseInt(request.getParameter("role"));
            } catch (NumberFormatException e) {
            }
            LocalDateTime dob = user.getDob();
            if (!request.getParameter("newDob").equals("__/__/____")) {
                String strDob = request.getParameter("newDob") + " 00:00";
                DateTimeFormatter df = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
                dob = LocalDateTime.parse(strDob, df);
            }
            UsersDAO.updateProfile(user.getId(), fullName, role, email, dob);
            ServletUtils.redirect("/Admin/Users/Profile?id="+id, request, response);
        } else
            ServletUtils.forward("/views/204.jsp", request, response);
    }

    private void adminResetPass(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.setCharacterEncoding("UTF-8");

        int id = 0;
        try {
            id = Integer.parseInt(request.getParameter("id"));
            String rawpwd = request.getParameter("rawpwd");
            String bcryptHashString = BCrypt.withDefaults().hashToString(12, rawpwd.toCharArray());
            UsersDAO.changePassword(id, bcryptHashString);
            ServletUtils.redirect("/Admin/Users/Profile?id="+id, request, response);

        } catch (NumberFormatException e) {
            ServletUtils.redirect("/Admin/Users/Profile?id="+id, request, response);
        }
    }

    private void assignCategories(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        int editorId = 0;
        try {
            editorId = Integer.parseInt(request.getParameter("editorId"));
        } catch (NumberFormatException e) { 
        }
        Users editor = UsersDAO.findById(editorId);
        if (editor != null) {
            String[] catesIdStr =request.getParameter("catesId").split(",");
            System.out.println(catesIdStr.length);
            if (!catesIdStr[0].equals("")) {
                int[] catesId = Arrays
                        .stream(catesIdStr)
                        .mapToInt(Integer::parseInt)
                        .toArray();
                UsersDAO.assignCategories(editorId, catesId);
            } else {
                UsersDAO.deleteEditorCategories(editorId);
            }

            this.check = "3";
            ServletUtils.redirect("/Admin/Users/List", request, response);

        } else {
            ServletUtils.forward("/views/204.jsp", request, response);
        }
    }

    private void adminDeleteUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = 0;
        try {
            id = Integer.parseInt(request.getParameter("id"));
        } catch (NumberFormatException e) {
        }
        Users user = UsersDAO.findById(id);
        if (user != null) {
            UsersDAO.delete(user);
            int role = user.getRole();
            this.check = Integer.toString(role);
            ServletUtils.redirect("/Admin/Users/List", request, response);

        } else {
            ServletUtils.forward("/views/204.jsp", request, response);
        }

    }

    private void adminAddUser(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");

        String rawpwd = request.getParameter("rawpwd");
        String bcryptHashString = BCrypt.withDefaults().hashToString(12, rawpwd.toCharArray());

        String strDob = request.getParameter("dob") + " 00:00";
        DateTimeFormatter df = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        LocalDateTime dob = LocalDateTime.parse(strDob, df);

        String username = request.getParameter("username");
        String fullName = request.getParameter("fullName");
        String email = request.getParameter("email");

        int role = 0;
        try {
            role = Integer.parseInt(request.getParameter("role"));
        } catch (NumberFormatException e) {
        }

        Users c = new Users();
        if (role == 1) {
            c = new Users(0, username, bcryptHashString, fullName, LocalDateTime.now(), 7 * 24 * 60, 1, dob, email, null, null);
        } else {
            c = new Users(0, username, bcryptHashString, fullName, LocalDateTime.now(), 0, role, dob, email, null, null);
        }
        UsersDAO.add(c);
        this.check = Integer.toString(role);
        ServletUtils.redirect("/Admin/Users/List", request, response);
    }

    private void adminExtendExpUser(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        int id = 0;
        try {
            id = Integer.parseInt(request.getParameter("id"));
        } catch (NumberFormatException e) {
        }
        Users user = UsersDAO.findById(id);
        if (user != null) {

            String expireDateStr = request.getParameter("expireDate") + " 00:00";
            DateTimeFormatter df = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
            LocalDateTime expireDate = LocalDateTime.parse(expireDateStr, df);

            UsersDAO.extendSubscriber(id, expireDate);
            this.check = "1";
            ServletUtils.redirect("/Admin/Users/List", request, response);
        } else {
            ServletUtils.forward("/views/204.jsp", request, response);
        }
    }
}
