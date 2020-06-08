package service;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

import dao.DBConnection;
import dao.StudentDao;
import ui.login;

public class StudentService{
	private StudentDao studentDao;
	
	public StudentService(){
		studentDao = new StudentDao(DBConnection.getConnection());
	}
	
	public Object[][] getStudentAttendance(String usn) {
		Object[][] attendanceArray = new String[12][6];
		ResultSet rs = studentDao.getStudentAttendance(usn);
		try {
			while (rs.next()) {
				Date date = rs.getDate("month");
				int eng = rs.getInt("eng");
				int mat = rs.getInt("mat");
				int sci = rs.getInt("sci");
				int cs = rs.getInt("cs");
				int bs = rs.getInt("bs");

				int monthIndex = date.getMonth();
				attendanceArray[monthIndex][1] = "" + eng;
				attendanceArray[monthIndex][2] = "" + mat;
				attendanceArray[monthIndex][3] = "" + sci;
				attendanceArray[monthIndex][4] = "" + cs;
				attendanceArray[monthIndex][5] = "" + bs;
			}

			attendanceArray[0][0] = "Jan";
			attendanceArray[1][0] = "Feb";
			attendanceArray[2][0] = "Mar";
			attendanceArray[3][0] = "Apr";
			attendanceArray[4][0] = "May";
			attendanceArray[5][0] = "Jun";
			attendanceArray[6][0] = "July";
			attendanceArray[7][0] = "Aug";
			attendanceArray[8][0] = "Sept";
			attendanceArray[9][0] = "Oct";
			attendanceArray[10][0] = "Nov";
			attendanceArray[11][0] = "Dec";
			
			return attendanceArray;
		} catch (SQLException e) {
			throw new RuntimeException();
		}
		
	}
	
}