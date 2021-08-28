package com.flipkart.service;

import com.flipkart.dao.UserDaoInterface;
import com.flipkart.dao.UserDaoOperation;
import com.flipkart.exception.UserNotFoundException;
import org.apache.log4j.Logger;

/**
 * 
 * @author Goenka
 * Implementations of User Operations
 *
 */
public class UserOperation implements UserInterface {
	private static Logger logger = Logger.getLogger(UserOperation.class);
	private static volatile UserOperation instance=null;
	UserDaoInterface userDaoInterface= UserDaoOperation.getInstance();
	private UserOperation()
	{
		
	}
	
	/**
	 * Method to make UserOperation Singleton
	 * @return
	 */
	public static UserOperation getInstance()
	{
		if(instance==null)
		{
			// This is a synchronized block, when multiple threads will access this instance
			synchronized(UserOperation.class){
				instance=new UserOperation();
			}
		}
		return instance;
	}

	/**
	 * Method to update password of a user
	 * @param userId
	 * @param newPassword
	 * @return boolean indicating if the password is updated successfully
	 */
	
	@Override
	public boolean updatePassword(String userId,String newPassword) {
		logger.info("updatePassword()");
		logger.debug(userId);
		return userDaoInterface.updatePassword(userId, newPassword);
	}

	
	/**
	 * Method to verify User credentials
	 * @param userId
	 * @param password
	 * @return boolean indicating if user exists in the database
	 */

	@Override
	public boolean verifyCredentials(String userId, String password) throws UserNotFoundException {
		logger.info("verifyCredentials()");
		logger.debug(userId);
		//DAO class
		try
		{
			return userDaoInterface.verifyCredentials(userId, password);
		}
		finally
		{
			
		}
	}
	
	/**
	 * Method to get role of a specific User
	 * @param userId
	 * @return RoleConstant of the User
	 */
	@Override
	public String getRole(String userId) {
		logger.info("getRole()");
		logger.debug(userId);
		return userDaoInterface.getRole(userId);
	}

	


	

}