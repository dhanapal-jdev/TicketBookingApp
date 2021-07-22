/**
 * 
 */
package com.ticketbooking.transaction;

import java.sql.SQLException;
import java.util.List;

import com.ticketbooking.model.CustomerContactsModel;

/**
 * @author Dhanapal
 *
 */
public interface CustomerContactsDao {

	public long createNewCustomer(CustomerContactsModel customerContactsModel) throws SQLException;
	public List<CustomerContactsModel> getCustomerContacts()throws SQLException;
	public int updateCustomerContacts(CustomerContactsModel customerContactsModel) throws SQLException;
}
