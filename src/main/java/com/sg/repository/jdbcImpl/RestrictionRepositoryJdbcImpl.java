package com.sg.repository.jdbcImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.sg.entity.*;
import com.sg.repository.RestrictionRepository;

@Repository
public class RestrictionRepositoryJdbcImpl implements RestrictionRepository{
	
	private static final String SQL_ADD_RESTRICTION = "INSERT INTO restriction "
			+ "(id_discipline, id_lecturer, id_classroom, id_daytime, id_periodtime, selection, id_worksheet) "
			+ "VALUES (?, ?, ?, ?, ?, ?, ?)";
	private static final String SQL_DELETE_RESTRICTION = "DELETE FROM restriction WHERE id=?";
	private static final String SQL_GET_ALL_RESTRICTIONS = "SELECT *, "
			+ "discipline.name AS discipline_name, lecturer.name AS lecturer_name, "
			+ "classroom.building AS classroom_building, classroom.number AS classroom_number, "
			+ "daytime.name AS daytime_name, periodtime.number AS periodtime_number "
			+ "FROM restriction "
			+ "LEFT JOIN discipline ON restriction.id_discipline=discipline.id "
			+ "LEFT JOIN lecturer ON restriction.id_lecturer=lecturer.id "
			+ "LEFT JOIN classroom ON restriction.id_classroom=classroom.id "
			+ "LEFT JOIN daytime ON restriction.id_daytime=daytime.id "
			+ "LEFT JOIN periodtime ON restriction.id_periodtime=periodtime.id "
			+ "WHERE restriction.id_worksheet = ?;";
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public void add(final Restriction restriction) {
		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(new PreparedStatementCreator() {

			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				PreparedStatement ps = con.prepareStatement(SQL_ADD_RESTRICTION, new String[] { "id" });
				if (restriction.getDiscipline() != null){
					ps.setInt(1, restriction.getDiscipline().getId());
				}
				else{
					ps.setNull(1, java.sql.Types.INTEGER);
				}
				if (restriction.getLecturer() != null){
					ps.setInt(2, restriction.getLecturer().getId());
				}
				else{
					ps.setNull(2, java.sql.Types.INTEGER);
				}
				if (restriction.getClassroom() != null){
					ps.setInt(3, restriction.getClassroom().getId());
				}
				else{
					ps.setNull(3, java.sql.Types.INTEGER);
				}
				if (restriction.getDaytime() != null){
					ps.setInt(4, restriction.getDaytime().getId());
				}
				else{
					ps.setNull(4, java.sql.Types.INTEGER);
				}
				if (restriction.getPeriodtime() != null){
					ps.setInt(5, restriction.getPeriodtime().getId());
				}
				else{
					ps.setNull(5, java.sql.Types.INTEGER);
				}
				if (restriction.isSelection()){
					ps.setInt(6, 1);
				}
				else{
					ps.setInt(6, 0);
				}
				ps.setInt(7, restriction.getWorksheetId());
				return ps;
			}
			
		}, keyHolder);	
		restriction.setId(keyHolder.getKey().intValue());
	}

	@Override
	public void delete(int idRestriction) {
		jdbcTemplate.update(SQL_DELETE_RESTRICTION, idRestriction);
	}

	@Override
	public List<Restriction> getAllByWorksheet(int id_worksheet) {
		return jdbcTemplate.query(SQL_GET_ALL_RESTRICTIONS, new RestrictionExtractor(), id_worksheet);
	}
	
	private static class RestrictionExtractor implements ResultSetExtractor<List<Restriction>>{
		@Override
		public List<Restriction> extractData(ResultSet rs) throws SQLException, DataAccessException {
			
			List<Restriction> restrictions = new ArrayList<Restriction>();
			
			List<Discipline> disciplines = new ArrayList<Discipline>();
			List<Lecturer> lecturers = new ArrayList<Lecturer>();
			List<Classroom> classrooms = new ArrayList<Classroom>();
			List<Daytime> daytimes = new ArrayList<Daytime>();
			List<Periodtime> periodtimes = new ArrayList<Periodtime>();

			while(rs.next()){
				Restriction restriction = new Restriction(rs.getInt("id"));
				
				if (rs.getInt("id_discipline") != 0){
					Discipline discipline = new Discipline(rs.getInt("id_discipline"));
					if (disciplines.contains(discipline)){
						discipline = disciplines.get(disciplines.indexOf(discipline));
					}
					else{
						discipline.setName(rs.getString("discipline_name"));
						disciplines.add(discipline);
					}
					restriction.setDiscipline(discipline);
				}
				
				if (rs.getInt("id_lecturer") != 0){
					Lecturer lecturer = new Lecturer(rs.getInt("id_lecturer"));
					if (lecturers.contains(lecturer)){
						lecturer = lecturers.get(lecturers.indexOf(lecturer));
					}
					else{
						lecturer.setName(rs.getString("lecturer_name"));
						lecturers.add(lecturer);
					}
					restriction.setLecturer(lecturer);
				}
				
				if (rs.getInt("id_classroom") != 0){
					Classroom classroom = new Classroom(rs.getInt("id_classroom"));
					if (classrooms.contains(classroom)){
						classroom = classrooms.get(classrooms.indexOf(classroom));
					}
					else{
						classroom.setBuilding(rs.getString("classroom_building"));
						classroom.setNumber(rs.getString("classroom_number"));
						classrooms.add(classroom);
					}
					restriction.setClassroom(classroom);
				}
				
				if (rs.getInt("id_daytime") != 0){
					Daytime daytime = new Daytime(rs.getInt("id_daytime"));
					if (daytimes.contains(daytime)){
						daytime = daytimes.get(daytimes.indexOf(daytime));
					}
					else{
						daytime.setName(rs.getString("daytime_name"));
						daytimes.add(daytime);
					}
					restriction.setDaytime(daytime);
				}
				
				if (rs.getInt("id_periodtime") != 0){
					Periodtime periodtime = new Periodtime(rs.getInt("id_periodtime"));
					if (periodtimes.contains(periodtime)){
						periodtime = periodtimes.get(periodtimes.indexOf(periodtime));
					}
					else{
						periodtime.setNumber(rs.getInt("periodtime_number"));
						periodtimes.add(periodtime);
					}
					restriction.setPeriodtime(periodtime);
				}
				
				if (rs.getInt("selection") == 1){
					restriction.setSelection(true);
				}
				else{
					restriction.setSelection(false);
				}
				
				restrictions.add(restriction);
				
			}
			
			return restrictions;
		}
	}

}
