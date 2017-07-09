package org.Lukashman.DB;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.Lukashman.Model.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

public class UserDAOImpl implements UserDAO {

	public JdbcTemplate jdbcTemp;
	
	public void setJdbcTemp(JdbcTemplate jdbcTemp) {
		this.jdbcTemp = jdbcTemp;
	}
	
	@Override
	public ArrayList<User> getAll() {
		List<User> Userlist = jdbcTemp.query("select * from user_table", new RowMapper<User>(){
			public User mapRow(ResultSet rs, int rowNum) throws SQLException{
				User user = new User();
				user.setId(rs.getInt("user_id"));
				user.setUsername(rs.getString("user_name"));
				user.setPassword(rs.getString("user_password"));
				user.setRole(rs.getString("user_role"));
				return user;
			}
		});
		return (ArrayList<User>) Userlist;
	}

	@Override
	public User getOne(String username) {
		User user = jdbcTemp.queryForObject(
				"select * from user_table where user_name = ?", User.class, username);
		return user;
	}

	@Override
	public void addOne(User us) {
		jdbcTemp.update(
				"insert into user_table (user_name,user_password,user_role) values (?, ?, ?)",us.getUsername(),us.getPassword(),us.getRole()); 
		
	}

	@Override
	public void UpdateOne(int id, User us) {
		jdbcTemp.update(
				"update user_table set user_name = ?, user_password = ? where user_id = ?",us.getUsername(),us.getPassword(),id);
		
	}

	@Override
	public void DeleteOne(int id) {
		jdbcTemp.update("delete from user_table where user_id = ?", id);
		
	}

}
