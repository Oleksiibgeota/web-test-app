package com.test.mywebapp.servlet;

import com.test.mywebapp.dao.mysql.MySqlUserDAO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CreateCarServlet extends HttpServlet {

    private MySqlUserDAO userDAO = new MySqlUserDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("create-user.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String carName = req.getParameter("carName");
        String idUser = req.getParameter("idUser");
         if (req.getParameter("actionForCar").equals("Delete")) {
            userDAO.deleteCarByUserIdAndCarName(Integer.parseInt(idUser), carName);
        } else if (req.getParameter("actionForCar").equals("Create")) {
            userDAO.createCarForUser(Integer.parseInt(idUser), carName);
        }
        resp.sendRedirect("/mywebapp/createCar");
    }
}
