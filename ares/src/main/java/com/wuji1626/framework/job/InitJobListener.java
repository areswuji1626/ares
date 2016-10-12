package com.wuji1626.framework.job;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.web.context.ServletContextAware;

/**
 * 
* @ClassName: InitJobListener 
* @Description: 任务队列初始化 
* @author zhangwh wuji1626@163.com
* @date 2015年12月17日 下午4:04:06  
*
 */
public class InitJobListener implements InitializingBean, ServletContextAware {
	/**
	 * 初始化任务队列
	 */
	public void setServletContext(ServletContext servletContext) {
		// TODO Do nothing
		
	}

	public void afterPropertiesSet() throws Exception {
		// TODO Auto-generated method stub

	}

}
