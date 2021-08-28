package com.flipkart.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.flipkart.constant.SQLQueriesConstant;
import com.flipkart.exception.UserNotFoundException;
import com.flipkart.utils.DBUtils;
import org.apache.log4j.Logger;

/**
 * 
 * @author Suicide Squad
 */
public class UserDaoOperation implements UserDaoInterface{
	private static Logger logger = Logger.getLogger(UserDaoOperation.class);
	private static volatile UserDaoOperation instance=null;

	/**
	 * Default Constructor
	 */
	private UserDaoOperation()
	{
		
	}
	
	/**
	 * Method to make UserDaoOperation Singleton
	 * @return
	 */
	public static UserDaoOperation getInstance()
	{
		if(instance==null)
		{
			// This is a synchronized block, when multiple threads will access this instance
			synchronized(UserDaoOperation.class){
				instance=new UserDaoOperation();
			}
		}
		return instance;
	}

	/**
	 * Method to update password of user in DataBase
	 * @param newPassword
	 * @return Update Password operation Status
	 */
	@Override
	public boolean updatePassword(String userId, String newPassword) {
		logger.info("updatePassword()");
		logger.debug(userId);
		Connection connection=DBUtils.getConnection();
		try {
			PreparedStatement statement = connection.prepareStatement(SQLQueriesConstant.UPDATE_PASSWORD);
			
			statement.setString(1, newPassword);
			statement.setString(2, userId);
			
			int row = statement.executeUpdate();
			
			if(row==1)
				return true;
			else
				return false;
		}
		catch(SQLException e)
		{
			System.out.println(e.getMessage());
		}
		finally
		{
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return false;
	}
	
	/**
	 * Method to verify credentials of Users from DataBase
	 * @param userId
	 * @param password
	 * @return Verify credentials operation status
	 * @throws UserNotFoundException
	 */
	@Override
	public boolean verifyCredentials(String userId, String password) throws UserNotFoundException {
		logger.info("verifyCredentials()");
		logger.debug(userId);
		Connection connection = DBUtils.getConnection();
		try
		{
			//open db connection
			PreparedStatement preparedStatement=connection.prepareStatement(SQLQueriesConstant.VERIFY_CREDENTIALS);
			preparedStatement.setString(1,userId);
			ResultSet resultSet = preparedStatement.executeQuery();

			System.out.println("inside verify");
			
			if(!resultSet.next())
				throw new UserNotFoundException(userId);

			else if(password.equals(resultSet.getString("password")))
			{
				System.out.println("inside equals");
				return true;
			}
			else
			{
				return false;
			}
			
		}
		catch(SQLException ex)
		{
			System.out.println("Something went wrong, please try again! "+ ex.getMessage());
		}
		finally
		{
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return false;
	}

	/**
	 * Method to update password of user in DataBase
	 * @param userID
	 * @return Update Password operation Status
	 */
	@Override
	public boolean updatePassword(String userId) {
		// TODO Auto-generated method stub
		logger.info("updatePassword()");
		logger.debug(userId);
		return false;
	}
	
	/**
	 * Method to get RoleConstant of User from DataBase
	 * @param userId
	 * @return RoleConstant
	 */
	@Override
	public String getRole(String userId) 
	{
		logger.info("getRole()");
		logger.debug(userId);
		Connection connection=DBUtils.getConnection();
		try {
			System.out.println(userId);
			connection=DBUtils.getConnection();
			
			PreparedStatement statement = connection.prepareStatement(SQLQueriesConstant.GET_ROLE);
			statement.setString(1, userId);
			ResultSet rs = statement.executeQuery();



			System.out.println("query executed");
			
			if(rs.next())
			{
				System.out.println(rs.getString("role"));
				return rs.getString("role");
			}
				
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
			
		}
		
		finally
		{
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
			}
		}
		return null;
	}

	
}