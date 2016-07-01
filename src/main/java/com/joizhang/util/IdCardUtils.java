package com.joizhang.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Hashtable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class IdCardUtils {
	private int[] weight = {7,9,10,5,8,4,2,1,6,3,7,9,10,5,8,4,2};    				//十七位数字本体码权重
	private char[] validate = { '1','0','X','9','8','7','6','5','4','3','2'};    	//mod11,对应校验码字符值
	/**
	 * 将15位的身份证转化为18位的
	 * */
	protected String convert(String sfzh){
		int sum = 0;
		int mode = 0;
		String sfzh_17 = sfzh.substring(0,6)+"19"+sfzh.substring(6);			//将身份证号转化为17位
		
		for(int i = 0; i < sfzh_17.length(); i++){
			sum = sum + Integer.parseInt(String.valueOf(sfzh_17.charAt(i)))*weight[i];
		}
		
		mode = sum % 11;
		String sfzh_last = String.valueOf(validate[mode]);
		String sfzh_18 = sfzh_17 + sfzh_last;
		return sfzh_18;
	} 
	
	/**
	 * 获得出生日期
	 * */
	public String getCsrq(String sfzh){
		String csrq = "", sfzh_18 = "";
		if(sfzh.length() == 15){
			sfzh_18 = convert(sfzh);
		} else {
			sfzh_18 = sfzh;
		}
		csrq = sfzh_18.substring(6, 14);
		return csrq;
	}
	
	/**
	 * 获得性别
	 * */
	public String getXb(String sfzh){
		int xb;
		String sfzh_18 = "";
		if(sfzh.length() == 15){
			sfzh_18 = convert(sfzh);
		} else {
			sfzh_18 = sfzh;
		}
		xb = Integer.parseInt(sfzh_18.substring(16, 17));
		
		if(xb % 2 == 0){
			return "女";
		} else {
			return "男";
		}
	}
	
	/**
	 * 身份证后六位作为密码
	 * */
	public String getMm(String sfzh){
		String mm = "";
		if(sfzh.length() == 15){
			mm = sfzh.substring(9);
		} else {
			mm = sfzh.substring(12);
		}
		return mm;
	}
	
	/**
	 * 判断是否是无效身份证
	 * */
	public boolean sfzhIsNotValid(String sfzh) {
		int sum = 0;
		int mode = 0;
		String Ai = "";
		// 判断号码的长度 15位或18位
		if (sfzh.length() != 15 && sfzh.length() != 18) {
			System.out.println("长度 15位或18位");
			return true;
		}
		
		// 18位身份证前17位位数字，如果是15位的身份证则所有号码都为数字
		if (sfzh.length() == 18) {
			Ai = sfzh.substring(0, 17);
		} else if (sfzh.length() == 15) {
			Ai = sfzh.substring(0, 6) + "19" + sfzh.substring(6, 15);
		}
		
		//身份证15位号码都应为数字 ; 18位号码除最后一位外，都应为数字
		if (isNumeric(Ai) == false) {
			System.out.println("如果是15位的身份证则所有号码都为数字,18位号码除最后一位外，都应为数字");
			return true;
		}
		
		// 判断出生年月是否有效
		String strYear = Ai.substring(6, 10);// 年份
		String strMonth = Ai.substring(10, 12);// 月份
		String strDay = Ai.substring(12, 14);// 日期
		if (isDate(strYear + "-" + strMonth + "-" + strDay) == false) {
			System.out.println("出生年月无效");
			return true;
		}
		
		GregorianCalendar gc = new GregorianCalendar();
        SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd");
        try {
            if ((gc.get(Calendar.YEAR) - Integer.parseInt(strYear)) > 150
                    || (gc.getTime().getTime() - s.parse(
                            strYear + "-" + strMonth + "-" + strDay).getTime()) < 0) {
                return true;
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
        } catch (java.text.ParseException e) {
            e.printStackTrace();
        }
        if (Integer.parseInt(strMonth) > 12 || Integer.parseInt(strMonth) == 0) {		//是否月份无效
            return true;
        }
        if (Integer.parseInt(strDay) > 31 || Integer.parseInt(strDay) == 0) {			//日期无效
            return true;
        }
        
        //地区码是否有效 
        @SuppressWarnings("rawtypes")
		Hashtable h = GetAreaCode();
        if (h.get(Ai.substring(0, 2)) == null) {
        	System.out.println("地区无效");
            return true;
        }
        
        // ================ 判断最后一位的值 ================
        if(sfzh.length() == 18){
        	for(int i = 0; i < (sfzh.length()-1); i++){
    			sum = sum + Integer.parseInt(String.valueOf(sfzh.charAt(i)))*weight[i];
    		}
        	
        	mode = sum % 11;
    		String sfzh_last = String.valueOf(validate[mode]);
//    		System.out.println(sfzh_last);
//    		System.out.println(sfzh.substring(17));
    		if(!sfzh_last.equals(sfzh.substring(17))){
    			return true;
    		}
        }
        
		return false;
	}
	
	/**
     * 功能：设置地区编码
     * 
     * @return Hashtable 对象
     */
    @SuppressWarnings({ "unchecked", "rawtypes"})
    private static Hashtable GetAreaCode() {
        Hashtable hashtable = new Hashtable();
        hashtable.put("11", "北京");
        hashtable.put("12", "天津");
        hashtable.put("13", "河北");
        hashtable.put("14", "山西");
        hashtable.put("15", "内蒙古");
        hashtable.put("21", "辽宁");
        hashtable.put("22", "吉林");
        hashtable.put("23", "黑龙江");
        hashtable.put("31", "上海");
        hashtable.put("32", "江苏");
        hashtable.put("33", "浙江");
        hashtable.put("34", "安徽");
        hashtable.put("35", "福建");
        hashtable.put("36", "江西");
        hashtable.put("37", "山东");
        hashtable.put("41", "河南");
        hashtable.put("42", "湖北");
        hashtable.put("43", "湖南");
        hashtable.put("44", "广东");
        hashtable.put("45", "广西");
        hashtable.put("46", "海南");
        hashtable.put("50", "重庆");
        hashtable.put("51", "四川");
        hashtable.put("52", "贵州");
        hashtable.put("53", "云南");
        hashtable.put("54", "西藏");
        hashtable.put("61", "陕西");
        hashtable.put("62", "甘肃");
        hashtable.put("63", "青海");
        hashtable.put("64", "宁夏");
        hashtable.put("65", "新疆");
        hashtable.put("71", "台湾");
        hashtable.put("81", "香港");
        hashtable.put("82", "澳门");
        hashtable.put("91", "国外");
        return hashtable;
    }
	
	/**
     * 功能：判断字符串是否为数字
     * 
     * @param str
     * @return
     */
    private static boolean isNumeric(String str) {
        Pattern pattern = Pattern.compile("[0-9]*");
        Matcher isNum = pattern.matcher(str);
        if (isNum.matches()) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 功能：判断字符串是否为日期格式
     * 
     * @param str
     * @return
     */
    public static boolean isDate(String strDate) {
    	Pattern pattern = Pattern
                .compile("^((\\d{2}(([02468][048])|([13579][26]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])))))|(\\d{2}(([02468][1235679])|([13579][01345789]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|(1[0-9])|(2[0-8]))))))(\\s(((0?[0-9])|([1-2][0-3]))\\:([0-5]?[0-9])((\\s)|(\\:([0-5]?[0-9])))))?$");
        Matcher m = pattern.matcher(strDate);
        if (m.matches()) {
            return true;
        } else {
            return false;
        }
    }
}