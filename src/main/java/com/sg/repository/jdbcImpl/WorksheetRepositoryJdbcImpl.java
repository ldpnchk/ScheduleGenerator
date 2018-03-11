package com.sg.repository.jdbcImpl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.sg.entity.Worksheet;
import com.sg.repository.WorksheetRepository;

@Repository
public class WorksheetRepositoryJdbcImpl implements WorksheetRepository{
	
	private static final String SQL_ADD_WORKSHEET = "INSERT INTO worksheet (name, userId) VALUES(?, ?);";
	private static final String SQL_UPDATE_WORKSHEET = "UPDATE worksheet SET name=? WHERE id=?;";
	private static final String SQL_DELETE_WORKSHEET = "DELETE FROM worksheet WHERE id=?;";
	private static final String SQL_GET_WORKSHEET_BY_ID = "SELECT * FROM worksheet WHERE id=?;";
	private static final String SQL_GET_BY_USER = "SELECT * FROM worksheet WHERE userId=?;";
	
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public void add(final Worksheet worksheet) {
		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(new PreparedStatementCreator() {
			
			@Override 
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				PreparedStatement ps = con.prepareStatement(SQL_ADD_WORKSHEET, new String[] { "id" });
				ps.setString(1, worksheet.getName());
				ps.setInt(2, worksheet.getUserId());
				return ps;
			}
			
		}, keyHolder);	
		worksheet.setId(keyHolder.getKey().intValue());
	}

	@Override
	public void update(Worksheet worksheet) {
		jdbcTemplate.update(SQL_UPDATE_WORKSHEET, worksheet.getName(), worksheet.getId());
	}

	@Override
	public void delete(int idWorksheet) {
		jdbcTemplate.update(SQL_DELETE_WORKSHEET, idWorksheet);
	}
	
	@Override
	public Worksheet getById(int idWorksheet) {
		List<Worksheet> worksheets = jdbcTemplate.query(SQL_GET_WORKSHEET_BY_ID, new WorksheetMapper(), idWorksheet);
		return worksheets.isEmpty() ? null : worksheets.get(0);
	}
	
	@Override
	public List<Worksheet> getByUser(int userId) {
		List<Worksheet> worksheets = jdbcTemplate.query(SQL_GET_BY_USER, new WorksheetMapper(), userId);
		return worksheets;
	}
	
	private static class WorksheetMapper implements RowMapper<Worksheet> {

		@Override
		public Worksheet mapRow(ResultSet rs, int rowNum) throws SQLException {
			Worksheet worksheet = new Worksheet();
			worksheet.setId(rs.getInt("id"));
			worksheet.setName(rs.getString("name"));
			return worksheet;
		}

	}

}
