/**
 * 
 */
package com.flipkart.bean;

/**
 * @author Aditya
 *
 */
public class Grade 
{
	private String crsCode;
	private String crsName;
	private String grade;
	
	public Grade(String courseCode, String courseName, String grade) {
		this.crsCode = courseCode;
		this.crsName = courseName;
		this.grade = grade;
	}


	/**
	 * @return the crsCode
	 */
	public String getCrsCode() {
		return crsCode;
	}
	
	
	/**
	 * @param crsCode the crsCode to set
	 */
	public void setCrsCode(String crsCode) {
		this.crsCode = crsCode;
	}
	
	
	/**
	 * @return the crsName
	 */
	public String getCrsName() {
		return crsName;
	}
	
	
	/**
	 * @param crsName the crsName to set
	 */
	public void setCrsName(String crsName) {
		this.crsName = crsName;
	}
	
	
	/**
	 * @return the grade
	 */
	public String getGrade() {
		return grade;
	}
	
	
	/**
	 * @param grade the grade to set
	 */
	public void setGrade(String grade) {
		this.grade = grade;
	}
	
	
	
}
