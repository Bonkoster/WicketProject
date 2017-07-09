package org.Lukashman.DB;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.Lukashman.Model.Image;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

public class ImageDAOimpl implements ImageDAO {

	public JdbcTemplate jdbcTemp;
	
	public void setJdbcTemp(JdbcTemplate jdbcTemp) {
		this.jdbcTemp = jdbcTemp;
	}

	@Override
	public ArrayList<Image> getAll() {
		List<Image> images = jdbcTemp.query("select * from image_table", new RowMapper<Image>(){
			public Image mapRow(ResultSet rs, int rowNum) throws SQLException{
				Image image = new Image();
				image.setAuthor(rs.getString("image_author"));
				image.setLink(rs.getString("image_link"));
				image.setTitle(rs.getString("image_title"));
				image.setType(rs.getString("image_type"));
				image.setSub_date(rs.getDate("submission_date"));
				return image;
			}
				});
		return (ArrayList<Image>) images;
	}

	@Override
	public Image getOne(int id) {
		Image image = jdbcTemp.queryForObject(
				"select * from image_table where image_id = ?", Image.class, id);
		return image;
	}

	@Override
	public void addOne(Image im) {
		jdbcTemp.update(
				"insert into image_table (image_title,image_link,submission_date,image_author,image_type) values (?, ?, ?, ?, ?)", 
				im.getTitle(),im.getLink(),im.getSub_date(),im.getAuthor(),im.getType());
		
	}

	@Override
	public void UpdateOne(int id, Image im) {
		jdbcTemp.update(
				"update image_table set image_title = ?, image_link = ?,image_author = ?, image_type = ? where image_id = ?",
				im.getTitle(),im.getLink(),im.getAuthor(),im.getType(),id);
		
	}

	@Override
	public void DeleteOne(int id) {
		jdbcTemp.update("delete from image_table where image_id = ?", id);
		
	}

	@Override
	public long getCount() {
		long count = jdbcTemp.queryForObject("select count(*) from image_table", long.class);
		return count;
	}

	@Override
	public long getTypedCount(String type) {
		long count = jdbcTemp.queryForObject("select count(*) from image_table where image_type = ?", long.class, type);
		return count;
	}
}
	
	