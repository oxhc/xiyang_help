package com.es.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateFormatUtils {
	public static String DateToString(Date date) {
		SimpleDateFormat sm = new SimpleDateFormat("yyyy-MM-dd");
		return sm.format(date);
	}
	public static Date StringToDate(String str) {
		SimpleDateFormat sm = new SimpleDateFormat("yyyy-MM-dd");
		try {
			return sm.parse(str);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new Date();
	}
	public static  int getAge(Date birthDay) {  
        Calendar cal = Calendar.getInstance();  
        if (cal.before(birthDay)) {  
            return 0;
        }  
        int yearNow = cal.get(Calendar.YEAR);  
        int monthNow = cal.get(Calendar.MONTH);  
        int dayOfMonthNow = cal.get(Calendar.DAY_OF_MONTH);  
        cal.setTime(birthDay);   
  
        int yearBirth = cal.get(Calendar.YEAR);  
        int monthBirth = cal.get(Calendar.MONTH);  
        int dayOfMonthBirth = cal.get(Calendar.DAY_OF_MONTH);   
  
        int age = yearNow - yearBirth;  
  
        if (monthNow <= monthBirth) {  
            if (monthNow == monthBirth) {  
                if (dayOfMonthNow < dayOfMonthBirth) age--;  
            }else{  
                age--;  
            }  
        }  
        return age;  
    } 

}
