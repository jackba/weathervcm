/**
 * 2009-6-22
 */
package com.cma.intervideo.util.list;

import java.util.List;

/**
 * @author geyb
 */
@SuppressWarnings("unchecked")
public class PageResult {
	private int totalCount;
	private List resultList;
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	public List getResultList() {
		return resultList;
	}
	public void setResultList(List resultList) {
		this.resultList = resultList;
	}

	private int pageNum;
	private int totalPage;
	
	public void setPageDetail(PageConditions conditions) {
		setPageDetail(conditions.getStart(), conditions.getLimit());
	}
	
	public void setPageDetail(int start, int limit) {
		pageNum = start / limit + 1;
		totalPage = (totalCount - 1) / limit + 1;
	}
	
	public int getPageNum() {
		return pageNum;
	}

	public int getTotalPage() {
		return totalPage;
	}
}
