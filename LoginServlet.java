package com.food.servlet;

import java.io.IOException;

import com.food.daoimplementation.UserDAOImplementation;
import com.food.model.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class LoginServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;

    private UserDAOImplementation userDao;

    @Override
    public void init() throws ServletException {
        userDao = new UserDAOImplementation();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String email = request.getParameter("email");
        String password = request.getParameter("password");

        User user = userDao.loginUser(email, password);
        System.out.println("Login attempt: " + email);
        System.out.println("Password: " + password);
        if (user != null) {
        	  System.out.println("✅ Login success for: " + user.getUsername());
            HttpSession session = request.getSession();
            session.setAttribute("user", user);
            response.sendRedirect("Restaurant.html"); // Success
        } else {
        	System.out.println("❌ Login failed");
            response.sendRedirect("signin.html?error=invalid"); // Fail
        }
    }
}
