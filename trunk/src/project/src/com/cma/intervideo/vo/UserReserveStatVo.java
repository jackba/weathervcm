package com.cma.intervideo.vo;

public class UserReserveStatVo {
	private int index;
	private String userName;
	private int number;
	private int length;
	
	public String toString() {
		StringBuilder buf = new StringBuilder();
		
		String line = System.getProperty("line.separator");
		buf.append(line + "[UserReserveStatVo:( @" + hashCode() + ")" + line);
		buf.append(" index = " + index + ", userName = " + userName + line);
		buf.append(" number = " + number + ", length = " + length + line);
		buf.append("]." + line);
		return buf.toString();
	}
	
	public int getIndex() {
		return index;
	}
	public void setIndex(int index) {
		this.index = index;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public int getLength() {
		return length;
	}
	public void setLength(int length) {
		this.length = length;
	}
	
}
