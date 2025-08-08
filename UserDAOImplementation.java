package com.food.daoimplementation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.food.dao.UserDAO;
import com.food.model.User;
import com.food.util.DBConnection;

public class UserDAOImplementation implements UserDAO{

	private static final String GETALLUSERSQUERY="select * from `user`";
	private static final String GETUSERQUERY="select * from `user` where `userid`=?";
	private static final String ADDUSERQUERY="insert into `user`(name,username,password,email,phonenumber,address,role,createddate,lastlogindate) values(?,?,?,?,?,?,?,?,?)";
	private static final String UPDATEUSERQUERY="update `user` set `name`=?,`username`=?,`password`=?,`email`=?,`phonenumber`=?,`address`=?,`role`=? where `userid`=?";
	private static final String DELETEUSERQUERY="delete  from `user` where `userid`=?";

	@Override
	public List<User> getAllUsers() {
		List<User> users=null;

		try(Connection connection = DBConnection.getConnection();
			PreparedStatement prepareStatement=connection.prepareStatement(GETALLUSERSQUERY);) {

			ResultSet resultSet=prepareStatement.executeQuery();

			while(resultSet.next())
			{
				String name=resultSet.getString("name");
				String username=resultSet.getString("username");
				String password=resultSet.getString("password");
				String email=resultSet.getString("email");
				String phonenumber=resultSet.getString("phonenumber");
				String address=resultSet.getString("address");
				String role=resultSet.getString("role");

				User user=new User(name,username,password,email,phonenumber,address,role);
				users.add(user);
			}

		} catch (SQLException e) {

			e.printStackTrace();
		}



		return users;
	}

	@Override
	public User getUser(int userId) {
		User user=null;

		try (Connection connection = DBConnection.getConnection();
			PreparedStatement prepareStatement = connection.prepareStatement(GETUSERQUERY)) {
		    prepareStatement.setInt(1, userId);
		    ResultSet resultSet = prepareStatement.executeQuery();

		    if (resultSet.next()) {
		        String name = resultSet.getString("name");
		        String username = resultSet.getString("username");
		        String password = resultSet.getString("password");
		        String email = resultSet.getString("email");
		        String phonenumber = resultSet.getString("phonenumber");
		        String address = resultSet.getString("address");
		        String role = resultSet.getString("role");

		        user = new User(name, username, password, email, phonenumber, address, role);
		        user.setUserId(userId);
		        user.setCreatedDate(resultSet.getTimestamp("createddate"));
		        user.setLastLoginDate(resultSet.getTimestamp("lastlogindate"));
		    }

		} catch (SQLException e) {
		    e.printStackTrace();
		}

		return user;
	}

	@Override
	public void addUser(User user) {

		try(Connection connection = DBConnection.getConnection();
			PreparedStatement prepareStatement=connection.prepareStatement(ADDUSERQUERY);) {

			prepareStatement.setString(1, user.getName());
			prepareStatement.setString(2, user.getUsername());
			prepareStatement.setString(3, user.getPassword());
			prepareStatement.setString(4, user.getEmail());
			prepareStatement.setString(5, user.getPhonenumber());
			prepareStatement.setString(6, user.getAddress());
			prepareStatement.setString(7, user.getRole());
			prepareStatement.setTimestamp(8,user.getCreatedDate());
			prepareStatement.setTimestamp(9,user.getLastLoginDate());

			int i=prepareStatement.executeUpdate();
			System.out.println(i);

		} catch (SQLException e) {

			e.printStackTrace();
		}



	}

	@Override
	public void updateUser(User user) {

		try(Connection connection = DBConnection.getConnection();
			PreparedStatement prepareStatement=connection.prepareStatement(UPDATEUSERQUERY);) {

			prepareStatement.setString(1, user.getName());
			prepareStatement.setString(2, user.getUsername());
			prepareStatement.setString(3, user.getPassword());
			prepareStatement.setString(4, user.getEmail());
			prepareStatement.setString(5, user.getPhonenumber());
			prepareStatement.setString(6, user.getAddress());
			prepareStatement.setString(7, user.getRole());
			prepareStatement.setInt(8, user.getUserId());

			int i=prepareStatement.executeUpdate();
			System.out.println(i);

		} catch (SQLException e) {

			e.printStackTrace();
		}

	}

	@Override
	public void deleteUser(int userId) {

		try(Connection connection = DBConnection.getConnection();
			PreparedStatement prepareStatement=connection.prepareStatement(DELETEUSERQUERY);) {

			prepareStatement.setInt(1, userId );


			int i=prepareStatement.executeUpdate();
			System.out.println(i);

		} catch (SQLException e) {

			e.printStackTrace();
		}

	}
	public User loginUser(String email, String password) {
	    User user = null;
	    String sql = "SELECT * FROM user WHERE (email = ? OR username = ?) AND password = ?";

	    try (Connection conn = DBConnection.getConnection();
	         PreparedStatement ps = conn.prepareStatement(sql)) {

	        ps.setString(1, email);
	        ps.setString(2, email);
	        ps.setString(3, password);

	        ResultSet rs = ps.executeQuery();

	        if (rs.next()) {
	            user = new User();
	            user.setUserId(rs.getInt("userid"));
	            user.setName(rs.getString("name"));
	            user.setUsername(rs.getString("username"));
	            user.setPassword(rs.getString("password"));
	            user.setEmail(rs.getString("email"));
	            user.setPhonenumber(rs.getString("phonenumber"));
	            user.setAddress(rs.getString("address"));
	            user.setRole(rs.getString("role"));
	            user.setCreatedDate(rs.getTimestamp("createddate"));
	            user.setLastLoginDate(rs.getTimestamp("lastlogindate"));
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return user;
	}


	public boolean checkUsernameExists(String username) {
		// TODO Auto-generated method stub
		 boolean exists = false;
		    String sql = "SELECT * FROM user WHERE username = ?";

		    try (Connection conn = DBConnection.getConnection();
		         PreparedStatement ps = conn.prepareStatement(sql)) {

		        ps.setString(1, username);
		        ResultSet rs = ps.executeQuery();
		        if (rs.next()) {
		            exists = true;
		        }
		    } catch (Exception e) {
		        e.printStackTrace();
		    }

		    return exists;

	}



}
