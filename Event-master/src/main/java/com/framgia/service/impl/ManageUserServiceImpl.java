package com.framgia.service.impl;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.framgia.bean.ConditionUserBean;
import com.framgia.bean.PermissionInfo;
import com.framgia.bean.StatisticalInfo;
import com.framgia.bean.UserInfo;
import com.framgia.model.Group;
import com.framgia.model.Permission;
import com.framgia.model.User;
import com.framgia.service.ManageUserService;
import com.framgia.util.Constants;
import com.framgia.util.DateUtil;
import com.framgia.util.Helpers;

import net.sf.jxls.transformer.XLSTransformer;

/**
 * ManagementUsersServiceImpl.java
 * 
 * @version 18/04/2017
 * @author vu.thi.tran.van@framgia.com
 * 
 */
@SuppressWarnings("serial")
public class ManageUserServiceImpl extends BaseServiceImpl implements ManageUserService {

	private static final Logger logger = Logger.getLogger(ManageUserServiceImpl.class);

	@Override
	public List<UserInfo> findByUsersWithCondition(ConditionUserBean conditionUserBean) {
		try {
			List<User> userList = userDAO.findByConditon(conditionUserBean);
			if (Helpers.isEmpty(userList)) {
				return null;
			}
			List<UserInfo> userInfo = new ArrayList<UserInfo>();

			for (User item : userList) {

				UserInfo user = new UserInfo();

				user.setId(item.getId());
				user.setUsername(item.getUsername());
				user.setName(item.getName());
				if (Constants.GENDER_CODE_MALE.equals(item.getGender())) {
					user.setGender(Constants.GENDER_VALUE_MALE);
				} else {
					user.setGender(Constants.GENDER_VALUE_FMALE);
				}
				user.setBirthday(item.getBirthday());
				user.setPhone(item.getPhone());
				user.setEmail(item.getEmail());

				PermissionInfo perInfo = new PermissionInfo();

				perInfo.setName(item.getPermission().getName());
				user.setPermission(perInfo);

				userInfo.add(user);

			}
			return userInfo;
		} catch (Exception e) {
			logger.info("Exception at function findByUsersWithCondition in ManageUserServiceImpl: ", e);
		}
		return null;
	}

	@Override
	public List<PermissionInfo> findByDelFlg() {
		try {
			// get value permission role for select box
			List<PermissionInfo> permissionInfo = new ArrayList<PermissionInfo>();

			logger.info("Start find data Permission");
			List<Permission> permissionList = permissionDAO.findByDelFlg();
			if (Helpers.isEmpty(permissionList)) {
				return null;
			}
			for (Permission item : permissionList) {

				PermissionInfo per = new PermissionInfo();
				per.setId(item.getId());
				per.setName(item.getName());
				permissionInfo.add(per);
			}

			return permissionInfo;
		} catch (Exception e) {
			logger.info("Exception at function findByDelFlg in ManageUserServiceImpl: ", e);
		}
		return null;
	}

	@Override
	public UserInfo findById(Integer id) {
		try {
			User user = userDAO.findById(id, false);

			if (user == null) {
				return null;
			}
			UserInfo userInfo = new UserInfo();

			userInfo.setId(user.getId());
			userInfo.setUsername(user.getUsername());
			userInfo.setName(user.getName());
			if (Constants.GENDER_CODE_MALE.equals(user.getGender())) {
				userInfo.setGender(Constants.GENDER_VALUE_MALE);
			} else {
				userInfo.setGender(Constants.GENDER_VALUE_FMALE);
			}
			userInfo.setBirthday(user.getBirthday());
			userInfo.setPhone(user.getPhone());
			userInfo.setEmail(user.getEmail());

			PermissionInfo perInfo = new PermissionInfo();

			perInfo.setName(user.getPermission().getName());
			userInfo.setPermission(perInfo);

			userInfo.setIdGroup(user.getIdGroup());

			if (Constants.STATUSJOIN_CODE_FREE.equals(user.getStatusJoin())) {
				userInfo.setStatusJoin(Constants.STATUSJOIN_VALUE_FREE);
			} else if (Constants.STATUSJOIN_CODE_REQUEST.equals(user.getStatusJoin())) {
				userInfo.setStatusJoin(Constants.STATUSJOIN_VALUE_REQUEST);
			} else {
				userInfo.setStatusJoin(Constants.STATUSJOIN_VALUE_APPOVE);
			}
			userInfo.setDateCreate(user.getDateCreate());
			userInfo.setDateUpdate(user.getDateUpdate());
			userInfo.setUserCreate(user.getUserCreate());
			userInfo.setUserUpdate(user.getUserUpdate());
			return userInfo;
		} catch (Exception e) {
			logger.info("Exception at function findByUsersWithCondition in ManageUserServiceImpl: ", e);
		}
		return null;
	}

	@Override
	public Boolean deleteUser(Integer idUser, String userName) {
		try {
			User updUser = userDAO.findById(idUser, true);
			if (updUser == null) {
				return false;
			}
			updUser.setDeleteFlag(Constants.DEL_FLG_DEL);
			updUser.setUserUpdate(userName);
			updUser.setDateUpdate(DateUtil.getDateNow());

			userDAO.saveOrUpdate(updUser);

			if (updUser.getIdGroup() != null && updUser.getPermission().getId() == 2) {

				Group group = groupDAO.findById(updUser.getIdGroup(), true);

				if (group != null) {
					group.setDeleteFlag(Constants.DEL_FLG_DEL);
					group.setUserUpdate(userName);
					group.setDateUpdate(DateUtil.getDateNow());

					groupDAO.saveOrUpdate(group);
					for (User item : group.getUsers()) {
						if (item == null)
							continue;

						if (item.getPermission().getId().equals(Constants.PERMISSION_CODE_MANAGER)
								&& group.getDeleteFlag().equals(Constants.DEL_FLG_DEL))
							continue;

						User user = getUserDAO().findById(item.getId(), true);
						user.setIdGroup(null);
						user.setStatusJoin(Constants.STATUSJOIN_CODE_FREE);
						user.setDateUpdate(DateUtil.getDateNow());
						user.setUserUpdate(userName);

						if (group.getDeleteFlag() == Constants.DEL_FLG_DEL)
							user.setPermission(new Permission(Constants.PERMISSION_CODE_USER));

						userDAO.saveOrUpdate(user);
					}
				}
			}

			return true;

		} catch (Exception e) {
			logger.error("Error delete logic User: ", e);
			throw e;
		}
	}

	@Override
	public StatisticalInfo getStatisticalInfo(Integer groupType) {
		try {
			List<String> nameGroup = new ArrayList<>();

			List<Long> numberUser = new ArrayList<>();

			List<Group> groupList = groupDAO.findByGroupType(groupType);

			if (groupList == null) {
				return null;
			}
			for (Group group : groupList) {
				nameGroup.add(group.getName());
				numberUser.add(userDAO.getCountUser(group.getId()));
			}
			String nameTypeGroup;
			if (groupType == 0) {
				nameTypeGroup = Constants.GROUP_TYPE_VALUE_PRIVATE;
			} else {
				nameTypeGroup = Constants.GROUP_TYPE_VALUE_PUBLIC;
			}

			return new StatisticalInfo(nameTypeGroup, numberUser, nameGroup);
		} catch (Exception e) {
			logger.error("Exception at function getStatisticalInfo in ManageUserServiceImpl: ", e);
		}
		return null;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public void exportUser(HttpServletRequest request, HttpServletResponse response, ServletContext context) {
		try {

			// set output header
			ServletOutputStream os = response.getOutputStream();
			response.setContentType(Constants.FORMAT_FILE);
			response.setHeader(Constants.HEADER, Constants.HEADER_FILE);

			// Path container file excel
			String reportLocation = context.getRealPath(Constants.PATH);

			// List data export
			List idUser = new ArrayList();
			List permission = new ArrayList();
			List username = new ArrayList();
			List name = new ArrayList();
			List gender = new ArrayList();
			List birthday = new ArrayList();
			List phone = new ArrayList();
			List email = new ArrayList();
			List statusJoin = new ArrayList();
			List group = new ArrayList();
			List deleteFlag = new ArrayList();
			List dateCreate = new ArrayList();
			List userCreate = new ArrayList();
			List dateUpdate = new ArrayList();
			List userUpdate = new ArrayList();

			// Get list user
			List<User> users = userDAO.getListUser();

			// Get information user
			for (User user : users) {
				idUser.add(user.getId());
				permission.add(user.getPermission().getName());
				username.add(user.getUsername());
				name.add(user.getName());
				if (Constants.GENDER_CODE_FMALE.equals(user.getGender())) {
					gender.add(Constants.GENDER_VALUE_FMALE);
				} else {
					gender.add(Constants.GENDER_VALUE_MALE);
				}
				birthday.add(user.getBirthday());
				phone.add(user.getPhone());
				email.add(user.getEmail());

				if (Constants.STATUSJOIN_CODE_FREE.equals(user.getStatusJoin())) {
					statusJoin.add(Constants.STATUSJOIN_VALUE_FREE);
				} else if (Constants.STATUSJOIN_CODE_APPOVE.equals(user.getStatusJoin())) {
					statusJoin.add(Constants.STATUSJOIN_VALUE_APPOVE);
				} else {
					statusJoin.add(Constants.STATUSJOIN_VALUE_REQUEST);
				}
				if (user.getIdGroup() != null) {
					Group dataGroup = groupDAO.findById(user.getIdGroup(), false);
					if (dataGroup != null) {
						group.add(dataGroup.getName());
					}
				} else {
					group.add(null);
				}
				deleteFlag.add(user.getDeleteFlag());
				dateCreate.add(DateUtil.convertDateTimetoString(user.getDateUpdate()));
				userCreate.add(user.getUserCreate());
				dateUpdate.add(DateUtil.convertDateTimetoString(user.getDateUpdate()));
				userUpdate.add(user.getUserUpdate());
			}

			Map beans = new HashMap();
			beans.put("id", idUser);
			beans.put("permission", permission);
			beans.put("username", username);
			beans.put("fullname", name);
			beans.put("gender", gender);
			beans.put("birthday", birthday);
			beans.put("phone", phone);
			beans.put("email", email);
			beans.put("statusjoin", statusJoin);
			beans.put("group", group);
			beans.put("deleteflag", deleteFlag);
			beans.put("datecreate", dateCreate);
			beans.put("usercreate", userCreate);
			beans.put("dateupdate", dateUpdate);
			beans.put("userupdate", userUpdate);

			// Write file excel
			XLSTransformer transformer = new XLSTransformer();
			HSSFWorkbook workbook = transformer.transformXLS(new FileInputStream(reportLocation + Constants.FILE_NAME),
					beans);
			workbook.write(os);
			os.flush();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
