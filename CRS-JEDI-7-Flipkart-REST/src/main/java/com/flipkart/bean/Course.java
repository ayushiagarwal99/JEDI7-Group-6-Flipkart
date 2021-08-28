/**
 * 
 */
package com.flipkart.bean;

/**
 * @author Aditya
 *
 */
public class Course 
{
	private String crsCode;
	private String crsName;
	private String instructorId;
	private int seats = 10;
	
	public Course()
	{
		
	}
	
	public Course(String crsCode,String crsName,String professorId,int seats) {
		this.crsCode=crsCode;
		this.crsName=crsName;
		this.instructorId=professorId;
		this.seats=seats;
	}
	/**
	 * Method to get Course Code
	 * @return Course Code
	 */
	public String getCourseCode() {
		return crsCode;
	}
	
	/**
	 * Method to set Course Code
	 * @param courseCode
	 */
	public void setCourseCode(String courseCode) {
		this.crsCode = courseCode;
	}
	
	/**
	 * Method to get Course Name
	 * @return Course Name
	 */
	public String getCourseName() {
		return crsName;
	}
	
	/**
	 * Method to set Course Name
	 * @param courseName
	 */
	public void setCourseName(String courseName) {
		this.crsName = courseName;
	}

	/**
	 * Method to get available seats
	 * @return Seats available
	 */
	public int getSeats() {
		return seats;
	}
	
	/**
	 * Method to set available seats
	 * @param seats
	 */
	public void setSeats(int seats) {
		this.seats = seats;
	}
	
	/**
	 * Method to get Instructor Id of professor teaching the course
	 * @return Instructor Id
	 */
	public String getInstructorId() {
		return instructorId;
	}
	
	/**
	 * Method to set Instructor Id of professor teaching the course
	 * @param instructorId
	 */
	public void setInstructorId(String instructorId) {
		this.instructorId = instructorId;
	}
	
}
