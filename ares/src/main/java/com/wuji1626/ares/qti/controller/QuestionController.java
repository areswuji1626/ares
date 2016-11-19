package com.wuji1626.ares.qti.controller;

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

import com.wuji1626.ares.qti.domain.Question;
import com.wuji1626.ares.qti.service.QuestionService;

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

}
