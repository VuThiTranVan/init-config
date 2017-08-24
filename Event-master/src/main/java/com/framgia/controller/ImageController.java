package com.framgia.controller;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.framgia.bean.ImageInfo;
import com.framgia.bean.PagingImage;
import com.framgia.service.ImageService;
import com.framgia.service.UserService;
import com.framgia.util.Constants;
import com.framgia.util.DateUtil;
import com.framgia.util.Helpers;

/**
 * 
 * @version 05/06/2017
 * @author vu.thi.tran.van@framgia.com
 * 
 */
@RestController
public class ImageController {
	private static final Logger logger = Logger.getLogger(ImageController.class);

	@Autowired
	ImageService imageService;

	@Autowired
	UserService userService;

	@InitBinder
	public void initBinder(WebDataBinder webDataBinder) {
		webDataBinder.registerCustomEditor(Date.class, new CustomDateEditor(DateUtil.getSimpleDateFormat(), true));
	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView initPage() {
		logger.info("Init page image");
		ModelAndView mv = null;
		List<ImageInfo> listImage = imageService.getListImage(null, Constants.NUMBER_PAGE_DEFAULT);

		if (Helpers.getUsername() == null) {
			mv = new ModelAndView("homeGuest", "image", listImage);
		} else {
			Integer check = imageService.getPermissionId(Helpers.getUsername());

			if (check == null) {
				return new ModelAndView("login");
			} else if (check == 1) {
				mv = new ModelAndView("homePageAdmin", "image", listImage);
			} else if (check == 2) {
				mv = new ModelAndView("homeManagePage", "image", listImage);
			} else {
				if (imageService.getInfoUser(check) == null) {
					return new ModelAndView("login");
				}
				if (imageService.getInfoUser(Helpers.getIdUser())) {
					mv = new ModelAndView("homeUserGroup", "image", listImage);
				} else {
					mv = new ModelAndView("homeUser", "image", listImage);
				}
			}
		}
		Integer noOfRecord = imageService.getNoOfRecord(null);
		if (noOfRecord == null) {
			mv.addObject("paging", null);
			return mv;
		}

		PagingImage paging = new PagingImage(noOfRecord,
		        (int) Math.ceil(noOfRecord * 1.0 / Constants.NUMBER_PAGE_LIMIT), 1, 2, 0);
		mv.addObject("paging", paging);
		mv.addObject("valueSearch", null);

		return mv;
	}

	@RequestMapping(value = { "/user", "/searchImage" }, method = RequestMethod.GET)
	public ModelAndView initPageUser() {
		logger.info("Init page image");
		List<ImageInfo> listImage = imageService.getListImage(null, Constants.NUMBER_PAGE_DEFAULT);

		Boolean check = imageService.getInfoUser(Helpers.getIdUser());
		ModelAndView mv;
		if (check == null) {
			return new ModelAndView("login");
		}
		if (check) {
			mv = new ModelAndView("homeUserGroup", "image", listImage);
		} else {
			mv = new ModelAndView("homeUser", "image", listImage);
		}

		Integer noOfRecord = imageService.getNoOfRecord(null);
		if (noOfRecord == null) {
			mv.addObject("paging", null);
			return mv;
		}

		PagingImage paging = new PagingImage(noOfRecord,
		        (int) Math.ceil(noOfRecord * 1.0 / Constants.NUMBER_PAGE_LIMIT), 1, 2, 0);
		mv.addObject("paging", paging);
		mv.addObject("valueSearch", null);

		return mv;
	}

	@RequestMapping(value = "/searchImage", method = RequestMethod.POST)
	public ModelAndView findByCondition(@RequestParam String valueSearch, @RequestParam int noPage) {

		if (noPage == 0) {
			noPage = Constants.NUMBER_PAGE_DEFAULT;
		}

		List<ImageInfo> image = imageService.getListImage(valueSearch, noPage);

		Integer noOfRecord = imageService.getNoOfRecord(valueSearch);

		PagingImage paging = new PagingImage(noOfRecord,
		        (int) Math.ceil(noOfRecord * 1.0 / Constants.NUMBER_PAGE_LIMIT), noPage, noPage + 1, noPage - 1);

		ModelAndView model;
		Integer check = userService.getInfoUser(Helpers.getIdUser());
		if (check == null) {
			return new ModelAndView("login");
		}

		if (check == 1) {
			model = new ModelAndView("homePageAdmin", "image", image);
		} else if (check == 2) {
			model = new ModelAndView("homeManagePage", "image", image);
		} else if (check == 3) {
			model = new ModelAndView("homeUserGroup", "image", image);
		} else {
			model = new ModelAndView("homeUser", "image", image);
		}

		model.addObject("paging", paging);
		model.addObject("valueSearch", valueSearch);
		return model;
	}

	@RequestMapping(value = "/user/vote/add/{id}", method = RequestMethod.GET)
	@ResponseBody
	public boolean addVote(@PathVariable("id") Integer id) {
		return imageService.addVote(id, Helpers.getIdUser());
	}

	@RequestMapping(value = "/user/vote/remove/{id}", method = RequestMethod.GET)
	@ResponseBody
	public boolean removeVote(@PathVariable("id") Integer id) {

		return imageService.removeVote(id, Helpers.getIdUser());

	}

	@RequestMapping(value = { "/homePageUserGroup" }, method = RequestMethod.GET)
	public ModelAndView homeUserGroupPage() {

		logger.info("Init page image manager Group");
		List<ImageInfo> listImage = imageService.getListImage(null, Constants.NUMBER_PAGE_DEFAULT);
		ModelAndView mv = new ModelAndView("homeUserGroup", "image", listImage);

		Integer noOfRecord = imageService.getNoOfRecord(null);
		if (noOfRecord == null) {
			mv.addObject("paging", null);
			return mv;
		}

		PagingImage paging = new PagingImage(noOfRecord,
		        (int) Math.ceil(noOfRecord * 1.0 / Constants.NUMBER_PAGE_LIMIT), 1, 2, 0);
		mv.addObject("paging", paging);
		mv.addObject("valueSearch", null);

		return mv;
	}

	@RequestMapping(value = { "/homePageUser" }, method = RequestMethod.GET)
	public ModelAndView homeUserPage() {

		logger.info("Init page image manager Group");
		List<ImageInfo> listImage = imageService.getListImage(null, Constants.NUMBER_PAGE_DEFAULT);
		ModelAndView mv = new ModelAndView("homeUser", "image", listImage);

		Integer noOfRecord = imageService.getNoOfRecord(null);
		if (noOfRecord == null) {
			mv.addObject("paging", null);
			return mv;
		}

		PagingImage paging = new PagingImage(noOfRecord,
		        (int) Math.ceil(noOfRecord * 1.0 / Constants.NUMBER_PAGE_LIMIT), 1, 2, 0);
		mv.addObject("paging", paging);
		mv.addObject("valueSearch", null);

		return mv;
	}
}
