package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class StudentDao {
	private Connection conn;

	public StudentDao(Connection conn) {
		this.conn = conn;
	}

	public ResultSet getStudentAttendance(String usn) {
		String query = "select * from studb.student_attendance where usn = ?";
		try {
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, usn);
			return ps.executeQuery();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

}