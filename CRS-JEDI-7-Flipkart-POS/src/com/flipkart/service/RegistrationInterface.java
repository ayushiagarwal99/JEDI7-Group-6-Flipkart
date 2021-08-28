package com.flipkart.service;

import java.sql.SQLException;
import java.util.List;

import com.flipkart.bean.Course;
import com.flipkart.bean.Grade;
import com.flipkart.bean.Notification;
import com.flipkart.bean.Grade;
import com.flipkart.constant.PaymentModeConstant;

import com.flipkart.exception.CourseLimitExceededException;
import com.flipkart.exception.CourseNotFoundException;
import com.flipkart.exception.SeatNotAvailableException;

/**
 * 
 * @author Aditya
 * Interface for Registration Operation
 * 
 */
public interface RegistrationInterface {
	
		public boolean addCourse(String courseCode, String studentId, List<Course> availableCourseList)
			throws CourseNotFoundException, CourseLimitExceededException, SeatNotAvailableException, SQLException;

	/**
	 * Method to set student registration status
	 * @param studentId
	 * @throws SQLException
	 */
	void setRegistrationStatus(String studentId) throws SQLException;

	/**
	 *  Method to check student registration status
	 * @param studentId
	 * @return boolean indicating if the student's registration status
	 * @throws SQLException
	 */
	boolean getRegistrationStatus(String studentId) throws SQLException;
	
	boolean getPaymentStatus(String studentId) throws SQLException;

	/**
	 * Method to view the list of courses registered by the student
	 * @param studentId
	 * @return List of courses
	 * @throws SQLException 
	 */
	List<Course> viewRegisteredCourses(String studentId) throws SQLException;

	/**
	 *  Method to view the list of available courses
	 * @param studentId
	 * @return List of courses
	 * @throws SQLException 
	 */
	List<Course> viewCourses(String studentId) throws SQLException;

	/**
	 * Method to view grade card for students
	 * @param studentId
	 * @return List of Student's Grades
	 * @throws SQLException 
	 */
	List<Grade> viewGradeCard(String studentId) throws SQLException;

	/** Method for Fee Calculation for selected courses
	 * Fee calculation for selected courses
	 * @param studentId
	 * @return Fee Student has to pay
	 * @throws SQLException 
	 */
	double calculateFee(String studentId) throws SQLException;

	/**
	 *  Method to drop Course selected by student
	 * @param courseCode
	 * @param studentId
	 * @param registeredCourseList 
	 * @return boolean indicating if the course is dropped successfully
	 * @throws CourseNotFoundException
	 * @throws SQLException 
	 */
	boolean dropCourse(String courseCode, String studentId, List<Course> registeredCourseList)
			throws CourseNotFoundException, SQLException;

	public boolean isReportGenerated(String studentId) throws SQLException;

	public void setPaymentStatus(String studentId) throws SQLException;
	
}