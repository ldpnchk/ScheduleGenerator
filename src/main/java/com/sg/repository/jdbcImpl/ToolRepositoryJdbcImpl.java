package com.sg.repository.jdbcImpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.sg.entity.Tool;
import com.sg.repository.ToolRepository;

@Repository
public class ToolRepositoryJdbcImpl implements ToolRepository{
	
	private static final String SQL_GET_ALL_TOOLS = "SELECT * FROM tool";
	
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public List<Tool> getAll() {
		return jdbcTemplate.query(SQL_GET_ALL_TOOLS, new ToolMapper());
	}
	
	private static class ToolMapper implements RowMapper<Tool> {

		@Override
		public Tool mapRow(ResultSet rs, int rowNum) throws SQLException {
			Tool tool = new Tool();
			tool.setId(rs.getInt("id"));
			tool.setName(rs.getString("name"));
			return tool;
		}

	}

}
