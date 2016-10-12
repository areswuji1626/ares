package com.wuji1626.ares.picoperation.service;

import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.wuji1626.ares.picoperation.domain.PicInfo;
import com.wuji1626.framework.result.Result;

public interface PicOperationService {

	public Result<String> savePic(MultipartHttpServletRequest multipartResolver);
	
	public String convertPicUrl(String fileName);
	
	public Result<String> clipPic(PicInfo picInfo);
	
}
