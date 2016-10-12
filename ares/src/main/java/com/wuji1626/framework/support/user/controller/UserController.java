package com.wuji1626.framework.support.user.controller;

import java.util.List;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.wuji1626.framework.constant.CommonConstant;
import com.wuji1626.framework.support.user.domain.Result;
import com.wuji1626.framework.support.user.domain.User;
import com.wuji1626.framework.support.user.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController{

	@Autowired
	private UserService userService;
	
	@RequestMapping(value="/searchUser/{user_name}.do",method=RequestMethod.POST)
	public String searchUser(@PathVariable("user_name") String user_name){
		System.out.print(user_name);
		User user = userService.queryById(new Integer(user_name));
		System.out.print(user.getUser_name());
        return "index";
	}
	@RequestMapping(value = "/saveUserJson", method=RequestMethod.POST) 
	@ResponseBody
    public String saveUserJson(@RequestBody User user) {
         if(!StringUtils.isEmpty(userService.saveUser(user))){
        	 return "success";
         }
         return "fail";
    } 
	@RequestMapping(value = "/saveUser", method=RequestMethod.POST) 
    public String saveUser(@ModelAttribute("userModel")User user) {
        if(!StringUtils.isEmpty(userService.saveUser(user))){
        	return "user/success";
        }
        return "user/fail";
    } 
	
	// 页面跳转
	// 用户列表显示
	@RequestMapping("/listUserView")
	public ModelAndView listUserView(Model model){
		List<User> userList = userService.queryAll();
		
		// 结果封装
		Result res = new Result();
		res.setStatus(CommonConstant.SUCCESS_ST);
		res.setList(userList);
		model.addAttribute("userList", JSONObject.fromObject(res));
		return new ModelAndView("/support/user/listUser");
	}
	@RequestMapping("/insertUserView")
	public ModelAndView insertUserView(Model model){
		// 页面初始化配置模型
		return new ModelAndView("/support/user/insertUser");
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
