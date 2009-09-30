/**
 * 2008-1-28
 */
package com.cma.intervideo.dao.util;

import java.io.Serializable;
import java.util.List;

import com.cma.intervideo.util.list.PageConditions;
import com.cma.intervideo.util.list.QueryParam;

/**
 * @author geyb
 */
public interface ObjectDao {
	
	void save(Object obj);
	
	Object merge(Object obj);
	
	<T> T getById(Class<T> type, Serializable id);
	
	<T> List<T> getAll(Class<T> type);
	
	<T> void remove(Class<T> type, Serializable id);
	
	void remove(Object obj);
	
	void initialize(Object obj);

	<T> void removeAll(Class<T> type);

	/**
	 * 根据单一条件查询
	 */
	<T> List<T> get(Class<T> type, String param, Object value);
	
	/**
	 * update某个字段的值，根据id
	 * 找到这条记录，update成功，返回true
	 * 否则返回false
	 */
	<T> boolean updateById(Class<T> type, Serializable id, String param, Object value);

	//以下为分页列表查询
	/**
	 * 使用criteria查询。但无法进行关联的查询（写起来太麻烦了）。
	 * @deprecated
	 */
    <T> List<T> getByCriteria(Class<T> type, PageConditions conditions);

    /**
     * 使用criteria查询。但无法进行关联的查询（写起来太麻烦了）。
     * @deprecated
     */
    <T> int getTotalCount(Class<T> type, List<QueryParam> list);
    
    /**
     * 使用hql查询。利用hql的隐式join特性，关联的查询写起来很方便
     */
    <T> List<T> get(Class<T> type, PageConditions conditions);
    
    /**
     * 使用hql查询符合条件的记录总数
     */
    <T> int getTotalCount(Class<T> type, PageConditions conditions);

	/**
	 * 使用hql查询符合条件的记录总数
	 */
	int getTotalCount(String countQuery, PageConditions conditions);

	/**
	 * 根据hql查询符合条件的列表
	 */
	List<?> get(String query, PageConditions conditions);
}
