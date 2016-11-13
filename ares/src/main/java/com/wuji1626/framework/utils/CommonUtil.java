package com.wuji1626.framework.utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import com.wuji1626.framework.config.SysConfig;
import com.wuji1626.framework.constant.CommonConstant;

public class CommonUtil {
	/**
	 * 获取UUID
	 * @return
	 */
	public static String genUUID() { 
      return UUID.randomUUID().toString().toUpperCase(); 
	} 
	//-------时间相关
	/**
	 * 获取系统当前时间
	 */
	public static String getNowTime(){
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
		return df.format(new Date());
	}
	public static String getNowTime(String fmt){
		SimpleDateFormat df = new SimpleDateFormat(fmt);//设置日期格式
		return df.format(new Date());
	}
	public static String strWipeSymbol(String str){
		//String pattern = "4398732fladsjfoisa4709875^(#*&^.,.,.,,;@#)&*dfa;ljfa;sdfj";
		return str.replaceAll("[^0-9]", "");
	}
	/**
	 * 处理日期格式，将不是日期形式的字符串从列表中过滤掉，将超过8位的字符串截取为8为日期
	 * @param date
	 * @return
	 */
	public static List<String> convertDateFormat(List<String> dateList){
		List<String> convertedList = new ArrayList<String>();
		for(String date:dateList){
			date = fetchNum(date);
			if(date.length()==0){
				continue;
			}
			if(date.length()>8){
				date.substring(0, 8);
			}
			convertedList.add(date);
		}
		return convertedList;
	}
	/**
	 * 将日期格式转换为YYYY/MM/dd
	 * @param dateList
	 * @param dateFormat
	 * @return
	 */
	public static List<String> convertDateBySlash(List<String> dateList){
		List<String> convertedList = new ArrayList<String>();
		StringBuffer buf = null;
		for(String date:dateList){
			date = fetchNum(date);
//			System.out.println(date);
			buf = new StringBuffer();
			buf.append(date.substring(0, 4)).append("/").append(date.substring(4, 6))
							.append("/").append(date.substring(6, 8));
			convertedList.add(buf.toString());
		}
		return convertedList;
	}
	/**
	 * 将字符串中的数字提取
	 * @param str
	 * @return
	 */
	public static String fetchNum(String str){
		String str2="";

		if(str != null && !"".equals(str)){
			// 数据预处理
			// 当数据为XXXX.X.X或者XXXX.XX.XX时进行如下处理
			if(str.contains(".")){
				String year = str.substring(0, str.indexOf("."));
				String month = str.substring(str.indexOf(".")+1, str.lastIndexOf("."));
				if(month.length() == 1){
					month = "0" + month;
				}
				String day = str.substring(str.lastIndexOf(".")+1);
				if(day.length() == 1){
					day = "0" + day;
				}
				return year + month + day;
			}
			for(int i=0;i<str.length();i++){
				if(str.charAt(i)>=48 && str.charAt(i)<=57){
					str2+=str.charAt(i);
				}
			}
		}
		return str2;
	}
	/**
	 * 该方法适合一段中文与应用混编的场景
	 * E+C或C+E
	 * 
	 */
	public static String fetchChineseStr(String str){
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < str.length(); i++) {
			if ((str.charAt(i)+"").getBytes().length>1) {
				sb.append(str.charAt(i));
			}
		}
		return sb.toString();
	}
	/**
	 * 该方法适合一段中文与应用混编的场景
	 * E+C或C+E
	 * 
	 */
	public static String fetchEnglishStr(String str){
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < str.length(); i++) {
			if ((str.charAt(i)+"").getBytes().length==1) {
				sb.append(str.charAt(i));
			}
		}
		return sb.toString();
	}
	
	/**   
     * 追加文件：使用FileWriter   
     *    
     * @param fileName   
     * @param content   
     */    
    public static void appendContent(String fileName, String content) {   
        FileWriter writer = null;  
        try {     
            // 打开一个写文件器，构造函数中的第二个参数true表示以追加形式写文件     
            writer = new FileWriter(fileName, true);     
            BufferedWriter buffer = new BufferedWriter(writer);  
            int flag = 0;  
            boolean isGO = true; 
            String contentString = ""; 
            while((flag = content.indexOf(CommonConstant.NEW_LINE)) != -1 && isGO ){  
            	 contentString = content.substring(0,flag);
            	 buffer.write(contentString);  
                 buffer.newLine();  
                 buffer.flush();  
                 if( flag+2 >= content.length()){  
                     isGO  = false;  
                 }else{  
                	 content = content.substring(flag+2);  
                 }  
            }
            buffer.flush();  
            writer.flush();  
            buffer.close();  
            writer.close();       
        } catch (IOException e) {     
            e.printStackTrace();     
        } finally {     
            try {     
                if(writer != null){  
                    writer.close();     
                }  
            } catch (IOException e) {     
                e.printStackTrace();     
            }     
        }   
    }  
    public static void makeDirExist(String path){
    	File dir =new File(path);    
    	//如果文件夹不存在则创建    
    	if  (!dir .exists()  && !dir .isDirectory()){       
    	    dir.mkdirs();    
    	}
    }
    public static void makeFileOrDirExist(String fileName){
    	String path = fileName.substring(0, fileName.lastIndexOf(CommonConstant.FILESYS_SEPERATOR));
    	File dir =new File(path);    
    	//如果文件夹不存在则创建    
    	if  (!dir .exists()  && !dir .isDirectory()){       
    	    dir.mkdirs();    
    	}
    	File file=new File(fileName);    
    	if(!file.exists()) {    
    	    try {    
    	        file.createNewFile();    
    	    } catch (IOException e) {    
    	        // TODO Auto-generated catch block    
    	        e.printStackTrace();    
    	    }    
    	} 
    }
}
