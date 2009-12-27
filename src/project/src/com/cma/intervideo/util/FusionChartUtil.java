package com.cma.intervideo.util;

import java.io.UnsupportedEncodingException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.cma.intervideo.vo.Chart;
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
		
		if(chart instanceof com.cma.intervideo.vo.column3d.Chart){
			xstream.alias("chart", com.cma.intervideo.vo.column3d.Chart.class);
			xstream.useAttributeFor(com.cma.intervideo.vo.column3d.Chart.class, "rotateYAxisName");
			xstream.useAttributeFor(com.cma.intervideo.vo.column3d.Chart.class, "rotateLabels");
			xstream.useAttributeFor(com.cma.intervideo.vo.column3d.Chart.class, "baseFont");
			xstream.useAttributeFor(com.cma.intervideo.vo.column3d.Chart.class, "baseFontSize");
			xstream.useAttributeFor(com.cma.intervideo.vo.column3d.Chart.class, "caption");
			xstream.useAttributeFor(com.cma.intervideo.vo.column3d.Chart.class, "xAxisName");
			xstream.useAttributeFor(com.cma.intervideo.vo.column3d.Chart.class, "yAxisName");
			xstream.useAttributeFor(com.cma.intervideo.vo.column3d.Chart.class, "decimals");
			xstream.useAttributeFor(com.cma.intervideo.vo.column3d.Chart.class, "formatNumberScale");
			xstream.alias("set", com.cma.intervideo.vo.column3d.Set.class);
			xstream.addImplicitCollection(com.cma.intervideo.vo.column3d.Chart.class, "set");
			xstream.useAttributeFor(com.cma.intervideo.vo.column3d.Set.class, "label");
			xstream.useAttributeFor(com.cma.intervideo.vo.column3d.Set.class, "value");
		}
		if(chart instanceof com.cma.intervideo.vo.stcol3d.Chart){
			xstream.alias("chart", com.cma.intervideo.vo.stcol3d.Chart.class);
			xstream.useAttributeFor(com.cma.intervideo.vo.stcol3d.Chart.class, "rotateYAxisName");
			xstream.useAttributeFor(com.cma.intervideo.vo.stcol3d.Chart.class, "rotateLabels");
			xstream.useAttributeFor(com.cma.intervideo.vo.stcol3d.Chart.class, "baseFont");
			xstream.useAttributeFor(com.cma.intervideo.vo.stcol3d.Chart.class, "baseFontSize");
			xstream.useAttributeFor(com.cma.intervideo.vo.stcol3d.Chart.class, "caption");
			xstream.useAttributeFor(com.cma.intervideo.vo.stcol3d.Chart.class, "xAxisName");
			xstream.useAttributeFor(com.cma.intervideo.vo.stcol3d.Chart.class, "yAxisName");
			xstream.useAttributeFor(com.cma.intervideo.vo.stcol3d.Chart.class, "decimals");
			xstream.useAttributeFor(com.cma.intervideo.vo.stcol3d.Chart.class, "showvalues");
			xstream.useAttributeFor(com.cma.intervideo.vo.stcol3d.Chart.class, "showLegend");
			xstream.useAttributeFor(com.cma.intervideo.vo.stcol3d.Chart.class, "showSum");
			xstream.useAttributeFor(com.cma.intervideo.vo.stcol3d.Chart.class, "overlapColumns");
			xstream.alias("category", com.cma.intervideo.vo.stcol3d.Category.class);
			xstream.useAttributeFor(com.cma.intervideo.vo.stcol3d.Category.class, "label");
			xstream.addImplicitCollection(com.cma.intervideo.vo.stcol3d.Chart.class, "datasets");
			xstream.alias("dataset", com.cma.intervideo.vo.stcol3d.Dataset.class);
			xstream.useAttributeFor(com.cma.intervideo.vo.stcol3d.Dataset.class, "seriesName");
			xstream.useAttributeFor(com.cma.intervideo.vo.stcol3d.Dataset.class, "showValues");
			xstream.useAttributeFor(com.cma.intervideo.vo.stcol3d.Dataset.class, "color");
			xstream.addImplicitCollection(com.cma.intervideo.vo.stcol3d.Dataset.class, "sets");
			xstream.alias("set", com.cma.intervideo.vo.stcol3d.Set.class);
			xstream.useAttributeFor(com.cma.intervideo.vo.stcol3d.Set.class, "value");
		}
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
