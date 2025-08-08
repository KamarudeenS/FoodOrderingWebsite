package com.food.model;

import java.sql.Timestamp;

public class Restaurant {



	private int restaurantId;
	private String name;
	private String address;
	private String phonenumber;
	private String cuisineType;
	private Timestamp DeliveryTime;
	private int adminUserId;
	private String rating;
	private String isActive;
	private String imagePath;

	public Restaurant() {

	}

	public Restaurant(int restaurantId, String name, String address, String phonenumber, String cuisineType,
			Timestamp deliveryTime, int adminUserId, String rating, String isActive, String imagePath) {
		super();
		this.restaurantId = restaurantId;
		this.name = name;
		this.address = address;
		this.phonenumber = phonenumber;
		this.cuisineType = cuisineType;
		DeliveryTime = deliveryTime;
		this.adminUserId = adminUserId;
		this.rating = rating;
		this.isActive = isActive;
		this.imagePath = imagePath;
	}

	public Restaurant(String name, String address, String phonenumber, String cuisineType, Timestamp deliveryTime,
			int adminUserId, String rating, String isActive, String imagePath) {
		super();
		this.name = name;
		this.address = address;
		this.phonenumber = phonenumber;
		this.cuisineType = cuisineType;
		DeliveryTime = deliveryTime;
		this.adminUserId = adminUserId;
		this.rating = rating;
		this.isActive = isActive;
		this.imagePath = imagePath;
	}

	public int getRestaurantId() {
		return restaurantId;
	}

	public void setRestaurantId(int restaurantId) {
		this.restaurantId = restaurantId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhonenumber() {
		return phonenumber;
	}

	public void setPhonenumber(String phonenumber) {
		this.phonenumber = phonenumber;
	}

	public String getCuisineType() {
		return cuisineType;
	}

	public void setCuisineType(String cuisineType) {
		this.cuisineType = cuisineType;
	}

	public Timestamp getDeliveryTime() {
		return DeliveryTime;
	}

	public void setDeliveryTime(Timestamp deliveryTime) {
		DeliveryTime = deliveryTime;
	}

	public int getAdminUserId() {
		return adminUserId;
	}

	public void setAdminUserId(int adminUserId) {
		this.adminUserId = adminUserId;
	}

	public String getRating() {
		return rating;
	}

	public void setRating(String rating) {
		this.rating = rating;
	}

	public String getIsActive() {
		return isActive;
	}

	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}


}
