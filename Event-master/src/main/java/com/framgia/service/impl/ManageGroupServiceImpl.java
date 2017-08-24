package com.framgia.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import com.framgia.bean.ConditionGroupBean;
import com.framgia.bean.GroupInfo;
import com.framgia.bean.StatisticalInfo;
import com.framgia.model.Group;
import com.framgia.model.Permission;
import com.framgia.model.User;
import com.framgia.service.ManageGroupService;
import com.framgia.util.Constants;
import com.framgia.util.ConvetBeanAndModel;
import com.framgia.util.DateUtil;
import com.framgia.util.Helpers;

/**
 * ManagementUsersServiceImpl.java
 * 
 * @version 18/04/2017
 * @author phan.van.hieu@framgia.com
 * 
 */
@SuppressWarnings("serial")
public class ManageGroupServiceImpl extends BaseServiceImpl implements ManageGroupService {

	// Log
	private static final Logger logger = Logger.getLogger(ManageGroupServiceImpl.class);

	@Override
	public List<GroupInfo> findByGroupWithCondition(ConditionGroupBean conditionGroupBean) {
		try {
			List<Group> groupList = groupDAO.findByConditon(conditionGroupBean);
			if (Helpers.isEmpty(groupList)) {
				return null;
			}
			List<GroupInfo> groupInfo = new ArrayList<GroupInfo>();

			for (Group item : groupList) {

				GroupInfo group = new GroupInfo();

				group.setId(item.getId());
				group.setName(item.getName());
				group.setDescription(item.getDescription());
				if (Constants.GROUP_TYPE_CODE_PRIVATE.equals(item.getType().toString())) {
					group.setType(Constants.GROUP_TYPE_VALUE_PRIVATE);
				} else {
					group.setType(Constants.GROUP_TYPE_VALUE_PUBLIC);
				}
				if (Constants.GROUP_STATUS_CODE_ACTIVE.equals(item.getStatus().toString())) {
					group.setStatus(Constants.GROUP_STATUS_VALUE_ACTIVE);
				} else {
					group.setStatus(Constants.GROUP_STATUS_VALUE_INACTIVE);
				}
				group.setDateStart(DateUtil.convertDatetoString(item.getDateStart()));
				group.setDateEnd(DateUtil.convertDatetoString(item.getDateEnd()));

				groupInfo.add(group);

			}
			return groupInfo;
		} catch (Exception e) {
			logger.info("Exception at function findByGroupWithCondition in ManageGroupServiceImpl: ", e);
		}
		return null;
	}

	@Override
	public Boolean deleteGroup(Integer id, String userName) {
		try {
			Group group = groupDAO.findById(id, true);

			if (group == null) {
				return false;
			} else {
				group.setDeleteFlag(Constants.DEL_FLG_DEL);
				group.setUserUpdate(userName);
				group.setDateUpdate(DateUtil.getDateNow());

				groupDAO.saveOrUpdate(group);
				for (User item : group.getUsers()) {
					if (item == null)
						continue;

					if (item.getPermission().getId().equals(Constants.PERMISSION_CODE_MANAGER)
					        && group.getDeleteFlag().equals(Constants.DEL_FLG))
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

			return true;

		} catch (Exception e) {
			logger.error("Error delete logic User: ", e);
			throw e;
		}
	}

	@Override
	public GroupInfo findById(Integer id) {
		try {
			Group group = groupDAO.findById(id, false);

			if (group == null) {
				return null;
			}
			GroupInfo groupInfo = ConvetBeanAndModel.convertGroupModelToBean(group);

			if (Constants.GROUP_TYPE_CODE_PRIVATE.equals(groupInfo.getType())) {
				groupInfo.setType(Constants.GROUP_TYPE_VALUE_PRIVATE);
			} else {
				groupInfo.setType(Constants.GROUP_TYPE_VALUE_PUBLIC);
			}
			if (Constants.GROUP_STATUS_CODE_ACTIVE.equals(groupInfo.getStatus())) {
				groupInfo.setStatus(Constants.GROUP_STATUS_VALUE_ACTIVE);
			} else {
				groupInfo.setStatus(Constants.GROUP_STATUS_VALUE_INACTIVE);
			}

			return groupInfo;

		} catch (Exception e) {
			logger.info("Exception at function findById in ManageGroupServiceImpl: ", e);
		}
		return null;
	}

	@Override
	public StatisticalInfo getStatisticalInfo(Integer typeStatictical) {
		try {
			List<String> nameGroup = new ArrayList<>();

			List<Long> numberUser = new ArrayList<>();

			if (typeStatictical == 0) {
				nameGroup.add(Constants.GROUP_TYPE_VALUE_PRIVATE);
				numberUser.add(groupDAO.getCountType(Constants.DEFAULT_VALUE_0, null, Constants.DEL_FLG));

				nameGroup.add(Constants.GROUP_TYPE_VALUE_PUBLIC);
				numberUser.add(groupDAO.getCountType(Constants.DEFAULT_VALUE_1, null, Constants.DEL_FLG));
				return new StatisticalInfo(Constants.TYPE, numberUser, nameGroup);
			} else if (typeStatictical == 1) {
				nameGroup.add(Constants.GROUP_STATUS_VALUE_ACTIVE);
				numberUser.add(groupDAO.getCountType(null, Constants.DEFAULT_VALUE_0, Constants.DEL_FLG));

				nameGroup.add(Constants.GROUP_STATUS_VALUE_INACTIVE);
				numberUser.add(groupDAO.getCountType(null, Constants.DEFAULT_VALUE_1, Constants.DEL_FLG));
				return new StatisticalInfo(Constants.STATUS, numberUser, nameGroup);
			} else {
				nameGroup.add(Constants.NOT_DELETE);
				numberUser.add(groupDAO.getCountType(null, null, Constants.DEL_FLG));

				nameGroup.add(Constants.DELETED);
				numberUser.add(groupDAO.getCountType(null, null, Constants.DEL_FLG_DEL));
				return new StatisticalInfo(Constants.DELETE_FLAG, numberUser, nameGroup);
			}
		} catch (Exception e) {
			logger.error("Exception at function getStatisticalInfo in ManageGroupServiceImpl: ", e);
		}
		return null;
	}
	@Override
	public void backgroundJobDeleteGroup() {
		try {
			List<Group> groups = groupDAO.getListGroup();

			logger.info("background auto delete ground expired");
			if (groups != null) {

				// Define systemDate
				Date systemDate = DateUtil.getDateNow();

				for (Group group : groups) {
					if (group.getDateEnd().before(systemDate)) {
						Group dataGroup = groupDAO.findById(group.getId(), true);
						if (dataGroup != null) {
							dataGroup.setDeleteFlag(Constants.DEL_FLG_DEL);
							dataGroup.setUserUpdate(Constants.NAME_SYSTEM);
							dataGroup.setDateUpdate(DateUtil.getDateNow());

							// Delete group
							groupDAO.saveOrUpdate(dataGroup);
							
							for (User item : dataGroup.getUsers()) {
								if (item == null)
									continue;

								if (item.getPermission().getId().equals(Constants.PERMISSION_CODE_MANAGER)
								        && group.getDeleteFlag().equals(Constants.DEL_FLG))
									continue;

								User user = getUserDAO().findById(item.getId(), true);
								user.setIdGroup(null);
								user.setStatusJoin(Constants.STATUSJOIN_CODE_FREE);
								user.setDateUpdate(DateUtil.getDateNow());
								user.setUserUpdate(Constants.NAME_SYSTEM);

								if (group.getDeleteFlag() == Constants.DEL_FLG_DEL)
									user.setPermission(new Permission(Constants.PERMISSION_CODE_USER));

								userDAO.saveOrUpdate(user);
							}
						}
					}
				}
			}
		} catch (Exception e) {
			logger.error("Exception at function backgroundJobDeleteGroup in ManageGroupServiceImpl: ", e);
			throw e;
		}
	}

}
