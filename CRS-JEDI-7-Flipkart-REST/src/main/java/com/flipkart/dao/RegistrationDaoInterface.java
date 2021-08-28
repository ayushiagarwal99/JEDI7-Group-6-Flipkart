package com.flipkart.dao;


import java.sql.SQLException;
import java.util.List;

import com.flipkart.bean.Course;
import com.flipkart.bean.Grade;
import com.flipkart.bean.Notification;
import com.flipkart.constant.PaymentModeConstant;

/**
 * @author vijayrathod
 * Interface for Registration DAO Operation
 */


public interface RegistrationDaoInterface {
	
	
	/**
	 * Method to add course in database
	 * @param courseCode
	 * @param studentId
	 * @return boolean indicating if the course is added successfully
	 */
	public boolean addCourse(String courseCode, String studentId) throws SQLException;

	
	/**
	 * Drop Course selected by student
	 * @param courseCode
	 * @param studentId
	 * @return boolean indicating if the course is dropped successfully
	 */
	
	public boolean dropCourse(String courseCode, String studentId) throws SQLException;

	/**
	 * Method to get the list of courses available from course catalog 
	 * @param studentId
	 * @return list of Courses
	 */
	public List<Course> viewCourses(String studentId) throws SQLException;

	
	/**
	 * Method to View list of Registered Courses
	 * @param studentId
	 * @return list of Registered Courses
	 */
	public List<Course> viewRegisteredCourses(String studentId) throws SQLException;

	
	/**
	 * Method to view grade card of the student
	 * @param studentId
	 * @return GradeConstant Card
	 */
	
	public double calculateFee(String studentId) throws SQLException;

	/**
	 * Check if seat is available for that particular course
	 * @param courseCode
	 * @return seat availability status
	 */
	public boolean seatAvailable(String courseCode) throws SQLException;

	/**
	 * Method to get the list of courses registered by the student
	 * Number of registered courses for a student
	 * @param studentId
	 * @return Number of registered Courses
	 */
	public int numOfRegisteredCourses(String studentId) throws SQLException;

	/**
	 * Method checks if the student is registered for that course
	 * @param courseCode
	 * @param studentId
	 * @return Students registration status
	 */
	public boolean isRegistered(String courseCode, String studentId) throws SQLException;

	
	/**
	 *  Method to get student registration status
	 * @param studentId
	 * @return Student's registration status
	 */
	public boolean getRegistrationStatus(String studentId) throws SQLException;
	
	public boolean getPaymentStatus(String studentId) throws SQLException;

	/**
	 *  Method to set student registration status
	 * @param studentId
	 */
	public void setRegistrationStatus(String studentId) throws SQLException;


	/**
	 * Method to view grade card of the student
	 * @param studentId
	 * @throws SQLException 
	 * @return Studen's grade card
	 */
	public List<Grade> viewGradeCard(String studentId) throws SQLException;


	public boolean isReportGenerated(String studentId) throws SQLException;


	public void setPaymentStatus(String studentId) throws SQLException;


	public int checkCourseAvailability(String studentId, String courseCode) throws SQLException;


	public Notification payFee(String studentId, PaymentModeConstant mode, double fee) throws SQLException;


	int checkCourseAvailability(int studentId, String courseCode) throws SQLException; 

	
}
