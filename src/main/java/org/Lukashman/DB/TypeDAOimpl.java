package org.Lukashman.DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import org.Lukashman.Model.Image;
import org.Lukashman.Model.Type;

public class TypeDAOimpl implements TypeDAO {

	static Connection conn = null;
	static PreparedStatement pstat = null;
	static ResultSet rs = null;
		
	final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
    final String DB_URL = "jdbc:mysql://localhost/wicketDB";
	
	final String USERNAME = "root";
	final String PASSWORD = "root";

	
	
	@Override
	public ArrayList<Type> getAll() {
		
		ArrayList<Type> types = new ArrayList<>();
		Type ty = new Type();
		
		try {
			Class.forName(JDBC_DRIVER);
			
			conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
			
			pstat = conn.prepareStatement("Select * from ?");
			pstat.setString(1, "type_table");
			
			rs = pstat.executeQuery();
			
			while(rs.next()){
				ty.setId(rs.getInt("type_id"));
				ty.setType(rs.getString("type_name"));
				types.add(ty);
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
		}	return types;		
	}

	@Override
	public Type getOne(int id) {
		Type ty = null;
		
		try {
			Class.forName(JDBC_DRIVER);
			
			conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
			
			pstat = conn.prepareStatement("Select * from ? where type_id = ? ");
			pstat.setString(1, "type_table");
			pstat.setInt(2, id);
			
			rs = pstat.executeQuery();
			
			while(rs.next()){
				ty = new Type(rs.getInt("type_id"),rs.getString("type_name"));
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
		}	return ty;		
	}

	@Override
	public void addOne(Type im) {	
		try {
			Class.forName(JDBC_DRIVER);			
			conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
			
			pstat = conn.prepareStatement("insert into type_table values(?)");
					
			pstat.setString(1, im.getType());
			
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
	public void UpdateOne(int id,Type ty) {
		try {
			Class.forName(JDBC_DRIVER);			
			conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
			
			pstat = conn.prepareStatement("update type_table set type_name = ? where type_id = ?");
			
			pstat.setString(4, ty.getType());
			pstat.setInt(5, id);
			
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
			
			pstat = conn.prepareStatement("delete from type_table where type_id = ?");
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

	@Override
	public long getCount() {
		long count = 0;
		try {
			Class.forName(JDBC_DRIVER);			
			conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
				
			PreparedStatement pstat = conn.prepareStatement("select count(*) from type_table");
			
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
		}	return count;
	}

}
