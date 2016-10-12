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
import com.wuji1626.framework.support.code.domain.Code;
import com.wuji1626.framework.support.code.domain.CodeClass;
import com.wuji1626.framework.support.code.service.CodeService;
import com.wuji1626.framework.support.user.domain.Result;

@Controller
@RequestMapping("/code")
public class CodeController {

	@Autowired
	private CodeService service;
	
	@RequestMapping(value = "/saveCode", method=RequestMethod.POST) 
    public String saveCode(@ModelAttribute("codeModel") Code code) {
        if(!StringUtils.isEmpty(service.saveCode(code))){
        	return "redirect:./listCodeView";
        }
        return "user/fail";
    } 
	@RequestMapping(value = "/deleteCode/{code_id}", method=RequestMethod.GET) 
    public String deleteCode(@PathVariable("code_id") String code_id) {
        if(!StringUtils.isEmpty(service.deleteCode(code_id))){
        	return "redirect:../listCodeView";
        }
        return "user/fail";
    } 
	
	@RequestMapping(value = "/editCode/{code_id}", method=RequestMethod.GET) 
    public ModelAndView editCode(@PathVariable("code_id") String code_id) {
		
        ModelAndView model = new ModelAndView("/support/codelist/editCode");
        // 获取枚举信息
        Code code = service.queryCodeById(code_id);
        // 获取枚举类列表
        List<CodeClass> codeList  = service.getAllCodeClass();
        Result res = new Result();
		res.setStatus(CommonConstant.SUCCESS_ST);
		res.setList(codeList);
        model.addObject("codeModel",code);
        model.addObject("classList", JSONObject.fromObject(res));
    		// 页面初始化配置模型  
    	return model;
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
	@RequestMapping("/insertCodeView")
	public ModelAndView insertCodeView(Model model){
		// 页面初始化配置模型
		Code code = new Code();
		model.addAttribute("codeModel",code);
		List<CodeClass> codeList  = service.getAllCodeClass();
		// 结果封装
		Result res = new Result();
		res.setStatus(CommonConstant.SUCCESS_ST);
		res.setList(codeList);
		model.addAttribute("classList", JSONObject.fromObject(res));
		return new ModelAndView("/support/codelist/insertCode");
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
	@RequestMapping("/listCodeView")
	public ModelAndView listCodeView(Model model){
		List<Code> codeList = service.queryAllCode();
		
		// 结果封装
		Result res = new Result();
		res.setStatus(CommonConstant.SUCCESS_ST);
		res.setList(codeList);
		model.addAttribute("codeList", JSONObject.fromObject(res));
		// 页面初始化配置模型  
		return new ModelAndView("/support/codelist/listCode");
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
