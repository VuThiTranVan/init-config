package com.framgia.bean;

import java.util.Date;

/**
 * 
 * @version 22/05/2017
 * @author vu.thi.tran.van@framgia.com
 * 
 */
public class VoteInfo {
	private Integer id;
	private ImageInfo image;
	private UserInfo user;
	private String deleteFlag;
	private Date dateCreate;
	private String userUpdate;
	private Date dateUpdate;

	public VoteInfo() {
		super();
	}

	public VoteInfo(Integer id, ImageInfo image, UserInfo user, String deleteFlag, Date dateCreate, String userUpdate,
	        Date dateUpdate) {
		super();
		this.id = id;
		this.image = image;
		this.user = user;
		this.deleteFlag = deleteFlag;
		this.dateCreate = dateCreate;
		this.userUpdate = userUpdate;
		this.dateUpdate = dateUpdate;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public ImageInfo getImage() {
		return image;
	}

	public void setImage(ImageInfo image) {
		this.image = image;
	}

	public UserInfo getUser() {
		return user;
	}

	public void setUser(UserInfo user) {
		this.user = user;
	}

	public String getDeleteFlag() {
		return deleteFlag;
	}

	public void setDeleteFlag(String deleteFlag) {
		this.deleteFlag = deleteFlag;
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

}
