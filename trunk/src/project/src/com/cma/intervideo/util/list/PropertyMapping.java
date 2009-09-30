package com.cma.intervideo.util.list;

import java.util.Map;

/**
 * 用于给resultList中的bean添加属性
 */
public class PropertyMapping {
	String source;
	String target;
	Converter converter;
	
	/**
	 * @param source 源属性名
	 * @param target 添加的新属性名
	 * @param converter 由原属性转化为新属性的方法。例如一个statusMap <0-正常，1-删除>，可以用一个MapConverter将0，1转化为正常、删除
	 */
	public PropertyMapping(String source, String target, Converter converter) {
		this.source = source;
		this.target = target;
		this.converter = converter;
	}
	
	@SuppressWarnings("unchecked")
	public PropertyMapping(String source, String target, Map map) {
		this.source = source;
		this.target = target;
		this.converter = new MapConverter(map);
	}
}