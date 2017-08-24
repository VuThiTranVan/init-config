package com.framgia.controller;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
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

import com.framgia.bean.ConditionGroupBean;
import com.framgia.bean.GroupInfo;
import com.framgia.bean.StatisticalInfo;
import com.framgia.service.ManageGroupService;
import com.framgia.util.DateUtil;

/**
 * ManagementUsersController.java
 * 
 * @version 18/04/2017
 * @author phan.van.hieu@framgia.com
 */
@RestController
@EnableScheduling
@Component
public class ManageGroupController {

	// log
	private static final Logger logger = Logger.getLogger(ManageGroupController.class);

	@Autowired
	ManageGroupService manageGroupService;

	@InitBinder
	public void initBinder(WebDataBinder webDataBinder) {
		webDataBinder.registerCustomEditor(Date.class, new CustomDateEditor(DateUtil.getSimpleDateFormat(), true));
	}

	@RequestMapping(value = "/manageGroup", method = RequestMethod.GET)
	public ModelAndView adminPage() {

		return new ModelAndView("managegroup");
	}

	@RequestMapping(value = "/searchgroup", method = RequestMethod.GET)
	public @ResponseBody List<com.framgia.bean.GroupInfo> findByCondition(
	        @ModelAttribute("conditionSearchGroup") ConditionGroupBean conditionGroupBean,
	        RedirectAttributes redirectAttributes) {
		logger.info("call service; get list group");

		List<com.framgia.bean.GroupInfo> groupInfo = manageGroupService.findByGroupWithCondition(conditionGroupBean);

		return groupInfo;
	}

	@RequestMapping(value = "/detailgroup/{id}", method = RequestMethod.GET)
	public ModelAndView detailPage(@PathVariable("id") Integer id) {
		logger.info("call service: get group");

		// get infor group
		GroupInfo groupInfo = manageGroupService.findById(id);

		// render page detail group
		return new ModelAndView("detailgroup", "groupInfo", groupInfo);
	}

	@RequestMapping(value = "/deletegroup/{id}", method = RequestMethod.PUT)
	public @ResponseBody Boolean delLogicUser(@PathVariable("id") Integer id) {
		logger.info("call service: delete group" + id);

		return manageGroupService.deleteGroup(id, getUserName());

	}

	@RequestMapping(value = "/staticticalgroup", method = RequestMethod.GET)
	public ModelAndView staticticalPage() {
		logger.info("Call page statictical group");

		return new ModelAndView("staticticalgroup");
	}

	@RequestMapping(value = "/staticticalgroup/{typeStatictical}", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody StatisticalInfo getSales(@PathVariable("typeStatictical") Integer typeStatictical) {

		return manageGroupService.getStatisticalInfo(typeStatictical);
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

	/**
	 * Backgroud job auto delete group expired
	 */
	@Scheduled(cron = "0 0 1 * * *", zone = "Asia/Saigon")
	public void writeCurrentTime() {

		logger.info("Call background job");
		// Call service manage groud
		manageGroupService.backgroundJobDeleteGroup();
	}
}
