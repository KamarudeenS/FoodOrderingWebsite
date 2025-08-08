package com.food.dao;

import java.util.List;

import com.food.model.Restaurant;

public interface RestaurantDAO {
	List<Restaurant> getAllRestaurants();
	Restaurant getRestaurant(int restaurantId);
	void addRestaurant(Restaurant restaurant);
	void updateRestaurant(Restaurant restaurant);
	void deleteRestaurant(int restaurantId);
}
