package com.ticketbooking.model;

import java.util.List;

public class ShowInfoModel {

	private long hallId, showId, movie_id;
	private String showStartDate ="", showStartTime ="";
	private List<HallInformation> lstHallInformation = null;
	private List<MovieInfoModel> lstMovieInfo = null;
	private List<SeatTransactionModel> lstSeatTransactionInfo = null;
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
	 * @return the movie_id
	 */
	public long getMovie_id() {
		return movie_id;
	}
	/**
	 * @param movie_id the movie_id to set
	 */
	public void setMovie_id(long movie_id) {
		this.movie_id = movie_id;
	}
	/**
	 * @return the showStartDate
	 */
	public String getShowStartDate() {
		return showStartDate;
	}
	/**
	 * @param showStartDate the showStartDate to set
	 */
	public void setShowStartDate(String showStartDate) {
		this.showStartDate = showStartDate;
	}
	/**
	 * @return the showStartTime
	 */
	public String getShowStartTime() {
		return showStartTime;
	}
	/**
	 * @param showStartTime the showStartTime to set
	 */
	public void setShowStartTime(String showStartTime) {
		this.showStartTime = showStartTime;
	}
	/**
	 * @return the lstHallInformation
	 */
	public List<HallInformation> getLstHallInformation() {
		return lstHallInformation;
	}
	/**
	 * @param lstHallInformation the lstHallInformation to set
	 */
	public void setLstHallInformation(List<HallInformation> lstHallInformation) {
		this.lstHallInformation = lstHallInformation;
	}
	/**
	 * @return the lstMovieInfo
	 */
	public List<MovieInfoModel> getLstMovieInfo() {
		return lstMovieInfo;
	}
	/**
	 * @param lstMovieInfo the lstMovieInfo to set
	 */
	public void setLstMovieInfo(List<MovieInfoModel> lstMovieInfo) {
		this.lstMovieInfo = lstMovieInfo;
	}
	/**
	 * @return the lstSeatTransactionModel
	 */
	public List<SeatTransactionModel> getLstSeatTransactionInfo() {
		return lstSeatTransactionInfo;
	}
	/**
	 * @param lstSeatTransactionModel the lstSeatTransactionModel to set
	 */
	public void setLstSeatTransactionInfo(
			List<SeatTransactionModel> lstSeatTransactionInfo) {
		this.lstSeatTransactionInfo = lstSeatTransactionInfo;
	}

}
