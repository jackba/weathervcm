/**
 * 2008-8-25
 */
package com.cma.intervideo.util.list;

/**
 * @author geyb
 */
public class ObjectPropertyMapping {
	int index;
	String property;
	String target;
	
	public ObjectPropertyMapping(int index, String property, String target) {
		this.index = index;
		this.property = property;
		this.target = target;
	}
}