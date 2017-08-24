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
public class GroupInfo {
	private Integer id;
	private UserInfo userCreate;
	private String name;
	private String description;
	private String note;
	private String type;
	private String status;
	private String dateStart;
	private String dateEnd;
	private String deleteFlag;
	private Date dateCreate;
	private String userUpdate;
	private Date dateUpdate;
	private List<ImageInfo> image = new ArrayList<ImageInfo>();
	private List<UserInfo> user = new ArrayList<UserInfo>();

	public GroupInfo() {
		super();
	}

	public GroupInfo(Integer id, UserInfo userCreate, String name, String description, String note, String type,
			String status, String dateStart, String dateEnd, String deleteFlag, Date dateCreate, String userUpdate,
	        Date dateUpdate, List<ImageInfo> image, List<UserInfo> user) {
		super();
		this.id = id;
		this.userCreate = userCreate;
		this.name = name;
		this.description = description;
		this.note = note;
		this.type = type;
		this.status = status;
		this.dateStart = dateStart;
		this.dateEnd = dateEnd;
		this.deleteFlag = deleteFlag;
		this.dateCreate = dateCreate;
		this.userUpdate = userUpdate;
		this.dateUpdate = dateUpdate;
		this.image = image;
		this.user = user;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public UserInfo getUserCreate() {
		return userCreate;
	}

	public void setUserCreate(UserInfo userCreate) {
		this.userCreate = userCreate;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getDateStart() {
		return dateStart;
	}

	public void setDateStart(String dateStart) {
		this.dateStart = dateStart;
	}

	public String getDateEnd() {
		return dateEnd;
	}

	public void setDateEnd(String dateEnd) {
		this.dateEnd = dateEnd;
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

	public List<ImageInfo> getImage() {
		return image;
	}

	public void setImage(List<ImageInfo> image) {
		this.image = image;
	}

	public List<UserInfo> getUser() {
		return user;
	}

	public void setUser(List<UserInfo> user) {
		this.user = user;
	}

}
