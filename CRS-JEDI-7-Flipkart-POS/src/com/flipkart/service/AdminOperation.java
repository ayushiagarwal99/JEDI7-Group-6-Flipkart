/**
 * 
 */
package com.flipkart.service;

import com.flipkart.exception.*;
import com.flipkart.validator.AdminValidator;

import java.util.List;

import com.flipkart.bean.*;
import com.flipkart.dao.AdminDaoInterface;
import com.flipkart.dao.AdminDaoOperation;
import org.apache.log4j.Logger;

/**
 * @author Suicide Squad
 *
 */

public class AdminOperation implements AdminInterface{
	private static Logger logger = Logger.getLogger(AdminOperation.class);
	private static volatile AdminOperation instance = null;
	
	private AdminOperation()
	{
		
	}
	
	/**
	 * Method to make AdminOperation Singleton
	 */
	
	public static AdminOperation getInstance()
	{
		if(instance == null)
		{
			synchronized(AdminOperation.class){
				instance = new AdminOperation();
			}
		}
		return instance;
	}
	
	AdminDaoInterface adminDaoOperation =AdminDaoOperation.getInstance();
	
	
	public List<Course> viewCourses()
	{
		logger.info("viewCourses()");
		return adminDaoOperation.viewCourses();
	}
	public List<Professor> viewProfessors()
	{
		logger.info("viewProfessors()");
		return adminDaoOperation.viewProfessors();
	}
	
	/**
	 * Method to view Students yet to be approved by Admin
	 * @return List of Students with pending admissions
	 */
	@Override
	public List<Student> viewPendingAdmissions() {
		logger.info("viewPendingAdmissions()");
		return adminDaoOperation.viewPendingAdmissions();
	}
	
	/**
	 * Method to generate grade card of a Student 
	 * studentid 
	 */
	
	public List<RegisteredCourse> generateGradeCard(String studentId)
	{
		logger.info("generateGradeCard()");
		logger.debug(studentId);
		return adminDaoOperation.generateGradeCard(studentId);
	}
	
	/**
	 * Method to remove Course from Course Catalog
	 * @param dropCourseCode
	 * @param courseList : Courses available in the catalog
	 * @throws CourseNotFoundException 
	 */
	@Override
	public void removeCourse(String dropCourseCode, List<Course> courseList) throws CourseNotFoundException, CourseNotDeletedException {
		logger.info("removeCourse()");
		logger.debug(dropCourseCode);
		if(!AdminValidator.isValidDropCourse(dropCourseCode, courseList)) {
			System.out.println("courseCode: " + dropCourseCode + " not present in catalog!");
			throw new CourseNotFoundException(dropCourseCode);
		}
		
		try {
			adminDaoOperation.removeCourse(dropCourseCode);
		}
		catch(CourseNotFoundException | CourseNotDeletedException e) {
			throw e;
		}
	}

	/**
	 * Method to add Course to Course Catalog
	 * @param newCourse : Course object storing details of a course
	 * @param courseList : Courses available in catalog
	 * @throws CourseExistsAlreadyException
	 */
	@Override
	public void addCourse(Course newCourse, List<Course> courseList) throws CourseExistsAlreadyException 
	{
		logger.info("addCourse()");
		//logger.debug(newCourse);
		try {
			if(!AdminValidator.isValidNewCourse(newCourse, courseList)) {
				System.out.println("courseCode: " + newCourse.getCourseCode() + " already present in catalog!");
				throw new CourseExistsAlreadyException(newCourse.getCourseCode());
			}
			adminDaoOperation.addCourse(newCourse);
		}
		catch(CourseExistsAlreadyException e) {
			throw e;
		}
	}
	
	/**
	 * Method to approve a Student 
	 * @param studentId
	 * @param studentList 
	 * @throws StudentNotFoundForApprovalException
	 */
	@Override
	public void approveStudent(String studentId, List<Student> studentList) throws StudentNotFoundForApprovalException {
		logger.info("approveStudent()");
		logger.debug(studentId);
		
		try {
			
			if(AdminValidator.isValidUnapprovedStudent(studentId, studentList)) {
				
				throw new StudentNotFoundForApprovalException(studentId);
			}
			adminDaoOperation.approveStudent(studentId);
		}
		catch(StudentNotFoundForApprovalException e) {
			
			throw e;
		}
	}

	/**
	 * @param professor : Professor Object storing details of a professor
	 * @throws ProfessorNotAddedException
	 */
	@Override
	public void addProfessor(Professor professor) throws ProfessorNotAddedException, UserIdAlreadyInUseException {
		logger.info("addProfessor()");
		try {
			adminDaoOperation.addProfessor(professor);
		}
		catch(ProfessorNotAddedException | UserIdAlreadyInUseException e) {
			throw e;
		}
		
	}
	
	/**
	 * Method to assign Course to a Professor
	 * @param courseCode
	 * @param professorId
	 * @throws CourseNotFoundException 
	 * @throws UserNotFoundException 
	 */
	public void assignCourse(String courseCode, String professorId) throws CourseNotFoundException, UserNotFoundException
	{
		logger.info("assignCourse()");
		logger.debug(courseCode + " " + professorId);
		try {
			adminDaoOperation.assignCourse(courseCode, professorId);
		}
		catch(CourseNotFoundException | UserNotFoundException e) {
			throw e;
		}
	}

	@Override
	public void setGeneratedReportCardTrue(String studentId) {
		logger.info("setGeneratedReportCardTrue()");
		logger.debug(studentId);
		adminDaoOperation.setGeneratedReportCardTrue(studentId);
		
	}

}
