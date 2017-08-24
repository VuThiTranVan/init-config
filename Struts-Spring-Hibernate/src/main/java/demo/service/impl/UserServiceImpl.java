package demo.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.transaction.annotation.Transactional;

import demo.bean.ConditionSearchUser;
import demo.bean.UserInfo;
import demo.model.User;
import demo.service.UserService;

public class UserServiceImpl extends BaseServiceimpl implements UserService {

	// log
	private static final Logger logger = Logger.getLogger(UserServiceImpl.class);

	@Override
	public List<UserInfo> findAll() {
		try {
			List<User> resultDAO = userDAO.findAll();

			List<UserInfo> result = new ArrayList<>();

			for (User user : resultDAO) {

				if (user != null)
					result.add(convertModelToBean(user));
			}

			return result;

		} catch (Exception e) {

			logger.error("findAll has exception", e);
			return null;
		}
	}

	@Override
	public List<UserInfo> findByCondition(ConditionSearchUser condition) {
		try {
			List<User> resultDAO = userDAO.findByCondition(condition);

			List<UserInfo> result = new ArrayList<>();

			for (User user : resultDAO) {

				if (user != null)
					result.add(convertModelToBean(user));
			}

			return result;

		} catch (Exception e) {

			logger.error("findAll has exception", e);
			return null;
		}
	}

	@Transactional(readOnly = true)
	@Override
	public UserInfo getUserByUserName(String username) {

		try {
			User mUser = userDAO.finByUserName(username);

			if (mUser != null)
				return convertModelToBean(mUser);
			return null;

		} catch (Exception e) {

			logger.error("getUserByUserName has exception", e);
			return null;
		}
	}

	@Override
	public UserInfo getUserById(Integer id) {
		try {
			User mUser = userDAO.findById(id);
			if (mUser != null)
				return convertModelToBean(mUser);

			return null;

		} catch (Exception e) {
			logger.error("getUserById has exception", e);

			return null;
		}
	}

	@Override
	public UserInfo createUser(UserInfo userBean) {
		try {

			return convertModelToBean(userDAO.save(convertBeanToModel(userBean)));

		} catch (Exception e) {
			logger.error("createUser has exception", e);

			throw e;
		}
	}

	@Override
	public boolean updateUser(UserInfo userBean) {

		try {
			User mUser = userDAO.findById(userBean.getId(), true);

			if (mUser == null)
				return false;

			mUser.setUserName(userBean.getUserName());
			mUser.setAddress(userBean.getAddress());
			mUser.setEmail(userBean.getEmail());
			mUser.setGender(Integer.parseInt(UserInfo.KEY_GENDER_FMALE));

			if (UserInfo.KEY_GENDER_MALE.equals(userBean.getGender()))
				mUser.setGender(Integer.parseInt(UserInfo.KEY_GENDER_MALE));

			mUser.setPassword(userBean.getPassword());
			mUser.setTelNumber(userBean.getTelNumber());

			userDAO.save(mUser);

			return true;

		} catch (Exception e) {
			logger.error("updateUser has exception", e);

			throw e;
		}
	}

	@Override
	public boolean deleteUser(Integer id) {
		try {
			User mUser = userDAO.findById(id, true);
			userDAO.delete(mUser);
			return true;
		} catch (Exception e) {
			logger.error("deleteUser has exception", e);

			throw e;
		}
	}

	public UserInfo convertModelToBean(User mUser) {
		UserInfo user = new UserInfo();

		user.setId(mUser.getId());
		user.setUserName(mUser.getUserName());
		user.setAddress(mUser.getAddress());
		user.setEmail(mUser.getEmail());
		user.setGender(UserInfo.VALUE_GENDER_FMALE);

		if (UserInfo.KEY_GENDER_MALE.equals(mUser.getGender()))
			user.setGender(UserInfo.VALUE_GENDER_MALE);

		user.setPassword(mUser.getPassword());
		user.setTelNumber(mUser.getTelNumber());

		return user;
	}

	public User convertBeanToModel(UserInfo userBean) {
		User user = new User();

		user.setId(userBean.getId());
		user.setUserName(userBean.getUserName());
		user.setAddress(userBean.getAddress());
		user.setEmail(userBean.getEmail());
		user.setGender(Integer.parseInt(UserInfo.KEY_GENDER_FMALE));

		if (UserInfo.KEY_GENDER_MALE.equals(userBean.getGender()))
			user.setGender(Integer.parseInt(UserInfo.KEY_GENDER_MALE));

		user.setPassword(userBean.getPassword());
		user.setTelNumber(userBean.getTelNumber());

		return user;
	}

}
