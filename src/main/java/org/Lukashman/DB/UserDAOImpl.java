package org.Lukashman.DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import org.Lukashman.Model.User;

public class UserDAOImpl implements UserDAO {

	static Connection conn = null;
	static PreparedStatement pstat = null;
	static ResultSet rs = null;
		
	final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
    final String DB_URL = "jdbc:mysql://localhost/wicketDB";
	
	final String USERNAME = "root";
	final String PASSWORD = "root";
	
	@Override
	public ArrayList<User> getAll() {
		ArrayList<User> users = new ArrayList<>();
		User user = new User();
		
		try {
			Class.forName(JDBC_DRIVER);
			
			conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
			
			pstat = conn.prepareStatement("Select * from ?");
			pstat.setString(1, "user_table");
			
			rs = pstat.executeQuery();
			
			while(rs.next()){
				user.setId(rs.getInt("user_id"));
				user.setUsername(rs.getString("user_name"));
				user.setPassword(rs.getString("user_password"));
				user.setRole(rs.getString("image_link"));
				users.add(user);
			}				
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				pstat.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}	return users;		
	}

	@Override
	public User getOne(int id) {
	User user = null;
		
		try {
			Class.forName(JDBC_DRIVER);
			
			conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
			
			pstat = conn.prepareStatement("Select * from ? where user_id = ? ");
			pstat.setString(1, "user_table");
			pstat.setInt(2, id);
			
			rs = pstat.executeQuery();
			
			while(rs.next()){
				user = new User(rs.getInt("user_id"), rs.getString("user_name"), rs.getString("user_password"), rs.getString("user_role"));
			}				
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				pstat.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}	return user;		
	}

	@Override
	public void addOne(User us) {
		try {
			Class.forName(JDBC_DRIVER);			
			conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
			
			pstat = conn.prepareStatement("insert into user_table values(?,?,?)");
			
			java.sql.Date dat = new java.sql.Date(new Date().getTime());
			
			pstat.setString(1, us.getUsername());
			pstat.setString(2, us.getPassword());
			pstat.setString(3, us.getRole());
			
			pstat.executeQuery();
						
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				pstat.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}		
		
	}

	@Override
	public void UpdateOne(int id, User us) {
		try {
			Class.forName(JDBC_DRIVER);			
			conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
			
			pstat = conn.prepareStatement("update user_table set user_name = ?,user_password = ?, user_role = ? where image_id = ?");
			
			pstat.setString(1, us.getUsername());
			pstat.setString(2, us.getPassword());
			pstat.setString(3, us.getRole());
			pstat.setInt(4, id);
			
			pstat.executeQuery();
						
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				pstat.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}		
		
	}

	@Override
	public void DeleteOne(int id) {
		try {
			Class.forName(JDBC_DRIVER);			
			conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
			
			pstat = conn.prepareStatement("delete from user_table where user_id = ?");
			pstat.setInt(1, id);
						
			pstat.executeQuery();
						
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				pstat.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}		
		
		
	}

}
