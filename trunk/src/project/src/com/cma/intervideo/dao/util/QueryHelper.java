/**
 * 2007-12-12
 */
package com.cma.intervideo.dao.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.cma.intervideo.util.list.PageConditions;
import com.cma.intervideo.util.list.QueryParam;


/**
 * @author geyb
 */
public class QueryHelper {
	public static String createHql(Class<?> type, PageConditions conditions) {
		StringBuilder builder = new StringBuilder();
		builder.append("from ").append(type.getSimpleName()).append(" t");
		addWhereClause(conditions, builder);
		if (conditions.isSorted()) {
			builder.append(" order by t.").append(conditions.getSort());
			if ("DESC".equalsIgnoreCase(conditions.getDirect())) {
				builder.append(" desc");
			} else if ("ASC".equalsIgnoreCase(conditions.getDirect())) {
				builder.append(" asc");
			}
		}
		return builder.toString();
	}
	
	public static String createCountHql(Class<?> type, PageConditions conditions) {
		StringBuilder builder = new StringBuilder();
		builder.append("select count(*) from ").append(type.getSimpleName()).append(" t");
		addWhereClause(conditions, builder);
		return builder.toString();
	}

	private static void addWhereClause(PageConditions conditions,
			StringBuilder builder) {
		if (!conditions.getParams().isEmpty()) {
			builder.append(" where");
			int index = 0;
			for (QueryParam param : conditions.getParams()) {
				if (index > 0) builder.append(" and");
				builder.append(" t.").append(param.getParamName());
				String bindName = param.getParamName().replaceAll("\\.", "") + param.getType();
				switch (param.getType()) {
				case QueryParam.PARAM_TYPE_EQUAL:
					builder.append("=:").append(bindName);
					break;
				case QueryParam.PARAM_TYPE_LIKE:
				case QueryParam.PARAM_TYPE_LIKE_RIGHT:
					builder.append(" like :").append(bindName);
					break;
				case QueryParam.PARAM_TYPE_LESSTHAN:
					builder.append("<:").append(bindName);
					break;
				case QueryParam.PARAM_TYPE_LESSEQUAL:
					builder.append("<=:").append(bindName);
					break;
				case QueryParam.PARAM_TYPE_GREATTHAN:
					builder.append(">:").append(bindName);
					break;
				case QueryParam.PARAM_TYPE_GREATEQUAL:
					builder.append(">=:").append(bindName);
					break;
				case QueryParam.PARAM_TYPE_NOTEQUAL:
					builder.append("!=:").append(bindName);
					break;
				case QueryParam.PARAM_TYPE_IN:
					builder.append(" in (:").append(bindName).append(")");
					break;
				}
				index++;
			}
		}
	}
	
	@SuppressWarnings("unchecked")
	public static List<Criterion> createQuery(List<QueryParam> list) {
		List<Criterion> query = new ArrayList<Criterion>();
		for (QueryParam param : list) {
			switch (param.getType()) {
			case QueryParam.PARAM_TYPE_EQUAL:
				query.add(Restrictions.eq(param.getParamName(), param.getParamValue()));
				break;
			case QueryParam.PARAM_TYPE_LIKE:
				query.add(Restrictions.like(param.getParamName(), "%" + param.getParamValue() + "%"));
				break;
			case QueryParam.PARAM_TYPE_LIKE_RIGHT:
				query.add(Restrictions.like(param.getParamName(), param.getParamValue() + "%"));
				break;
			case QueryParam.PARAM_TYPE_LESSTHAN:
				query.add(Restrictions.lt(param.getParamName(), param.getParamValue()));
				break;
			case QueryParam.PARAM_TYPE_LESSEQUAL:
				query.add(Restrictions.le(param.getParamName(), param.getParamValue()));
				break;
			case QueryParam.PARAM_TYPE_GREATTHAN:
				query.add(Restrictions.gt(param.getParamName(), param.getParamValue()));
				break;
			case QueryParam.PARAM_TYPE_GREATEQUAL:
				query.add(Restrictions.ge(param.getParamName(), param.getParamValue()));
				break;
			case QueryParam.PARAM_TYPE_NOTEQUAL:
				query.add(Restrictions.ne(param.getParamName(), param.getParamValue()));
				break;
			case QueryParam.PARAM_TYPE_IN:
				query.add(Restrictions.in(param.getParamName(), (Collection)param.getParamValue()));
				break;
			}
		}
		return query;
	}

	@SuppressWarnings("unchecked")
	public static DetachedCriteria createCriteria(Class type, PageConditions conditions,
			List<Criterion> critList) {
		DetachedCriteria criteria = DetachedCriteria.forClass(type);
		for (Criterion cirterion : critList) {
			criteria.add(cirterion);
		}
		if (conditions.isSorted()) {
			if ("DESC".equalsIgnoreCase(conditions.getDirect())) {
				criteria.addOrder(Order.desc(conditions.getSort()));
			} else if ("ASC".equalsIgnoreCase(conditions.getDirect())) {
				criteria.addOrder(Order.asc(conditions.getSort()));
			}
		}
		return criteria;
	}

	@SuppressWarnings("unchecked")
	public static DetachedCriteria createCriteria(Class type, List<Criterion> critList) {
		DetachedCriteria criteria = DetachedCriteria.forClass(type);
		for (Criterion cirterion : critList) {
			criteria.add(cirterion);
		}
		return criteria;
	}
	
	public static Object[] getObjectParams(PageConditions conditions) {
		List<Object> list = new ArrayList<Object>();
		for (QueryParam param : conditions.getParams()) {
    		if (param.getType() == QueryParam.PARAM_TYPE_LIKE) {
    			list.add("%" + param.getParamValue() + "%");
    		} else if (param.getType() == QueryParam.PARAM_TYPE_LIKE_RIGHT) {
    			list.add(param.getParamValue() + "%");
    		} else {
    			list.add(param.getParamValue());
    		}
		}
		return list.toArray();
	}

	public static String addQueryParam(String query, PageConditions conditions) {
		StringBuilder builder = new StringBuilder(query);
		for (QueryParam param : conditions.getParams()) {
			builder.append(" AND ");
			builder.append(param.getParamName());
			switch (param.getType()) {
			case QueryParam.PARAM_TYPE_EQUAL:
				builder.append("=?");
				continue;
			case QueryParam.PARAM_TYPE_LIKE:
			case QueryParam.PARAM_TYPE_LIKE_RIGHT:
				builder.append(" like ?");
				continue;
			case QueryParam.PARAM_TYPE_GREATTHAN:
				builder.append(">?");
				continue;
			case QueryParam.PARAM_TYPE_GREATEQUAL:
				builder.append(">=?");
				continue;
			case QueryParam.PARAM_TYPE_LESSTHAN:
				builder.append("<?");
				continue;
			case QueryParam.PARAM_TYPE_LESSEQUAL:
				builder.append("<=?");
				continue;
			case QueryParam.PARAM_TYPE_NOTEQUAL:
				builder.append("!=?");
				continue;
			case QueryParam.PARAM_TYPE_IN:
				builder.append(" in (");
				int len = ((java.util.Collection) param.getParamValue()).size();
				for (int i = 0; i < len - 1; i++) {
					builder.append("?,");
				}
				builder.append("?)");
				continue;
			}
		}
		return builder.toString();
	}
	
	public static String createHql(String query, PageConditions conditions) {
		query = addQueryParam(query, conditions);
		if (conditions.isSorted()) {
			query += " order by " + conditions.getSort() + " " + conditions.getDirect();
		}
		return query;
	}
}
