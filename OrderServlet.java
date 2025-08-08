package com.food.servlet;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;

import com.food.daoimplementation.OrderDAOImplementation;
import com.food.daoimplementation.OrderItemDAOImplementation;
import com.food.model.CartItem;
import com.food.model.Order;
import com.food.model.OrderItem;
import com.food.model.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class OrderServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private OrderDAOImplementation orderDAO;
    private OrderItemDAOImplementation orderItemDAO;

    @Override
    public void init() throws ServletException {
        orderDAO = new OrderDAOImplementation();
        orderItemDAO = new OrderItemDAOImplementation();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("user") == null) {
            response.sendRedirect("signin.html");
            return;
        }

        // Get user and cart
        User user = (User) session.getAttribute("user");
        List<CartItem> cart = (List<CartItem>) session.getAttribute("cart");

        if (cart == null || cart.isEmpty()) {
            response.sendRedirect("menu.html");
            return;
        }

        // Calculate total amount
        int totalAmount = 0;
        for (CartItem item : cart) {
            totalAmount += item.getTotalAmount();  // quantity * price (if calculated earlier)
        }

        // Prepare order
        Order order = new Order();
        order.setUserId(user.getUserId());
        order.setRestaurantId(1); // You can make this dynamic later
        order.setOrderDate(new Timestamp(System.currentTimeMillis()));
        order.setTotalAmmount(totalAmount);
        order.setStatus("Pending");
        order.setPaymentMode("Cash on Delivery");

        // Insert order into DB
        orderDAO.addOrder(order);

        // Get last inserted order ID
        int orderId = orderDAO.getLastInsertedOrderId();

        // Save each order item
        for (CartItem item : cart) {
            OrderItem orderItem = new OrderItem();
            orderItem.setOrderId(orderId);
            orderItem.setMenuId(item.getMenuId()); // make sure CartItem has menuId
            orderItem.setQuantity(item.getQuantity());
            orderItem.setTotalAmmount(item.getTotalAmount());
            orderItemDAO.addOrderItem(orderItem);
        }

        // Clear the cart
        session.removeAttribute("cart");

        // Redirect to confirmation page
        response.sendRedirect("orderconfirmation.html");
    }
}
