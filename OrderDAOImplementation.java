package com.food.daoimplementation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.food.dao.OrderDAO;
import com.food.model.Order;
import com.food.util.DBConnection;

public class OrderDAOImplementation implements OrderDAO{
	private static final String GETALLORDERSQUERY="select * from `order`";
	private static final String GETORDERQUERY="select * from `order` where `orderid`=?";
	private static final String ADDORDERQUERY="insert into `order`(orderdate,totalammount,status,paymentmode)values(?,?,?,?)";
	private static final String UPDATEORDERQUERY="update `order` set `status`=? where `orderid`=?";
	private static final String DELETEORDERQUERY="delete from `order` where `orderid`=?";

	@Override
	public int getLastInsertedOrderId() {

		int orderId = 0;

	    try (Connection  connection = DBConnection.getConnection();
	    		PreparedStatement ps = connection.prepareStatement("SELECT MAX(orderid) FROM `order`")) {
	        ResultSet rs = ps.executeQuery();
	        if (rs.next()) {
	            orderId = rs.getInt(1);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return orderId;
	}

	@Override
	public List<Order> getAllOrders() {
		List<Order> orders=null;
		try(Connection connection = DBConnection.getConnection();
				PreparedStatement prepareStatement=connection.prepareStatement(GETALLORDERSQUERY);) {

			ResultSet resultSet=prepareStatement.executeQuery();
			while(resultSet.next())
			{
				int orderId=resultSet.getInt("orderid");
				int restaurantId=resultSet.getInt("restaurantid");
				int userId=resultSet.getInt("userid");
				Timestamp orderDate=resultSet.getTimestamp("orderdate");
				int totalAmmount=resultSet.getInt("totalammount");
				String status=resultSet.getString("status");
				String paymentMode=resultSet.getString("paymentmode");

				Order order=new Order(orderId,restaurantId,userId,orderDate,totalAmmount,status,paymentMode);
				orders.add(order);
			}


		} catch (SQLException e) {

			e.printStackTrace();
		}
		return orders;
	}

	@Override
	public Order getOrder(int orderId) {

		Order order=null;
		try(Connection connection = DBConnection.getConnection();
				PreparedStatement prepareStatement=connection.prepareStatement(GETORDERQUERY);) {

			ResultSet resultSet=prepareStatement.executeQuery();
			while(resultSet.next())
			{

				int restaurantId=resultSet.getInt("restaurantid");
				int userId=resultSet.getInt("userid");
				Timestamp orderDate=resultSet.getTimestamp("orderdate");
				int totalAmmount=resultSet.getInt("totalammount");
				String status=resultSet.getString("status");
				String paymentMode=resultSet.getString("paymentmode");

				order=new Order(orderId,restaurantId,userId,orderDate,totalAmmount,status,paymentMode);

			}


		} catch (SQLException e) {

			e.printStackTrace();
		}
		return order;
  }

	@Override
	public int addOrder(Order order) {
	    int orderId = 0;
	    try (Connection connection = DBConnection.getConnection();
	         PreparedStatement ps = connection.prepareStatement(
	                 "INSERT INTO `order` (restaurantid, userid, orderdate, totalammount, status, paymentmode) VALUES (?, ?, ?, ?, ?, ?)",
	                 Statement.RETURN_GENERATED_KEYS)) {

	        ps.setInt(1, order.getRestaurantId());
	        ps.setInt(2, order.getUserId());
	        ps.setTimestamp(3, order.getOrderDate());
	        ps.setInt(4, order.getTotalAmmount());
	        ps.setString(5, order.getStatus());
	        ps.setString(6, order.getPaymentMode());

	        ps.executeUpdate();
	        ResultSet rs = ps.getGeneratedKeys();
	        if (rs.next()) {
	            orderId = rs.getInt(1);
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return orderId;
	}


	@Override
	public void updateOrder(Order order) {

		try(Connection connection = DBConnection.getConnection();
				PreparedStatement prepareStatement=connection.prepareStatement(UPDATEORDERQUERY);) {



			prepareStatement.setString(1, order.getStatus());
			prepareStatement.setInt(2, order.getOrderId());


			int i=prepareStatement.executeUpdate();
			System.out.println(i);

		} catch (SQLException e) {

			e.printStackTrace();
		}

	}

	@Override
	public void deleteOrder(int orderId) {

		try(Connection connection = DBConnection.getConnection();
				PreparedStatement prepareStatement=connection.prepareStatement(DELETEORDERQUERY);) {




			prepareStatement.setInt(1,orderId);


			int i=prepareStatement.executeUpdate();
			System.out.println(i);

		} catch (SQLException e) {

			e.printStackTrace();
		}

	}
	@Override
	public List<Order> getOrdersByUserId(int userId) {
	    List<Order> orders = new ArrayList<>();
	    String sql = "SELECT * FROM `order` WHERE userid = ? ORDER BY orderdate DESC";

	    try (Connection connection = DBConnection.getConnection();
	         PreparedStatement ps = connection.prepareStatement(sql)) {
	        ps.setInt(1, userId);
	        ResultSet rs = ps.executeQuery();

	        while (rs.next()) {
	            int orderId = rs.getInt("orderid");
	            int restaurantId = rs.getInt("restaurantid");
	            Timestamp orderDate = rs.getTimestamp("orderdate");
	            int totalAmount = rs.getInt("totalammount");
	            String status = rs.getString("status");
	            String paymentMode = rs.getString("paymentmode");

	            Order order = new Order(orderId, restaurantId, userId, orderDate, totalAmount, status, paymentMode);
	            orders.add(order);
	        }

	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    return orders;
	}



}
