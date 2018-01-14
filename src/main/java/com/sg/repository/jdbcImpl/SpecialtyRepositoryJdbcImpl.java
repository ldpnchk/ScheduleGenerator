package com.sg.repository.jdbcImpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.sg.entity.Specialty;
import com.sg.repository.SpecialtyRepository;

@Repository
public class SpecialtyRepositoryJdbcImpl implements SpecialtyRepository{
	
	private static final String SQL_GET_SPECIALTY_BY_ID = "SELECT * FROM specialty WHERE id=?;";
	private static final String SQL_GET_ALL_SPECILTIES = "SELECT * FROM specialty";
	
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public List<Specialty> getAll() {
		return jdbcTemplate.query(SQL_GET_ALL_SPECILTIES, new SpecialtyMapper());
	}

	@Override
	public Specialty getById(int idSpecialty) {
		List<Specialty> specilties = jdbcTemplate.query(SQL_GET_SPECIALTY_BY_ID, new SpecialtyMapper(), idSpecialty);
		return specilties.isEmpty() ? null : specilties.get(0);
	}
	
	
	private static class SpecialtyMapper implements RowMapper<Specialty> {

		@Override
		public Specialty mapRow(ResultSet rs, int rowNum) throws SQLException {
			Specialty specialty = new Specialty();
			specialty.setId(rs.getInt("id"));
			specialty.setName(rs.getString("name"));
			return specialty;
		}

	}

}
