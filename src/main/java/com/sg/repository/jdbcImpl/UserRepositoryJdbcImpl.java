package com.sg.repository.jdbcImpl;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.sg.repository.UserRepository;

@Repository
public class UserRepositoryJdbcImpl implements UserRepository{

	private static final String SQL_INSERT_NEW_USER = "INSERT INTO Users (email, password, roleId) VALUES (?, ?, 1)";

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public void addNewUser(String name, String password) {
		Connection conn = null;
		try {
			conn = jdbcTemplate.getDataSource().getConnection();
			PreparedStatement ps = conn.prepareStatement(SQL_INSERT_NEW_USER);
			ps.setString(1, name);
			ps.setString(2, md5Custom(password));
			ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {}
			}
		}
	}


	// MD5 hash
	private static String md5Custom(String st) {
		MessageDigest messageDigest = null;
		byte[] digest = new byte[0];
		try {
			messageDigest = MessageDigest.getInstance("MD5");
			messageDigest.reset();
			messageDigest.update(st.getBytes());
			digest = messageDigest.digest();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		BigInteger bigInt = new BigInteger(1, digest);
		String md5Hex = bigInt.toString(16);

		while( md5Hex.length() < 32 ){
			md5Hex = "0" + md5Hex;
		}
		return md5Hex;
	}
}
