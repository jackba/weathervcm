package com.cma.intervideo.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import org.apache.struts2.util.StrutsTypeConverter;

public class DateConverter extends StrutsTypeConverter{
	private static final DateFormat[] ACCEPT_DATE_FORMATS = {
		new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"),
		new SimpleDateFormat("yyyy-MM-dd"),
		new SimpleDateFormat("dd/MM/yyyy"),
		new SimpleDateFormat("yyyy/MM/dd")
	};
	@Override
	public Object convertFromString(Map context, String[] values, Class toClass) {
		if(values == null){
			return null;
		}
		String dateString = values[0];
		if(dateString==null || dateString.equals("") || toClass!=Date.class){
			return null;
		}
		for(DateFormat format:ACCEPT_DATE_FORMATS){
			try{
				return format.parse(dateString);
			}catch(Exception e){
				continue;
			}
		}
		return null;
	}

	@Override
	public String convertToString(Map context, Object o) {
		// TODO Auto-generated method stub
		return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format((Date)o);
	}
	
}
