package com.ticketbooking.model;

public class HallInformation {

	private long hallId,createdTime,updatedTime,seat;
	private String name = "", description = "",address = "",phone = "";
	private int status ;
	
	public long getHallId() {
		return hallId;
	}
	public void setHallId(long hallId) {
		this.hallId = hallId;
	}
	public long getCreatedTime() {
		return createdTime;
	}
	public void setCreatedTime(long createdTime) {
		this.createdTime = createdTime;
	}
	public long getUpdatedTime() {
		return updatedTime;
	}
	public void setUpdatedTime(long updatedTime) {
		this.updatedTime = updatedTime;
	}
	public long getSeat() {
		return seat;
	}
	public void setSeat(long seat) {
		this.seat = seat;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}

}
