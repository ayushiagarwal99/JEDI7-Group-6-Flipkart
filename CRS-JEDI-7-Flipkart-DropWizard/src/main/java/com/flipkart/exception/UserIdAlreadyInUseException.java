package com.flipkart.exception;

/**
 * @author Tribhav Chaudhary
 *
 */
public class UserIdAlreadyInUseException extends Exception{
	private String userId;
	
	
	/***
	 * Setter function for UserId
	 * @param userId
	 */
	
	public UserIdAlreadyInUseException(String id) {
		userId = id;
	}
	
	/***
	 * Getter function for UserId
	 * @param userId
	 */
	
	public String getUserId() {
		return userId;
	}
	
	
	@Override
	public String getMessage() {
		return "userId: " + userId + " is already in use.";
	}

}