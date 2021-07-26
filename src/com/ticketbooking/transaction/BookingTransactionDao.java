/**
 * 
 */
package com.ticketbooking.transaction;

import java.sql.SQLException;
import java.util.List;

import com.ticketbooking.model.BookingTransactionModel;
import com.ticketbooking.model.SeatTransactionModel;

/**
 * @author Dhanapal
 *
 */
public interface BookingTransactionDao {

	public List<SeatTransactionModel> getBookedSeatInformation(long showId) throws SQLException;
	public long creatNewBooking(long showId,long customerId, Long currentTime, List<SeatTransactionModel> lstSeatTransactionModel) throws SQLException;
	public List<BookingTransactionModel> getAllBookedInformation(BookingTransactionModel bookingTransactionModel) throws SQLException;
}
