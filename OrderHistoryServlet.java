package com.food.servlet;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import com.food.dao.OrderDAO;
import com.food.daoimplementation.OrderDAOImplementation;
import com.food.model.Order;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/orderHistory")
public class OrderHistoryServlet  extends HttpServlet{

	private static final long serialVersionUID = 1L;
    private final OrderDAO orderDAO = new OrderDAOImplementation();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        Integer userId = (Integer) session.getAttribute("userId");

        if (userId == null) {
            response.sendRedirect("login.html");
            return;
        }

        List<Order> orders = orderDAO.getOrdersByUserId(userId);

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        out.println("<html><head><title>My Orders</title></head><body>");
        out.println("<h2>🧾 My Order History</h2>");

        if (orders.isEmpty()) {
            out.println("<p>No orders placed yet.</p>");
        } else {
            out.println("<table border='1'>");
            out.println("<tr><th>Order ID</th><th>Date</th><th>Total</th><th>Status</th></tr>");
            for (Order order : orders) {
                out.println("<tr>");
                out.println("<td>" + order.getOrderId() + "</td>");
                out.println("<td>" + order.getOrderDate() + "</td>");
                out.println("<td>₹" + order.getTotalAmmount() + "</td>");
                out.println("<td>" + order.getStatus() + "</td>");
                out.println("</tr>");
            }
            out.println("</table>");
        }

        out.println("<a href='menu'>🍔 Back to Menu</a>");
        out.println("</body></html>");
    }
}
