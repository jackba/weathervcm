package com.cma.intervideo.pojo;

public class UserUnitId implements java.io.Serializable{
	private String userId;
	private int unitId;
	public UserUnitId(){
		
	}
	public UserUnitId(String userId, int unitId){
		this.userId = userId;
		this.unitId = unitId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public int getUnitId() {
		return unitId;
	}
	public void setUnitId(int unitId) {
		this.unitId = unitId;
	}
	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof UserUnitId))
			return false;
		UserUnitId castOther = (UserUnitId) other;

		return (this.getUnitId() == castOther.getUnitId())
				&& ((this.getUserId() == castOther.getUserId()) || (this
						.getUserId() != null
						&& castOther.getUserId() != null && this.getUserId()
						.equals(castOther.getUserId())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + this.getUnitId();
		result = 37 * result
				+ (getUserId() == null ? 0 : this.getUserId().hashCode());
		return result;
	}
}
