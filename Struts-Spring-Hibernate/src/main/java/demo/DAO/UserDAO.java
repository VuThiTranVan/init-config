package demo.DAO;

import java.util.List;

import demo.bean.ConditionSearchUser;
import demo.model.User;

public interface UserDAO extends IGenericDAO<User, Integer> {
	List<User> findByCondition(ConditionSearchUser condition);

	User finByUserName(String userName);
}
