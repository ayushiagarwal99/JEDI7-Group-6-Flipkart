package com.flipkart.service;

import java.sql.SQLException;
import java.util.List;

import com.flipkart.bean.Course;
import com.flipkart.bean.Notification;
import com.flipkart.bean.Grade;
import com.flipkart.constant.PaymentModeConstant;
import com.flipkart.dao.RegistrationDaoInterface;
import com.flipkart.dao.RegistrationDaoOperation;
import com.flipkart.exception.CourseLimitExceededException;
import com.flipkart.exception.CourseNotFoundException;
import com.flipkart.exception.SeatNotAvailableException;
import com.flipkart.validator.StudentValidator;
import org.apache.log4j.Logger;

/**
 * @author Suicide Squad
 * The Registration Operation provides the business logic for student registration.
 * 
 */
public class RegistrationOperation implements RegistrationInterface {
	private static Logger logger = Logger.getLogger(RegistrationOperation.class);
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
	 * @param availableCourseList
	 * @return boolean indicating if the course is added successfully
	 * @throws CourseNotFoundException
	 * @throws SeatNotAvailableException 
	 * @throws CourseLimitExceededException
	 * @throws SQLException 
	 */
	@Override
	
	public boolean addCourse(String courseCode, String studentId,List<Course> availableCourseList) throws CourseNotFoundException, CourseLimitExceededException, SeatNotAvailableException, SQLException 
	{

		logger.info("addCourse()");
		logger.debug(studentId + " " + courseCode);

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
		logger.info("dropCourse()");
		logger.debug(studentId + " " + courseCode);
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
		logger.info("calculateFee()");
		logger.debug(studentId);
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
		logger.info("viewGradeCard()");
		logger.debug(studentId);
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
		logger.info("viewCourses()");
		logger.debug(studentId);
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
		logger.info("viewRegisteredCourses()");
		logger.debug(studentId);
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
		logger.info("getRegistrationStatus()");
		logger.debug(studentId);
		return registrationDaoInterface.getRegistrationStatus(studentId);
	}
	
	/**
	 * Method to set student registration status
	 * @param studentId
	 * @throws SQLException
	 */
	@Override
	
	public void setRegistrationStatus(String studentId) throws SQLException {
		logger.info("setRegistrationStatus()");
		logger.debug(studentId);
		registrationDaoInterface.setRegistrationStatus(studentId);

	}

	@Override
	public boolean isReportGenerated(String studentId) throws SQLException {
		logger.info("isReportGenerated()");
		logger.debug(studentId);
		return registrationDaoInterface.isReportGenerated(studentId);
	}

	@Override
	public boolean getPaymentStatus(String studentId) throws SQLException 
	{
		logger.info("getPaymentStatus()");
		logger.debug(studentId);
		return registrationDaoInterface.getPaymentStatus(studentId);
	}

	@Override
	public void setPaymentStatus(String studentId) throws SQLException{
		logger.info("setPaymentStatus()");
		logger.debug(studentId);
		registrationDaoInterface.setPaymentStatus(studentId);
	}

}