package com.wuji1626.framework.codegen.controller;

import java.util.List;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.wuji1626.framework.codegen.domain.DataSourceInfo;
import com.wuji1626.framework.codegen.service.DBMetaDataService;
import com.wuji1626.framework.constant.CommonConstant;
import com.wuji1626.framework.controller.BaseController;
import com.wuji1626.framework.result.Result;
import com.wuji1626.framework.support.code.domain.Code;
import com.wuji1626.framework.support.code.service.CodeService;

@Controller
@RequestMapping("/codegen")
public class CodeGenController extends BaseController{

	@Autowired
	private DBMetaDataService service;
	@Autowired
	private CodeService codeService;
	
	private Result<Code> dsTypes;
	
	private void setDsTypes(){
		List<Code> typeList  = codeService.queryCodeByClass(CommonConstant.DS_TYPE_CLASS_KEY);
		dsTypes = new Result<Code>();
		dsTypes.setResultSet(typeList);
	}
	private Result<Code> getDsTypes(){
		return dsTypes;
	}
	
	/**
	 * 
	 * <p>Method : savaDs </p>
	 * <p>@Description : 保存数据源 </p>
	 * <p>@param ds
	 * <p>@param model
	 * <p>@return
	 * <p>@return ModelAndView
	 * <p>@exception:
	 *
	 * @author zhangwh 
	 *<p>
	 *--------------------------------------------------------------<br>
	 * 修改履历：<br>
	 *        <li> 2016年6月2日 下午9:41:37 zhangwh 创建方法；<br>
	 *--------------------------------------------------------------<br>
	 *</p>
	 */
	@RequestMapping(value = "/saveDS", method=RequestMethod.POST) 
    public ModelAndView savaDs(@ModelAttribute("dsModel") DataSourceInfo ds,Model model) {
		
		res = service.insertDataSource(ds);
		// 向前端传递数据不需要堆栈信息，防止出错
		res.setErrorStack("");
		// 如果新建数据源成功重新情况数据源的model
		if(res.getStatus().equals(CommonConstant.SUCCESS_ST)){
			ds = new DataSourceInfo();
			model.addAttribute("dsModel",ds);
		}
		model.addAttribute("result", JSONObject.fromObject(res));
		
		//初始化Select控件
		setDsTypes();
		model.addAttribute("typeList", JSONObject.fromObject(getDsTypes()));
		return new ModelAndView("/codegen/insertDataSource");
//		return "redirect:./insertDataSourceView";
    } 
	
	/**
	 * 
	 * <p>Method : insertCodeView </p>
	 * <p>@Description : 跳转到添加数据源视图 </p>
	 * <p>@param model
	 * <p>@return
	 * <p>@return ModelAndView
	 * <p>@exception:
	 *
	 * @author zhangwh 
	 *<p>
	 *--------------------------------------------------------------<br>
	 * 修改履历：<br>
	 *        <li> 2016年5月29日 上午10:05:26 zhangwh 创建方法；<br>
	 *--------------------------------------------------------------<br>
	 *</p>
	 */
	// 视图控制
	@RequestMapping("/insertDataSourceView")
	public ModelAndView insertDataSourceView(Model model){
		DataSourceInfo ds = new DataSourceInfo();
		model.addAttribute("dsModel",ds);
		
		// 结果封装
		setDsTypes();
		model.addAttribute("typeList", JSONObject.fromObject(getDsTypes()));
		return new ModelAndView("/codegen/insertDataSource");
	}
	/**
	 * 
	 * <p>Method : listDataSourceView </p>
	 * <p>@Description : 显示数据源 </p>
	 * <p>@param model
	 * <p>@return
	 * <p>@return ModelAndView
	 * <p>@exception:
	 *
	 * @author zhangwh 
	 *<p>
	 *--------------------------------------------------------------<br>
	 * 修改履历：<br>
	 *        <li> 2016年8月29日 下午11:22:57 zhangwh 创建方法；<br>
	 *--------------------------------------------------------------<br>
	 *</p>
	 */
	@RequestMapping("/listDataSourceView")
	public ModelAndView listDataSourceView(Model model){
		Result<DataSourceInfo> res = service.listDataSource();
		model.addAttribute("dsModel",JSONObject.fromObject(res));
		return new ModelAndView("/codegen/listDataSource");
	}
}
