package ui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import dao.AdminDao;
import dao.DBConnection;
import service.AdminService;

public class Admin_view extends JFrame implements ActionListener {
	
	AdminDao adminDao;
	JPanel panel;
	JLabel eng, mat, sci, cs, bs, message;
	JTextField eng_tf, mat_tf, sci_tf, cs_tf, bs_tf;
	public String studentUsn ;
	String engtf, mattf, scitf, cstf, bstf, dmo;
	JButton update;
	JComboBox<String> cb ;
	JComboBox<String> month;
	AdminService adminService;
	JScrollPane jscrollPane;
	JTable attendanceTable;
	DefaultTableModel defaultTableModel;
	Object[][] attendanceData;
	Object[] columnNames = new String[] { "Month", "English", "Maths", "Science", "Computer Science",
			"Business Studies" };
	String getMonth;

	public Admin_view() {
		adminDao = new AdminDao(DBConnection.getConnection());
		adminService = new AdminService();
		String[] choices = adminService.getStudentDropdownInfo();
		choices[0] = "Please Select Student";
		cb = new JComboBox<String>(choices);
		String[] months = {"Please Select the month","January", "February", "March", "April","May","June","July","August","September","October","November","December"};
		month = new JComboBox<String>(months);
		eng = new JLabel();
		mat = new JLabel();
		sci = new JLabel();
		cs = new JLabel();
		bs = new JLabel();
		eng_tf = new JTextField();
		mat_tf = new JTextField();
		sci_tf = new JTextField();
		cs_tf = new JTextField();
		bs_tf = new JTextField();
		eng.setText(" English");
		mat.setText(" Maths");
		sci.setText(" Science");
		cs.setText(" Computer Science");
		bs.setText(" Business Studies");
		update = new JButton("UPDATE");

		attendanceTable = new JTable();
		defaultTableModel = (DefaultTableModel) attendanceTable.getModel();
		defaultTableModel.setColumnIdentifiers(columnNames);
		attendanceTable.setShowGrid(true);
		
		jscrollPane = new JScrollPane(attendanceTable);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		panel = new JPanel(new GridLayout(4, 4, 30, 50));
		panel.add(cb);
		panel.add(month);
		panel.add(jscrollPane);
		panel.add(eng);
		panel.add(eng_tf);
		panel.add(mat);
		panel.add(mat_tf);
		panel.add(sci);
		panel.add(sci_tf);
		panel.add(cs);
		panel.add(cs_tf);
		panel.add(bs);
		panel.add(bs_tf);

		panel.add(update);
		message = new JLabel();
		panel.add(message);

		cb.addActionListener(this);
		update.addActionListener(this);

		add(panel, BorderLayout.CENTER);
		setTitle("Please Update the Student's Attendance Here !");
		setSize(500, 500);
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		if (ae.getSource() == cb) {
			 studentUsn = cb.getSelectedItem().toString();
			defaultTableModel.setRowCount(0);
			if("Please Select Student".equalsIgnoreCase(studentUsn)) {
				defaultTableModel.fireTableDataChanged();
			}else {
				attendanceData = adminService.getStudentAttendance(studentUsn.substring(0, 10));
				for (int i = 0; i < attendanceData.length; i++) {
					defaultTableModel.addRow(attendanceData[i]);
				}	
				defaultTableModel.fireTableDataChanged();
			}
			
			
		} else if (ae.getSource() == update) {
			studentUsn = cb.getSelectedItem().toString();
			getMonth = month.getSelectedItem().toString();
			
			engtf = eng_tf.getText();
			mattf = mat_tf.getText();
			scitf = sci_tf.getText();
			cstf = cs_tf.getText();
			bstf = bs_tf.getText();
			adminDao.saveStudentAttendance(studentUsn.substring(0,10),adminService.convertToDate(getMonth),engtf, mattf, scitf, cstf, bstf);
			message.setText(" Student data is updated ");
			
						
			
			}	
			
		}
	}
