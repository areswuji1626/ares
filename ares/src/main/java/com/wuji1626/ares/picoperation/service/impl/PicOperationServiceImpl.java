package com.wuji1626.ares.picoperation.service.impl;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import net.coobird.thumbnailator.Thumbnails;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.wuji1626.ares.constant.BusinessConstant;
import com.wuji1626.ares.picoperation.domain.PicInfo;
import com.wuji1626.ares.picoperation.service.PicOperationService;
import com.wuji1626.framework.config.SysConfig;
import com.wuji1626.framework.constant.CommonConstant;
import com.wuji1626.framework.result.Result;
import com.wuji1626.framework.utils.CommonUtil;
import com.wuji1626.framework.utils.ValidateUtil;

@Service("picOperationService")
@Transactional
public class PicOperationServiceImpl implements PicOperationService {

	@Override
	public Result<String> savePic(MultipartHttpServletRequest multiRequest) {
		Result<String> res = new Result<String>();
		List<String> fNames = new ArrayList<String>();
		CommonUtil.makeDirExist(SysConfig.getConfigFromProperties(BusinessConstant.PIC_LOCATE));
	    //取得request中的所有文件名  
	    Iterator<String> iter = multiRequest.getFileNames();  
	    while(iter.hasNext()){  
	        //记录上传过程起始时的时间，用来计算上传时间  
	        int pre = (int) System.currentTimeMillis();  
	        //取得上传文件  
	        MultipartFile file = multiRequest.getFile(iter.next());  
	        if(file != null){  
	            //取得当前上传文件的文件名称  
	            String myFileName = file.getOriginalFilename();  
	            //如果名称不为“”,说明该文件存在，否则说明该文件不存在  
	            if(myFileName.trim() !=""){  
	                System.out.println(myFileName);  
	                //重命名上传后的文件名  
	                String fileName = SysConfig.getConfigFromProperties(BusinessConstant.PIC_FILE_PREFIX)
	                		+ CommonUtil.getNowTime("YYYYMMDDHHmmss");
	                String fName = new String(fileName);
	                fNames.add(fName);
	                res.setResultSet(fNames);
	                
	                fileName += SysConfig.getConfigFromProperties(BusinessConstant.PIC_FILE_SUFFIX);
	                		//+ file.getOriginalFilename();  
	                
	                //定义上传路径  
	                String path = SysConfig.getConfigFromProperties(BusinessConstant.PIC_LOCATE) 
	                		+ fileName;  
	                File localFile = new File(path);  
	                try {
						file.transferTo(localFile);
					} catch (IllegalStateException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						res.setStatus(CommonConstant.FAIL_ST);
						res.setErrorCode(BusinessConstant.ILLEGAL_STATE_EXCEPTION_COMMON);
						res.setErrorStack(ExceptionUtils.getFullStackTrace(e));
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						res.setStatus(CommonConstant.FAIL_ST);
						res.setErrorCode(BusinessConstant.IO_EXCEPTION_COMMON);
						res.setErrorStack(ExceptionUtils.getFullStackTrace(e));
					}  
	            }  
	        }  
	        //记录上传该文件后的时间  
	        int finaltime = (int) System.currentTimeMillis();  
	        System.out.println(finaltime - pre);  
	    }  
              
		return res;
	}

	@Override
	public String convertPicUrl(String fileName) {
		// TODO Auto-generated method stub
		String fileUrl = SysConfig.getConfigFromProperties(BusinessConstant.HTTP_PIC_PREFIX)
				+ fileName + SysConfig.getConfigFromProperties(BusinessConstant.PIC_FILE_SUFFIX);
		
		return fileUrl;
	}

	@Override
	public Result<String> clipPic(PicInfo picInfo) {
		// TODO Auto-generated method stub
		int width = Math.abs(Integer.parseInt(picInfo.getPicX1())-Integer.parseInt(picInfo.getPicX2()));
		int height = Math.abs(Integer.parseInt(picInfo.getPicY1())-Integer.parseInt(picInfo.getPicY2()));
		Result<String> res = new Result<String>();
		String clipName = "";
		if(ValidateUtil.isBlank(picInfo.getPicName())){
			clipName = picInfo.getPicOrgName() + ".png";
		}else{
			clipName = picInfo.getPicName() + CommonUtil.getNowTime("YYYYMMDDhhmmss") + 
					SysConfig.getConfigFromProperties(BusinessConstant.PIC_FILE_SUFFIX);
		}
		CommonUtil.makeDirExist(SysConfig.getConfigFromProperties(BusinessConstant.PIC_CLIP_LOCATE));
		try {
			Thumbnails.of(SysConfig.getConfigFromProperties(BusinessConstant.PIC_LOCATE) + picInfo.getPicOrgName() +
					SysConfig.getConfigFromProperties(BusinessConstant.PIC_FILE_SUFFIX))
				.sourceRegion(new Integer(picInfo.getPicX1()), new Integer(picInfo.getPicY1()),
						width,height)
				.size(width, height)
				.toFile(SysConfig.getConfigFromProperties(BusinessConstant.PIC_CLIP_LOCATE) + clipName);
			List<String> resList = new ArrayList<String>();
			resList.add(SysConfig.getConfigFromProperties(BusinessConstant.PIC_CLIP_LOCATE) + clipName);
			res.setResultSet(resList);
			
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			res.setErrorCode(BusinessConstant.NUMBER_FORMAT_EXCEPTION_COMMON);
			res.setErrorStack(ExceptionUtils.getFullStackTrace(e));
			res.setStatus(CommonConstant.FAIL_ST);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			res.setErrorCode(BusinessConstant.IO_EXCEPTION_COMMON);
			res.setErrorStack(ExceptionUtils.getFullStackTrace(e));
			res.setStatus(CommonConstant.FAIL_ST);
		}
		writePicInfo2DescFile(picInfo);
		return res;
	}
	private Result<String> writePicInfo2DescFile(PicInfo picInfo){
		Result<String> res = new Result<String>();
		
		StringBuffer contentBuf = new StringBuffer();
		contentBuf.append(ValidateUtil.isBlank(picInfo.getPicName())?picInfo.getPicOrgName():picInfo.getPicName());
		contentBuf.append(CommonConstant.NEW_LINE);
		contentBuf.append(picInfo.getPicDesc());
		contentBuf.append(CommonConstant.NEW_LINE);
		contentBuf.append(CommonConstant.NEW_LINE);
		String descFileName = SysConfig.getConfigFromProperties(BusinessConstant.PIC_CLIP_LOCATE) 
				+ SysConfig.getConfigFromProperties(BusinessConstant.PIC_DESC_FILENAME);
		CommonUtil.appendContent(descFileName, contentBuf.toString());
		
		return res;
	}

}
