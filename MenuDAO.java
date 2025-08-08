package com.food.dao;

import java.util.List;

import com.food.model.Menu;


public interface MenuDAO {
	List<Menu> getAllMenus();
	Menu getMenu(int menuId);
	void addMenu(Menu menu);
	void updateMenu(Menu menu);
	void deleteMenu(int menuId);
	public List<Menu> getMenusByRestaurantId(int restaurantId);

}
