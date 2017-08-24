package com.framgia.service;

import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.framgia.bean.ConditionUserBean;
import com.framgia.bean.PermissionInfo;
import com.framgia.bean.StatisticalInfo;
import com.framgia.bean.UserInfo;

/**
 * ManagementUsersService.java
 * 
 * @version 18/04/2017
 * @author vu.thi.tran.van@framgia.com
 * 
 */
public interface ManageUserService extends BaseService {

	List<PermissionInfo> findByDelFlg();

	List<UserInfo> findByUsersWithCondition(ConditionUserBean conditionSearch);

	UserInfo findById(Integer id);

	Boolean deleteUser(Integer idUser, String userName);

	StatisticalInfo getStatisticalInfo(Integer groupType);
	
	void exportUser(HttpServletRequest request, HttpServletResponse response, ServletContext context);

}
