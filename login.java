package ui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import service.LoginService;

public class login extends JFrame implements ActionListener {
	JPanel panel;
	JLabel user_label, password_label, message;
	JTextField userName_text;
	JPasswordField password_text;
	JButton submit;
	LoginService loginService;
	String userName;

	public login() {
		// Username Label
		user_label = new JLabel();
		user_label.setText("User Name :");
		userName_text = new JTextField();
		// Password Label
		password_label = new JLabel();
		password_label.setText("Password :");
		password_text = new JPasswordField();
		// Submit
		submit = new JButton("SUBMIT");
		panel = new JPanel(new GridLayout(4, 1, 30, 50));
		panel.add(user_label);
		panel.add(userName_text);
		panel.add(password_label);
		panel.add(password_text);
		message = new JLabel();
		panel.add(submit);
		panel.add(message);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// Adding the listeners to components..
		submit.addActionListener(this);
		add(panel, BorderLayout.CENTER);
		setTitle("Please Login Here !");
		setSize(500, 500);
		setVisible(true);

		loginService = new LoginService();
	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		if (ae.getSource() == submit) {
			String userName = userName_text.getText();
			String password = String.valueOf(password_text.getPassword());
			
			String role = loginService.validateUser(userName, password);
			
			if ("admin".equalsIgnoreCase(role)) {
				new Admin_view();
				this.dispose();
			}else if ("student".equalsIgnoreCase(role)) {
				new Student_view(userName);
				this.dispose();
			}
			else {
				message.setText("Invalid user . . .");
			}
		}
	}
}