
package com.ticketbooking.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.ticketbooking.model.BookingTransactionModel;
import com.ticketbooking.model.CustomerContactsModel;
import com.ticketbooking.model.SeatTransactionModel;
import com.ticketbooking.transaction.BookingTransactionDaoImpl;


/**
 * @author Dhanapal
 *
 */
public class BookingTransactionController extends ActionSupport {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String retSuccess;
	private int retFlag = 0;
	private int requestType = -1;

	private static final int BOOKING_NO_ERROR = 1;
	private static final int BOOKING_NO_ERROR_WARNING = 2;
	private static final int BOOKING_ERROR_NO_RESPONSE = 3;
	private static final int BOOKING_ERROR_UNAVAILABLE = 4;
	private static final int BOOKING_UNKNOWN_ERROR = 5;
	private static final int BOOKING_UNATHORIZED_REQUEST = 6;

		
	private static Map<Integer,String> errorMessageVal = null;

	private static int GET_TICKET_BOOKING_INFORMATION = 1;
	
	List<BookingTransactionModel> lstBookedSeatInformation = null;
	List<SeatTransactionModel> lstSeatTransactionModel = null;
	CustomerContactsModel customerInfo = null;
	SeatTransactionModel seatBookingInfo = null;
	private long showId = 0 ,bookTrnx = 0,movieId = 0 , hallId = 0;

	
	/**
	 * @return the customerInfo
	 */
	public CustomerContactsModel getCustomerInfo() {
		return customerInfo;
	}

	/**
	 * @param customerInfo the customerInfo to set
	 */
	public void setCustomerInfo(CustomerContactsModel customerInfo) {
		this.customerInfo = customerInfo;
	}

	/**
	 * @return the seatBookingInfo
	 */
	public SeatTransactionModel getSeatBookingInfo() {
		return seatBookingInfo;
	}

	/**
	 * @param seatBookingInfo the seatBookingInfo to set
	 */
	public void setSeatBookingInfo(SeatTransactionModel seatBookingInfo) {
		this.seatBookingInfo = seatBookingInfo;
	}

	/**
	 * @return the lstSeatTransactionModel
	 */
	public List<SeatTransactionModel> getLstSeatTransactionModel() {
		return lstSeatTransactionModel;
	}

	/**
	 * @param lstSeatTransactionModel the lstSeatTransactionModel to set
	 */
	public void setLstSeatTransactionModel(
			List<SeatTransactionModel> lstSeatTransactionModel) {
		this.lstSeatTransactionModel = lstSeatTransactionModel;
	}

	/**
	 * @return the requestType
	 */
	public int getRequestType() {
		return requestType;
	}

	/**
	 * @param requestType the requestType to set
	 */
	public void setRequestType(int requestType) {
		this.requestType = requestType;
	}

	/**
	 * @return the lstBookedSeatInformation
	 */
	public List<BookingTransactionModel> getLstBookedSeatInformation() {
		return lstBookedSeatInformation;
	}

	/**
	 * @param lstBookedSeatInformation the lstBookedSeatInformation to set
	 */
	public void setLstBookedSeatInformation(
			List<BookingTransactionModel> lstBookedSeatInformation) {
		this.lstBookedSeatInformation = lstBookedSeatInformation;
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
	 * @return the movieId
	 */
	public long getMovieId() {
		return movieId;
	}

	/**
	 * @param movieId the movieId to set
	 */
	public void setMovieId(long movieId) {
		this.movieId = movieId;
	}

	/**
	 * @return the hallId
	 */
	public long getHallId() {
		return hallId;
	}

	/**
	 * @param hallId the hallId to set
	 */
	public void setHallId(long hallId) {
		this.hallId = hallId;
	}

	public BookingTransactionController(){
		if (errorMessageVal == null) {
			errorMessageVal = new HashMap<Integer, String>();
		}
		
		if (errorMessageVal.isEmpty())
		{
			errorMessageVal.put(BookingTransactionController.BOOKING_NO_ERROR, "No Error");
			errorMessageVal.put(BookingTransactionController.BOOKING_NO_ERROR_WARNING, "No Error, but there is a warning");
			errorMessageVal.put(BookingTransactionController.BOOKING_ERROR_UNAVAILABLE, "Ticket Booking is Unavailable");
			errorMessageVal.put(BookingTransactionController.BOOKING_ERROR_NO_RESPONSE, "Ticket Booking hasn't gotten a response still");
			errorMessageVal.put(BookingTransactionController.BOOKING_UNKNOWN_ERROR, "Unknown error while processing Ticket Booking Fetch & Response");
			errorMessageVal.put(BookingTransactionController.BOOKING_UNATHORIZED_REQUEST, "Ticket Booking Unauthorized request or session invalidated");
		}
	}
	private int errorCode = BookingTransactionController.BOOKING_NO_ERROR;
	private String errorMessage = "";
	
	public String getRetSuccess() {
		return retSuccess;
	}

	public void setRetSuccess(String retSuccess) {
		this.retSuccess = retSuccess;
	}

	public int getRetFlag() {
		return retFlag;
	}

	public void setRetFlag(int retFlag) {
		this.retFlag = retFlag;
	}

	

	public String newTicketBooking() {
		System.out.println("Action getting triggered");
		return "success";
		
	}
	public int getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorMessage() {
		String toRet = this.errorMessage;
		if (this.errorCode != BookingTransactionController.BOOKING_UNKNOWN_ERROR)
		{
			toRet = BookingTransactionController.errorMessageVal.get(this.errorCode);
			if (toRet == null || toRet.length() <= 0) {
				toRet = "No Error";
			}
		}
		return toRet;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}


	
	public String execute() throws Exception {
		HttpServletRequest request = null;
		String toRet = ERROR;
		String actionName = ActionContext.getContext().getName();
		System.out.println("action Name :: " + actionName);
		if (requestType <= 0) {

			if (actionName.equals("getBookedSeatInformation")) {
				requestType = BookingTransactionController.GET_TICKET_BOOKING_INFORMATION ;
			} else {
				requestType = -1;
			}
		}

		if (requestType == BookingTransactionController.GET_TICKET_BOOKING_INFORMATION) {
			System.out.println("Fcuntion for Getting show information ");
			toRet = this.getBookedSeatInformation();
		}
		return toRet;
	}

	
	/**
	 * @return the lstTicketBookedInformation
	 */
	public List<BookingTransactionModel> getLstTicketBookedInformation() {
		return lstBookedSeatInformation;
	}

	/**
	 * @param lstTicketBookedInformation the lstTicketBookedInformation to set
	 */
	public void setLstTicketBookedInformation(
			List<BookingTransactionModel> lstTicketBookedInformation) {
		this.lstBookedSeatInformation = lstTicketBookedInformation;
	}

	private String getBookedSeatInformation() {
		// TODO Auto-generated method stub
		String toRet = ERROR;
		BookingTransactionDaoImpl bookingTransactionDaoImpl = null; 
		try {
			bookingTransactionDaoImpl = new BookingTransactionDaoImpl();
			lstSeatTransactionModel = new ArrayList<SeatTransactionModel>();
			
			lstSeatTransactionModel = bookingTransactionDaoImpl.getBookedSeatInformation(showId);
			if(lstBookedSeatInformation != null){
				toRet = SUCCESS;
				if(lstBookedSeatInformation.size() > 0){
					
					
					this.errorCode = BookingTransactionController.BOOKING_NO_ERROR;
					this.errorMessage = "Succefully fetch data from server";
				}
				else{
					this.errorCode = BookingTransactionController.BOOKING_ERROR_NO_RESPONSE;
					this.errorMessage = "No data available";
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return toRet;

	}

}
