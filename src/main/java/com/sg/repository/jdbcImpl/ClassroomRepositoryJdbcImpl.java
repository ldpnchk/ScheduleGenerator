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
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.sg.entity.Classroom;
import com.sg.entity.RoomType;
import com.sg.entity.Tool;
import com.sg.repository.ClassroomRepository;

@Repository
public class ClassroomRepositoryJdbcImpl implements ClassroomRepository{
	
	private static final String SQL_GET_ALL_CLASSROOMS_WITH_TOOLS = "SELECT classroom.id, classroom.building, "
			+ "classroom.number, classroom.capacity, classroom.id_room_type, classroom_tool.id_tool "
			+ "FROM classroom INNER JOIN room_type ON classroom.id_room_type = room_type.id "
			+ "LEFT JOIN classroom_tool ON classroom.id=classroom_tool.id_classroom "
			+ "WHERE classroom.id_worksheet = ?";
	private static final String SQL_INSERT_CLASSROOM = ""
			+ "INSERT INTO classroom (building, number, capacity, id_room_type, id_worksheet) VALUES (?, ?, ?, ?, ?)";
	
	private static final String SQL_INSERT_CLASSROOM_TOOL = "INSERT INTO classroom_tool (id_classroom, id_tool) VALUES (?,?)";
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public void addClassroom(Classroom classroom) {
		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(new PreparedStatementCreator() {

			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				PreparedStatement ps = con.prepareStatement(SQL_INSERT_CLASSROOM, new String[] { "id" });
				ps.setString(1, classroom.getBuilding());
				ps.setString(2, classroom.getNumber());
				ps.setInt(3, classroom.getCapacity());
				ps.setInt(4, classroom.getRoomType().getId());
				ps.setInt(5, classroom.getWorksheetId());
				return ps;
			}
			
		}, keyHolder);	
		classroom.setId(keyHolder.getKey().intValue());
		
		if(!(classroom.getTools().isEmpty())){
			jdbcTemplate.update(new PreparedStatementCreator() {
	
				public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
					PreparedStatement ps = con.prepareStatement(SQL_INSERT_CLASSROOM_TOOL);
					ps.setInt(1, classroom.getId());
					ps.setInt(2, 1);
					return ps;
				}
				
			});
		}
	}
	
	@Override
	public List<Classroom> getAllWithToolsByWorksheet(int id_worksheet) {
		return jdbcTemplate.query(SQL_GET_ALL_CLASSROOMS_WITH_TOOLS, new ClassroomExtractor(), id_worksheet);
	}
	
	private static class ClassroomMapper implements RowMapper<Classroom> {

		@Override
		public Classroom mapRow(ResultSet rs, int rowNum) throws SQLException {
			Classroom classroom = new Classroom();
			classroom.setId(rs.getInt("id"));
			classroom.setBuilding(rs.getString("building"));
			classroom.setNumber(rs.getString("number"));
			classroom.setCapacity(rs.getInt("capacity"));
			classroom.setRoomType(new RoomType(rs.getInt("id_room_type")));
			return classroom;
		}

	}
	
	private static class ClassroomExtractor implements ResultSetExtractor<List<Classroom>>{
		@Override
		public List<Classroom> extractData(ResultSet rs) throws SQLException, DataAccessException {
			
			List<Classroom> classrooms = new ArrayList<Classroom>();
			List<RoomType> roomTypes = new ArrayList<RoomType>();
			List<Tool> tools = new ArrayList<Tool>();

			while(rs.next()){

				RoomType roomType = new RoomType(rs.getInt("id_room_type"));
				if (roomTypes.contains(roomType)){
					roomType = roomTypes.get(roomTypes.indexOf(roomType));
				}
				else{
					roomTypes.add(roomType);
				}
				
				Classroom classroom = new Classroom(rs.getInt("id"));
				if (classrooms.contains(classroom)){
					classroom = classrooms.get(classrooms.indexOf(classroom));
				}
				else{
					classroom.setBuilding(rs.getString("building"));
					classroom.setNumber(rs.getString("number"));
					classroom.setCapacity(rs.getInt("capacity"));
					classroom.setRoomType(roomType);
					classrooms.add(classroom);
				}
				
				if (rs.getInt("id_tool") != 0){
					Tool tool = new Tool(rs.getInt("id_tool"));
					if (tools.contains(tool)){
						tool = tools.get(tools.indexOf(tool));
					}
					else{
						tools.add(tool);
					}
					classroom.getTools().add(tool);
				}

			}
			
			return classrooms;
		}
	}

}
