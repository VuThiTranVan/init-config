package com.framgia.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 
 * @version 22/05/2017
 * @author vu.thi.tran.van@framgia.com
 * 
 */
public class ImageInfo {
	private Integer id;
	private String url;
	private String description;
	private String title;
	private GroupInfo group;
	private String deleteFlag;
	private String userCreate;
	private Date dateCreate;
	private String userUpdate;
	private Date dateUpdate;
	List<VoteInfo> votes = new ArrayList<VoteInfo>();;

	public ImageInfo() {
		super();
	}

	public ImageInfo(Integer id, String url, String description, String title, GroupInfo group, String deleteFlag,
	        String userCreate, Date dateCreate, String userUpdate, Date dateUpdate, List<VoteInfo> votes) {
		super();
		this.id = id;
		this.url = url;
		this.description = description;
		this.title = title;
		this.group = group;
		this.deleteFlag = deleteFlag;
		this.userCreate = userCreate;
		this.dateCreate = dateCreate;
		this.userUpdate = userUpdate;
		this.dateUpdate = dateUpdate;
		this.votes = votes;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public GroupInfo getGroup() {
		return group;
	}

	public void setGroup(GroupInfo group) {
		this.group = group;
	}

	public String getDeleteFlag() {
		return deleteFlag;
	}

	public void setDeleteFlag(String deleteFlag) {
		this.deleteFlag = deleteFlag;
	}

	public String getUserCreate() {
		return userCreate;
	}

	public void setUserCreate(String userCreate) {
		this.userCreate = userCreate;
	}

	public Date getDateCreate() {
		return dateCreate;
	}

	public void setDateCreate(Date dateCreate) {
		this.dateCreate = dateCreate;
	}

	public String getUserUpdate() {
		return userUpdate;
	}

	public void setUserUpdate(String userUpdate) {
		this.userUpdate = userUpdate;
	}

	public Date getDateUpdate() {
		return dateUpdate;
	}

	public void setDateUpdate(Date dateUpdate) {
		this.dateUpdate = dateUpdate;
	}

	public List<VoteInfo> getVotes() {
		return votes;
	}

	public void setVotes(List<VoteInfo> votes) {
		this.votes = votes;
	}

}
