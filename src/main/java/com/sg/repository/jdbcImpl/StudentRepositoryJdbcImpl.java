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

import com.sg.entity.Specialty;
import com.sg.entity.Student;
import com.sg.repository.StudentRepository;

@Repository
public class StudentRepositoryJdbcImpl implements StudentRepository{
	
	private static final String SQL_ADD_STUDENT = "INSERT INTO student (name, course, id_specialty) VALUES (?, ?, ?);";
	private static final String SQL_UPDATE_STUDENT = "UPDATE student SET name=?, course=?, id_specialty=? WHERE id=?;";
	private static final String SQL_DELETE_STUDENT = "DELETE FROM student WHERE id=?;";
	private static final String SQL_GET_ALL_STUDENTS = "SELECT *, student.id AS student_id, "
			+ "specialty.name AS specialty_name FROM student "
			+ "INNER JOIN specialty ON student.id_specialty=specialty.id "
			+ "WHERE specialty.id_worksheet = ? "
			+ "ORDER BY student.name;";
	private static final String SQL_GET_ALL_STUDENTS_BY_COURSE_AND_SPECIALTY = "SELECT *, "
			+ "student.id AS student_id, specialty.name AS specialty_name FROM student "
			+ "INNER JOIN specialty ON student.id_specialty=specialty.id "
			+ "WHERE student.course=? AND student.id_specialty=? ORDER BY student.name;";
	private static final String SQL_GET_ALL_STUDENTS_BY_DISCIPLINE = "SELECT student.id, "
			+ "student.name, student.course FROM student INNER JOIN discipline_student "
			+ "ON student.id=discipline_student.id_student "
			+ "WHERE discipline_student.id_discipline=? ORDER BY student.name;";
	private static final String SQL_getByDisciplineAndCourseAndSpecialty = "SELECT * FROM student WHERE course=? AND "
			+"id_specialty=? AND id NOT IN (SELECT id_student FROM discipline_student WHERE id_discipline=?)";
	
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public void add(final Student student) {
		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(new PreparedStatementCreator() {

			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				PreparedStatement ps = con.prepareStatement(SQL_ADD_STUDENT, new String[] { "id" });
				ps.setString(1, student.getName());
				ps.setInt(2, student.getYear());
				ps.setInt(3, student.getSpecialty().getId());
				return ps;
			}
			
		}, keyHolder);	
		student.setId(keyHolder.getKey().intValue());
	}

	@Override
	public void update(Student student) {
		jdbcTemplate.update(SQL_UPDATE_STUDENT, student.getName(), student.getYear(), student.getSpecialty().getId(), student.getId());
	}

	@Override
	public void delete(int idStudent) {
		jdbcTemplate.update(SQL_DELETE_STUDENT, idStudent);
	}
	
	@Override
	public List<Student> getAllByWorksheet(int id_worksheet) {
		return jdbcTemplate.query(SQL_GET_ALL_STUDENTS, new StudentWithSpecialtyMapper(), id_worksheet);
	}
	
	@Override
	public List<Student> getAllByCourseAndSpecialty(int course, int idSpecialty) {
		return jdbcTemplate.query(SQL_GET_ALL_STUDENTS_BY_COURSE_AND_SPECIALTY, new StudentWithSpecialtyMapper(), course, idSpecialty);
	}
	
	@Override
	public List<Student> getAllByDiscipline(int idDiscipline) {
		return jdbcTemplate.query(SQL_GET_ALL_STUDENTS_BY_DISCIPLINE, new StudentMapper(), idDiscipline);
	}
	
	@Override
	public List<Student> getNotEnrolledStudentsByDisciplineAndCourseAndSpecialty(int course, int idSpecialty, int idDiscipline) {
		return jdbcTemplate.query(SQL_getByDisciplineAndCourseAndSpecialty, new StudentMapper(), course, idSpecialty, idDiscipline);
	}
	
	private static class StudentMapper implements RowMapper<Student> {

		@Override
		public Student mapRow(ResultSet rs, int rowNum) throws SQLException {
			Student student = new Student();
			student.setId(rs.getInt("id"));
			student.setName(rs.getString("name"));
			student.setYear(rs.getInt("course"));
			return student;
		}

	}
	
	private static class StudentWithSpecialtyMapper implements RowMapper<Student> {

		@Override
		public Student mapRow(ResultSet rs, int rowNum) throws SQLException {
			Student student = new Student();
			student.setId(rs.getInt("student_id"));
			student.setName(rs.getString("name"));
			student.setYear(rs.getInt("course"));
			Specialty specialty = new Specialty(rs.getInt("id_specialty"), rs.getString("specialty_name"));
			student.setSpecialty(specialty);
			return student;
		}

	}

}