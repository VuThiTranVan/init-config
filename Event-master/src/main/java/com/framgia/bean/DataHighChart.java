package com.framgia.bean;

import java.util.List;

public class DataHighChart {
	private String note;
	private List<String> content;

	public DataHighChart() {
		super();
		// TODO Auto-generated constructor stub
	}

	public DataHighChart(String note, List<String> content) {
		super();
		this.note = note;
		this.content = content;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public List<String> getContent() {
		return content;
	}

	public void setContent(List<String> content) {
		this.content = content;
	}

}
