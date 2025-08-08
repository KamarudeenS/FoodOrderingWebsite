package com.food.daoimplementation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.food.dao.RestaurantDAO;
import com.food.model.Restaurant;
import com.food.util.DBConnection;

public class RestaurantDAOImplementation implements RestaurantDAO{

	private static final String GETALLRESTAURANTSQUERY="select * from `restaurant`";
	private static final String GETRESTAURANTQUERY="select * from `restaurant` where `restaurantid`=?";
	private static final String ADDRESTAURANTQUERY="insert into `restaurant`(name,address,phonenumber,cuisinetype,deliverytime,adminuserid,rating,isactive,imagepath)values(?,?,?,?,?,?,?,?,?)";
	private static final String UPDATERESTAURANTQUERY="update `restaurant` set `name`=?,`address`=?,`phonenumber`=?,`cuisinetype`=?,`deliverytime`=?,`isactive`=?,`imagepath`=? where `restaurantid`=?";
	private static final String DELETERESTAURANTQUERY="delete from `restaurant` where `restaurantid`=?";

	@Override
	public List<Restaurant> getAllRestaurants() {
		List<Restaurant> restaurants=new ArrayList<>();
		try(Connection connection = DBConnection.getConnection();
			PreparedStatement prepareStatement=connection.prepareStatement(GETALLRESTAURANTSQUERY);) {

			ResultSet resultSet=prepareStatement.executeQuery();
			while(resultSet.next())
			{
				String name=resultSet.getString("name");
				String address=resultSet.getString("address");
				String phonenumber=resultSet.getString("phonenumber");
				String cuisineType=resultSet.getString("cuisinetype");
				Timestamp deliveryTime=resultSet.getTimestamp("deliverytime");
				int adminUserId=resultSet.getInt("adminuserid");
				String rating=resultSet.getString("rating");
				String isActive=resultSet.getString("isactive");
				String imagePath=resultSet.getString("imagepath");

				Restaurant restaurant=new Restaurant(name,address,phonenumber,cuisineType,deliveryTime,adminUserId,rating,isActive,imagePath);
				restaurants.add(restaurant);
			}


		} catch (SQLException e) {

			e.printStackTrace();
		}
		return restaurants;
	}

	@Override
	public Restaurant getRestaurant(int restaurantId) {
		Restaurant restaurant=null;
		try(Connection connection = DBConnection.getConnection();
				PreparedStatement prepareStatement=connection.prepareStatement(GETRESTAURANTQUERY);) {

			ResultSet resultSet=prepareStatement.executeQuery();
			while(resultSet.next())
			{
				String name=resultSet.getString("name");
				String address=resultSet.getString("address");
				String phonenumber=resultSet.getString("phonenumber");
				String cuisineType=resultSet.getString("cuisinetype");
				Timestamp deliveryTime=resultSet.getTimestamp("deliverytime");
				int adminUserId=resultSet.getInt("adminuserid");
				String rating=resultSet.getString("rating");
				String isActive=resultSet.getString("isactive");
				String imagePath=resultSet.getString("imagepath");

				restaurant=new Restaurant(name,address,phonenumber,cuisineType,deliveryTime,adminUserId,rating,isActive,imagePath);

			}


		} catch (SQLException e) {

			e.printStackTrace();
		}



		return restaurant;
	}

	@Override
	public void addRestaurant(Restaurant restaurant) {

		try(Connection connection = DBConnection.getConnection();
				PreparedStatement prepareStatement=connection.prepareStatement(ADDRESTAURANTQUERY);) {

			prepareStatement.setString(1, restaurant.getName());
			prepareStatement.setString(2, restaurant.getAddress());
			prepareStatement.setString(3, restaurant.getPhonenumber());
			prepareStatement.setString(4, restaurant.getCuisineType());
			prepareStatement.setTimestamp(5,new Timestamp(System.currentTimeMillis()));
			prepareStatement.setInt(6, restaurant.getAdminUserId());
			prepareStatement.setString(7, restaurant.getRating());
			prepareStatement.setString(8, restaurant.getIsActive());
			prepareStatement.setString(9, restaurant.getImagePath());


			int i=prepareStatement.executeUpdate();
			System.out.println(i);

		} catch (SQLException e) {

			e.printStackTrace();
		}


	}

	@Override
	public void updateRestaurant(Restaurant restaurant) {

		try(Connection connection = DBConnection.getConnection();
				PreparedStatement prepareStatement=connection.prepareStatement(UPDATERESTAURANTQUERY);) {

			prepareStatement.setString(1, restaurant.getName());
			prepareStatement.setString(2, restaurant.getAddress());
			prepareStatement.setString(3, restaurant.getPhonenumber());
			prepareStatement.setString(4, restaurant.getCuisineType());
			prepareStatement.setTimestamp(5,new Timestamp(System.currentTimeMillis()));
			prepareStatement.setString(6, restaurant.getRating());
			prepareStatement.setString(7, restaurant.getIsActive());
			prepareStatement.setString(8, restaurant.getImagePath());
			prepareStatement.setInt(9, restaurant.getRestaurantId());

			int i=prepareStatement.executeUpdate();
			System.out.println(i);

		} catch (SQLException e) {

			e.printStackTrace();
		}

	}

	@Override
	public void deleteRestaurant(int restaurantId) {

		try(Connection connection = DBConnection.getConnection();
				PreparedStatement prepareStatement=connection.prepareStatement(DELETERESTAURANTQUERY);) {


			prepareStatement.setInt(1, restaurantId);

			int i=prepareStatement.executeUpdate();
			System.out.println(i);

		} catch (SQLException e) {

			e.printStackTrace();
		}

	}

}
