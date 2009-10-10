package com.cma.intervideo.vo;

public class ResourceVo {
	private int occupyNum;
	private int availableNum;
	//小时分钟,格式为22:15
	private String hourMinutes;
	public int getOccupyNum() {
		return occupyNum;
	}
	public void setOccupyNum(int occupyNum) {
		this.occupyNum = occupyNum;
	}
	public int getAvailableNum() {
		return availableNum;
	}
	public void setAvailableNum(int availableNum) {
		this.availableNum = availableNum;
	}
	public String getHourMinutes() {
		return hourMinutes;
	}
	public void setHourMinutes(String hourMinutes) {
		this.hourMinutes = hourMinutes;
	}
	
	
}
