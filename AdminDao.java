package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import ui.Admin_view;

public class AdminDao {
	private Connection conn;

	public AdminDao(Connection conn) {
		
		this.conn = conn;
	}

	public ResultSet getAttendance(String usn) {
		String query = "select * from studb.student_attendance where usn = ?";
		try {
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, usn);
			return ps.executeQuery();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public ResultSet getAllStudents() {
		String query = "select name,usn from student_info " ; 
		try {
			PreparedStatement ps = conn.prepareStatement(query);
			return ps.executeQuery();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
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
	
	public void saveStudentAttendance(String usn, Date month, String eng, String mat, String sci, String cs, String bs ) {
		System.out.println("inside updating merthod" + usn +" "+  month);
		String query = "select * from studb.student_attendance where usn = ? and month = ?";
		System.out.println("after query" + usn +" "+  month);
		try {
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, usn);
			ps.setDate(2, month);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				String updateQuery = "update studb.student_attendance set eng = ?, mat = ?,sci = ?, cs = ?, bs = ? where usn = ? and month = ?" ;
				try {
					PreparedStatement psu = conn.prepareStatement(updateQuery);
					psu.setString(1, eng);
					psu.setString(2, mat);
					psu.setString(3, sci);
					psu.setString(4, cs);
					psu.setString(5, bs);
					psu.setString(6, usn);
					psu.setDate(7, month);
					 psu.executeUpdate();
					 System.out.println("Updation done");
				} catch (SQLException e) {
					throw new RuntimeException(e);
				}
			} else {
				String insertQuery = "insert into studb.student_attendance (usn,month,eng,mat,sci,cs,bs) values (?,?,?,?,?,?,?) ";
				
				try {
					PreparedStatement psForInsert = conn.prepareStatement(insertQuery);
					psForInsert.setString(1, usn);
					psForInsert.setDate(2, month);
					psForInsert.setString(3, eng);
					psForInsert.setString(4, mat);
					psForInsert.setString(5, sci);
					psForInsert.setString(6, cs);
					psForInsert.setString(7, bs);
					psForInsert.executeUpdate();
					 System.out.println("Insertion done");
				} catch (SQLException e) {
					throw new RuntimeException(e);
				}
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
		
		
		
	}
	
}

