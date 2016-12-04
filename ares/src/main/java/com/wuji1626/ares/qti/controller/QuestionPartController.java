package com.wuji1626.ares.qti.controller;

import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import com.wuji1626.framework.result.Result;
import com.wuji1626.framework.utils.JsonDateValueProcessor;
import com.wuji1626.framework.constant.CommonConstant;

import com.wuji1626.ares.qti.domain.QuestionPart;
import com.wuji1626.ares.qti.service.QuestionPartService;

import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

@Controller
@RequestMapping("/questionpart")
public class QuestionPartController{

	@Autowired
	private QuestionPartService questionPartService;
	
	@RequestMapping(value = "/insertQuestionPart", method=RequestMethod.POST) 
	public String insertQuestionPart(@ModelAttribute("questionPart") QuestionPart questionPart) {
		questionPartService.insertQuestionPart(questionPart);
		
		return "listQuestionPart";
	}
	
	@RequestMapping(value = "/deleteQuestionPart/{questionPartId}", method=RequestMethod.GET) 
	public String deleteQuestionPart(@PathVariable("questionPartId") String questionPartId) {
		QuestionPart questionPart = new QuestionPart();
		questionPart.setQuestionPartId(questionPartId);
		questionPartService.deleteQuestionPart(questionPart);
		
		return "redirect:../listQuestionPartView";
	}
	@RequestMapping(value = "/saveQuestionPart", method=RequestMethod.POST) 
	public String saveQuestionPart(@ModelAttribute("questionPart") QuestionPart questionPart) {
		questionPartService.updateQuestionPart(questionPart);
		return "redirect:./listQuestionPartView";
	}
	
	@RequestMapping(value = "/editQuestionPart/{questionPartId}", method=RequestMethod.GET) 
	public ModelAndView editQuestionPart(@PathVariable("questionPartId") String questionPartId, Model model) {
		QuestionPart questionPart = new QuestionPart();
		questionPart.setQuestionPartId(questionPartId);
		questionPart = questionPartService.getQuestionPartById(questionPart);
		model.addAttribute("questionPartModel",questionPart);
		return new ModelAndView("/qti/questionPart/editQuestionPart");
	}
	
	@RequestMapping("/listQuestionPartView")
	public ModelAndView listQuestionPartView(Model model){
		List<QuestionPart> questionPartList = questionPartService.getAllQuestionPart();
		
		// Result
		Result<QuestionPart> res = new Result<QuestionPart>();
		res.setStatus(CommonConstant.SUCCESS_ST);
		res.setResultSet(questionPartList);
		
		JsonConfig jsonConfig = new JsonConfig();  
		jsonConfig.registerJsonValueProcessor(Date.class, new JsonDateValueProcessor());
		model.addAttribute("questionPartList", JSONObject.fromObject(res,jsonConfig));
		
		// redirect to the view of listQuestion.jsp
		return new ModelAndView("/qti/questionPart/listQuestionPart");
	}

	@RequestMapping("/newQuestionPartView")
	public ModelAndView newQuestionPartView(Model model){
		QuestionPart questionPart = new QuestionPart();
		model.addAttribute("questionPartModel",questionPart);
		// Results
		Result<String> res = new Result<String>();
		res.setStatus(CommonConstant.SUCCESS_ST);
		return new ModelAndView("/qti/questionPart/newQuestionPart");
	}

}
