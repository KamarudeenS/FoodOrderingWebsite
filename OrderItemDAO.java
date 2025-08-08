package com.food.dao;

import java.util.List;

import com.food.model.OrderItem;

public interface OrderItemDAO {

	List<OrderItem> getAllOrderItems();
	OrderItem getOrderItem(int orderItemId);
	void addOrderItem(OrderItem orderItem);
	void updateOrderItem(OrderItem orderItem);
	void deleteOrderItem(int orderItemId);
}
