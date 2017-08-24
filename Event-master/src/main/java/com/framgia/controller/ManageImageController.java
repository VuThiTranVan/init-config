package com.framgia.controller;

import java.io.FileNotFoundException;
import java.util.Date;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.framgia.bean.FileFormInfo;
import com.framgia.bean.ImageInfo;
import com.framgia.service.ManageImageService;
import com.framgia.util.DateUtil;
import com.framgia.util.Helpers;

/**
 * ManagementUsersController.java
 * 
 * @version 18/04/2017
 * @author phan.van.hieu@framgia.com
 */
@RestController
public class ManageImageController {

	// log
	private static final Logger logger = Logger.getLogger(ManageImageController.class);

	@Autowired
	ManageImageService manageImageService;

	@InitBinder
	public void initBinder(WebDataBinder webDataBinder) {
		webDataBinder.registerCustomEditor(Date.class, new CustomDateEditor(DateUtil.getSimpleDateFormat(), true));
	}

	@RequestMapping(value = "/manageImage", method = RequestMethod.GET)
	public ModelAndView pageImportData(@ModelAttribute("dataImportBean") FileFormInfo dataImportBean,
			RedirectAttributes redirectAttributes, Model m) throws FileNotFoundException {

		logger.info("call page upload image");
		ImageInfo imageInfo = manageImageService.findImage(Helpers.getUsername());
		return new ModelAndView("fileUploadSuccess", "imageInfo", imageInfo);
	}

	@RequestMapping(value = "/uploadImage/update", method = RequestMethod.POST)
	public ModelAndView pageUpdateImage(@ModelAttribute("imageInfo") FileFormInfo dataImportBean, BindingResult result,
			RedirectAttributes redirectAttributes, ModelMap model) {

		ModelAndView modelAndView = new ModelAndView();

		Boolean update = manageImageService.updateImage(dataImportBean, Helpers.getUsername());
		ImageInfo imageInfo = manageImageService.findImage(Helpers.getUsername());
		model.addAttribute("imageInfo", imageInfo);
		if (update) {
			model.addAttribute("success", "Update success");
		} else {
			model.addAttribute("fail", "Update fail");
		}
		modelAndView.setViewName("fileUploadSuccess");

		return modelAndView;
	}

	@RequestMapping(value = "/deleteImage/{id}", method = RequestMethod.GET)
	public ModelAndView pageDeleteImage(@PathVariable("id") Integer id, ModelMap model) {

		ModelAndView modelAndView = new ModelAndView();

		Boolean delete = manageImageService.deleteImage(id, Helpers.getUsername());

		if (delete) {
			modelAndView.setViewName("uploadimage");
		} else {
			ImageInfo imageInfo = manageImageService.findImage(Helpers.getUsername());
			model.addAttribute("imageInfo", imageInfo);
			model.addAttribute("fail", "Delete fail");
			modelAndView.setViewName("fileUploadSuccess");
		}

		return modelAndView;
	}

}
