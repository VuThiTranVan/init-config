package com.framgia.service;

import java.util.List;

import com.framgia.bean.ConditionGroupBean;
import com.framgia.bean.DataHighChart;
import com.framgia.bean.GroupInfo;

public interface GroupService extends BaseService {

	GroupInfo findById(Integer id, boolean flgUpdate);

	boolean createGroup(GroupInfo groupInfo);

	boolean updateGroup(GroupInfo groupInfo);

	boolean deleteLogicGroup(Integer id);

	DataHighChart getDataForHighchart(Integer idUser);

	List<GroupInfo> findByGroupWithName(String name);
}