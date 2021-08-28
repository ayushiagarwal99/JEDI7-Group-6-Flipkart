/**
 * 
 */
package com.flipkart.exception;

/**
 * @author Tanishq
 *
 */
public class CourseNotAssignedException extends Exception{
	
	private String courseCode;
	private String userId;
	
	public CourseNotAssignedException(String courseCode, String userId) {
		this.courseCode = courseCode;
		this.userId = userId;
	}
	
	/**
	 * Get courseCode
	 * @return
	 */
	public String getCourseCode() {
		return courseCode;
	}
	/**
	 * get Professor id
	 * @return
	 */
	public String getUserId() {
		return userId;
	}

	/**
	 * set professor id
	 * @param professorId
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}

	/**
	 * set course code
	 * @param courseCode
	 */
	public void setCourseCode(String courseCode) {
		this.courseCode = courseCode;
	}


	/**
	 * Message returned when exception is thrown
	 */
	@Override
	public String getMessage() {
		return "courseCode: " + courseCode + " couldn't be assigned to UserId: " + userId ;
	}
}
