package com.sg.repository.jdbcImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.sg.entity.Lecturer;
import com.sg.repository.LecturerRepository;

@Repository
public class LecturerRepositoryJdbcImpl implements LecturerRepository{
	
	private static final String SQL_ADD_LECTURER = "INSERT INTO lecturer (name, id_worksheet) VALUES (?, ?);";
	private static final String SQL_UPDATE_LECTURER = "UPDATE lecturer SET name=? WHERE id=?;";
	private static final String SQL_DELETE_LECTURER = "DELETE FROM lecturer WHERE id=?;";
	private static final String SQL_GET_ALL_LECTURERS = "SELECT * FROM lecturer WHERE id_worksheet = ? ORDER BY lecturer.name;";
	private static final String SQL_GET_ALL_LECTURERS_BY_DISCIPLINE = "SELECT lecturer.id, lecturer.name FROM lecturer "
			+ "INNER JOIN discipline_lecturer ON lecturer.id=discipline_lecturer.id_lecturer "
			+ "WHERE discipline_lecturer.id_discipline=? ORDER BY lecturer.name;";
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public void add(final Lecturer lecturer) {
		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(new PreparedStatementCreator() {

			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				PreparedStatement ps = con.prepareStatement(SQL_ADD_LECTURER, new String[] { "id" });
				ps.setString(1, lecturer.getName());
				ps.setInt(2, lecturer.getWorksheetId());
				return ps;
			}
			
		}, keyHolder);	
		lecturer.setId(keyHolder.getKey().intValue());
	}
	

	@Override
	public void update(Lecturer lecturer) {
		jdbcTemplate.update(SQL_UPDATE_LECTURER, lecturer.getName(), lecturer.getId());
	}

	@Override
	public void delete(int idLecturer) {
		jdbcTemplate.update(SQL_DELETE_LECTURER, idLecturer);
	}
	
	@Override
	public List<Lecturer> getAllByWorksheet(int id_worksheet) {
		return jdbcTemplate.query(SQL_GET_ALL_LECTURERS, new LecturerMapper(), id_worksheet);
	}

	@Override
	public List<Lecturer> getAllByDiscipline(int idDiscipline) {
		return jdbcTemplate.query(SQL_GET_ALL_LECTURERS_BY_DISCIPLINE, new LecturerMapper(), idDiscipline);
	}
	
	private static class LecturerMapper implements RowMapper<Lecturer> {

		@Override
		public Lecturer mapRow(ResultSet rs, int rowNum) throws SQLException {
			Lecturer lecturer = new Lecturer();
			lecturer.setId(rs.getInt("id"));
			lecturer.setName(rs.getString("name"));
			return lecturer;
		}
	}

}