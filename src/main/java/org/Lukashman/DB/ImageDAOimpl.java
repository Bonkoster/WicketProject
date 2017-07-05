package org.Lukashman.DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import org.Lukashman.Model.Image;

public class ImageDAOimpl implements ImageDAO {

	static Connection conn = null;
	static PreparedStatement pstat = null;
	static ResultSet rs = null;
		
	final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
    final String DB_URL = "jdbc:mysql://localhost/wicketDB";
	
	final String USERNAME = "root";
	final String PASSWORD = "root";

	
	
	@Override
	public ArrayList<Image> getAll() {
		
		ArrayList<Image> images = new ArrayList<>();
		Image img = new Image();
		
		try {
			Class.forName(JDBC_DRIVER);
			
			conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
			
			pstat = conn.prepareStatement("Select * from ?");
			pstat.setString(1, "image_table");
			
			rs = pstat.executeQuery();
			
			while(rs.next()){
				img.setId(rs.getInt("image_id"));
				img.setTitle(rs.getString("image_title"));
				img.setAuthor(rs.getString("image_author"));
				img.setLink(rs.getString("image_link"));
				img.setSub_date(rs.getDate("submission_date"));
				images.add(img);
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
		}	return images;		
	}

	@Override
	public Image getOne(int id) {
		Image img = null;
		
		try {
			Class.forName(JDBC_DRIVER);
			
			conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
			
			pstat = conn.prepareStatement("Select * from ? where image_id = ? ");
			pstat.setString(1, "image_table");
			pstat.setInt(2, id);
			
			rs = pstat.executeQuery();
			
			while(rs.next()){
				img = new Image(rs.getInt("image_id"), rs.getString("image_title"), rs.getString("image_author"), rs.getString("image_link"));
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
		}	return img;		
	}

	@Override
	public void addOne(Image im) {	
		try {
			Class.forName(JDBC_DRIVER);			
			conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
			
			pstat = conn.prepareStatement("insert into image_table values(?,?,?,?)");
			
			java.sql.Date dat = new java.sql.Date(new Date().getTime());
			
			pstat.setString(1, im.getTitle());
			pstat.setString(2, im.getLink());
			pstat.setDate(3, dat);
			pstat.setString(4, im.getAuthor());
			
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
	public void UpdateOne(int id,Image im) {
		try {
			Class.forName(JDBC_DRIVER);			
			conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
			
			pstat = conn.prepareStatement("update image_table set image_title = ?,image_link = ?, image_author = ? where image_id = ?");
			
			java.sql.Date dat = new java.sql.Date(new Date().getTime());
			
			pstat.setString(1, im.getTitle());
			pstat.setString(2, im.getLink());
			pstat.setString(3, im.getAuthor());
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
			
			pstat = conn.prepareStatement("delete from image_table where image_id = ?");
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
				
			PreparedStatement pstat = conn.prepareStatement("select count(*) from image_table");
			
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

	@Override
	public long getTypedCount(String type) {
		// TODO Auto-generated method stub
		return 0;
	}	

}
