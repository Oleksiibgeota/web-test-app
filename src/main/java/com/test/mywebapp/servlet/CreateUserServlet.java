package com.test.mywebapp.servlet;

import com.test.mywebapp.dao.mysql.MySqlUserDAO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CreateUserServlet extends HttpServlet {

    private MySqlUserDAO userDAO = new MySqlUserDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("create-user.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        String firstName = req.getParameter("firstname");
        String lastName = req.getParameter("lastname");
        userDAO.createUser(firstName, lastName);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) {
        String firstName = req.getParameter("firstname");
        String lastName = req.getParameter("lastname");
        userDAO.deleteUserByFirstNameAndLastName(firstName, lastName);
    }
}
