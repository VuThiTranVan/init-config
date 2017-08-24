package com.framgia.bean;

/**
 * Created by sazzad on 2/15/16
 */
public class Message {
	private String name;
	private String kindChat;

	public String getName() {
		return name;
	}

	public Message(String name, String kindChat) {
		super();
		this.name = name;
		this.kindChat = kindChat;
	}

	public Message() {
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getKindChat() {
		return kindChat;
	}

	public void setKindChat(String kindChat) {
		this.kindChat = kindChat;
	}

}
