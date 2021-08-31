/**
 * 
 */
package com.flipkart.dao;

import java.util.List;

import com.flipkart.bean.Course;
import com.flipkart.bean.Professor;
import com.flipkart.bean.RegisteredCourse;
import com.flipkart.bean.Student;
import com.flipkart.bean.User;
import com.flipkart.exception.CourseExistsAlreadyException;
import com.flipkart.exception.CourseNotDeletedException;
import com.flipkart.exception.CourseNotFoundException;
import com.flipkart.exception.ProfessorNotAddedException;
import com.flipkart.exception.StudentNotFoundForApprovalException;
import com.flipkart.exception.UserIdAlreadyInUseException;
import com.flipkart.exception.UserNotAddedException;
import com.flipkart.exception.UserNotFoundException;

/**
 * @author Tanishq
 *
 */
public interface AdminDaoInterface {
	
	public List<Course> viewCourses();
	public List<Professor> viewProfessors();
	
	
	/**
	 * Method to generate grade card of a Student 
	 * studentid 
	 * @return 
	 * 
	 * 
	 */
	
	public void setGeneratedReportCardTrue(String Studentid);
	
	public List<RegisteredCourse> generateGradeCard(String Studentid);
	
	/**
	 * Fetch Students yet to approved using SQL commands
	 * @return List of Students yet to approved
	 */
	public List<Student> viewPendingAdmissions();
	
	/**
	 * Method to approve a Student 
	 * studentid
	 * studentlist
	 */
	
	
	public void approveStudent(String studentid) throws StudentNotFoundForApprovalException;
	
	/**
	 * Method to add Professor to DB
	 * professor : Professor Object storing details of a professor 
	 */
	
	public void addProfessor(Professor professor) throws ProfessorNotAddedException, UserIdAlreadyInUseException;
	
	/**
	 * Method to Delete Course from Course Catalog
	 * @param courseCode
	 * @param courseList : Courses available in the catalog
	 * @throws CourseNotFoundException 
	 * @throws CourseNotDeletedException 
	 */

	public void removeCourse(String coursecode) throws CourseNotFoundException, CourseNotDeletedException;
	
	/**
	 * Method to add Course to Course Catalog
	 * @param course : Course object storing details of a course
	 * @param courseList : Courses available in the catalog
	 * @throws CourseExistsAlreadyException;
	 */
	
	public void addCourse(Course course) throws CourseExistsAlreadyException;
	
	/**
	 * Method to assign Course to a Professor
	 * @param courseCode
	 * @param professorId
	 * @throws CourseNotFoundException 
	 * @throws UserNotFoundException 
	 */
	public void assignCourse(String courseCode, String professorId) throws CourseNotFoundException, UserNotFoundException;
	
	public void addUser(User user) throws UserNotAddedException, UserIdAlreadyInUseException;
	public void deleteCourse(String courseCode) throws CourseNotFoundException;
}
