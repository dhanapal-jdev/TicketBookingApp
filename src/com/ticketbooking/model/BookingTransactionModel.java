/**
 * 
 */
package com.ticketbooking.model;

/**
 * @author Dhanapal
 *
 */
public class BookingTransactionModel {
	
	private long bookTranx = 0, customerId = 0, showId = 0,createTime = 0, updateTime = 0;
	private int noOfSeat = 0, bookedStatus = 0, paymentStatus = 0;
	private double totalAmount = 0.0;
	/**
	 * @return the bookTranx
	 */
	public long getBookTranx() {
		return bookTranx;
	}
	/**
	 * @param bookTranx the bookTranx to set
	 */
	public void setBookTranx(long bookTranx) {
		this.bookTranx = bookTranx;
	}
	/**
	 * @return the customerId
	 */
	public long getCustomerId() {
		return customerId;
	}
	/**
	 * @param customerId the customerId to set
	 */
	public void setCustomerId(long customerId) {
		this.customerId = customerId;
	}
	/**
	 * @return the showId
	 */
	public long getShowId() {
		return showId;
	}
	/**
	 * @param showId the showId to set
	 */
	public void setShowId(long showId) {
		this.showId = showId;
	}
	/**
	 * @return the createTime
	 */
	public long getCreateTime() {
		return createTime;
	}
	/**
	 * @param createTime the createTime to set
	 */
	public void setCreateTime(long createTime) {
		this.createTime = createTime;
	}
	/**
	 * @return the updateTime
	 */
	public long getUpdateTime() {
		return updateTime;
	}
	/**
	 * @param updateTime the updateTime to set
	 */
	public void setUpdateTime(long updateTime) {
		this.updateTime = updateTime;
	}
	/**
	 * @return the noOfSeat
	 */
	public int getNoOfSeat() {
		return noOfSeat;
	}
	/**
	 * @param noOfSeat the noOfSeat to set
	 */
	public void setNoOfSeat(int noOfSeat) {
		this.noOfSeat = noOfSeat;
	}
	/**
	 * @return the bookedStatus
	 */
	public int getBookedStatus() {
		return bookedStatus;
	}
	/**
	 * @param bookedStatus the bookedStatus to set
	 */
	public void setBookedStatus(int bookedStatus) {
		this.bookedStatus = bookedStatus;
	}
	/**
	 * @return the paymentStatus
	 */
	public int getPaymentStatus() {
		return paymentStatus;
	}
	/**
	 * @param paymentStatus the paymentStatus to set
	 */
	public void setPaymentStatus(int paymentStatus) {
		this.paymentStatus = paymentStatus;
	}
	/**
	 * @return the totalAmount
	 */
	public double getTotalAmount() {
		return totalAmount;
	}
	/**
	 * @param totalAmount the totalAmount to set
	 */
	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}
	
	
	

}
