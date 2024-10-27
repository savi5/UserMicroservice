package dao;

import java.sql.Connection;

import model.UserBean;

import java.sql.*;

public class UserDao {
	
	public int registerUser(UserBean user) {
		
		if(userExist(user)) return 0;
		String ins_query = "INSERT INTO user (first_name,last_name,username,password) VALUES (?,?,?,?)";
		try {
		Class.forName("com.mysql.jdbc.Driver");
			Connection connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/savitha","root","root@123");
			PreparedStatement ps= connection.prepareStatement(ins_query);
			ps.setString(1,user.getFirst_name());
			ps.setString(2,user.getLast_name());
			ps.setString(3, user.getUsername());
			ps.setString(4, user.getPassword());
			
			System.out.println("Query :"+ ps);
			int result = ps.executeUpdate();
					
			return result;
		}
		catch (Exception e) {
			System.out.println(e.getMessage() + e.getStackTrace());
			return 0;
		}
	}
	
	public UserBean getUserDetails(UserBean user) {
		String query = "SELECT * FROM user where username = ? AND password = ?";
		try {
			Class.forName("com.mysql.jdbc.Driver");
				Connection connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/savitha","root","root@123");
				PreparedStatement ps= connection.prepareStatement(query);

				ps.setString(1, user.getUsername());
				ps.setString(2, user.getPassword());
				try {
					ResultSet resultSet = ps.executeQuery();
	
					while (resultSet.next()) {
							user.setFirst_name(resultSet.getString(1));
							user.setLast_name(resultSet.getString(2));
					}		

				}catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
						
					}
		}catch (Exception e) {
			System.out.println(e.getMessage() + e.getStackTrace());
			return user;
		}
		return user;

	}
	
	
	public boolean userExist(UserBean user) {
		String query = "SELECT * FROM user where username = ?";
		try {
			Class.forName("com.mysql.jdbc.Driver");
				Connection connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/savitha","root","root@123");
				PreparedStatement ps= connection.prepareStatement(query);

				ps.setString(1, user.getUsername());
			
				try {
					ResultSet resultSet = ps.executeQuery();
	
					resultSet.next();
					
					return resultSet.first();		

				}catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
						
					}
		}catch (Exception e) {
			System.out.println(e.getMessage() + e.getStackTrace());
			return false;
		}
	 return false;
	}
}
