package com.food.daoimplementation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.food.dao.MenuDAO;
import com.food.model.Menu;
import com.food.util.DBConnection;


public class MenuDAOImplementation implements MenuDAO {

	private static final String GETALLMENSQUERY="select * from `menu`";
	private static final String GETMENUQUERY="select * from `menu` where `menuid`=?";
	private static final String ADDMENUQUERY="insert into `menu`(itemname,description,price,isavailable,ratings,imagepath)values(?,?,?,?,?,?)";
	private static final String UPDATEMENUQUERY="update `menu` set `itemname`=?,`description`=?,`price`=?,`isavailable`=?,`ratings`=?,`imagepath`=? where `menuid`=?";
	private static final String DELETEMENUQUERY="delete from `menu` where `menuid`=?";
	private static final String GETMENUSBYRESTAURANT = "SELECT * FROM menu WHERE restaurantid=?";
	@Override
	public List<Menu> getAllMenus() {
		List<Menu> menus=null;
		try(Connection connection = DBConnection.getConnection();
				PreparedStatement prepareStatement=connection.prepareStatement(GETALLMENSQUERY);) {

			ResultSet resultSet=prepareStatement.executeQuery();
			while(resultSet.next())
			{
				String itemName=resultSet.getString("itemname");
				String description=resultSet.getString("description");
				int price=resultSet.getInt("price");
				String isAvailable=resultSet.getString("isavailable");
				String ratings=resultSet.getString("ratings");
				String imagePath=resultSet.getString("imagepath");
				Menu menu=new Menu(itemName,description,price,isAvailable,ratings,imagePath);
				menus.add(menu);
			}


		} catch (SQLException e) {

			e.printStackTrace();
		}
		return menus;
	}

	@Override
	public Menu getMenu(int menuId) {
		Menu menu=null;

		try(Connection connection = DBConnection.getConnection();
				PreparedStatement prepareStatement=connection.prepareStatement(GETMENUQUERY);) {
			prepareStatement.setInt(1, menuId);
			ResultSet resultSet=prepareStatement.executeQuery();
			while(resultSet.next())
			{
				String itemName=resultSet.getString("itemname");
				String description=resultSet.getString("description");
				int price=resultSet.getInt("price");
				String isAvailable=resultSet.getString("isavailable");
				String ratings=resultSet.getString("ratings");
				String imagePath=resultSet.getString("imagepath");
				menu=new Menu(itemName,description,price,isAvailable,ratings,imagePath);

			}


		} catch (SQLException e) {

			e.printStackTrace();
		}
		return menu;
	}

	@Override
	public void addMenu(Menu menu) {

		try(Connection connection = DBConnection.getConnection();
				PreparedStatement prepareStatement=connection.prepareStatement(ADDMENUQUERY);) {

			prepareStatement.setString(1, menu.getItemName());
			prepareStatement.setString(2, menu.getDescription());
			prepareStatement.setInt(3, menu.getPrice());
			prepareStatement.setString(4, menu.getIsAvailable());
			prepareStatement.setString(5, menu.getRatings());
			prepareStatement.setString(6, menu.getImagePath());


			int i=prepareStatement.executeUpdate();
			System.out.println(i);

		} catch (SQLException e) {

			e.printStackTrace();
		}

	}

	@Override
	public void updateMenu(Menu menu) {

		try(Connection connection = DBConnection.getConnection();
				PreparedStatement prepareStatement=connection.prepareStatement(UPDATEMENUQUERY);) {

			prepareStatement.setString(1, menu.getItemName());
			prepareStatement.setString(2, menu.getDescription());
			prepareStatement.setInt(3, menu.getPrice());
			prepareStatement.setString(4, menu.getIsAvailable());
			prepareStatement.setString(5, menu.getRatings());
			prepareStatement.setString(6, menu.getImagePath());
			prepareStatement.setInt(7, menu.getMenuId());


			int i=prepareStatement.executeUpdate();
			System.out.println(i);

		} catch (SQLException e) {

			e.printStackTrace();
		}

	}

	@Override
	public void deleteMenu(int menuId) {

		try(Connection connection = DBConnection.getConnection();
				PreparedStatement prepareStatement=connection.prepareStatement(DELETEMENUQUERY);) {


			prepareStatement.setInt(1,menuId);


			int i=prepareStatement.executeUpdate();
			System.out.println(i);

		} catch (SQLException e) {

			e.printStackTrace();
		}

	}

	@Override
	public List<Menu> getMenusByRestaurantId(int restaurantId) {
	    List<Menu> menus = new ArrayList<>();

	    try (Connection  connection = DBConnection.getConnection();
	    		PreparedStatement ps = connection.prepareStatement(GETMENUSBYRESTAURANT)) {
	        ps.setInt(1, restaurantId);
	        ResultSet rs = ps.executeQuery();
	        while (rs.next()) {
	            Menu menu = new Menu();
	            menu.setMenuId(rs.getInt("menuid"));
	            menu.setRestaurantId(rs.getInt("restaurantid"));
	            menu.setItemName(rs.getString("itemname"));
	            menu.setDescription(rs.getString("description"));
	            menu.setPrice(rs.getInt("price"));
	            menu.setIsAvailable(rs.getString("isavailable"));
	            menu.setRatings(rs.getString("ratings"));
	            menu.setImagePath(rs.getString("imagepath"));
	            menus.add(menu);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return menus;
	}


}
