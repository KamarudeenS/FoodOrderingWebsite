package com.food.dao;

import java.util.List;

import com.food.model.Order;

public interface OrderDAO {
	List<Order> getAllOrders();
	Order getOrder(int orderId);
	int addOrder(Order order);
	void updateOrder(Order order);
	void deleteOrder(int orderId);
	int getLastInsertedOrderId();
	List<Order> getOrdersByUserId(int userId);
}
