package com.sg.repository.jdbcImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.sg.entity.*;
import com.sg.repository.LessonRepository;

@Repository
public class LessonRepositoryJdbcImpl implements LessonRepository {
	
	private static final String SQL_ADD_LESSON = "INSERT INTO lesson (name, id_worksheet, id_discipline, id_lecturer, id_room_type) VALUES(?, ?, ?, ?, ?);";
	private static final String SQL_DELETE_LESSON = "DELETE FROM lesson WHERE id=?;";
	private static final String SQL_GET_LESSON_BY_ID = "SELECT * from lesson WHERE id=?;";
	private static final String SQL_ADD_STUDENT_TO_LESSON = "INSERT INTO lesson_student "
			+ "(id_lesson, id_student) VALUES (?, ?);";
	private static final String SQL_ADD_TOOL_TO_LESSON = "INSERT INTO lesson_tool "
			+ "(id_lesson, id_tool, selection) VALUES (?, ?, ?);";
	private static final String SQL_GET_ALL_LESSONS_WITH_STUDENTS_AMOUNT_BY_WORKSHEET = "SELECT lesson.id, "
			+ "lesson.name AS lesson_name, discipline.id AS discipline_id, "
			+ "discipline.name AS discipline_name, lecturer.id AS lecturer_id, "
			+ "lecturer.name AS lecturer_name, COUNT(id_student) AS amount FROM lesson "
			+ "INNER JOIN discipline ON lesson.id_discipline=discipline.id "
			+ "INNER JOIN lecturer ON lesson.id_lecturer=lecturer.id "
			+ "INNER JOIN lesson_student ON lesson.id=lesson_student.id_lesson "
			+ "WHERE lesson.id_worksheet=? GROUP BY lesson.id;";
	private static final String SQL_GET_ALL_LESSONS_BY_WORKSHEET = "SELECT lesson.id, "
			+ "discipline.id AS discipline_id, lecturer.id AS lecturer_id, "
			+ "lesson_student.id_student AS student_id, room_type.id AS room_type_id, "
			+ "lesson_tool.id_tool AS tool_id, lesson_tool.selection AS selection FROM lesson "
			+ "INNER JOIN discipline ON lesson.id_discipline=discipline.id "
			+ "INNER JOIN lecturer ON lesson.id_lecturer=lecturer.id "
			+ "INNER JOIN room_type ON lesson.id_room_type=room_type.id "
			+ "LEFT JOIN lesson_student ON lesson.id=lesson_student.id_lesson "
			+ "LEFT JOIN lesson_tool ON lesson.id=lesson_tool.id_lesson "
			+ "WHERE lesson.id_worksheet=?";

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public void add(final Lesson lesson) {
		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(new PreparedStatementCreator() {

			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				PreparedStatement ps = con.prepareStatement(SQL_ADD_LESSON, new String[] { "id" });
				ps.setString(1, lesson.getName());
				ps.setInt(2, lesson.getWorksheet().getId());
				ps.setInt(3, lesson.getDiscipline().getId());
				ps.setInt(4, lesson.getLecturer().getId());
				ps.setInt(5, lesson.getRoomType().getId());
				return ps;
			}
			
		}, keyHolder);	
		lesson.setId(keyHolder.getKey().intValue());
	}

	@Override
	public void delete(int idLesson) {
		jdbcTemplate.update(SQL_DELETE_LESSON, idLesson);
	}
	
	@Override
	public void addStudent(int idLesson, int idStudent) {
		jdbcTemplate.update(SQL_ADD_STUDENT_TO_LESSON, idLesson, idStudent);
	}
	
	@Override
	public void addTool(int idLesson, int idTool, int selection) {
		jdbcTemplate.update(SQL_ADD_TOOL_TO_LESSON, idLesson, idTool, selection);
	}

	@Override
	public Lesson getById(int id) {
		List<Lesson> lessons = jdbcTemplate.query(SQL_GET_LESSON_BY_ID, new LessonMapper(), id);
		return lessons.isEmpty() ? null : lessons.get(0);
	}

	@Override
	public Map<Lesson, Integer> getAllWithStudentsAmountByWorksheet(int idWorksheet) {
		Map<Lesson, Integer> lessons = jdbcTemplate.query(SQL_GET_ALL_LESSONS_WITH_STUDENTS_AMOUNT_BY_WORKSHEET, new Object[]{idWorksheet}, new LessonViewExtractor());
		return lessons;
	}
	
	@Override
	public List<Lesson> getAllByWorksheet(int idWorksheet) {
		List<Lesson> lessons = jdbcTemplate.query(SQL_GET_ALL_LESSONS_BY_WORKSHEET, new Object[]{idWorksheet}, new LessonProcessExtractor());
		return lessons;
	}
	
	private static class LessonMapper implements RowMapper<Lesson> {

		@Override
		public Lesson mapRow(ResultSet rs, int rowNum) throws SQLException {
			Lesson lesson = new Lesson();
			lesson.setId(rs.getInt("id_lesson"));
			lesson.setName(rs.getString("name"));
			//lesson.setId(rs.getInt("id_worksheet"));
			//lesson.setId(rs.getInt("id_discipline"));
			//lesson.setId(rs.getInt("id_lecturer"));
			//lesson.setId(rs.getInt("id_type"));
			return lesson;
		}

	}
	
	private static class LessonViewExtractor implements ResultSetExtractor<Map<Lesson, Integer>>{
		@Override
		public Map<Lesson, Integer> extractData(ResultSet rs) throws SQLException, DataAccessException {
			
			Map<Lesson, Integer> lessons = new HashMap<Lesson, Integer>();
			
			List<Discipline> disciplines = new ArrayList<Discipline>();
			List<Lecturer> lecturers = new ArrayList<Lecturer>();

			while(rs.next()){
				Discipline discipline = new Discipline(rs.getInt("discipline_id"));
				if (disciplines.contains(discipline)){
					discipline = disciplines.get(disciplines.indexOf(discipline));
				}
				else{
					discipline.setName(rs.getString("discipline_name"));
					disciplines.add(discipline);
				}
				
				Lecturer lecturer = new Lecturer(rs.getInt("lecturer_id"));
				if (lecturers.contains(lecturer)){
					lecturer = lecturers.get(lecturers.indexOf(lecturer));
				}
				else{
					lecturer.setName(rs.getString("lecturer_name"));
					lecturers.add(lecturer);
				}
				
				Lesson lesson = new Lesson();
				lesson.setId(rs.getInt("id"));
				lesson.setName(rs.getString("lesson_name"));
				lesson.setDiscipline(discipline);
				lesson.setLecturer(lecturer);
				
				lessons.put(lesson, rs.getInt("amount"));
			}
			
			return lessons;
		}
	}
	
	
	private static class LessonProcessExtractor implements ResultSetExtractor<List<Lesson>>{
		@Override
		public List<Lesson> extractData(ResultSet rs) throws SQLException, DataAccessException {
			
			List<Lesson> lessons = new ArrayList<Lesson>();
			
			List<Discipline> disciplines = new ArrayList<Discipline>();
			List<Lecturer> lecturers = new ArrayList<Lecturer>();
			List<Student> students = new ArrayList<Student>();
			List<RoomType> roomTypes = new ArrayList<RoomType>();
			List<Tool> tools = new ArrayList<Tool>();

			while(rs.next()){
				Discipline discipline = new Discipline(rs.getInt("discipline_id"));
				if (disciplines.contains(discipline)){
					discipline = disciplines.get(disciplines.indexOf(discipline));
				}
				else{
					disciplines.add(discipline);
				}
				
				Lecturer lecturer = new Lecturer(rs.getInt("lecturer_id"));
				if (lecturers.contains(lecturer)){
					lecturer = lecturers.get(lecturers.indexOf(lecturer));
				}
				else{
					lecturers.add(lecturer);
				}
				
				Student student = new Student(rs.getInt("student_id"));
				if (students.contains(student)){
					student = students.get(students.indexOf(student));
				}
				else{
					students.add(student);
				}
				
				RoomType roomType = new RoomType(rs.getInt("room_type_id"));
				if (roomTypes.contains(roomType)){
					roomType = roomTypes.get(roomTypes.indexOf(roomType));
				}
				else{
					roomTypes.add(roomType);
				}
				
				Tool tool = new Tool(rs.getInt("tool_id"));
				if (tools.contains(tool)){
					tool = tools.get(tools.indexOf(tool));
				}
				else{
					tools.add(tool);
				}
				
				Lesson lesson = new Lesson(rs.getInt("id"));
				if (lessons.contains(lesson)){
					lesson = lessons.get(lessons.indexOf(lesson));
				}
				else{
					lesson.setDiscipline(discipline);
					lesson.setLecturer(lecturer);
					lesson.setRoomType(roomType);
					lessons.add(lesson);
				}
				
				if (!lesson.getStudents().contains(student)){
					lesson.getStudents().add(student);
				}
				
				if (!lesson.getTools().keySet().contains(tool)){
					lesson.getTools().put(tool, rs.getInt("selection") == 1);
				}
			}
			
			return lessons;
		}
	}

}
