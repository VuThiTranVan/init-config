package demo.service.impl;

import demo.DAO.UserDAO;

public class BaseServiceimpl {
	protected UserDAO userDAO;

	public UserDAO getUserDAO() {
		return userDAO;
	}

	public void setUserDAO(UserDAO userDAO) {
		this.userDAO = userDAO;
	}

}
