/**
 * 2009-7-8
 */
package com.cma.intervideo.util.list;

import java.util.List;

import net.sf.cglib.beans.BeanCopier;
import net.sf.cglib.beans.BeanGenerator;
import net.sf.cglib.beans.BeanMap;

import org.apache.commons.beanutils.PropertyUtils;


/**
 * @author geyb
 */
@SuppressWarnings("unchecked")
public class Enhancer {
	public void enhance(List resultList, PageConditions conditions) {
		enhanceMultiObjects(resultList, conditions.getObjectMappings());
		enhance(resultList, conditions.getMappings());
	}
	
	public void enhance(List resultList, List<PropertyMapping> mappings) {
		if (resultList.isEmpty()) return;
		if (mappings.isEmpty()) return;
		
		BeanGenerator generator = new BeanGenerator();
		generator.setSuperclass(resultList.get(0).getClass());
		for (PropertyMapping enhancer : mappings) {
			generator.addProperty(enhancer.target, Object.class);
		}
		Object bean = generator.create();
		BeanCopier copier = BeanCopier.create(resultList.get(0).getClass(), 
				bean.getClass(), false);
		
		BeanMap map = BeanMap.create(bean); //为性能优化。BeanMap的newInstance比create方法快很多
		for (int i=0, n=resultList.size(); i<n; ++i) {
			bean = generator.create();
			copier.copy(resultList.get(i), bean, null);
			map = map.newInstance(bean);
			for (PropertyMapping enhancer : mappings) {
				map.put(enhancer.target, 
						enhancer.converter.convert(map.get(enhancer.source)));
			}

			resultList.set(i, map.getBean());
		}
	}
	
	/**
	 * 有可能查询会返回多个对象的一个数组，比如select a,b,c from A a, B b, C c where ...
	 * 这时候可以用这个方法把b,c中需要的属性放到a里去。
	 * @param index 数组中的第几个对象
	 * @param property 需要复制的属性名称。为空表示复制整个对象
	 * @param target 放到a里面的新属性的名称
	 */
	public void enhanceMultiObjects(List resultList, List<ObjectPropertyMapping> arrayObjects) {
		if (arrayObjects.isEmpty()) return;
		if (resultList.isEmpty()) return;

		BeanGenerator generator = new BeanGenerator();
		Class first = ((Object[])resultList.get(0))[0].getClass();
		generator.setSuperclass(first);
		for (ObjectPropertyMapping opm : arrayObjects) {
			generator.addProperty(opm.target, Object.class);
		}
		Object bean = generator.create();
		BeanCopier copier = BeanCopier.create(first, bean.getClass(), false);
		
		BeanMap map = BeanMap.create(bean); //为性能优化。BeanMap的newInstance比create方法快很多
		int i = 0;
		try {
			for (Object obj : resultList) {
				Object[] objs = (Object[])obj;
				bean = generator.create();
				copier.copy(((Object[])obj)[0], bean, null);
				map = map.newInstance(bean);
				for (ObjectPropertyMapping opm : arrayObjects) {
					if (opm.property == null) {
						map.put(opm.target, objs[opm.index]);
					} else {
						map.put(opm.target, PropertyUtils.getProperty(
								objs[opm.index], opm.property));
					}
				}
				resultList.set(i++, map.getBean());
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	
}
