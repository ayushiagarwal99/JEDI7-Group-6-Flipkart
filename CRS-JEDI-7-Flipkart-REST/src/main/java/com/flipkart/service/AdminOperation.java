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

/**
 * @author Tanishq
 *
 */

public class AdminOperation implements AdminInterface{
	
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
		return adminDaoOperation.viewCourses();
	}
	public List<Professor> viewProfessors()
	{
		return adminDaoOperation.viewProfessors();
	}
	
	/**
	 * Method to view Students yet to be approved by Admin
	 * @return List of Students with pending admissions
	 */
	@Override
	public List<Student> viewPendingAdmissions() {
		return adminDaoOperation.viewPendingAdmissions();
	}
	
	/**
	 * Method to generate grade card of a Student 
	 * studentid 
	 */
	
	public List<RegisteredCourse> generateGradeCard(String Studentid)
	{
		return adminDaoOperation.generateGradeCard(Studentid);
	}
	
	/**
	 * Method to remove Course from Course Catalog
	 * @param courseCode
	 * @param courseList : Courses available in the catalog
	 * @throws CourseNotFoundException 
	 */
	@Override
	public void removeCourse(String dropCourseCode, List<Course> courseList) throws CourseNotFoundException, CourseNotDeletedException {
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
	 * @param course : Course object storing details of a course
	 * @param courseList : Courses available in catalog
	 * @throws CourseFoundException
	 */
	@Override
	public void addCourse(Course newCourse, List<Course> courseList) throws CourseExistsAlreadyException 
	{
		
		
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
	 * @throws StudentNotFoundException 
	 */
	@Override
	public void approveStudent(String studentId, List<Student> studentList) throws StudentNotFoundForApprovalException {
		
		
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
		try {
			adminDaoOperation.assignCourse(courseCode, professorId);
		}
		catch(CourseNotFoundException | UserNotFoundException e) {
			throw e;
		}
	}

	@Override
	public void setGeneratedReportCardTrue(String Studentid) {
		adminDaoOperation.setGeneratedReportCardTrue(Studentid);
		
	}

	@Override
	public void deleteCourse(String courseCode, List<Course> courseList) throws CourseNotFoundException {
		if(!AdminValidator.isValidDropCourse(courseCode, courseList)) {
			System.out.println("courseCode: " + courseCode + " not present in catalog!");
			throw new CourseNotFoundException(courseCode);
		}
		
		adminDaoOperation.deleteCourse(courseCode);
		
	}

}
