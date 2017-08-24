package demo.util;

import java.io.File;
import java.io.IOException;

import org.apache.log4j.Logger;
import org.springframework.web.multipart.MultipartFile;

public class FileUtil {

	static final Logger logger = Logger.getLogger(FileUtil.class);

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