package com.test.mywebapp.servlet;

import com.test.mywebapp.dao.mysql.MySqlUserDAO;
import com.test.mywebapp.domain.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class GetUserWithCarDetail extends HttpServlet {

    private MySqlUserDAO userDAO = new MySqlUserDAO();


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.valueOf(req.getParameter("userId"));

        User user = userDAO.getUserById(id);
        System.out.println("id = " + id);
        System.out.println("doGet = " + user);

        req.setAttribute("user", user);
        req.getRequestDispatcher("user.jsp").forward(req, resp);
    }
}


