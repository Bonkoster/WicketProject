package org.Lukashman.DB;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.Lukashman.Model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.SqlParameterValue;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

public class UserDAOImpl implements UserDAO, Serializable {
	
	private static final long serialVersionUID = -8456218232834938641L;
	
	@Autowired
	public JdbcTemplate jdbcTemp;
	public void setJdbcTemp(JdbcTemplate jdbcTemp) {
		this.jdbcTemp = jdbcTemp;
	}
	
	@Autowired
	public NamedParameterJdbcTemplate jdbcNamedTemplate;
	public void setJdbcNamedTemplate(NamedParameterJdbcTemplate jdbcNamedTemplate) {
		this.jdbcNamedTemplate = jdbcNamedTemplate;
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
		String check = "select count(*) from user_table where user_name = :username";
		String sql = "select * from user_table where user_name = :username";
		
		User user = null;
		
		SqlParameterSource namedParameter = new MapSqlParameterSource("username", String.valueOf(username));
		
		int count = jdbcNamedTemplate.queryForObject(check, namedParameter, Integer.class);
		if(count != 0){
		user = jdbcNamedTemplate.queryForObject(sql, namedParameter, new RowMapper<User>() {

			@Override
			public User mapRow(ResultSet rs, int rowNum) throws SQLException {
				User user = new User();
				user.setId(rs.getInt("user_id"));
				user.setUsername(rs.getString("user_name"));
				user.setPassword(rs.getString("user_password"));
				user.setRole(rs.getString("user_role"));
				return user;
			}
		});
		} else {
			user = new User();
		}
		return user;
	}

	@Override
	public void addOne(User us) {
		jdbcTemp.update(
				"insert into user_table (user_name,user_password,user_role) values (?, ?, ?)",us.getUsername(),us.getPassword(),"User"); 
		
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
