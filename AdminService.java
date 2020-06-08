package service;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dao.AdminDao;
import dao.DBConnection;
import ui.Admin_view;

public class AdminService {
	private AdminDao adminDao;

	public AdminService() {
		adminDao = new AdminDao(DBConnection.getConnection());
	}

	public String[] getStudentDropdownInfo() {
		ArrayList<Student> listOfStudent = getAllStudents();
		String StudentArray[] = new String[listOfStudent.size() + 1];
		for (int i = 0; i < listOfStudent.size(); i++) {
			Student s = listOfStudent.get(i);
			StudentArray[i + 1] = s.getUsn() + " (" + s.getName() + ")";
		}
		return StudentArray;
	}

	public ArrayList<Student> getAllStudents() {
		ArrayList<Student> listOfStudent = new ArrayList<Student>();
		ResultSet rs = adminDao.getAllStudents();
		try {
			while (rs.next()) {
				Student s = new Student(rs.getString("name"), rs.getString("usn"));
				listOfStudent.add(s);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return listOfStudent;
	}

	public Object[][] getStudentAttendance(String usn) {
		Object[][] attendanceArray = new String[12][6];
		ResultSet rs = adminDao.getAttendance(usn);
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
	
	public Date convertToDate(String month) {
		String January,February,March,April,May,June,July,August,September,October,November,December;
		switch(month) {
		
		case "January" :
		String janDate = "2020-01-01";
		return Date.valueOf(janDate);
		case "February" : 
			String febDate = "2020-02-01";
			return Date.valueOf(febDate);
		case "March" : 
			String marDate = "2020-03-01";
			return Date.valueOf(marDate);
		case "April" : 
			String aprDate = "2020-04-01";
			return Date.valueOf(aprDate);
		case "May" : 
			String mayDate = "2020-05-01";
			return Date.valueOf(mayDate);
		case "June" : 
			String junDate = "2020-06-01";
			return Date.valueOf(junDate);
		case "July" : 
			String julDate = "2020-07-01";
			return Date.valueOf(julDate);
		case "August" : 
			String augDate = "2020-08-01";
			return Date.valueOf(augDate);
		case "September" : 
			String sepDate = "2020-09-01";
			return Date.valueOf(sepDate);
		case "October" : 
			String octDate = "2020-10-01";
			return Date.valueOf(octDate);
		case "November" : 
			String novDate = "2020-11-01";
			return Date.valueOf(novDate);
		case "December" :
			String decDate = "2020-12-01";
			return Date.valueOf(decDate);
	
		}
		return null;
		
	}
	
}
