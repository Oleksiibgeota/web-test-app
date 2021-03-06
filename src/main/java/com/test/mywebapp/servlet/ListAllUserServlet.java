package com.test.mywebapp.servlet;

import com.test.mywebapp.dao.mysql.MySqlUserDAO;
import com.test.mywebapp.domain.Car;
import com.test.mywebapp.domain.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ListAllUserServlet extends HttpServlet {

    private MySqlUserDAO userDAO = new MySqlUserDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<User> users = userDAO.getAllUsers();
        System.out.println("\nUser size: " + users.size() + "\n");
        req.setAttribute("users", users);
        req.getRequestDispatcher("all-users.jsp").forward(req, resp);
    }
}
