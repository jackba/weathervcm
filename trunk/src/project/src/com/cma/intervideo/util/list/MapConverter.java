/**
 * 2008-3-23
 */
package com.cma.intervideo.util.list;

import java.util.Map;

/**
 * @author geyb
 */
@SuppressWarnings("unchecked")
public class MapConverter implements Converter {
	private Map map;
	
	public MapConverter() {}
	
	public MapConverter(Map map) {
		this.map = map;
	}

	/* (non-Javadoc)
	 * @see com.asiainfo.intervideo.util.extreme.DataProcessor#execute(java.lang.Object)
	 */
	public Object convert(Object data) {
		Object res = map.get(data);
		if (res == null) {
			return data;
		}
		
		return res;
	}

	public void setMap(Map map) {
		this.map = map;
	}
}
