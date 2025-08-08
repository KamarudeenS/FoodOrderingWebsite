package com.food.servlet;

import java.io.IOException;
import java.sql.Timestamp;

import com.food.daoimplementation.UserDAOImplementation;
import com.food.model.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class SignupServlet  extends HttpServlet {


	 private UserDAOImplementation userDao;

	    @Override
	    public void init() throws ServletException {
	        userDao = new UserDAOImplementation();
	    }

	    @Override
	    protected void doPost(HttpServletRequest request, HttpServletResponse response)
	            throws ServletException, IOException {

	        String name = request.getParameter("name");
	        String username = request.getParameter("username");
	        String password = request.getParameter("password");
	        String email = request.getParameter("email");
	        String phonenumber = request.getParameter("phonenumber");
	        String address = request.getParameter("address");

	        // Validate email format
	        if (!email.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")) {
	            response.sendRedirect("signup.html?error=invalidEmail");
	            return;
	        }

	        // Validate phone number (10 digits)
	        if (!phonenumber.matches("^\\d{10}$")) {
	            response.sendRedirect("signup.html?error=invalidPhone");
	            return;
	        }

	        // Validate password (min 8, 1 uppercase, 1 number, 1 special char)
	        String passwordRegex = "^(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%^&+=!]).{8,}$";
	        if (!password.matches(passwordRegex)) {
	            response.sendRedirect("signup.html?error=weakPassword");
	            return;
	        }

	        // Check if username already exists
	        if (userDao.checkUsernameExists(username)) {
	            response.sendRedirect("signup.html?error=usernameExists");
	            return;
	        }

	        // Create new user
	        Timestamp now = new Timestamp(System.currentTimeMillis());
	        User user = new User(name, username, password, email, phonenumber, address, "user");
	        user.setCreatedDate(now);
	        user.setLastLoginDate(now);

	        userDao.addUser(user);
	        response.sendRedirect("signin.html?signup=success");
	    }
}
