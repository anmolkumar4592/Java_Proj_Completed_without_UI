package ui;

import java.awt.*;
import javax.swing.*;
import service.StudentService;

public class Student_view extends JFrame {
	private String usn;
	JPanel panel;
	StudentService studentService;

	JScrollPane jscrollPane;
	JTable attendanceTable;
	Object[][] attendanceData;
	Object[] columnNames = new String[] { "Month", "English", "Maths", "Science", "Computer Science",
			"Business Studies" };

	public Student_view(String usn) {
		this.usn = usn;
		studentService = new StudentService();

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		panel = new JPanel(new GridLayout(4, 4, 30, 50));

		attendanceData = studentService.getStudentAttendance(usn);
		attendanceTable = new JTable(attendanceData, columnNames);
		attendanceTable.setShowGrid(true);
		
		jscrollPane = new JScrollPane(attendanceTable);

		add(panel, BorderLayout.CENTER);
		panel.add(jscrollPane);		
		
		setTitle("Here is your attendance !");
		setSize(500, 500);
		setVisible(true);
	}

}