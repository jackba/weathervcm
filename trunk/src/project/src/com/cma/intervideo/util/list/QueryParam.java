/**
 * 2007-10-25
 */
package com.cma.intervideo.util.list;

/**
 * @author geyb
 */
public class QueryParam {
	public static final int PARAM_TYPE_EQUAL = 0;
	public static final int PARAM_TYPE_LIKE = 1;
	public static final int PARAM_TYPE_LESSTHAN = 2;
	public static final int PARAM_TYPE_GREATTHAN = 3;
	public static final int PARAM_TYPE_LESSEQUAL = 4;
	public static final int PARAM_TYPE_GREATEQUAL = 5;
	public static final int PARAM_TYPE_NOTEQUAL = 6;
	public static final int PARAM_TYPE_IN = 7;
	public static final int PARAM_TYPE_LIKE_RIGHT = 8;
	
	private String paramName;
	private Object paramValue;
	private int type = PARAM_TYPE_EQUAL;
	
	public QueryParam() {}
	
	public QueryParam(String name, Object value) {
		this.paramName = name;
		this.paramValue = value;
	}

	public QueryParam(String name, Object value, int type) {
		this(name, value);
		this.type = type;
	}
	
	public String getParamName() {
		return paramName;
	}
	public QueryParam setParamName(String paramName) {
		this.paramName = paramName;
		return this;
	}
	public Object getParamValue() {
		return paramValue;
	}
	public QueryParam setParamValue(Object paramValue) {
		this.paramValue = paramValue;
		return this;
	}
	public int getType() {
		return type;
	}
	public QueryParam setType(int type) {
		this.type = type;
		return this;
	}
}
