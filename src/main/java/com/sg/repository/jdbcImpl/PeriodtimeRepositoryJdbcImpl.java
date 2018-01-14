package com.sg.repository.jdbcImpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.sg.entity.Periodtime;
import com.sg.repository.PeriodtimeRepository;

@Repository
public class PeriodtimeRepositoryJdbcImpl implements PeriodtimeRepository{
	
	private static final String SQL_GET_ALL_PERIODTIMES = "SELECT * FROM periodtime";
	
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public List<Periodtime> getAll() {
		return jdbcTemplate.query(SQL_GET_ALL_PERIODTIMES, new PeriodtimeMapper());
	}
	
	private static class PeriodtimeMapper implements RowMapper<Periodtime> {

		@Override
		public Periodtime mapRow(ResultSet rs, int rowNum) throws SQLException {
			Periodtime periodtime = new Periodtime();
			periodtime.setId(rs.getInt("id"));
			periodtime.setNumber(rs.getInt("number"));
			return periodtime;
		}

	}

}