package com.ticketbooking.transaction;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.text.html.HTMLDocument.HTMLReader.PreAction;

import com.ticketbooking.database.DatabaseConnection;
import com.ticketbooking.model.HallInformation;
import com.ticketbooking.model.MovieInfoModel;
import com.ticketbooking.model.SeatTransactionModel;
import com.ticketbooking.model.ShowInfoModel;

public class ShowInformationDaoImpl implements ShowInformationDao {

	static Connection con =  DatabaseConnection.getConnection();
	@Override
	public List<ShowInfoModel> getShowInformation() {
		// TODO Auto-generated method stub
		List<ShowInfoModel> lstShowInfoModel = null;
		ShowInfoModel showInfoModel = null;
		ResultSet resultSet = null;
		PreparedStatement preparedStatement = null;
		try {
			lstShowInfoModel = new ArrayList<ShowInfoModel>();
			String strQuery = "select * from show_information where status = 1";
			preparedStatement = con.prepareStatement(strQuery);
			resultSet = preparedStatement.executeQuery();
			while(resultSet.next()){
				System.out.println("Data :: " );
				long hallId = resultSet.getLong("HALL_ID");
				long movieId = resultSet.getLong("MOVIE_ID");
				long showId = resultSet.getLong("SHOW_ID");
				showInfoModel = new ShowInfoModel();
				showInfoModel.setShowId(showId);
				showInfoModel.setHallId(hallId);
				showInfoModel.setMovie_id(movieId);
				showInfoModel.setShowStartDate(resultSet.getString("SHOW_START_DATE"));
				showInfoModel.setShowStartTime(resultSet.getString("SHOW_START_TIME"));
				showInfoModel.setLstHallInformation(getHallInformation(hallId));
				showInfoModel.setLstSeatTransactionInfo(getBookedSeatInformation(showId));
				showInfoModel.setLstMovieInfo(getMovieInformation(movieId));
				System.out.println("Data :: " + showInfoModel.getShowStartDate());
				lstShowInfoModel.add(showInfoModel);
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
		return lstShowInfoModel;
	}
	
	private List<SeatTransactionModel> getBookedSeatInformation(long showId) {
		// TODO Auto-generated method stub
		List<SeatTransactionModel> lstSeatTransactionModel = null;
		PreparedStatement prepStatement = null;
		ResultSet ressultSet = null;
		SeatTransactionModel seatTransactionModel = null; 
		
		try {
			lstSeatTransactionModel  = new ArrayList<SeatTransactionModel>();
			String queryStr = "select B.BOOK_TRNX,S.ROW_ID,S.SEAT_NUMBER from seat_transaction as S, booking_transaction as B where B.BOOK_TRNX = S.BOOK_TRNX and B.SHOW_ID = ? ";
			prepStatement = con.prepareStatement(queryStr);
			prepStatement.setLong(1, showId);
			ressultSet = prepStatement.executeQuery();
			while (ressultSet.next()) {
				seatTransactionModel = new SeatTransactionModel();
				seatTransactionModel.setBookTrnx(ressultSet.getLong("B.BOOK_TRNX"));
				seatTransactionModel.setRowId(ressultSet.getLong("S.ROW_ID"));
				seatTransactionModel.setSeatNumber(ressultSet.getLong("S.SEAT_NUMBER"));
				lstSeatTransactionModel.add(seatTransactionModel);
				
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		finally{
			try {
				if(prepStatement != null){
					prepStatement.clearParameters();
					prepStatement.close();
					prepStatement = null;
				}
				if (ressultSet != null) {
					ressultSet.close();
					ressultSet = null;
				}
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		return lstSeatTransactionModel;
	}

	private List<MovieInfoModel> getMovieInformation(long movieId) {
		// TODO Auto-generated method stub
		MovieInfoModel movieInfoModel = null;
		List<MovieInfoModel> lstMovieInfoModel = null;
		PreparedStatement prepStatement = null;
		ResultSet ressultSet = null;
		
		try {
			lstMovieInfoModel  = new ArrayList<MovieInfoModel>();
			String queryStr = "select * from movie_information where STATUS = 1 and MOVIE_ID = ?";
			prepStatement = con.prepareStatement(queryStr);
			prepStatement.setLong(1, movieId);
			ressultSet = prepStatement.executeQuery();
			while (ressultSet.next()) {
				movieInfoModel = new MovieInfoModel();
				movieInfoModel.setMovieId(ressultSet.getLong("MOVIE_ID"));
				movieInfoModel.setMovieName(ressultSet.getString("MOVIE_NAME"));
				movieInfoModel.setPrice(ressultSet.getDouble("PRICE"));
				movieInfoModel.setDuration(ressultSet.getInt("DURATION"));
				movieInfoModel.setDescription(ressultSet.getString("DESCRIPTION"));
				lstMovieInfoModel.add(movieInfoModel);
				
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		finally{
			try {
				if(prepStatement != null){
					prepStatement.clearParameters();
					prepStatement.close();
					prepStatement = null;
				}
				if (ressultSet != null) {
					ressultSet.close();
					ressultSet = null;
				}
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		return lstMovieInfoModel;
	}

	public List<HallInformation> getHallInformation(long hallId) {
		HallInformation hallinformation  = null;
		List<HallInformation> lstHallInformation = null;
		PreparedStatement prepStatement = null;
		ResultSet resultSet = null;
		
		try {
			lstHallInformation = new ArrayList<HallInformation>();
			String strQuery = "select * from hall_screen_information where STATUS = 1 and hall_id = ?";
			prepStatement = con.prepareStatement(strQuery);
			prepStatement.setLong(1, hallId);
			resultSet = prepStatement.executeQuery();
			while (resultSet.next()) {
				hallinformation = new HallInformation();
				hallinformation.setHallId(resultSet.getLong("hall_id"));
				hallinformation.setName(resultSet.getString("NAME"));
				hallinformation.setDescription(resultSet.getString("DESCRIPTION"));
				hallinformation.setAddress(resultSet.getString("ADDRESS"));
				hallinformation.setPhone(resultSet.getString("PHONE"));
				hallinformation.setSeat(resultSet.getLong("SEAT"));
				lstHallInformation.add(hallinformation);
				
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		finally{
			try {
				if(prepStatement != null){
					prepStatement.clearParameters();
					prepStatement.close();
					prepStatement = null;
				}
				if (resultSet != null) {
					resultSet.close();
					resultSet = null;
				}
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		return lstHallInformation;
		
	}

}
