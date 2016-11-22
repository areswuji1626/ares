package ${package}.controller;

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

import ${package}.domain.${entityName};
import ${package}.service.${entityName}Service;

import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

@Controller
@RequestMapping("/${entityName?lower_case}")
public class ${entityName}Controller{

	@Autowired
	private ${entityName}Service ${entityName?uncap_first}Service;
	
	@RequestMapping(value = "/insert${entityName}", method=RequestMethod.POST) 
	public String insert${entityName}(@ModelAttribute("${entityName?uncap_first}") ${entityName} ${entityName?uncap_first}) {
		${entityName?uncap_first}Service.insert${entityName}(${entityName?uncap_first});
		
		return "list${entityName}";
	}
	
	@RequestMapping("/list${entityName}View")
	public ModelAndView list${entityName}View(Model model){
		List<${entityName}> ${entityName?uncap_first}List = ${entityName?uncap_first}Service.getAll${entityName}();
		
		// Result
		Result<${entityName}> res = new Result<${entityName}>();
		res.setStatus(CommonConstant.SUCCESS_ST);
		res.setResultSet(${entityName?uncap_first}List);
		
		JsonConfig jsonConfig = new JsonConfig();  
		jsonConfig.registerJsonValueProcessor(Date.class, new JsonDateValueProcessor());
		model.addAttribute("${entityName?uncap_first}List", JSONObject.fromObject(res,jsonConfig));
		
		// redirect to the view of listQuestion.jsp
		return new ModelAndView("/qti/${entityName?uncap_first}/list${entityName}");
	}

	@RequestMapping("/new${entityName}View")
	public ModelAndView new${entityName}View(Model model){
		${entityName} question = new ${entityName}();
		model.addAttribute("${entityName?uncap_first}Model",${entityName?uncap_first});
		// Results
		Result<String> res = new Result<String>();
		res.setStatus(CommonConstant.SUCCESS_ST);
		return new ModelAndView("/qti/${entityName?uncap_first}/new${entityName}");
	}

}
