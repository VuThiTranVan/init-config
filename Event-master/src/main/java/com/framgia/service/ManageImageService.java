package com.framgia.service;

import com.framgia.bean.FileFormInfo;
import com.framgia.bean.ImageInfo;

/**
 * FileUploadService.java
 * 
 * @version 18/04/2017
 * @author phan.van.hieu@framgia.com
 * 
 */
public interface ManageImageService extends BaseService {

	ImageInfo findImage(String username);

	Boolean updateImage(FileFormInfo dataImportBean, String username);

	Boolean deleteImage(Integer id, String username);

}
