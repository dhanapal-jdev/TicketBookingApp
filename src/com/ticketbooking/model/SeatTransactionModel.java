package com.ticketbooking.model;

public class SeatTransactionModel {

	private long seatId = 0, bookTrnx = 0;
	
	private long rowId = 0, seatNumber = 0;

	/**
	 * @return the seatId
	 */
	public long getSeatId() {
		return seatId;
	}

	/**
	 * @param seatId the seatId to set
	 */
	public void setSeatId(long seatId) {
		this.seatId = seatId;
	}

	/**
	 * @return the bookTrnx
	 */
	public long getBookTrnx() {
		return bookTrnx;
	}

	/**
	 * @param bookTrnx the bookTrnx to set
	 */
	public void setBookTrnx(long bookTrnx) {
		this.bookTrnx = bookTrnx;
	}

	/**
	 * @return the rowId
	 */
	public long getRowId() {
		return rowId;
	}

	/**
	 * @param rowId the rowId to set
	 */
	public void setRowId(long rowId) {
		this.rowId = rowId;
	}

	/**
	 * @return the seatNumber
	 */
	public long getSeatNumber() {
		return seatNumber;
	}

	/**
	 * @param seatNumber the seatNumber to set
	 */
	public void setSeatNumber(long seatNumber) {
		this.seatNumber = seatNumber;
	}
	
	
}
