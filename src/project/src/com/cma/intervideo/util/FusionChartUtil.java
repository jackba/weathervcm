package com.cma.intervideo.util;

import java.io.UnsupportedEncodingException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.cma.intervideo.vo.column3d.Chart;
import com.cma.intervideo.vo.column3d.Set;
import com.thoughtworks.xstream.XStream;

public class FusionChartUtil {
	public static String createDummyData(Chart chart) {
		byte[] utf8Bom = new byte[]{(byte) 0xef, (byte) 0xbb, (byte) 0xbf};   
		String utf8BomStr="";   
		try {   
			utf8BomStr = new String(utf8Bom,"UTF-8");//定义BOM标记   
		} catch (UnsupportedEncodingException e) {   
		   e.printStackTrace();   
		}
		return utf8BomStr+"<?xml version=\"1.0\" encoding=\"UTF-8\"?>"+convertXML(chart2XML(chart));
	}
	
	
	
	private static String chart2XML(Chart chart) {
		XStream xstream = new XStream();
		xstream.alias("chart", Chart.class);
		xstream.useAttributeFor(Chart.class, "rotateYAxisName");
		xstream.useAttributeFor(Chart.class, "rotateLabels");
		xstream.useAttributeFor(Chart.class, "baseFont");
		xstream.useAttributeFor(Chart.class, "baseFontSize");
		xstream.useAttributeFor(Chart.class, "caption");
		xstream.useAttributeFor(Chart.class, "xAxisName");
		xstream.useAttributeFor(Chart.class, "yAxisName");
		xstream.useAttributeFor(Chart.class, "decimals");
		xstream.useAttributeFor(Chart.class, "formatNumberScale");
		
		xstream.alias("set", Set.class);
		xstream.addImplicitCollection(Chart.class, "set");
		xstream.useAttributeFor(Set.class, "label");
		xstream.useAttributeFor(Set.class, "value");
		return xstream.toXML(chart);
	}
	
	private static String convertXML(String src) {
		// replace all the double quotation to the single quotation
		// otherwise fusioncharts cannot display it correctly 
		Pattern p = Pattern.compile("\"");
		Matcher m = p.matcher(src);
		String tmp = m.replaceAll("'");
		// remove all the whitespaces between '>' and '<'
		// otherwise fusioncharts cannot display it correctly 
		p = Pattern.compile(">\\s+<");
		m = p.matcher(tmp);
		return m.replaceAll("><");
	}
}
