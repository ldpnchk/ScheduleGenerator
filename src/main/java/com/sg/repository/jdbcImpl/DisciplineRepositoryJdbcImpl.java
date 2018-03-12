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

import com.sg.entity.Discipline;
import com.sg.repository.DisciplineRepository;

@Repository
public class DisciplineRepositoryJdbcImpl implements DisciplineRepository{
	
	private static final String SQL_ADD_DISCIPLINE = "INSERT INTO discipline (name, id_worksheet) VALUES (?, ?);";
	private static final String SQL_UPDATE_DISCIPLINE = "UPDATE discipline SET name=? WHERE id=?;";
	private static final String SQL_DELETE_DISCIPLINE = "DELETE FROM discipline WHERE id=?;";
	private static final String SQL_ADD_LECTURER = "INSERT INTO discipline_lecturer (id_discipline, id_lecturer) VALUES (?, ?);";
	private static final String SQL_ADD_STUDENT = "INSERT INTO discipline_student (id_discipline, id_student) VALUES (?, ?);";
	private static final String SQL_REMOVE_LECTURER = "DELETE FROM discipline_lecturer WHERE id_discipline=? AND id_lecturer=?;";
	private static final String SQL_REMOVE_STUDENT = "DELETE FROM discipline_student WHERE id_discipline=? AND id_student=?;";
	private static final String SQL_GET_ALL_BY_STUDENT = "SELECT * FROM discipline INNER JOIN discipline_student ON discipline.id=discipline_student.id_discipline WHERE discipline_student.id_student=? ORDER BY discipline.name ;";
	private static final String SQL_GET_ALL_BY_LECTURER = "SELECT * FROM discipline INNER JOIN discipline_lecturer ON discipline.id=discipline_lecturer.id_discipline WHERE discipline_lecturer.id_lecturer=? ORDER BY discipline.name;";
	private static final String SQL_GET_ALL_DISCIPLINES = "SELECT * FROM discipline WHERE id_worksheet = ? ORDER BY discipline.name;";
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public void add(final Discipline discipline) {
		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(new PreparedStatementCreator() {

			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				PreparedStatement ps = con.prepareStatement(SQL_ADD_DISCIPLINE, new String[] { "id" });
				ps.setString(1, discipline.getName());
				ps.setInt(2, discipline.getWorksheetId());
				return ps;
			}
			
		}, keyHolder);	
		discipline.setId(keyHolder.getKey().intValue());
	}
	
	@Override
	public void update(Discipline discipline) {
		jdbcTemplate.update(SQL_UPDATE_DISCIPLINE, discipline.getName(), discipline.getId());
	}

	@Override
	public void delete(int idDiscipline) {
		jdbcTemplate.update(SQL_DELETE_DISCIPLINE, idDiscipline);
	}
	
	@Override
	public void addLecturer(int idDiscipline, int idLecturer) {
		jdbcTemplate.update(SQL_ADD_LECTURER, idDiscipline, idLecturer);
	}
	
	@Override
	public void addStudent(int idDiscipline, int idStudent) {
		jdbcTemplate.update(SQL_ADD_STUDENT, idDiscipline, idStudent);
	}
	
	@Override
	public void removeLecturer(int idDiscipline, int idLecturer) {
		jdbcTemplate.update(SQL_REMOVE_LECTURER, idDiscipline, idLecturer);
	}

	@Override
	public void removeStudent(int idDiscipline, int idStudent) {
		jdbcTemplate.update(SQL_REMOVE_STUDENT, idDiscipline, idStudent);
	}
	
	@Override
	public List<Discipline> getAllByLecturer(int idLecturer) {
		return jdbcTemplate.query(SQL_GET_ALL_BY_LECTURER, new DisciplineMapper(), idLecturer);
	}

	@Override
	public List<Discipline> getAllByStudent(int idStudent) {
		return jdbcTemplate.query(SQL_GET_ALL_BY_STUDENT, new DisciplineMapper(), idStudent);
	}

	@Override
	public List<Discipline> getAllByWorksheet(int id_worksheet) {
		return jdbcTemplate.query(SQL_GET_ALL_DISCIPLINES, new DisciplineMapper(), id_worksheet);
	}
	
	private static class DisciplineMapper implements RowMapper<Discipline> {

		@Override
		public Discipline mapRow(ResultSet rs, int rowNum) throws SQLException {
			Discipline discipline = new Discipline();
			discipline.setId(rs.getInt("id"));
			discipline.setName(rs.getString("name"));
			return discipline;
		}

	}

}
