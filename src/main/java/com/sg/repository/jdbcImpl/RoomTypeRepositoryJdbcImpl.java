package com.sg.repository.jdbcImpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.sg.entity.RoomType;
import com.sg.repository.RoomTypeRepository;

@Repository
public class RoomTypeRepositoryJdbcImpl implements RoomTypeRepository{
	
	private static final String SQL_GET_ALL_ROOMTYPES = "SELECT * FROM room_type";
	
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public List<RoomType> getAll() {
		return jdbcTemplate.query(SQL_GET_ALL_ROOMTYPES, new RoomTypeMapper());
	}
	
	private static class RoomTypeMapper implements RowMapper<RoomType> {

		@Override
		public RoomType mapRow(ResultSet rs, int rowNum) throws SQLException {
			RoomType roomType = new RoomType();
			roomType.setId(rs.getInt("id"));
			roomType.setName(rs.getString("name"));
			return roomType;
		}

	}

}
