package com.test.mywebapp.servlet;

import com.test.mywebapp.dao.mysql.MySqlUserDAO;
import com.test.mywebapp.domain.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SearchUserServlet extends HttpServlet {
    private MySqlUserDAO userDAO = new MySqlUserDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String firstName = req.getParameter("firstName");
        if (firstName != null) {
            User user = userDAO.getUserByName(firstName);
            System.out.println("search user: " + user);
            if (user != null) {
                req.setAttribute("user", user);
            }
        }
        req.getRequestDispatcher("search-user.jsp").forward(req, resp);
    }
}
