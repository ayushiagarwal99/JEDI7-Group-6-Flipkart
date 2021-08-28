/**
 * 
 */
package com.flipkart.bean;

/**
 * @author Goenka
 *
 */
public class EnrolledStudent {
	String courseCode;
	String courseName;
	String studentId;
	
	public EnrolledStudent(String courseCode,String courseName,String studentId){
		this.courseCode=courseCode;
		this.courseName=courseName;
		this.studentId=studentId;
	}

	public String getCourseCode() {
		return courseCode;
	}

	public void setCourseCode(String courseCode) {
		this.courseCode = courseCode;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public String getStudentId() {
		return studentId;
	}

	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}
	
}
