package com.flipkart.service;

import java.sql.SQLException; 
import java.util.List;

import org.apache.log4j.Logger;

import org.apache.log4j.lf5.Log4JLogRecord;

import com.flipkart.bean.Course;
import com.flipkart.bean.Notification;
import com.flipkart.bean.Grade;
import com.flipkart.constant.PaymentModeConstant;
import com.flipkart.dao.RegistrationDaoInterface;
import com.flipkart.dao.RegistrationDaoOperation;
import com.flipkart.exception.CourseAlreadyRegisteredException;
import com.flipkart.exception.CourseLimitExceededException;
import com.flipkart.exception.CourseNotFoundException;
import com.flipkart.exception.SeatNotAvailableException;
import com.flipkart.validator.StudentValidator;

/**
 * @author Aditya
 * The Registration Operation provides the business logic for student registration.
 * 
 */
public class RegistrationOperation implements RegistrationInterface {

	private static volatile RegistrationOperation instance = null;

	private RegistrationOperation() {
	}

	/**
	 * Method to make Registration Operation Singleton
	 * 
	 * @return
	 */
	public static RegistrationOperation getInstance() {
		if (instance == null) {
			synchronized (RegistrationOperation.class) {
				instance = new RegistrationOperation();
			}
		}
		return instance;
	}

	RegistrationDaoInterface registrationDaoInterface = RegistrationDaoOperation.getInstance();

	/**
	 * Method to add Course selected by student 
	 * @param courseCode
	 * @param studentId
	 * @param courseList 
	 * @return boolean indicating if the course is added successfully
	 * @throws CourseNotFoundException
	 * @throws SeatNotAvailableException 
	 * @throws CourseLimitExceedException 
	 * @throws SQLException 
	 */
	@Override
	public boolean checkCourse(String courseCode,String studentId,List<Course> availableCourseList) throws CourseLimitExceededException, CourseAlreadyRegisteredException, SeatNotAvailableException, CourseNotFoundException
	{
		
			try {
					int response = registrationDaoInterface.checkCourseAvailability(studentId, courseCode);
					
					if (response == 0){
						throw new CourseLimitExceededException(6);
					}
					else if (response == 1) {
						throw new CourseAlreadyRegisteredException(courseCode);
					}
					else if (!registrationDaoInterface.seatAvailable(courseCode)) {
						throw new SeatNotAvailableException(courseCode);
					} 
					else if(!StudentValidator.isValidCourseCode(courseCode, availableCourseList)){
						throw new CourseNotFoundException(courseCode);
					}	
					
					return true;
					
			} 
			catch (SQLException e) {
				System.out.println(e.getMessage());
				
			}
			
			return false;
			
	}
	
	public boolean addCourse(String courseCode, String studentId,List<Course> availableCourseList) throws CourseNotFoundException, CourseLimitExceededException, SeatNotAvailableException, SQLException 
	{
       
		

		if (registrationDaoInterface.numOfRegisteredCourses(studentId) >= 6)
		{	
			throw new CourseLimitExceededException(6);
		}
		else if (registrationDaoInterface.isRegistered(courseCode, studentId)) 
		{
			return false;
		} 
		else if (!registrationDaoInterface.seatAvailable(courseCode)) 
		{
			throw new SeatNotAvailableException(courseCode);
		} 
		else if(!StudentValidator.isValidCourseCode(courseCode, availableCourseList))
		{
			throw new CourseNotFoundException(courseCode);
		}
		
		  

		return registrationDaoInterface.addCourse(courseCode, studentId);

	}

	/**
	 *  Method to drop Course selected by student
	 * @param courseCode
	 * @param studentId
	 * @param registeredCourseList 
	 * @return boolean indicating if the course is dropped successfully
	 * @throws CourseNotFoundException
	 * @throws SQLException 
	 */
	@Override
	
	public boolean dropCourse(String courseCode, String studentId,List<Course> registeredCourseList) throws CourseNotFoundException, SQLException {
		  if(!StudentValidator.isRegistered(courseCode, studentId, registeredCourseList))
	        {
	        	throw new CourseNotFoundException(courseCode);
	        }
		
		return registrationDaoInterface.dropCourse(courseCode, studentId);

	}

	/** Method for Fee Calculation for selected courses
	 * Fee calculation for selected courses
	 * @param studentId
	 * @return Fee Student has to pay
	 * @throws SQLException 
	 */
	@Override
	
	public double calculateFee(String studentId) throws SQLException {
		return registrationDaoInterface.calculateFee(studentId);
	}


	/**
	 * Method to view grade card for students
	 * @param studentId
	 * @return List of Student's Grades
	 * @throws SQLException 
	 */
	@Override
	
	public List<Grade> viewGradeCard(String studentId) throws SQLException {
		return registrationDaoInterface.viewGradeCard(studentId);
	}

	/**
	 *  Method to view the list of available courses
	 * @param studentId
	 * @return List of courses
	 * @throws SQLException 
	 */
	@Override
	
	public List<Course> viewCourses(String studentId) throws SQLException {
		return registrationDaoInterface.viewCourses(studentId);
	}

	/**
	 * Method to view the list of courses registered by the student
	 * @param studentId
	 * @return List of courses
	 * @throws SQLException 
	 */
	@Override
	
	public List<Course> viewRegisteredCourses(String studentId) throws SQLException {
		return registrationDaoInterface.viewRegisteredCourses(studentId);
	}
    
	/**
	 *  Method to check student registration status
	 * @param studentId
	 * @return boolean indicating if the student's registration status
	 * @throws SQLException
	 */
	@Override

	public boolean getRegistrationStatus(String studentId) throws SQLException {
		return registrationDaoInterface.getRegistrationStatus(studentId);
	}
	
	/**
	 * Method to set student registration status
	 * @param studentId
	 * @throws SQLException
	 */
	@Override
	
	public void setRegistrationStatus(String studentId) throws SQLException {
		registrationDaoInterface.setRegistrationStatus(studentId);

	}

	@Override
	public boolean isReportGenerated(String studentId) throws SQLException {
		
		return registrationDaoInterface.isReportGenerated(studentId);
	}

	@Override
	public boolean getPaymentStatus(String studentId) throws SQLException 
	{
		return registrationDaoInterface.getPaymentStatus(studentId);
		
	}

	@Override
	public void setPaymentStatus(String studentId) throws SQLException{
		registrationDaoInterface.setPaymentStatus(studentId);
		
	}

	@Override
	public boolean addCourse(String courseCode, String studentId)
	{

		try
		{
			registrationDaoInterface.addCourse(courseCode, studentId);
			return true;
		}
		catch(SQLException e){
			System.out.println(e.getMessage());
		}
		return false;


	}

	@Override
	public Notification payFee(String studentId, PaymentModeConstant mode, double fee) throws SQLException {
		try 
		{
			return registrationDaoInterface.payFee(studentId, mode, fee);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

		return null;
		
	}

//	@Override
//	public boolean checkCourse(String courseCode, String studentId, List<Course> availableCourseList) throws CourseLimitExceededException, CourseAlreadyRegisteredException, SeatNotAvailableException, CourseNotFoundException {
//		try {
//			int response = registrationDaoInterface.checkCourseAvailability(studentId, courseCode);
//			
//			if (response == 0){
//				throw new CourseLimitExceededException(6);
//			}
//			else if (response == 1) {
//				throw new CourseAlreadyRegisteredException(courseCode);
//			}
//			else if (!registrationDaoInterface.seatAvailable(courseCode)) {
//				throw new SeatNotAvailableException(courseCode);
//			} 
//			else if(!StudentValidator.isValidCourseCode(courseCode, availableCourseList)){
//				throw new CourseNotFoundException(courseCode);
//			}	
//			
//			return true;
//			
//	} 
//	catch (SQLException e) {
//		System.out.println(e.getMessage());
//		
//	}
//	
//	return false;
//		
//	}

}