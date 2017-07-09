package org.Lukashman.DB;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.Lukashman.Model.Type;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

public class TypeDAOimpl implements TypeDAO {

	public JdbcTemplate jdbcTemp;
	
	public void setJdbcTemp(JdbcTemplate jdbcTemp) {
		this.jdbcTemp = jdbcTemp;
	}
	
	@Override
	public ArrayList<Type> getAll() {
		ArrayList<Type> typelist = (ArrayList<Type>) jdbcTemp.query("select * from type_table", new RowMapper<Type>(){

			@Override
			public Type mapRow(ResultSet rs, int rowNum) throws SQLException {
				Type type = new Type();
				type.setId(rs.getInt("type_id"));
				type.setType(rs.getString("type_name"));
				return type;
			}
			
		});
		return typelist;
	}

	@Override
	public Type getOne(int id) {
		Type type = jdbcTemp.queryForObject(
				"select * from type_table where type_id = ?", Type.class, id);
		return type;
	}

	@Override
	public void addOne(Type ty) {
		jdbcTemp.update(
				"insert into type_table (type_name) values (?)",ty.getType());
		
	}

	@Override
	public void UpdateOne(int id, Type ty) {
		jdbcTemp.update(
				"update type_table set type_name = ? where todo_id = ?",ty.getType(),id);
		
	}

	@Override
	public void DeleteOne(int id) {
		jdbcTemp.update("delete from type_table where type_id = ?", id);
		
	}

	@Override
	public long getCount() {
		long count = jdbcTemp.queryForObject("select count(*) from type_table", long.class);
		return count;
	}

	

}
