package com.framgia.controller;

import java.security.Principal;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.framgia.bean.ImageInfo;
import com.framgia.bean.Message;
import com.framgia.bean.MessageGreeter;
import com.framgia.bean.PagingImage;
import com.framgia.service.ImageService;
import com.framgia.service.UserService;
import com.framgia.util.Constants;
import com.framgia.util.Helpers;

/**
 * Created by phan.van.hieu@framgia.com on 6/3/2017
 */
@RestController
public class ChatSocketController {

	// log
	private static final Logger logger = Logger.getLogger(ManageUserController.class);

	@Autowired
	UserService userService;

	@Autowired
	ImageService imageService;

	@MessageMapping("/request")
	@SendTo("/topic/greetings")
	public MessageGreeter greeting(Message message, Principal principal) throws Exception {

		logger.info("Request service: " + message.getName());
		return new MessageGreeter(principal.getName() + " : &lt;b&gt;" + message.getName() + "&lt;/b&gt;");
	}

	@RequestMapping(value = "/chat", method = RequestMethod.GET)
	public ModelAndView chatPage() {

		logger.info("Call page chat client");
		return new ModelAndView("chat", "userlogin", Helpers.getUsername());
	}

	@RequestMapping(value = "/homePageUserLogin", method = RequestMethod.GET)
	public ModelAndView initPageUser() {
		logger.info("Init page image");
		List<ImageInfo> listImage = imageService.getListImage(null, Constants.NUMBER_PAGE_DEFAULT);

		ModelAndView mv;
		Integer check = userService.getInfoUser(Helpers.getIdUser());
		if (check == null) {
			return new ModelAndView("login");
		}

		if (check == 1) {
			mv = new ModelAndView("homePageAdmin", "image", listImage);
		} else if (check == 2) {
			mv = new ModelAndView("homeManagePage", "image", listImage);
		} else if (check == 3) {
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

}
