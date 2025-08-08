package com.food.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.food.model.CartItem;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/AddToCartServlet")
public class AddToCartServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String name = request.getParameter("name");
        int price = Integer.parseInt(request.getParameter("price"));
        int menuId = Integer.parseInt(request.getParameter("menuId"));
        int restaurantId = Integer.parseInt(request.getParameter("restaurantId"));

        HttpSession session = request.getSession();
        List<CartItem> cart = (List<CartItem>) session.getAttribute("cart");

        if (cart == null) {
            cart = new ArrayList<>();
        }

        boolean itemExists = false;
        for (CartItem item : cart) {
            if (item.getMenuId() == menuId) {
                item.setQuantity(item.getQuantity() + 1);
                itemExists = true;
                break;
            }
        }

        if (!itemExists) {
            CartItem newItem = new CartItem();
            newItem.setName(name);
            newItem.setPrice(price);
            newItem.setMenuId(menuId);
            newItem.setRestaurantId(restaurantId);
            newItem.setQuantity(1);
            cart.add(newItem);
        }

        session.setAttribute("cart", cart);
        response.sendRedirect("menu.html");
    }
}