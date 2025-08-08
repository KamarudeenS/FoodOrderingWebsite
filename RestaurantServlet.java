package com.food.servlet;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import com.food.daoimplementation.RestaurantDAOImplementation;
import com.food.model.Restaurant;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
public class RestaurantServlet extends HttpServlet {
	private RestaurantDAOImplementation restaurantDAO;

    @Override
    public void init() throws ServletException {
        restaurantDAO = new RestaurantDAOImplementation();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        List<Restaurant> restaurants = restaurantDAO.getAllRestaurants();

        out.println("<html><head><title>Restaurants</title>");
        out.println("<style>");
        out.println(".card { border: 1px solid gray; padding: 10px; margin: 10px; display: inline-block; width: 250px; background: #f4f4f4; }");
        out.println(".card img { width: 100%; height: 150px; }");
        out.println("</style></head><body>");
        out.println("<h1>🍽️ Available Restaurants</h1>");

        for (Restaurant r : restaurants) {
            out.println("<div class='card'>");
            out.println("<img src='" + r.getImagePath() + "' alt='Image of " + r.getName() + "'>");
            out.println("<h3>" + r.getName() + "</h3>");
            out.println("<p>Cuisine: " + r.getCuisineType() + "</p>");
            out.println("<p>📞 " + r.getPhonenumber() + "</p>");
            out.println("<p>Rating: " + r.getRating() + "</p>");
            out.println("</div>");
        }

        out.println("</body></html>");
    }
}
