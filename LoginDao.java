package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginDao {
	private Connection conn;

	public LoginDao(Connection conn) {
		this.conn = conn;
	}

	public String getUser(String userName, String password) {
		String userRole = null;
		try {
			String query = "select role from studb.user_table where UserName=? and password=?";
			PreparedStatement st = conn.prepareStatement(query);
			st.setString(1, userName);
			st.setString(2, password);
			ResultSet rs = st.executeQuery();
			if (rs.next()) {
				userRole = rs.getString("role");
			}
			return userRole;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

}
