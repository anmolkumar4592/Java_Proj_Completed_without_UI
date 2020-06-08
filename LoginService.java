package service;

import dao.DBConnection;
import dao.LoginDao;

public class LoginService {
	LoginDao loginDao;

	public LoginService() {
		loginDao = new LoginDao(DBConnection.getConnection());
	}

	public String validateUser(String userName, String password) {
		return loginDao.getUser(userName, password);
	}

}
