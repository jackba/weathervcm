/**
 * 2009-6-22
 */
package com.cma.intervideo.util.list;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;


/**
 * @author geyb
 */
public class PageConditions {
	private List<QueryParam> params = new ArrayList<QueryParam>();
	private int start;
	private int limit;
	private int totalCount;
	private boolean isSorted;
	private String sort;
	private String direct;
	
	/** 使用CGLib的BeanMap增强原有的bean，动态给bean添加属性用于页面显示 */
	private List<PropertyMapping> mappings = new ArrayList<PropertyMapping>();
	
	/** 用于SQL查询返回数组对象时，将数组中其它对象需要显示的属性添加到第一个对象中 */
	private List<ObjectPropertyMapping> multiObjects = new ArrayList<ObjectPropertyMapping>();
	
	public List<QueryParam> getParams() {
		return params;
	}
	
	public PageConditions addParam(QueryParam param) {
		this.params.add(param);
		return this;
	}
	
	public PageConditions addParam(String name, Object value) {
		this.params.add(new QueryParam(name, value));
		return this;
	}
	
	public PageConditions addParam(String name, Object value, int type) {
		this.params.add(new QueryParam(name, value, type));
		return this;
	}
	
	public PageConditions addNotBlank(String name, String value) {
		if (StringUtils.isNotBlank(value)) {
			this.addParam(name, value);
		}
		return this;
	}

	public PageConditions addNotBlank(String name, String value, int type) {
		if (StringUtils.isNotBlank(value)) {
			this.addParam(name, value, type);
		}
		return this;
	}
	
	public PageConditions addNotNull(String name, Object value) {
		if (value != null) {
			this.addParam(name, value);
		}
		return this;
	}
	
	public PageConditions addNotNull(String name, Object value, int type) {
		if (value != null) {
			this.addParam(name, value, type);
		}
		return this;
	}
	
	public void setParams(List<QueryParam> params) {
		this.params = params;
	}

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public String getDirect() {
		return direct;
	}

	public void setDirect(String direct) {
		this.direct = direct;
	}

	/**
	 * @param isSorted the isSorted to set
	 */
	public void setSorted(boolean isSorted) {
		this.isSorted = isSorted;
	}

	/**
	 * @return the isSorted
	 */
	public boolean isSorted() {
		return isSorted;
	}

	public List<PropertyMapping> getMappings() {
		return mappings;
	}
	public void addMapping(PropertyMapping mapping) {
		this.mappings.add(mapping);
	}
	public void addMapping(String source, String target, Converter converter) {
		this.mappings.add(new PropertyMapping(source, target, converter));
	}
	@SuppressWarnings("unchecked")
	public void addMapping(String source, String target, Map map) {
		this.mappings.add(new PropertyMapping(source, target, map));
	}
	public PageConditions addObjectMapping(int index, String property, String target) {
		this.multiObjects.add(new ObjectPropertyMapping(index, property, target));
		return this;
	}
	public List<ObjectPropertyMapping> getObjectMappings() {
		return this.multiObjects;
	}
}
