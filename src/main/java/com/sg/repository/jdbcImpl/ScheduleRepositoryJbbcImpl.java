package com.sg.repository.jdbcImpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Repository;

import com.sg.entity.*;
import com.sg.repository.ScheduleRepository;

@Repository
public class ScheduleRepositoryJbbcImpl implements ScheduleRepository{
	
	private static final String SQL_ADD_SCHEDULE = "INSERT INTO schedule (id_lesson, id_classroom, id_daytime, id_periodtime) VALUES(?, ?, ?, ?);";
	private static final String SQL_GET_ALL_BY_WORKSHEET = "SELECT *, lesson.name AS lesson_name, "
			+ "classroom.building AS classroom_building, classroom.number AS classroom_number,  "
			+ "daytime.name AS daytime_name, periodtime.number AS periodtime_number "
			+ "FROM schedule "
			+ "INNER JOIN lesson ON schedule.id_lesson=lesson.id "
			+ "INNER JOIN classroom ON schedule.id_classroom=classroom.id "
			+ "INNER JOIN daytime ON schedule.id_daytime=daytime.id "
			+ "INNER JOIN periodtime ON schedule.id_periodtime=periodtime.id WHERE lesson.id_worksheet=?;";
	private static final String SQL_GET_ALL_BY_COURSE_SPECIALTY_WORKSHEET = "SELECT *, "
			+ "lesson.name AS lesson_name, classroom.building AS classroom_building, "
			+ "classroom.number AS classroom_number, daytime.name AS daytime_name, "
			+ "periodtime.number AS periodtime_number FROM schedule "
			+ "INNER JOIN lesson ON schedule.id_lesson=lesson.id "
			+ "INNER JOIN classroom ON schedule.id_classroom=classroom.id "
			+ "INNER JOIN daytime ON schedule.id_daytime=daytime.id "
			+ "INNER JOIN periodtime ON schedule.id_periodtime=periodtime.id "
			+ "WHERE lesson.id_worksheet=? AND id_lesson IN "
			+ "(SELECT id_lesson FROM lesson_student "
			+ "INNER JOIN student on lesson_student.id_student=student.id "
			+ "WHERE student.course=? AND student.id_specialty=?)";
	private static final String SQL_DELETE_ALL_BY_WORKSHEET = "DELETE FROM schedule WHERE id_lesson IN "
								+ "(SELECT id FROM lesson WHERE id_worksheet=?);";
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public void add(Schedule schedule) {
		jdbcTemplate.update(SQL_ADD_SCHEDULE, schedule.getLesson().getId(), schedule.getClassroom().getId(), schedule.getDaytime().getId(), schedule.getPeriodtime().getId());
	}

	@Override
	public void deleteAllByWorksheet(int idWorksheet) {
		jdbcTemplate.update(SQL_DELETE_ALL_BY_WORKSHEET, idWorksheet);
	}

	@Override
	public List<Schedule> getAllByWorksheet(int idWorksheet) {
		List<Schedule> schedules = jdbcTemplate.query(SQL_GET_ALL_BY_WORKSHEET, new Object[]{idWorksheet}, new ScheduleExtractor());
		return schedules;
	}

	@Override
	public List<Schedule> getAllByCourseSpecialtyWorksheet(int course, int idSpecialty, int idWorksheet) {
		List<Schedule> schedules = jdbcTemplate.query(SQL_GET_ALL_BY_COURSE_SPECIALTY_WORKSHEET, new Object[]{idWorksheet, course, idSpecialty}, new ScheduleExtractor());
		return schedules;
	}
	
	private static class ScheduleExtractor implements ResultSetExtractor<List<Schedule>>{
		@Override
		public List<Schedule> extractData(ResultSet rs) throws SQLException, DataAccessException {
			
			List<Schedule> schedules = new ArrayList<Schedule>();
			
			List<Lesson> lessons = new ArrayList<Lesson>();
			List<Classroom> classrooms = new ArrayList<Classroom>();
			List<Daytime> daytimes = new ArrayList<Daytime>();
			List<Periodtime> periodtimes = new ArrayList<Periodtime>();

			while(rs.next()){
				Lesson lesson = new Lesson(rs.getInt("id_lesson"));
				if (lessons.contains(lesson)){
					lesson = lessons.get(lessons.indexOf(lesson));
				}
				else{
					lesson.setName(rs.getString("lesson_name"));
					lessons.add(lesson);
				}
				
				Classroom classroom = new Classroom(rs.getInt("id_classroom"));
				if (classrooms.contains(classroom)){
					classroom = classrooms.get(classrooms.indexOf(classroom));
				}
				else{
					classroom.setBuilding(rs.getString("classroom_building"));
					classroom.setNumber(rs.getString("classroom_number"));
					classrooms.add(classroom);
				}
				
				Daytime daytime = new Daytime(rs.getInt("id_daytime"));
				if (daytimes.contains(daytime)){
					daytime = daytimes.get(daytimes.indexOf(daytime));
				}
				else{
					daytime.setName(rs.getString("daytime_name"));
					daytimes.add(daytime);
				}
				
				Periodtime periodtime = new Periodtime(rs.getInt("id_periodtime"));
				if (periodtimes.contains(periodtime)){
					periodtime = periodtimes.get(periodtimes.indexOf(periodtime));
				}
				else{
					periodtime.setNumber(rs.getInt("periodtime_number"));
					periodtimes.add(periodtime);
				}
				
				schedules.add(new Schedule(lesson, classroom, daytime, periodtime));
			}
			
			return schedules;
		}
	}

}
