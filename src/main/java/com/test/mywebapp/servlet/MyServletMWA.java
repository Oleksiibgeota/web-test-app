package com.test.mywebapp.servlet;

import com.test.mywebapp.dao.mysql.MySqlUserDAO;
import com.test.mywebapp.dto.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class MyServletMWA extends HttpServlet {

    MySqlUserDAO userDAO = new MySqlUserDAO();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = new User();
        String firstName = req.getParameter("firstname");
        String lastName = req.getParameter("lastname");

        if (req.getParameter("action").equals("Delete")) {
            userDAO.deleteUserByFirstNameAndLastName(firstName, lastName);
            System.out.println("update");
        } else if (req.getParameter("action").equals("Create")) {
            userDAO.createUser(firstName, lastName);
//            user.setNameFirst(firstName);
//            user.setNameLast(lastName);
        } else if (req.getParameter("Update").equals("Update")) {

        }

        List<User> users = userDAO.getAllUsers();
        System.out.println("\nUser size: " + users.size() + "\n");
        req.setAttribute("users", users);
        req.getRequestDispatcher("all-users.jsp").forward(req, resp);

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String firstName = req.getMethod();
        List<User> users = userDAO.getAllUsers();
        System.out.println("\nUser size: " + users.size() + "\n");
        req.setAttribute("users", users);
        req.getRequestDispatcher("all-users.jsp").forward(req, resp);

    }


}
