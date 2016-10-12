package com.wuji1626.ares.picoperation.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ModelAndView;

import com.wuji1626.ares.picoperation.domain.PicInfo;
import com.wuji1626.ares.picoperation.service.PicOperationService;
import com.wuji1626.framework.constant.CommonConstant;
import com.wuji1626.framework.controller.BaseController;
import com.wuji1626.framework.result.Result;

@Controller
@RequestMapping("/picoperation")
public class PicOperationController extends BaseController{

	@Autowired
	private PicOperationService operService;
	
	@RequestMapping("/uploadPic")
	public ModelAndView uploadPic(@RequestParam("file") CommonsMultipartFile[] files,HttpServletRequest request) throws IllegalStateException, IOException{
		
		//创建一个通用的多部分解析器  
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());  
		//判断 request 是否有文件上传,即多部分请求  
        if(multipartResolver.isMultipart(request)){  
        	//转换成多部分request    
            MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest)request;
            res = operService.savePic(multiRequest);
        	
        }
        if(res.getStatus().equals(CommonConstant.SUCCESS_ST)){
        	return new ModelAndView("redirect:/picoperation/loadView/" + res.getResultSet().get(0));
        }else{
        	return new ModelAndView("/common/error");
        }
		
	}
	@RequestMapping(value="/loadView/{filename}",method=RequestMethod.GET)
	public ModelAndView loadView(@PathVariable("filename") String fileName,Model model){
		
		System.out.println(fileName);
		String picUrl = operService.convertPicUrl(fileName);
		
		model.addAttribute("picUrl", picUrl);
		model.addAttribute("fileName",fileName);
		model.addAttribute("picModel", new PicInfo());
		
        if(res.getStatus().equals(CommonConstant.SUCCESS_ST)){
        	return new ModelAndView("/picoperation/loadPic");
        }else{
        	return new ModelAndView("/common/error");
        }
		
	}
	@RequestMapping(value = "/clipPic", method=RequestMethod.POST)
	public ModelAndView clipPic(@ModelAttribute("picModel") PicInfo picInfo, Model model){
		
		res = operService.clipPic(picInfo);
        if(res.getStatus().equals(CommonConstant.SUCCESS_ST)){
        	return new ModelAndView("/picoperation/uploadPic");
        }else{
        	return new ModelAndView("/common/error");
        }
		
		
	}
	// 页面跳转
	// 用户列表显示
	@RequestMapping("/uploadPicView")
	public ModelAndView uploadPicView(Model model){
		
		return new ModelAndView("/picoperation/uploadPic");
		
	}
	
}
