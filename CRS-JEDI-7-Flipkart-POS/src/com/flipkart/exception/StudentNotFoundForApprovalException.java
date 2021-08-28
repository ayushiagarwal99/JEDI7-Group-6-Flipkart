/**
 * 
 */
package com.flipkart.exception;

/**
 * @author Tanishq
 *
 */
public class StudentNotFoundForApprovalException extends Exception{
	
	private String StudentId;
	
	public StudentNotFoundForApprovalException(String id) {
		StudentId = id;
	}
	
	/**
	 * Getter function for professorId
	 * @return
	 */
	public String getUserId() {
		return StudentId;
	}
	

	/**
	 * Message returned when exception is thrown
	 */
	@Override
	public String getMessage() {
		return "StudentId: " + StudentId + " not registered!";
	}
}
