package com.food.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import com.food.model.CartItem;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class CartServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        List<CartItem> cart = (List<CartItem>) session.getAttribute("cart");

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        out.println("<!DOCTYPE html>");
        out.println("<html lang='en'>");
        out.println("<head>");
        out.println("<meta charset='UTF-8'>");
        out.println("<title>Your Cart - SAF Food Shop</title>");
        out.println("<link rel='stylesheet' href='cart.css'>");
        out.println("</head>");
        out.println("<body>");

        out.println("<div class='logo-header'><h1>🛒 SAF Food Shop - Cart</h1></div>");
        out.println("<main class='cart-page'>");

        if (cart == null || cart.isEmpty()) {
            out.println("<p class='empty-cart'>🛍️ Your cart is empty!</p>");
        } else {
            int total = 0;
            out.println("<div class='cart-items'>");

            for (CartItem item : cart) {
                int itemTotal = item.getPrice() * item.getQuantity();
                total += itemTotal;

                out.println("<div class='cart-item'>");
                out.println("<span class='item-name'>" + item.getName() + "</span>");
                out.println("<span class='item-qty'> × " + item.getQuantity() + "</span>");
                out.println("<span class='item-total'> ₹" + itemTotal + "</span>");
                out.println("</div>");
            }

            out.println("</div>");
            out.println("<div class='cart-total'><strong>Total: ₹" + total + "</strong></div>");

            out.println("<form action='checkout.html' method='get' class='checkout-form'>");
            out.println("<button type='submit'>Proceed to Checkout</button>");
            out.println("</form>");
        }

        out.println("</main>");
        out.println("<footer class='footer'>");
        out.println("<p>© 2025 SAF Food Shop | All rights reserved</p>");
        out.println("</footer>");

        out.println("</body>");
        out.println("</html>");
    }
}
