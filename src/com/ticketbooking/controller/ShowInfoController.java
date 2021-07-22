package com.ticketbooking.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.mysql.fabric.xmlrpc.base.Array;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.ticketbooking.model.ShowInfoModel;
import com.ticketbooking.transaction.ShowInformationDaoImpl;

public class ShowInfoController extends ActionSupport {

	private static final long serialVersionUID = 1L;

	private String retSuccess;
	private int retFlag = 0;
	private int requestType = -1;
	List<ShowInfoModel> lstShowInformation = null;
	ShowInfoModel showInfoModel = null;
	
	

	/**
	 * @return the lstShowInformation
	 */
	public List<ShowInfoModel> getLstShowInformation() {
		return lstShowInformation;
	}

	/**
	 * @param lstShowInformation the lstShowInformation to set
	 */
	public void setLstShowInformation(List<ShowInfoModel> lstShowInformation) {
		this.lstShowInformation = lstShowInformation;
	}

	/**
	 * @return the showInfoModel
	 */
	public ShowInfoModel getShowInfoModel() {
		return showInfoModel;
	}

	/**
	 * @param showInfoModel the showInfoModel to set
	 */
	public void setShowInfoModel(ShowInfoModel showInfoModel) {
		this.showInfoModel = showInfoModel;
	}

	public int getRequestType() {
		return requestType;
	}

	public void setRequestType(int requestType) {
		this.requestType = requestType;
	}

	private static final int SHOWINFORMATION_NO_ERROR = 1;
	private static final int SHOWINFORMATION_NO_ERROR_WARNING = 2;
	private static final int SHOWINFORMATION_ERROR_NO_RESPONSE = 3;
	private static final int SHOWINFORMATION_ERROR_UNAVAILABLE = 4;
	private static final int SHOWINFORMATION_UNKNOWN_ERROR = 5;
	private static final int SHOWINFORMATION_UNATHORIZED_REQUEST = 6;

	private static final int GET_SHOW_INFORMATION = 1;

	private static Map<Integer, String> errorMessageVal = null;

	public ShowInfoController() {
		if (errorMessageVal == null) {
			errorMessageVal = new HashMap<Integer, String>();
		}

		if (errorMessageVal.isEmpty()) {
			errorMessageVal.put(ShowInfoController.SHOWINFORMATION_NO_ERROR,
					"No Error");
			errorMessageVal.put(
					ShowInfoController.SHOWINFORMATION_NO_ERROR_WARNING,
					"No Error, but there is a warning");
			errorMessageVal.put(
					ShowInfoController.SHOWINFORMATION_ERROR_UNAVAILABLE,
					"Ticket Booking is Unavailable");
			errorMessageVal.put(
					ShowInfoController.SHOWINFORMATION_ERROR_NO_RESPONSE,
					"Ticket Booking hasn't gotten a response still");
			errorMessageVal
					.put(ShowInfoController.SHOWINFORMATION_UNKNOWN_ERROR,
							"Unknown error while processing Ticket Booking Fetch & Response");
			errorMessageVal
					.put(ShowInfoController.SHOWINFORMATION_UNATHORIZED_REQUEST,
							"Ticket Booking Unauthorized request or session invalidated");
		}
	}

	private int errorCode = ShowInfoController.SHOWINFORMATION_NO_ERROR;
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
		if (this.errorCode != ShowInfoController.SHOWINFORMATION_UNKNOWN_ERROR) {
			toRet = ShowInfoController.errorMessageVal.get(this.errorCode);
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

			if (actionName.equals("getMovieInformation")) {
				requestType = ShowInfoController.GET_SHOW_INFORMATION;
			} else {
				requestType = -1;
			}
		}

		if (requestType == ShowInfoController.GET_SHOW_INFORMATION) {
			System.out.println("Fcuntion for Getting show information ");
			toRet = this.getShowInformation();
		}

		
		return toRet;
	}

	private String getShowInformation() {
		// TODO Auto-generated method stub
		String toRet = ERROR;
		ShowInformationDaoImpl showInformation =null;
		System.out.println("Inside funtion for Getting show information ");
		try {
			showInformation = new ShowInformationDaoImpl();
			lstShowInformation = new ArrayList<ShowInfoModel>();
			
			lstShowInformation = showInformation.getShowInformation();
			if(lstShowInformation != null){
				toRet = SUCCESS;
				if(lstShowInformation.size() > 0){
					
					System.out.println("Successfully getting data from database" + lstShowInformation.size());
					
					this.errorCode = ShowInfoController.SHOWINFORMATION_NO_ERROR;
					this.errorMessage = "Succefully fetch data from server";
				}
				else{
					this.errorCode = ShowInfoController.SHOWINFORMATION_ERROR_NO_RESPONSE;
					this.errorMessage = "No data available";
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return toRet;
	}

}
