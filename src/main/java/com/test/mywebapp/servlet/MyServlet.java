package com.test.mywebapp.servlet;

import com.test.mywebapp.dao.mysql.MySqlUserDAO;
import com.test.mywebapp.dto.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MyServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        MySqlUserDAO userDAO = new MySqlUserDAO();
        String firstName = req.getParameter("firstName");
        if (firstName != null) {
            User user = userDAO.getUserByName(firstName);
            req.setAttribute("user", user);
            req.getRequestDispatcher("userdata.jsp").forward(req, resp);
        } else {
            req.getRequestDispatcher("test.jsp").forward(req, resp);
        }
    }
}
