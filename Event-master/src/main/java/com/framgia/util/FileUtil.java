package com.framgia.util;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.web.multipart.MultipartFile;

import com.cloudinary.Cloudinary;
import com.cloudinary.Transformation;
import com.cloudinary.utils.ObjectUtils;
import com.framgia.service.impl.UploadImageServiceImpl;

public class FileUtil {

	static final Logger logger = Logger.getLogger(UploadImageServiceImpl.class);

	@SuppressWarnings({ "unused", "rawtypes", "unchecked" })
	public static String uploadImage(MultipartFile sourceFile) {
		try {

			logger.info("Connecting cloud...");
			Cloudinary cloudinary = new Cloudinary(ObjectUtils.asMap("cloud_name", Constants.CLOUDINARY_CLOUD_NAME,
					"api_key", Constants.CLOUDINARY_API_KEY, "api_secret", Constants.CLOUDINARY_API_SECRET, "secure",
					true));

			Map<String, Object> cloudinaryUrl = null;
			Map params = ObjectUtils.asMap("public_id",
					Constants.PATH_UPLOAD + sourceFile.getOriginalFilename().split("\\.", 3)[0]);

			// Convert multipart file type image to File type because Cloudinary
			// doesn't accept multipart file type.
			File convFile = multipartToFile(sourceFile);
			if (convFile == null) {
				return null;
			}

			Map<String, Object> result = (Map<String, Object>) cloudinary.uploader().upload(convFile, params);
			cloudinaryUrl = result;
			return cloudinary.url().format("jpg")
					.transformation(new Transformation().width(650).height(400).crop("fit"))
					.generate(Constants.PATH_UPLOAD + sourceFile.getOriginalFilename().split("\\.", 3)[0]);
		} catch (IOException e) {
			logger.info("Could not upload file to Cloundinary from MultipartFile: ", e);
		}
		return null;

	}

	static File multipartToFile(MultipartFile image) {

		// Convert multipart to file
		File convFile = new File(image.getOriginalFilename());
		try {
			image.transferTo(convFile);
			return convFile;
		} catch (IllegalStateException | IOException e) {
			logger.info("Could not upload file to Cloundinary from multipartToFile: ", e);
		}
		return null;
	}
}