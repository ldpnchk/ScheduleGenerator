package com.sg.repository.jdbcImpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.sg.entity.Daytime;
import com.sg.repository.DaytimeRepository;

@Repository
public class DaytimeRepositoryJdbcImpl implements DaytimeRepository{
	
	private static final String SQL_GET_ALL_DAYTIMES = "SELECT * FROM daytime";
	
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public List<Daytime> getAll() {
		return jdbcTemplate.query(SQL_GET_ALL_DAYTIMES, new DaytimeMapper());
	}
	
	private static class DaytimeMapper implements RowMapper<Daytime> {

		@Override
		public Daytime mapRow(ResultSet rs, int rowNum) throws SQLException {
			Daytime daytime = new Daytime();
			daytime.setId(rs.getInt("id"));
			daytime.setName(rs.getString("name"));
			return daytime;
		}

	}

}
