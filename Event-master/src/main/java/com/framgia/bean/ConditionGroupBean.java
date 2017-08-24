package com.framgia.bean;

/**
 * Created by PhanVanHieu on 23/05/2017.
 */
public class ConditionGroupBean {

	private String nameGroup;
	private Integer typeGroup;

	public ConditionGroupBean() {
		super();
	}

	public ConditionGroupBean(String nameGroup, Integer typeGroup) {
		super();
		this.nameGroup = nameGroup;
		this.typeGroup = typeGroup;
	}

	public String getNameGroup() {
		return nameGroup;
	}

	public void setNameGroup(String nameGroup) {
		this.nameGroup = nameGroup;
	}

	public Integer getTypeGroup() {
		return typeGroup;
	}

	public void setTypeGroup(Integer typeGroup) {
		this.typeGroup = typeGroup;
	}

}
