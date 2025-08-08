package com.food.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import com.food.daoimplementation.MenuDAOImplementation;
import com.food.model.Menu;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
public class MenuServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
    private MenuDAOImplementation menuDao;

    @Override
    public void init() throws ServletException {
        menuDao = new MenuDAOImplementation();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        List<Menu> menus = menuDao.getAllMenus();

        out.println("<!DOCTYPE html><html><head><title>Menu - FoodApp</title>");
        out.println("<link rel='stylesheet' href='menu.css'>");
        out.println("</head><body>");
        out.println("<header><h1>📋 Menu - Spice Villa</h1></header>");
        out.println("<main class='menu-grid'>");

        for (Menu menu : menus) {
            out.println("<div class='menu-card'>");
            out.println("<img src='" + menu.getImagePath() + "' alt='" + menu.getItemName() + "'>");
            out.println("<h3>" + menu.getItemName() + "</h3>");
            out.println("<p>" + menu.getDescription() + "</p>");
            out.println("<p class='price'>₹" + menu.getPrice() + "</p>");
            out.println("<form action='AddToCartServlet' method='post'>");
            out.println("<input type='hidden' name='name' value='" + menu.getItemName() + "'>");
            out.println("<input type='hidden' name='price' value='" + menu.getPrice() + "'>");
            out.println("<button type='submit'>Add to Cart</button>");
            out.println("</form>");
            out.println("</div>");
        }

        out.println("</main><footer><p>© 2025 FoodApp | All rights reserved</p></footer>");
        out.println("</body></html>");
    }
}
