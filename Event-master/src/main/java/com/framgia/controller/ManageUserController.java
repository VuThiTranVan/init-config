package com.framgia.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.framgia.bean.ConditionUserBean;
import com.framgia.bean.PermissionInfo;
import com.framgia.bean.StatisticalInfo;
import com.framgia.bean.UserInfo;
import com.framgia.service.ManageUserService;
import com.framgia.util.DateUtil;

/**
 * ManagementUsersController.java
 * 
 * @version 18/04/2017
 * @author vu.thi.tran.van@framgia.com
 */
@RestController
public class ManageUserController {

	// log
	private static final Logger logger = Logger.getLogger(ManageUserController.class);

	@Autowired
	ManageUserService manageUserService;

	@Autowired
	private ServletContext context;

	@InitBinder
	public void initBinder(WebDataBinder webDataBinder) {
		webDataBinder.registerCustomEditor(Date.class, new CustomDateEditor(DateUtil.getSimpleDateFormat(), true));
	}

	@RequestMapping(value = "/manageUser", method = RequestMethod.GET)
	public ModelAndView adminPage() {
		logger.info("call service: get list permission");

		// get value permission role for select box
		List<PermissionInfo> permissionInfo = manageUserService.findByDelFlg();

		return new ModelAndView("manageuser", "permissionInfo", permissionInfo);
	}

	@RequestMapping(value = "/search", method = RequestMethod.GET)
	public @ResponseBody List<com.framgia.bean.UserInfo> findByCondition(
			@ModelAttribute("conditionSearch") ConditionUserBean conditionSearch,
			RedirectAttributes redirectAttributes) {
		logger.info("call service; get list user");

		List<com.framgia.bean.UserInfo> userInfo = manageUserService.findByUsersWithCondition(conditionSearch);

		return userInfo;
	}

	@RequestMapping(value = "/detail/{id}", method = RequestMethod.GET)
	public ModelAndView detailPage(@PathVariable("id") Integer id) {
		logger.info("call service: get user");

		// get infor user
		UserInfo user = manageUserService.findById(id);

		// render page detail user
		ModelAndView mv = new ModelAndView("manageusedretail", "user", user);

		return mv;
	}

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	public @ResponseBody Boolean delLogicUser(@PathVariable("id") int idUser) {
		logger.info("call service: delete user" + idUser);

		return manageUserService.deleteUser(idUser, getUserName());

	}

	@RequestMapping(value = "/statictical", method = RequestMethod.GET)
	public ModelAndView staticticalPage() {
		logger.info("Call page statictical");

		return new ModelAndView("statictical");
	}

	@RequestMapping(value = "/statictical/{groupType}", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody StatisticalInfo getSales(@PathVariable("groupType") Integer groupType) {

		return manageUserService.getStatisticalInfo(groupType);
	}

	public String getUserName() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (!(auth instanceof AnonymousAuthenticationToken)) {
			UserDetails userDetail = (UserDetails) auth.getPrincipal();
			logger.info("username: " + userDetail.getUsername());

			return userDetail.getUsername();
		} else {
			return null;
		}
	}

	@RequestMapping(value = "/export", method = RequestMethod.GET)
	public String export(HttpServletRequest request, HttpServletResponse response) {
		manageUserService.exportUser(request, response, context);
		return null;
	}
}
