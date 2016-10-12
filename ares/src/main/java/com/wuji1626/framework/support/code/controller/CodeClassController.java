package com.wuji1626.framework.support.code.controller;

import java.util.List;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.wuji1626.framework.constant.CommonConstant;
import com.wuji1626.framework.support.code.domain.CodeClass;
import com.wuji1626.framework.support.code.service.CodeClassService;
import com.wuji1626.framework.support.user.domain.Result;

@Controller
@RequestMapping("/codeclass")
public class CodeClassController {

	@Autowired
	private CodeClassService service;
	
	@RequestMapping(value = "/saveCodeClass", method=RequestMethod.POST) 
    public String saveCodeClass(@ModelAttribute("classModel") CodeClass cls) {
        if(!StringUtils.isEmpty(service.insertCodeClass(cls))){
        	return "redirect:./listCodeClassView";
        }
        return "user/fail";
    } 
	@RequestMapping(value = "/deleteCodeClass/{class_id}", method=RequestMethod.GET) 
    public String deleteCodeClass(@PathVariable("class_id") String class_id) {
        if(!StringUtils.isEmpty(service.deleteCodeClass(class_id))){
        	return "redirect:../listCodeClassView";
        }
        return "user/fail";
    } 
	
	// VIEW
	/**
	 * 
	 * <p>Method : insertCodeClassView </p>
	 * <p>@Description : 显示新建 枚举分类页面</p>
	 * <p>@param model
	 * <p>@return
	 * <p>@return ModelAndView
	 * <p>@exception:
	 *
	 * @author zhangwh 
	 *<p>
	 *--------------------------------------------------------------<br>
	 * 修改履历：<br>
	 *        <li> 2016年1月18日 下午5:22:08 zhangwh 创建方法；<br>
	 *--------------------------------------------------------------<br>
	 *</p>
	 */
	@RequestMapping("/insertCodeClassView")
	public ModelAndView insertCodeClassView(Model model){
		// 页面初始化配置模型
		CodeClass cls = new CodeClass();
		model.addAttribute("classModel",cls);  
		return new ModelAndView("/support/codelist/insertCodeClass");
	}
	/**
	 * 
	 * <p>Method : listCodeClassView </p>
	 * <p>@Description : TODO(这里用一句话描述这个方法的作用) </p>
	 * <p>@param model
	 * <p>@return
	 * <p>@return ModelAndView
	 * <p>@exception:
	 *
	 * @author zhangwh 
	 *<p>
	 *--------------------------------------------------------------<br>
	 * 修改履历：<br>
	 *        <li> 2016年1月18日 下午8:32:17 zhangwh 创建方法；<br>
	 *--------------------------------------------------------------<br>
	 *</p>
	 */
	@RequestMapping("/listCodeClassView")
	public ModelAndView listCodeClassView(Model model){
		List<CodeClass> codeclassList = service.queryAll();
		
		// 结果封装
		Result res = new Result();
		res.setStatus(CommonConstant.SUCCESS_ST);
		res.setList(codeclassList);
		model.addAttribute("codeclassList", JSONObject.fromObject(res));
		// 页面初始化配置模型  
		return new ModelAndView("/support/codelist/listCodeClass");
	}
	@RequestMapping("/success")
	public  ModelAndView success(){
		return new ModelAndView("success");
	}
	@RequestMapping("/fail")
	public ModelAndView fail(){
		return new ModelAndView("fail");
	}
	
}
