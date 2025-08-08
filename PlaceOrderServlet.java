package com.food.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;

import com.food.dao.OrderDAO;
import com.food.dao.OrderItemDAO;
import com.food.daoimplementation.OrderDAOImplementation;
import com.food.daoimplementation.OrderItemDAOImplementation;
import com.food.model.CartItem;
import com.food.model.Order;
import com.food.model.OrderItem;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/placeOrder")
public class PlaceOrderServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private final OrderDAO orderDAO = new OrderDAOImplementation();
    private final OrderItemDAO orderItemDAO = new OrderItemDAOImplementation();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        List<CartItem> cart = (List<CartItem>) session.getAttribute("cart");
        Integer userId = (Integer) session.getAttribute("userId");

        // Handle null or empty cart or not logged in
        if (cart == null || cart.isEmpty() || userId == null) {
            response.sendRedirect("cart");
            return;
        }

        int totalAmount = 0;
        for (CartItem item : cart) {
            totalAmount += item.getPrice() * item.getQuantity();
        }

        // Build Order
        Order order = new Order();
        order.setUserId(userId);
        order.setRestaurantId(cart.get(0).getRestaurantId()); // assuming one restaurant
        order.setOrderDate(Timestamp.from(Instant.now()));
        order.setTotalAmmount(totalAmount);
        order.setStatus("PLACED");
        order.setPaymentMode("COD");

        // Add order and get generated order ID
        int orderId = orderDAO.addOrder(order);

        // Add each order item
        for (CartItem item : cart) {
            OrderItem orderItem = new OrderItem();
            orderItem.setOrderId(orderId);
            orderItem.setMenuId(item.getMenuId());
            orderItem.setQuantity(item.getQuantity());
            orderItem.setTotalAmmount(item.getPrice() * item.getQuantity());

            orderItemDAO.addOrderItem(orderItem);
        }

        // Clear the cart from session
        session.removeAttribute("cart");

        // Show confirmation
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<html><head><title>Order Confirmation</title></head><body>");
        out.println("<h2>✅ Order placed successfully!</h2>");
        out.println("<p>🧾 Order ID: " + orderId + "</p>");
        out.println("<p>💰 Total Amount: ₹" + totalAmount + "</p>");
        out.println("<a href='menu'>🍽️ Back to Menu</a>");
        out.println("</body></html>");
    }
}
