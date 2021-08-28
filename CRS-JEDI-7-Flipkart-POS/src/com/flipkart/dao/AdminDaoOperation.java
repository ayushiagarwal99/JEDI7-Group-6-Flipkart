/**
 * 
 */
package com.flipkart.dao;

import java.util.ArrayList;  
import java.util.List;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.flipkart.bean.Course;
import com.flipkart.bean.Professor;
import com.flipkart.bean.RegisteredCourse;
import com.flipkart.bean.Student;
import com.flipkart.bean.User;
import com.flipkart.constant.GenderConstant;
import com.flipkart.constant.RoleConstant;
import com.flipkart.constant.SQLQueriesConstant;
import com.flipkart.exception.CourseExistsAlreadyException;
import com.flipkart.exception.CourseNotDeletedException;
import com.flipkart.exception.CourseNotFoundException;
import com.flipkart.exception.ProfessorNotAddedException;
import com.flipkart.exception.StudentNotFoundForApprovalException;
import com.flipkart.exception.UserIdAlreadyInUseException;
import com.flipkart.exception.UserNotAddedException;
import com.flipkart.exception.UserNotFoundException;
import com.flipkart.service.AdminOperation;
import com.flipkart.utils.DBUtils;
import org.apache.log4j.Logger;

/**
 * @author Tanishq
 *
 */


public class AdminDaoOperation implements AdminDaoInterface{
	private static Logger logger = Logger.getLogger(AdminDaoOperation.class);
	private static volatile AdminDaoOperation instance = null;
	private PreparedStatement statement = null;

	private AdminDaoOperation(){}
	
	/**
	 * Method to make AdminDaoOperation Singleton
	 * @return
	 */
	public static AdminDaoOperation getInstance()
	{
		if(instance == null)
		{
			synchronized(AdminDaoOperation.class){
				instance = new AdminDaoOperation();
			}
		}
		return instance;
	}
	
	Connection connection = DBUtils.getConnection();
	

	@Override
	public void removeCourse(String courseCode) throws CourseNotFoundException, CourseNotDeletedException{
		logger.info("removeCourse()");
		logger.debug(courseCode);
		statement = null;
		try {
			String sql = SQLQueriesConstant.DELETE_COURSE_QUERY;
			statement = connection.prepareStatement(sql);
			
			statement.setString(1,courseCode);
			int row = statement.executeUpdate();

			if(row == 0) {
				throw new CourseNotFoundException(courseCode);
			}


			
		}catch(SQLException se) {
			throw new CourseNotDeletedException(courseCode);
		}
		
	}

	/**
	 * Add Course using SQL commands
	 * @param course
	 */
	@Override
	public void addCourse(Course course) throws CourseExistsAlreadyException{
		logger.info("addCourse()");
		//logger.debug(courseCode);
		statement = null;
		try {
			
			String sql = SQLQueriesConstant.ADD_COURSE_QUERY;
			statement = connection.prepareStatement(sql);
			
			statement.setString(1, course.getCourseCode());
			statement.setString(2, course.getCourseName());
			
			statement.setInt(3, 10);
			statement.setString(4, "NOT_ASSIGNED");
			int row = statement.executeUpdate();

			if(row == 0) {
				throw new CourseExistsAlreadyException(course.getCourseCode());
			}
			
		}catch(SQLException se) {
			throw new CourseExistsAlreadyException(course.getCourseCode());
			
		}
		
	}
	
	/**
	 * Fetch Students yet to approved using SQL commands
	 * @return List of Students yet to approved
	 */
	@Override
	public List<Student> viewPendingAdmissions() {
		logger.info("viewPendingAdmissions()");
		statement = null;
		List<Student> userList = new ArrayList<Student>();
		try {
			
			String sql = SQLQueriesConstant.VIEW_PENDING_ADMISSION_QUERY;
			statement = connection.prepareStatement(sql);
			ResultSet resultSet = statement.executeQuery();

			while(resultSet.next()) {
				
				Student user = new Student();
				user.setUserId(resultSet.getString(1));
				user.setName(resultSet.getString(2));
				user.setPassword(resultSet.getString(3));
				user.setRole(RoleConstant.stringToName(resultSet.getString(4)));
				user.setGender(GenderConstant.stringToGender( resultSet.getString(5)));
				user.setAddress(resultSet.getString(6));
				user.setStudentId(resultSet.getString(7));
				userList.add(user);
				
			}
			

			
		}catch(SQLException se) {
			

			
		}
		
		return userList;
		
	}

	/**
	 * Approve Student using SQL commands
	 * @param studentId
	 */
	@Override
	public void approveStudent(String studentId) throws StudentNotFoundForApprovalException {
		logger.info("approveStudent()");
		logger.debug(studentId);
		statement = null;
		try {
			String sql = SQLQueriesConstant.APPROVE_STUDENT_QUERY;
			statement = connection.prepareStatement(sql);
			
			statement.setString(1,studentId);
			int row = statement.executeUpdate();
			

			if(row == 0) {

				throw new StudentNotFoundForApprovalException(studentId);
			}
			

			
		}catch(SQLException se) {

			
		}
		
	}

	/**
	 * Method to add user using SQL commands
	 * @param user
	 * @throws UserNotAddedException
	 * @throws UserIdAlreadyInUseException 
	 */
	@Override
	public void addUser(User user) throws UserNotAddedException, UserIdAlreadyInUseException{
		logger.info("addUser()");
		logger.debug(user.getName() + " " + user.getAddress() + " " + user.getGender());
		statement = null;
		try {
			
			String sql = SQLQueriesConstant.ADD_USER_QUERY;
			statement = connection.prepareStatement(sql);
			
			statement.setString(1, user.getUserId());
			statement.setString(2, user.getName());
			statement.setString(3, user.getPassword());
			statement.setString(4, user.getRole().toString());
			statement.setString(5, user.getGender().toString());
			statement.setString(6, user.getAddress());
			
			int row = statement.executeUpdate();
			

			if(row == 0) {

				throw new UserNotAddedException(user.getUserId()); 
			}


			
		}catch(SQLException se) {

			throw new UserIdAlreadyInUseException(user.getUserId());
			
		}
		
	}

	/**
	 * Add professor using SQL commands
	 * @param professor
	 * @throws UserIdAlreadyInUseException 
	 * @throws ProfessorNotAddedException 
	 */
	@Override
	public void addProfessor(Professor professor) throws UserIdAlreadyInUseException, ProfessorNotAddedException {
		logger.info("addProfessor()");
		logger.debug(professor.getName() + " " + professor.getAddress() + " " + professor.getGender());
		try {
			
			this.addUser(professor);
			
		}catch (UserNotAddedException e) {
			throw new ProfessorNotAddedException(professor.getUserId());
			
		}catch (UserIdAlreadyInUseException e) {

			throw e;
			
		}
		
		
		statement = null;
		try {
			
			String sql = SQLQueriesConstant.ADD_PROFESSOR_QUERY;
			statement = connection.prepareStatement(sql);
			
			statement.setString(1, professor.getUserId());
			statement.setString(2, professor.getDepartment());
			statement.setString(3, professor.getDesignation());
			int row = statement.executeUpdate();


			if(row == 0) {
				throw new ProfessorNotAddedException(professor.getUserId());
			}
			

			
		}catch(SQLException se) {
			throw new UserIdAlreadyInUseException(professor.getUserId());
			
		} 
		
	}
	
	/**
	 * Assign courses to Professor using SQL commands
	 * @param courseCode
	 * @param professorId
	 * @throws CourseNotFoundException
	 * @throws UserNotFoundException 
	 */
	@Override
	public void assignCourse(String courseCode, String professorId) throws CourseNotFoundException, UserNotFoundException{
		logger.info("assignCourse()");
		logger.debug(courseCode + " " + professorId);
		statement = null;
		try {
			String sql = SQLQueriesConstant.ASSIGN_COURSE_QUERY;
			statement = connection.prepareStatement(sql);
			
			statement.setString(1,professorId);
			statement.setString(2,courseCode);
			int row = statement.executeUpdate();

			if(row == 0) {
				throw new CourseNotFoundException(courseCode);
			}
			
			System.out.println("Course with courseCode: " + courseCode + " is assigned to professor with professorId: " + professorId + ".");
		
		}catch(SQLException se) {

			System.out.println(se.getMessage());
			throw new UserNotFoundException(professorId);
			
		}
		
	}
	
	/**
	 * View courses in the catalog
	 * @return List of courses in the catalog
	 */
	public List<Course> viewCourses() {
		logger.info("viewCourses()");
		statement = null;
		List<Course> courseList = new ArrayList<>();
		try {
			
			String sql = SQLQueriesConstant.VIEW_COURSE_QUERY;
			statement = connection.prepareStatement(sql);
			//statement.setInt(1, catalogId);
			ResultSet resultSet = statement.executeQuery();
			
			while(resultSet.next()) {
				
				Course course = new Course();
				course.setCourseCode(resultSet.getString(1));
				course.setCourseName(resultSet.getString(2));
				course.setInstructorId(resultSet.getString(3));
				courseList.add(course);
				
			}

			System.out.println("Number of courses in the Catalog are : " + courseList.size());
			
		}catch(SQLException se) {

			System.out.println(se.getMessage());
			
		}
		
		return courseList; 
		
	}
	
	/**
	 * View professor in the institute
	 * @return List of the professors in the institute  
	 */
	@Override
	public List<Professor> viewProfessors() {
		logger.info("viewProfessors()");
		statement = null;
		List<Professor> professorList = new ArrayList<Professor>();
		try {
			
			String sql = SQLQueriesConstant.VIEW_PROFESSOR_QUERY;
			statement = connection.prepareStatement(sql);
			ResultSet resultSet = statement.executeQuery();
			
			while(resultSet.next()) {
				
				Professor professor = new Professor();
				professor.setUserId(resultSet.getString(1));
				professor.setName(resultSet.getString(2));
				professor.setGender(GenderConstant.stringToGender(resultSet.getString(3)));
				professor.setDepartment(resultSet.getString(4));
				professor.setDesignation(resultSet.getString(5));
				professor.setAddress(resultSet.getString(6));
				professor.setRole(RoleConstant.PROFESSOR);
				professor.setPassword("*********");
				professorList.add(professor);
				
			}

			System.out.println(professorList.size() + " professors in the institute.");
			
		}catch(SQLException se) {

			System.out.println(se.getMessage());
			
		}
		return professorList;
	}
	
	public void setGeneratedReportCardTrue(String studentId)
	{
		logger.info("setGeneratedReportCardTrue()");
		logger.debug(studentId);
		String sql1 = SQLQueriesConstant.SET_GENERATED_REPORT_CARD_TRUE;
		try {
		statement = connection.prepareStatement(sql1);
		statement.setString(1, studentId);
		int row = statement.executeUpdate();
		}
		catch(SQLException e)
		{
			System.out.println(e.getMessage());
		}
	}

	@Override
	public List<RegisteredCourse> generateGradeCard(String studentId)
	{
		logger.info("generateGradeCard()");
		logger.debug(studentId);
		List<RegisteredCourse> CoursesOfStudent = new ArrayList<RegisteredCourse>();
		
		try {
					String sql = SQLQueriesConstant.VIEW_REGISTERED_COURSES;
					statement = connection.prepareStatement(sql);
					statement.setString(1, studentId);
					ResultSet resultSet = statement.executeQuery();
					
					while(resultSet.next()) {
						
						Course course = new Course();
						RegisteredCourse temp = new RegisteredCourse() ;
						course.setCourseCode(resultSet.getString(1));
						course.setCourseName(resultSet.getString(2));
						course.setInstructorId(resultSet.getString(3));
						course.setSeats(resultSet.getInt(4));
						
						
						temp.setCourse(course);
						System.out.println("course object generated");
						temp.setstudentId(studentId);
						
						
						temp.setGrade(resultSet.getString(8));
						
						System.out.println("graded");
						CoursesOfStudent.add(temp);
						
					}
					
					String sql1 = SQLQueriesConstant.SET_GENERATED_REPORT_CARD_TRUE;
					statement = connection.prepareStatement(sql1);
					statement.setString(1, studentId);
					int row = statement.executeUpdate();
						
					
				}catch(SQLException se) {

			 		System.out.println(se.getMessage());
		}
		
		return CoursesOfStudent;
		
		
	}

}
