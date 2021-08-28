package com.flipkart.exception;

/**
 * Exception to check if seats are available for course registration
 * @author Tribhav Chaudhary
 *
 */
public class CourseNotAvailableException extends Exception{
	
	private String courseCode;

	/**
	 * Constructor
	 * @param courseCode
	 */
	public CourseNotAvailableException(String courseCode)
	{	
		this.courseCode = courseCode;
	}


	/**
	 * Message returned when exception is thrown
	 */
	@Override
	public String getMessage() {
		return  "Seats are not available in : " + courseCode;
	}


}