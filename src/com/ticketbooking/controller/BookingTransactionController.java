package com.ticketbooking.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpServletRequest;

import com.amazonaws.util.json.JSONObject;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.sun.org.apache.bcel.internal.generic.FNEG;
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

	private static Map<Integer, String> errorMessageVal = null;

	private static int GET_TICKET_BOOKING_INFORMATION = 1;
	private static final int CREATE_NEW_TICKET_BOOKING = 2;

	private static final int CREATE_NEW_SEAT_HOLD = 3;

	private static final int SEAT_BOOKING_TIME_ELAPSED = 7;

	private static final int BOOKING_TICKET_FAIL = 8;

	List<BookingTransactionModel> lstBookedSeatInformation = null;
	List<SeatTransactionModel> lstSeatTransactionInfo = null;
	CustomerContactsModel customerInfo = null;
	String seatBookingInfo = "";
	private long showId = 0, bookTrnx = 0, movieId = 0, hallId = 0;
	private String addressLine1 = "", addressLine2 = "", city = "", eMail = "",
			lName = "", state = "", phone = "";
	private int zipCode;
	private String deviceHash = "";

	/**
	 * @return the deviceHash
	 */
	public String getDeviceHash() {
		return deviceHash;
	}

	/**
	 * @param deviceHash
	 *            the deviceHash to set
	 */
	public void setDeviceHash(String deviceHash) {
		this.deviceHash = deviceHash;
	}

	/**
	 * @return the phone
	 */
	public String getPhone() {
		return phone;
	}

	/**
	 * @param phone
	 *            the phone to set
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}

	/**
	 * @return the addressLine1
	 */
	public String getAddressLine1() {
		return addressLine1;
	}

	/**
	 * @param addressLine1
	 *            the addressLine1 to set
	 */
	public void setAddressLine1(String addressLine1) {
		this.addressLine1 = addressLine1;
	}

	/**
	 * @return the addressLine2
	 */
	public String getAddressLine2() {
		return addressLine2;
	}

	/**
	 * @param addressLine2
	 *            the addressLine2 to set
	 */
	public void setAddressLine2(String addressLine2) {
		this.addressLine2 = addressLine2;
	}

	/**
	 * @return the city
	 */
	public String getCity() {
		return city;
	}

	/**
	 * @param city
	 *            the city to set
	 */
	public void setCity(String city) {
		this.city = city;
	}

	/**
	 * @return the eMail
	 */
	public String geteMail() {
		return eMail;
	}

	/**
	 * @param eMail
	 *            the eMail to set
	 */
	public void seteMail(String eMail) {
		this.eMail = eMail;
	}

	/**
	 * @return the lName
	 */
	public String getlName() {
		return lName;
	}

	/**
	 * @param lName
	 *            the lName to set
	 */
	public void setlName(String lName) {
		this.lName = lName;
	}

	/**
	 * @return the state
	 */
	public String getState() {
		return state;
	}

	/**
	 * @param state
	 *            the state to set
	 */
	public void setState(String state) {
		this.state = state;
	}

	/**
	 * @return the zipCode
	 */
	public int getZipCode() {
		return zipCode;
	}

	/**
	 * @param zipCode
	 *            the zipCode to set
	 */
	public void setZipCode(int zipCode) {
		this.zipCode = zipCode;
	}

	/**
	 * @return the customerInfo
	 */
	public CustomerContactsModel getCustomerInfo() {
		return customerInfo;
	}

	/**
	 * @param customerInfo
	 *            the customerInfo to set
	 */
	public void setCustomerInfo(CustomerContactsModel customerInfo) {
		this.customerInfo = customerInfo;
	}

	/**
	 * @return the seatBookingInfo
	 */
	public String getSeatBookingInfo() {
		return seatBookingInfo;
	}

	/**
	 * @param seatBookingInfo
	 *            the seatBookingInfo to set
	 */
	public void setSeatBookingInfo(String seatBookingInfo) {
		this.seatBookingInfo = seatBookingInfo;
	}

	/**
	 * @return the lstSeatTransactionInfo
	 */
	public List<SeatTransactionModel> getLstSeatTransactionInfo() {
		return lstSeatTransactionInfo;
	}

	/**
	 * @param lstSeatTransactionModel
	 *            the lstSeatTransactionModel to set
	 */
	public void setLstSeatTransactionInfo(
			List<SeatTransactionModel> lstSeatTransactionInfo) {
		this.lstSeatTransactionInfo = lstSeatTransactionInfo;
	}

	/**
	 * @return the requestType
	 */
	public int getRequestType() {
		return requestType;
	}

	/**
	 * @param requestType
	 *            the requestType to set
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
	 * @param lstBookedSeatInformation
	 *            the lstBookedSeatInformation to set
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
	 * @param showId
	 *            the showId to set
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
	 * @param bookTrnx
	 *            the bookTrnx to set
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
	 * @param movieId
	 *            the movieId to set
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
	 * @param hallId
	 *            the hallId to set
	 */
	public void setHallId(long hallId) {
		this.hallId = hallId;
	}

	public BookingTransactionController() {
		if (errorMessageVal == null) {
			errorMessageVal = new HashMap<Integer, String>();
		}

		if (errorMessageVal.isEmpty()) {
			errorMessageVal.put(BookingTransactionController.BOOKING_NO_ERROR,
					"No Error");
			errorMessageVal.put(
					BookingTransactionController.BOOKING_NO_ERROR_WARNING,
					"No Error, but there is a warning");
			errorMessageVal.put(
					BookingTransactionController.BOOKING_ERROR_UNAVAILABLE,
					"Ticket Booking is Unavailable");
			errorMessageVal.put(
					BookingTransactionController.BOOKING_ERROR_NO_RESPONSE,
					"Ticket Booking hasn't gotten a response still");
			errorMessageVal
					.put(BookingTransactionController.BOOKING_UNKNOWN_ERROR,
							"Unknown error while processing Ticket Booking Fetch & Response");
			errorMessageVal
					.put(BookingTransactionController.BOOKING_UNATHORIZED_REQUEST,
							"Ticket Booking Unauthorized request or session invalidated");
			errorMessageVal
			.put(BookingTransactionController.SEAT_BOOKING_TIME_ELAPSED,
					"Seat selection time elapsed please retry again!!!");
			errorMessageVal.put(BookingTransactionController.BOOKING_TICKET_FAIL, "Ticket you trying to book was booked someone, Please try again!!!");
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
		if (this.errorCode != BookingTransactionController.BOOKING_UNKNOWN_ERROR) {
			toRet = BookingTransactionController.errorMessageVal
					.get(this.errorCode);
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
				requestType = BookingTransactionController.GET_TICKET_BOOKING_INFORMATION;
			} else if (actionName.equals("newTicketBooking")) {
				requestType = BookingTransactionController.CREATE_NEW_TICKET_BOOKING;
			} else if (actionName.equals("seatHoldAction")) {
				requestType = BookingTransactionController.CREATE_NEW_SEAT_HOLD;
			} else {
				requestType = -1;
			}
		}

		if (requestType == BookingTransactionController.GET_TICKET_BOOKING_INFORMATION) {
			System.out.println("Fcuntion for Getting show information ");
			toRet = this.getBookedSeatInformation();
		} else if (requestType == BookingTransactionController.CREATE_NEW_TICKET_BOOKING) {

			toRet = this.createNewTicketBooking();
		} else if (requestType == BookingTransactionController.CREATE_NEW_SEAT_HOLD) {

			toRet = this.createSeatHolding4Customer();
		}
		return toRet;
	}

	private String createSeatHolding4Customer() {
		// TODO Auto-generated method stub
		String toRet = ERROR;
		BookingTransactionDaoImpl bookingTransactionDaoImpl = null;
		try {
			bookingTransactionDaoImpl = new BookingTransactionDaoImpl();
			JSONObject jsonObj = new JSONObject(seatBookingInfo);
			// @SuppressWarnings("rawtypes")
			Iterator ite = jsonObj.keys();
			List<SeatTransactionModel> lstSeatTransactionModel = new ArrayList<SeatTransactionModel>();
			SeatTransactionModel seatTransactionModel = null;
			Long currentTime = System.currentTimeMillis();
			while (ite.hasNext()) {
				String key = (String) ite.next();
				System.out.println("JSON Data " + jsonObj.getString(key));
				String[] array = jsonObj.getString(key).split(":");
				seatTransactionModel = new SeatTransactionModel();
				seatTransactionModel.setRowId(Long.parseLong(array[0]));
				seatTransactionModel.setSeatNumber(Long.parseLong(array[1]));
				lstSeatTransactionModel.add(seatTransactionModel);

			}
			bookTrnx = bookingTransactionDaoImpl.createSeatHolding(deviceHash,
					showId, currentTime, lstSeatTransactionModel);
			// lstSeatTransactionModel =
			// bookingTransactionDaoImpl.getBookedSeatInformation(showId);
			if (bookTrnx > 0) {
				toRet = SUCCESS;
				this.errorCode = BookingTransactionController.BOOKING_NO_ERROR;
				this.errorMessage = "Succefully fetch data from server";

			} else {
				toRet = ERROR;
				this.errorCode = BookingTransactionController.BOOKING_UNKNOWN_ERROR;
				this.errorMessage = "Technical error while booking Ticket's";

			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out
					.println("Error while create new ticket Booking Transaction  :: "
							+ e.getStackTrace());
		}
		return toRet;

	}

	private String createNewTicketBooking() {
		// TODO Auto-generated method stub
		String toRet = ERROR;
		System.out.println("Fcuntion " + deviceHash);
		BookingTransactionDaoImpl bookingTransactionDaoImpl = null;
		try {
			Long currentTime = System.currentTimeMillis();
			bookingTransactionDaoImpl = new BookingTransactionDaoImpl();
			List<SeatTransactionModel> checkHoldSeat = bookingTransactionDaoImpl
					.getHoldingSeatExpired(deviceHash, showId,
							(currentTime - TimeUnit.MINUTES.toMillis(2)));
			if (checkHoldSeat.size() > 0) {

//				List<SeatTransactionModel> ticketQueueCount = bookingTransactionDaoImpl
//				.getOtherHoldingSeatExpired(deviceHash, showId,
//						(currentTime - TimeUnit.MINUTES.toMillis(2)),checkHoldSeat);
				JSONObject jsonObj = new JSONObject(seatBookingInfo);
				// @SuppressWarnings("rawtypes")
				Iterator ite = jsonObj.keys();
				List<SeatTransactionModel> lstSeatTransactionModel = new ArrayList<SeatTransactionModel>();
				SeatTransactionModel seatTransactionModel = null;
				while (ite.hasNext()) {
					String key = (String) ite.next();
					System.out.println("JSON Data " + jsonObj.getString(key));
					String[] array = jsonObj.getString(key).split(":");
					seatTransactionModel = new SeatTransactionModel();
					seatTransactionModel.setRowId(Long.parseLong(array[0]));
					seatTransactionModel
							.setSeatNumber(Long.parseLong(array[1]));
					lstSeatTransactionModel.add(seatTransactionModel);

				}
				long customerId = bookingTransactionDaoImpl.checkCustomerExist(
						eMail, phone);
				if (customerId <= 0) {
					customerId = bookingTransactionDaoImpl.createNewContact(
							eMail, phone, lName, addressLine1, addressLine2,
							city, state, zipCode, currentTime);
				}
				System.out.println(" Customer ID :: " + customerId);
				boolean checkTicketBooked = bookingTransactionDaoImpl.checkTicketBooked(showId,lstSeatTransactionModel);
				if(!checkTicketBooked){
					bookTrnx = bookingTransactionDaoImpl.creatNewBooking(showId,
							customerId, currentTime, lstSeatTransactionModel);
					if (bookTrnx > 0) {
						toRet = SUCCESS;
						this.errorCode = BookingTransactionController.BOOKING_NO_ERROR;
						this.errorMessage = "Succefully fetch data from server";

					} else {
						toRet = ERROR;
						this.errorCode = BookingTransactionController.BOOKING_UNKNOWN_ERROR;
						this.errorMessage = "Technical error while booking Ticket's";

					}

				}else{
					toRet = ERROR;
					this.errorCode = BookingTransactionController.BOOKING_TICKET_FAIL;
					this.errorMessage = "Ticket you trying to book was booked someone, Please try again!!!";
					
				}
				// lstSeatTransactionModel =
				// bookingTransactionDaoImpl.getBookedSeatInformation(showId);
				} else {
				toRet = ERROR;
				this.errorCode = BookingTransactionController.SEAT_BOOKING_TIME_ELAPSED;
				this.errorMessage = "Please select the seat again!!!";
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out
					.println("Error while create new ticket Booking Transaction  :: "
							+ e.getStackTrace());
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
	 * @param lstTicketBookedInformation
	 *            the lstTicketBookedInformation to set
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
			lstSeatTransactionInfo = new ArrayList<SeatTransactionModel>();
			System.out.println("Show ID  " + showId);
			lstSeatTransactionInfo = bookingTransactionDaoImpl
					.getBookedSeatInformation(showId);
			if (lstSeatTransactionInfo != null) {
				toRet = SUCCESS;
				if (lstSeatTransactionInfo.size() > 0) {

					this.errorCode = BookingTransactionController.BOOKING_NO_ERROR;
					this.errorMessage = "Succefully fetch data from server";
				} else {
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
