package demo.DAO.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import demo.DAO.GenericDAO;
import demo.DAO.UserDAO;
import demo.bean.ConditionSearchUser;
import demo.model.User;

@Repository
public class UserDAOImpl extends GenericDAO<User, Integer> implements UserDAO {

	private static final long serialVersionUID = 1L;

	// log
	private static final Logger logger = Logger.getLogger(UserDAOImpl.class);

	public UserDAOImpl() {
		super(User.class);
	}
	
	@Override
	public User finByUserName(String userName) {
		logger.info("Search by user name");
		Criteria criterion = getSession().createCriteria(User.class).add(Restrictions.eq("userName", userName));
		return (User) criterion.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> findByCondition(ConditionSearchUser condition) {
		logger.info("Search list user.");
		Criteria criterion = getSession().createCriteria(User.class);

		if (condition.getUserName() != null) {
			if (StringUtils.isNotEmpty(condition.getUserName()))
				criterion.add(Restrictions.like("userName", "%" + condition.getUserName() + "%"));

			if (StringUtils.isNotEmpty(condition.getAddress()))
				criterion.add(Restrictions.like("address", "%" + condition.getAddress() + "%"));

			if (StringUtils.isNotEmpty(condition.getGender()))
				criterion.add(Restrictions.eq("gender", condition.getGender()));

		}

		return criterion.list();
	}

}
