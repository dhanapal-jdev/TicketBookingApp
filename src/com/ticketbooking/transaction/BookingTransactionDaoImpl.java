/**
 * 
 */
package com.ticketbooking.transaction;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ticketbooking.database.DatabaseConnection;
import com.ticketbooking.model.BookingTransactionModel;
import com.ticketbooking.model.SeatTransactionModel;
import com.ticketbooking.model.ShowInfoModel;

/**
 * @author Dhanapal
 *
 */
public class BookingTransactionDaoImpl implements BookingTransactionDao{

	static Connection con =  DatabaseConnection.getConnection();
	@Override
	public List<SeatTransactionModel> getBookedSeatInformation(long showId)
			throws SQLException {
		// TODO Auto-generated method stub
		
		List<SeatTransactionModel> lstSeatTransactionModel = null;
		SeatTransactionModel seatTransactionModel = null;
		ResultSet resultSet = null;
		PreparedStatement preparedStatement = null;
		try {
			lstSeatTransactionModel = new ArrayList<SeatTransactionModel>();
			String strQuery = "select S.ROW_ID, S.SEAT_NUMBER, S.BOOK_TRNX from booking_transaction AS B, seat_transaction AS S where  B.BOOKED_STATUS= 1 AND B.PAYMENT_STATUS = 1 B.SHOW_ID = ? ";
			preparedStatement = con.prepareStatement(strQuery);
			resultSet = preparedStatement.executeQuery();
			while(resultSet.next()){
				seatTransactionModel = new SeatTransactionModel();
				seatTransactionModel.setRowId(resultSet.getString("S.ROW_ID"));
				seatTransactionModel.setSeatNumber(resultSet.getString("S.SEAT_NUMBER"));
				
				lstSeatTransactionModel.add(seatTransactionModel);
			}
			
//			return lstShowInofModel;
			
		} catch (SQLException e) {
			// TODO: handle exception
			System.out.println("Error while fetching data from database" + e.getMessage());
		}catch(Exception e){
			System.out.println("Error while fetching data from database" + e.getMessage());
		}
		finally{
			try {
				if(preparedStatement != null){
					preparedStatement.clearParameters();
					preparedStatement.close();
					preparedStatement = null;
				}
				if (resultSet != null) {
					resultSet.close();
					resultSet = null;
				}
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		return lstSeatTransactionModel;
	}

	@Override
	public List<BookingTransactionModel> creatNewBooking(
			BookingTransactionModel bookingTransactionModel)
			throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<BookingTransactionModel> getAllBookedInformation(
			BookingTransactionModel bookingTransactionModel)
			throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

}
