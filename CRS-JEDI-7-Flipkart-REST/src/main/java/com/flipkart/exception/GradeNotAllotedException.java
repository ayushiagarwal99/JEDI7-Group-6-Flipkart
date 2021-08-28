/**
 * 
 */
package com.flipkart.exception;

/**
 * @author Tanishq
 *
 */
public class GradeNotAllotedException extends Exception{
	 
	private String studentId;
	 
	/**
	 * Constructor
	 * @param studentId2
	 */
	 public GradeNotAllotedException(String studentId2)
	 {
		 this.studentId=studentId2;
	 }
	 
	 /**
	  * Getter function for studentId
	  * @return
	  */
	 public String getStudentId()
	 {
		 return studentId;
	 }
	 
	 /**
		 * Message returned when exception is thrown
	 */
	 
	 public String getMessage() 
	 {
			return "Student with id: " + studentId + "hasn't been alloted a grade yet";
	 }
}
