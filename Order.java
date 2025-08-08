package com.food.model;

import java.sql.Timestamp;

public class Order {



	private int orderId;
	private int restaurantId;
	private int userId;
	private Timestamp orderDate;
	private int totalAmmount;
	private String status;
	private String paymentMode;

	public Order() {

	}

	public Order(int orderId, int restaurantId, int userId, Timestamp orderDate, int totalAmmount, String status,
			String paymentMode) {
		super();
		this.orderId = orderId;
		this.restaurantId = restaurantId;
		this.userId = userId;
		this.orderDate = orderDate;
		this.totalAmmount = totalAmmount;
		this.status = status;
		this.paymentMode = paymentMode;
	}
	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public int getRestaurantId() {
		return restaurantId;
	}

	public void setRestaurantId(int restaurantId) {
		this.restaurantId = restaurantId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public Timestamp getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Timestamp orderDate) {
		this.orderDate = orderDate;
	}

	public int getTotalAmmount() {
		return totalAmmount;
	}

	public void setTotalAmmount(int totalAmmount) {
		this.totalAmmount = totalAmmount;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getPaymentMode() {
		return paymentMode;
	}

	public void setPaymentMode(String paymentMode) {
		this.paymentMode = paymentMode;
	}

}
