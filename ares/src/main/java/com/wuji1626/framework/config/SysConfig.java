package com.wuji1626.framework.config;

import java.io.IOException;
import java.util.Properties;

public class SysConfig {

	private static Properties prop = new Properties();    
	
	static{
        try  {    
           prop.load(SysConfig.class.getClassLoader().getResourceAsStream("config.properties"));    
       }  catch  (IOException e) {    
           e.printStackTrace();    
       }    

	}
	public static String getConfigFromProperties(String key){
		return prop.getProperty(key).trim();
	}
}
