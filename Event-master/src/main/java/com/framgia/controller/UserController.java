package com.framgia.controller;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
import org.springframework.web.util.UriComponentsBuilder;

import com.framgia.bean.GroupInfo;
import com.framgia.bean.UserInfo;
import com.framgia.service.GroupService;
import com.framgia.service.UserService;
import com.framgia.util.Constants;
import com.framgia.util.DateUtil;
import com.framgia.util.Helpers;

/**
 * 
 * @version 24/05/2017
 * @author vu.thi.tran.van@framgia.com
 * 
 */
@RestController
public class UserController {
	private static final Logger logger = Logger.getLogger(UserController.class);

	@Autowired
	UserService userService;

	@Autowired
	GroupService groupService;

	@InitBinder
	public void initBinder(WebDataBinder webDataBinder) {
		webDataBinder.registerCustomEditor(Date.class, new CustomDateEditor(DateUtil.getSimpleDateFormat(), true));
	}

	@RequestMapping(value = { "/register" }, method = RequestMethod.GET)
	public ModelAndView initRegisterPage() {
		logger.info("Regiter page: INIT");

		UserInfo user = new UserInfo();
		user.setUsername("");
		user.setPassword("");
		user.setName("");
		user.setBirthday("");
		user.setPhone("");
		user.setGender(Constants.GENDER_CODE_MALE);
		user.setEmail("");

		return new ModelAndView("registerUser", "user", user);
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public RedirectView createUser(@ModelAttribute("user") UserInfo user, UriComponentsBuilder ucBuilder) {
		logger.info("Regiter page: execute");
		userService.addUser(user);

		return new RedirectView("/EventMedia/login");
	}

	@RequestMapping(value = "/register/isExitUsername", method = RequestMethod.GET)
	public ResponseEntity<Void> isExitUsername(@RequestParam String username, UriComponentsBuilder ucBuilder) {
		UserInfo user = new UserInfo();
		user.setUsername(username);
		if (userService.isUserExist(user)) {
			return new ResponseEntity<Void>(HttpStatus.CONFLICT);
		}
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	// registe group
	@RequestMapping(value = { "/user/registerGroup" }, method = RequestMethod.GET)
	public ModelAndView initRegisterGroup() {
		logger.info("Regiter Group page: INIT");

		GroupInfo groupInfo = new GroupInfo();
		groupInfo.setName("");
		groupInfo.setDescription("");
		groupInfo.setNote("");
		groupInfo.setType(Constants.GROUP_TYPE_CODE_PRIVATE);
		groupInfo.setDateStart(null);
		groupInfo.setDateEnd(null);

		return new ModelAndView("registerGroup", "group", groupInfo);
	}

	@RequestMapping(value = "/user/registerGroup", method = RequestMethod.POST)
	public ResponseEntity<Void> registerGroup(@ModelAttribute("group") GroupInfo group) {
		boolean create = groupService.createGroup(group);
		if (!create)
			return new ResponseEntity<Void>(HttpStatus.CONFLICT);
		return new ResponseEntity<Void>(HttpStatus.CREATED);
	}

	@RequestMapping(value = { "/groupInfo/{id}" }, method = RequestMethod.GET)
	public ModelAndView initPage(@PathVariable("id") Integer id) {
		return new ModelAndView("initGroupAllPer", "idGroup", id);
	}

	@RequestMapping(value = { "/groupInfo/{id}" }, method = RequestMethod.POST)
	public @ResponseBody GroupInfo infoGroup(@PathVariable("id") Integer id) {
		return groupService.findById(id, false);
	}

	@RequestMapping(value = { "/groupInfo/{id}/request" }, method = RequestMethod.GET)
	public @ResponseBody boolean requestJoinGroup(@PathVariable("id") Integer id) {
		return userService.requestUserJoinGroup(id, Helpers.getIdUser());
	}

	// my Group
	@RequestMapping(value = { "/user/myGroup" }, method = RequestMethod.GET)
	public ModelAndView initMyGroup() {
		return new ModelAndView("myGroup");
	}

	@RequestMapping(value = { "/user/myGroup" }, method = RequestMethod.POST)
	public @ResponseBody GroupInfo infoMyGroup() {

		UserInfo user = userService.findById(Helpers.getIdUser(), false);
		if (user == null) {
			return null;
		}
		return groupService.findById(user.getIdGroup(), false);
	}

	@RequestMapping(value = { "/user/leaveGroup" }, method = RequestMethod.GET)
	public @ResponseBody boolean leaveGroup() {
		return userService.leaveGroup(Helpers.getIdUser());
	}
	
	// list group
	@RequestMapping(value = "/user/group", method = RequestMethod.GET)
	public ModelAndView adminPage() {

		return new ModelAndView("listGroup");
	}

	@RequestMapping(value = "/user/searchgroup", method = RequestMethod.GET)
	public @ResponseBody List<GroupInfo> findByCondition(
	        @ModelAttribute("name") String name) {
		logger.info("call service; get list group");

		return groupService.findByGroupWithName(name);

	}

}
