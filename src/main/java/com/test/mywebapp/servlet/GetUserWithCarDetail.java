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

public class GetUserWithCarDetail extends HttpServlet {

    private MySqlUserDAO userDAO = new MySqlUserDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.valueOf(req.getParameter("userId"));
        User user = userDAO.getUserById(id);
        List<Car> cars = userDAO.getListCarFromUser(id);
        System.out.println("cars from user id = " + id + " " + cars);
        req.setAttribute("cars", cars);
        req.setAttribute("user", user);
        req.getRequestDispatcher("user.jsp").forward(req, resp);
    }
}


