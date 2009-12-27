package com.cma.intervideo.vo.stcol3d;

import java.util.List;

public class Chart extends com.cma.intervideo.vo.Chart{
	private String showvalues = "0";
	private String rotateLabels = "1";
	private String rotateYAxisName = "0";
	private String baseFont = "宋体";
	private String baseFontSize = "14";
	private String xAxisName;
	private String yAxisName;
	private String caption;
	private String showSum = "1";
	private String decimals = "0";
	private String overlapColumns = "1";
	private String showLegend = "1";
	private List<Category> categories;
	private List<Dataset> datasets;
	public String getRotateLabels() {
		return rotateLabels;
	}
	public void setRotateLabels(String rotateLabels) {
		this.rotateLabels = rotateLabels;
	}
	public String getRotateYAxisName() {
		return rotateYAxisName;
	}
	public void setRotateYAxisName(String rotateYAxisName) {
		this.rotateYAxisName = rotateYAxisName;
	}
	public String getBaseFont() {
		return baseFont;
	}
	public void setBaseFont(String baseFont) {
		this.baseFont = baseFont;
	}
	public String getBaseFontSize() {
		return baseFontSize;
	}
	public void setBaseFontSize(String baseFontSize) {
		this.baseFontSize = baseFontSize;
	}
	public String getXAxisName() {
		return xAxisName;
	}
	public void setXAxisName(String axisName) {
		xAxisName = axisName;
	}
	public String getYAxisName() {
		return yAxisName;
	}
	public void setYAxisName(String axisName) {
		yAxisName = axisName;
	}
	public String getCaption() {
		return caption;
	}
	public void setCaption(String caption) {
		this.caption = caption;
	}
	
	public String getShowvalues() {
		return showvalues;
	}
	public void setShowvalues(String showvalues) {
		this.showvalues = showvalues;
	}
	public String getShowLegend() {
		return showLegend;
	}
	public void setShowLegend(String showLegend) {
		this.showLegend = showLegend;
	}
	public String getShowSum() {
		return showSum;
	}
	public void setShowSum(String showSum) {
		this.showSum = showSum;
	}
	public String getDecimals() {
		return decimals;
	}
	public void setDecimals(String decimals) {
		this.decimals = decimals;
	}
	public String getOverlapColumns() {
		return overlapColumns;
	}
	public void setOverlapColumns(String overlapColumns) {
		this.overlapColumns = overlapColumns;
	}
	public List<Category> getCategories() {
		return categories;
	}
	public void setCategories(List<Category> categories) {
		this.categories = categories;
	}
	public List<Dataset> getDatasets() {
		return datasets;
	}
	public void setDatasets(List<Dataset> datasets) {
		this.datasets = datasets;
	}
	
}
