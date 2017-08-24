package demo.service;

import java.io.Serializable;
import java.util.List;

import demo.bean.ConditionSearchUser;
import demo.bean.UserInfo;

public interface UserService extends Serializable {
	List<UserInfo> findAll();

	List<UserInfo> findByCondition(ConditionSearchUser condition);
	
	UserInfo getUserByUserName(String username);

	UserInfo getUserById(Integer id);

	UserInfo createUser(UserInfo userBean);

	boolean updateUser(UserInfo userBean);

	boolean deleteUser(Integer id);

}
