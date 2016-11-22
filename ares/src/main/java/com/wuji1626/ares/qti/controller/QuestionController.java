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

import com.wuji1626.ares.qti.domain.Question;
import com.wuji1626.ares.qti.service.QuestionService;

import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

@Controller
@RequestMapping("/question")
public class QuestionController{

	@Autowired
	private QuestionService questionService;
	
	@RequestMapping(value = "/insertQuestion", method=RequestMethod.POST) 
	public String insertQuestion(@ModelAttribute("question") Question question) {
		questionService.insertQuestion(question);
		
		return "listQuestion";
	}
	
	@RequestMapping("/listQuestionView")
	public ModelAndView listQuestionView(Model model){
		List<Question> questionList = questionService.getAllQuestion();
		
		// Result
		Result<Question> res = new Result<Question>();
		res.setStatus(CommonConstant.SUCCESS_ST);
		res.setResultSet(questionList);
		
		JsonConfig jsonConfig = new JsonConfig();  
		jsonConfig.registerJsonValueProcessor(Date.class, new JsonDateValueProcessor());
		model.addAttribute("questionList", JSONObject.fromObject(res,jsonConfig));
		
		// redirect to the view of listQuestion.jsp
		return new ModelAndView("/qti/question/listQuestion");
	}

	@RequestMapping("/newQuestionView")
	public ModelAndView newQuestionView(Model model){
		Question question = new Question();
		model.addAttribute("questionModel",question);
		// Results
		Result<String> res = new Result<String>();
		res.setStatus(CommonConstant.SUCCESS_ST);
		return new ModelAndView("/qti/question/newQuestion");
	}

}
