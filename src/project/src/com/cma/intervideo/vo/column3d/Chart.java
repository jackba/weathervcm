package com.cma.intervideo.vo.column3d;

import java.util.List;

public class Chart extends com.cma.intervideo.vo.Chart{
	private String rotateLabels = "0";
	private String rotateYAxisName = "0";
	private String baseFont = "宋体";
	private String baseFontSize = "14";
	private String caption;
	private String xAxisName;
	private String yAxisName;
	private String decimals = "0";
	private String formatNumberScale = "0";
	private List<Set> set;
	public String getCaption() {
		return caption;
	}
	public void setCaption(String caption) {
		this.caption = caption;
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
	public String getDecimals() {
		return decimals;
	}
	public void setDecimals(String decimals) {
		this.decimals = decimals;
	}
	public String getFormatNumberScale() {
		return formatNumberScale;
	}
	public void setFormatNumberScale(String formatNumberScale) {
		this.formatNumberScale = formatNumberScale;
	}
	public List<Set> getSet() {
		return set;
	}
	public void setSet(List<Set> set) {
		this.set = set;
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
	public String getRotateLabels() {
		return rotateLabels;
	}
	public void setRotateLabels(String rotateLabels) {
		this.rotateLabels = rotateLabels;
	}
	
	
}
