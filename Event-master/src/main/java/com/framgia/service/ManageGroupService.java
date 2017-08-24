package com.framgia.service;

import java.util.List;

import com.framgia.bean.ConditionGroupBean;
import com.framgia.bean.GroupInfo;
import com.framgia.bean.StatisticalInfo;

/**
 * ManagementUsersService.java
 * 
 * @version 18/04/2017
 * @author vu.thi.tran.van@framgia.com
 * 
 */
public interface ManageGroupService extends BaseService {

	List<GroupInfo> findByGroupWithCondition(ConditionGroupBean conditionGroupBean);

	Boolean deleteGroup(Integer id, String userName);

	GroupInfo findById(Integer id);

	StatisticalInfo getStatisticalInfo(Integer typeStatictical);

	void backgroundJobDeleteGroup();
}
