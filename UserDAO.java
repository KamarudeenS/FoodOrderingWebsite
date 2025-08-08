package com.food.dao;

import java.util.List;

import com.food.model.User;

public interface UserDAO {
	List<User> getAllUsers();
	User getUser(int userId);
	void addUser(User user);
	void updateUser(User user);
	void deleteUser(int userId);
}
