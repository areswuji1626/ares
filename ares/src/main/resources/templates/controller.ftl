package ${package}.controller;

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

import ${package}.domain.${entityName};
import ${package}.service.${entityName}Service;

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

}
