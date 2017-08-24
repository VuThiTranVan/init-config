package com.framgia.service.impl;

import org.apache.log4j.Logger;
import org.springframework.web.multipart.MultipartFile;

import com.framgia.bean.FileFormInfo;
import com.framgia.bean.GroupInfo;
import com.framgia.bean.ImageInfo;
import com.framgia.model.Group;
import com.framgia.model.Image;
import com.framgia.model.User;
import com.framgia.service.UploadImageService;
import com.framgia.util.Constants;
import com.framgia.util.ConvetBeanAndModel;
import com.framgia.util.DateUtil;
import com.framgia.util.FileUtil;

/**
 * 
 * @author phan.van.hieu@framgia.com
 *
 */
@SuppressWarnings("serial")
public class UploadImageServiceImpl extends BaseServiceImpl implements UploadImageService {

	private static final Logger logger = Logger.getLogger(UploadImageServiceImpl.class);

	@Override
	public Integer uploadImage(FileFormInfo dataImportBean, String username) {

		try {
			logger.info("Upload Image");

			MultipartFile multipartFile = dataImportBean.getFileImport();
			String url = null;
			// Check user upload image
			if (imageDAO.findByUserCreate(username) > 0) {
				return -1;
			}
			if (multipartFile != null) {
				url = FileUtil.uploadImage(multipartFile);
			}

			// Check error upload file
			if (url == null) {
				return null;
			}

			// Get Group
			User user = userDAO.findByUserName(username);
			Group dataGroup = groupDAO.findById(user.getIdGroup(), false);

			// Check data findById group
			if (dataGroup == null) {
				return null;
			}
			// Define Image
			Image image = new Image();
			image.setGroup(dataGroup);
			image.setUrl(url);
			image.setTitle(dataImportBean.getTitle());
			image.setDescription(dataImportBean.getDescription());
			image.setDeleteFlag(Constants.DEL_FLG);
			image.setDateCreate(DateUtil.getDateNow());
			image.setDateUpdate(DateUtil.getDateNow());
			image.setUserCreate(username);
			image.setUserUpdate(username);

			// Insert data into table Image
			imageDAO.saveOrUpdate(image);

			return image.getId();
		} catch (Exception e) {
			logger.info("Exception at function uploadImage in FileUploadServiceImpl: ", e);
			throw e;
		}
	}

	@Override
	public ImageInfo findById(Integer idImage) {

		logger.info("Get detail Image");
		Image image = imageDAO.findById(idImage, false);

		if (image == null) {
			return null;
		}
		// Convert data ImageInfo
		ImageInfo imageInfo = ConvetBeanAndModel.convertImageModelToBean(image);
		GroupInfo group = new GroupInfo();
		group.setName(image.getGroup().getName());
		imageInfo.setGroup(group);

		// Return data
		return imageInfo;

	}
}