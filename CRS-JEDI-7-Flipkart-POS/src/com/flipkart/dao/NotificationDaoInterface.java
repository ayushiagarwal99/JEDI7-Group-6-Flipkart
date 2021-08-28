/**
 * 
 */
package com.flipkart.dao;

import java.sql.SQLException;

import com.flipkart.constant.NotificationTypeConstant;
import com.flipkart.constant.PaymentModeConstant;

/**
 * @author vijayrathod
 *
 */
public interface NotificationDaoInterface {

	/**
	 * Send Notification using SQL commands
	 * @param type: type of the notification to be sent
	 * @param studentId: student to be notified
	 * @param modeOfPayment: mode of payment used, defined in enum
	 * @param amount
	 * @return notification id for the record added in the database
	 * @throws SQLException
	 */
	public int sendNotification(NotificationTypeConstant type,int studentId,PaymentModeConstant modeOfPayment,double amount) throws SQLException;
	
}
