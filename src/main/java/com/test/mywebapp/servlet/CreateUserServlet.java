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
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
//        String firstName = req.getParameter("firstname");
//        String lastName = req.getParameter("lastname");

        String carName = req.getParameter("carName");
        String idUser = req.getParameter("idUser");

//        if (req.getParameter("action").equals("Delete")) {
//            userDAO.deleteUserByFirstNameAndLastName(firstName, lastName);
//        } else if (req.getParameter("action").equals("Create")) {
//            userDAO.createUser(firstName, lastName);
//        }


        if (req.getParameter("actionForCar").equals("Delete")) {
            System.out.println("get parameter action for car delete " + req.getParameter("actionForCar"));
            userDAO.deleteCarByUserIdAndCarName(Integer.parseInt(idUser), carName);
        } else if (req.getParameter("actionForCar").equals("Create")) {
            userDAO.createCarForUser(Integer.parseInt(idUser), carName);
        }


        resp.sendRedirect("/mywebapp/create");
    }
}
