package demo.action;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionSupport;

import demo.bean.UserInfo;
import demo.service.UserService;

@SuppressWarnings("serial")
public class UserAction extends ActionSupport {
	private static final Logger logger = Logger.getLogger(UserAction.class);

	@Autowired
	private UserService userService;

	private UserInfo userInfo;

	public String execute() {
		userInfo = userService.getUserByUserName("vanvtt");
//		userInfo = userService.getUserById(1);
		logger.info("call excute");
		return SUCCESS;
	}

	public UserInfo getUserInfo() {
		return userInfo;
	}

	public void setUserInfo(UserInfo userInfo) {
		this.userInfo = userInfo;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}
}
