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

import javax.sound.midi.SysexMessage;
import javax.swing.text.DefaultEditorKit.CutAction;

import com.mysql.jdbc.Statement;
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
			String strQuery = "select S.ROW_ID, S.SEAT_NUMBER, S.BOOK_TRNX from booking_transaction AS B, seat_transaction AS S where  B.BOOKED_STATUS= 1 AND B.PAYMENT_STATUS = 1  AND B.SHOW_ID = ? ";
			preparedStatement = con.prepareStatement(strQuery);
			preparedStatement.setLong(1, showId);
			
			resultSet = preparedStatement.executeQuery();
			while(resultSet.next()){
				seatTransactionModel = new SeatTransactionModel();
				seatTransactionModel.setRowId(resultSet.getLong("S.ROW_ID"));
				seatTransactionModel.setSeatNumber(resultSet.getLong("S.SEAT_NUMBER"));
				
				lstSeatTransactionModel.add(seatTransactionModel);
			}
			
//			return lstShowInofModel;
			
		} catch (SQLException e) {
			// TODO: handle exception
			System.out.println("Error while fetching Booked Seat Information :: " + e.getMessage());
		}catch(Exception e){
			System.out.println("Error while fetching Booked Seat Information :: " + e.getMessage());
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
	public List<BookingTransactionModel> getAllBookedInformation(
			BookingTransactionModel bookingTransactionModel)
			throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long creatNewBooking(long showId, long customerId, Long currentTime, List<SeatTransactionModel> lstSeatTransactionModel) {
		// TODO Auto-generated method stub
		long bookingTransactionId = 0;
		ResultSet resultSet = null;
		PreparedStatement preparedStatement = null;
		try {
			
			String strQuery = "INSERT INTO booking_transaction (CUSTOMER_ID, SHOW_ID, NO_OF_SEAT, TOTAL_AMOUNT, BOOKED_STATUS, PAYMENT_STATUS,CREATE_TIME, UPDATE_TIME) VALUES (?, ?, ?, ?, ?, ?, ?, ? )";
			preparedStatement = con.prepareStatement(strQuery, Statement.RETURN_GENERATED_KEYS);
			preparedStatement.setLong(1, customerId);
			preparedStatement.setLong(2, showId);
			preparedStatement.setLong(3, lstSeatTransactionModel.size());
			preparedStatement.setLong(4, 0);
			preparedStatement.setLong(5, 1);
			preparedStatement.setLong(6, 1);
			preparedStatement.setLong(7, currentTime);
			preparedStatement.setLong(8, currentTime);
			
			long i  = preparedStatement.executeUpdate();
			resultSet = preparedStatement.getGeneratedKeys();
			resultSet.next();
			bookingTransactionId = resultSet.getInt(1);
			System.out.println("Error while Booking new ticket :: " + bookingTransactionId);
			if(bookingTransactionId > 0){
				long retResponse = insertSeatTransaction(bookingTransactionId, lstSeatTransactionModel);
				
			}
			
//			return lstShowInofModel;
			
		} catch (SQLException e) {
			// TODO: handle exception
			System.out.println("Error while Booking new ticket :: " + e.getMessage());
		}catch(Exception e){
			System.out.println("Error while Booking new ticket :: " + e.getMessage());
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
		
		return bookingTransactionId;
	}


	private long insertSeatTransaction(long bookTrnx,
			List<SeatTransactionModel> lstSeatTransactionModel) {
		// TODO Auto-generated method stub
		long seatTransactionId = 0;
		ResultSet resultSet = null;
		PreparedStatement preparedStatement = null;
		try {
			
//			String strQuery = "INSERT INTO seat_transaction (BOOK_TRNX, ROW_ID, SEAT_NUMBER) VALUES (?, ?, ?);";
			/*preparedStatement = con.prepareStatement(strQuery, Statement.RETURN_GENERATED_KEYS);
			preparedStatement.setLong(1, bookTrnx);
			preparedStatement.setLong(2, 0);
			preparedStatement.setLong(3, 0);
			
			long i  = preparedStatement.executeUpdate();
			resultSet = preparedStatement.getGeneratedKeys();
			resultSet.next();
			seatTransactionId = resultSet.getInt(1); */
			StringBuffer strQuery = new StringBuffer("INSERT INTO seat_transaction (BOOK_TRNX, ROW_ID, SEAT_NUMBER) VALUES (?, ?, ?)");

//			for (int i = 0; i < lstSeatTransactionModel.size() - 1; i++) {
//				strQuery.append(", (?, ?, ?)");
//			}
			System.out.println("Query final ::" + lstSeatTransactionModel.size() + " :: " + bookTrnx);
			preparedStatement = con.prepareStatement(strQuery.toString());

			for (int j = 0; j < lstSeatTransactionModel.size(); j++) {
				preparedStatement.setLong(1, bookTrnx);
				preparedStatement.setLong(2, lstSeatTransactionModel.get(j).getRowId());
				preparedStatement.setLong(3, lstSeatTransactionModel.get(j).getSeatNumber());
				preparedStatement.addBatch();
			}
			System.out.println("Query String final ::" + preparedStatement.toString());
			preparedStatement.executeBatch();
			
//			con.commit();
//			return lstShowInofModel;
			
		} catch (SQLException e) {
			// TODO: handle exception
			System.out.println("Error while Insert New Seat information :: " + e.getMessage());
		}catch(Exception e){
			System.out.println("Error while Insert New Seat information :: " + e.getMessage());
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
		
		return seatTransactionId;
	}


	public long checkCustomerExist(String eMail, String phone) {
		// TODO Auto-generated method stub
		long customerId = 0;
		ResultSet resultSet = null;
		PreparedStatement preparedStatement = null;
		try {
			String strQuery = "select CUSTOMER_ID from customer_contacts where EMAIL = ? or PHONE = ?";
			/*if(eMail != "" && phone != ""){
				strQuery += " where ";
				if(eMail != ""){
					strQuery += " EMAIL = ?";
				}
				
			}else if(eMail != "" || phone != ""){
			}
			else{
				return customerId;
			}*/
			preparedStatement = con.prepareStatement(strQuery);
			preparedStatement.setString(1, eMail);
			preparedStatement.setString(2, phone);
			resultSet = preparedStatement.executeQuery();
			while(resultSet.next()){
				customerId = resultSet.getLong("CUSTOMER_ID");
			}
			System.out.println("Customer ID:: " + customerId);
//			return lstShowInofModel;
			
		} catch (SQLException e) {
			// TODO: handle exception
			System.out.println("Error while fetching Customer Data from DB :: " + e.getMessage());
		}catch(Exception e){
			System.out.println("Error while fetching Customer Data from DB :: " + e.getMessage());
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
		
		return customerId;
	}


	public long createNewContact(String eMail, String phone, String lName,
			String addressLine1, String addressLine2, String city,
			String state, int zipCode, long createdTime) {
		// TODO Auto-generated method stub
		long customerId = 0;
		ResultSet resultSet = null;
		PreparedStatement preparedStatement = null;
		try {
			String strQuery = "INSERT INTO customer_contacts (FNAME,LNAME,EMAIL,PHONE,ADDRESS_LINE_1,ADDRESS_LINE_2, CITY,STATE,ZIPCODE,CREATED_TIME,UPDATED_TIME) VALUES (?,?,?,?,?,?,?,?,?,?,?)";
			
			preparedStatement = con.prepareStatement(strQuery, Statement.RETURN_GENERATED_KEYS);
			preparedStatement.setString(1, lName);
			preparedStatement.setString(2, lName);
			preparedStatement.setString(3, eMail);
			preparedStatement.setString(4, phone);
			preparedStatement.setString(5, addressLine1);
			preparedStatement.setString(6, addressLine2);
			preparedStatement.setString(7, city);
			preparedStatement.setString(8, state);
			preparedStatement.setLong(9, zipCode);
			preparedStatement.setLong(10, createdTime);
			preparedStatement.setLong(11, createdTime);
			
			int i = preparedStatement.executeUpdate();
			resultSet = preparedStatement.getGeneratedKeys();
			resultSet.next();
			customerId = resultSet.getInt(1);
			
//			return lstShowInofModel;
			
		} catch (SQLException e) {
			// TODO: handle exception
			System.out.println("Error while insert new contact " + e.getMessage());
		}catch(Exception e){
			System.out.println("Error while insert new contact " + e.getMessage());
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
		
		return customerId;
	}


	public long createSeatHolding(String deviceHash, Long showId,
			Long currentTime, List<SeatTransactionModel> lstSeatTransactionModel) {
		// TODO Auto-generated method stub
		long seatTransactionId = 0;
		ResultSet resultSet = null;
		PreparedStatement preparedStatement = null;
		try {
			StringBuffer strQuery = new StringBuffer("INSERT INTO seat_hold_transaction (HASH,SHOWID, ROW_ID, SEAT_NO,HOLDING_DURATION,CREATED_TIME,UPDATED_TIME) VALUES (?, ?, ?, ?, ?, ?, ? )");
			System.out.println("Query final ::" + lstSeatTransactionModel.size() + " :: " + deviceHash);
			preparedStatement = con.prepareStatement(strQuery.toString());

			for (int j = 0; j < lstSeatTransactionModel.size(); j++) {
				preparedStatement.setString(1, deviceHash);
				preparedStatement.setLong(2, showId);
				preparedStatement.setLong(3, lstSeatTransactionModel.get(j).getRowId());
				preparedStatement.setLong(4, lstSeatTransactionModel.get(j).getSeatNumber());
				preparedStatement.setLong(5, 2);
				preparedStatement.setLong(6, currentTime);
				preparedStatement.setLong(7, currentTime);
				
				preparedStatement.addBatch();
			}
			System.out.println("Query String final ::" + preparedStatement.toString());
			preparedStatement.executeBatch();
			
		} catch (SQLException e) {
			// TODO: handle exception
			System.out.println("Error while Insert New Seat information :: " + e.getMessage());
		}catch(Exception e){
			System.out.println("Error while Insert New Seat information :: " + e.getMessage());
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
		
		return seatTransactionId;

	}


	public List<SeatTransactionModel> getHoldingSeatExpired(String deviceHash, long showId, Long currentTime) {
		// TODO Auto-generated method stub
		String hash = null;
		List<SeatTransactionModel> lstSeatTransactionModel = null;
		SeatTransactionModel seatTranx = null;
		ResultSet resultSet = null;
		PreparedStatement preparedStatement = null;
		try {
			lstSeatTransactionModel = new ArrayList<SeatTransactionModel>();
			String strQuery = "select * from seat_hold_transaction where HASH =? and SHOWID =? and CREATED_TIME >= ?";
			preparedStatement = con.prepareStatement(strQuery);
			preparedStatement.setString(1, deviceHash);
			preparedStatement.setLong(2, showId);
			preparedStatement.setLong(3, currentTime);
			System.out.println("Execute Query :: " + preparedStatement.toString());
			resultSet = preparedStatement.executeQuery();
			
			while(resultSet.next()){
				seatTranx = new SeatTransactionModel();
				seatTranx.setRowId(resultSet.getLong("ROW_ID"));
				seatTranx.setSeatNumber(resultSet.getLong("SEAT_NO"));
				lstSeatTransactionModel.add(seatTranx);
			}
//			if(hash != null){
//				retVal = false;
//			}
			
		} catch (SQLException e) {
			// TODO: handle exception
			System.out.println("Error while fetching Holding tickets same user :: " + e.getMessage());
		}catch(Exception e){
			System.out.println("Error while fetching Holding tickets same user :: " + e.getMessage());
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


	public List<SeatTransactionModel> getOtherHoldingSeatExpired(
			String deviceHash, long showId, long currentTime, List<SeatTransactionModel> checkHoldSeat) {
		// TODO Auto-generated method stub
		List<SeatTransactionModel> lstSeatTransactionModel = null;
		SeatTransactionModel seatTranx = null;
		ResultSet resultSet = null;
		PreparedStatement preparedStatement = null;
		try {
			lstSeatTransactionModel = new ArrayList<SeatTransactionModel>();
			String strQuery = "select S.ROW_ID, S.SEAT_NUMBER, S.BOOK_TRNX from booking_transaction AS B, seat_transaction AS S where  B.BOOKED_STATUS = 1 AND B.PAYMENT_STATUS = 1  AND B.SHOW_ID = ?";
			preparedStatement = con.prepareStatement(strQuery);
			preparedStatement.setLong(1, showId);
			resultSet = preparedStatement.executeQuery();
			while(resultSet.next()){
				seatTranx = new SeatTransactionModel();
				seatTranx.setRowId(resultSet.getLong("S.ROW_ID"));
				seatTranx.setSeatNumber(resultSet.getLong("S.SEAT_NUMBER"));
				lstSeatTransactionModel.add(seatTranx);
			}
//			if(hash != null){
//				retVal = false;
//			}
			
		} catch (SQLException e) {
			// TODO: handle exception
			System.out.println("Error while fetching Other user hodling tickets DB :: " + e.getMessage());
		}catch(Exception e){
			System.out.println("Error while fetching Other user hodling tickets DB :: " + e.getMessage());
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

}
