package com.flipkart.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.flipkart.bean.Course;
import com.flipkart.bean.EnrolledStudent;
import com.flipkart.dao.ProfessorDaoInterface;
import com.flipkart.dao.ProfessorDaoOperation;
import com.flipkart.exception.GradeNotAllotedException;
import org.apache.log4j.Logger;

/**
 * @author Suicide Squad
 */
public class ProfessorOperation implements ProfessorInterface {
	private static Logger logger = Logger.getLogger(ProfessorOperation.class);

	private static volatile ProfessorOperation instance=null;
	ProfessorDaoInterface professorDAOInterface=ProfessorDaoOperation.getInstance();
	private ProfessorOperation()
	{

	}
	
	/**
	 * Method to make ProfessorOperation Singleton
	 * @return
	 */
	public static ProfessorOperation getInstance()
	{
		if(instance==null)
		{
			// This is a synchronized block, when multiple threads will access this instance
			synchronized(ProfessorOperation.class){
				instance=new ProfessorOperation();
			}
		}
		return instance;
	}
	
	
	/**
	 * Method to grade a Student
	 * @param studentId
	 * @param courseCode
	 * @param grade
	 * @return boolean indicating if grade is added or not
	 * @throws GradeNotAllotedException
	 */
	@Override
	
	public boolean addGrade(String studentId,String courseCode,String grade) throws GradeNotAllotedException {
		logger.info("addGrade()");
		logger.debug(studentId+" "+courseCode+" "+grade);
		try
		{
			professorDAOInterface.addGrade(studentId, courseCode, grade);
		}
		catch(Exception ex)
		{
			throw new GradeNotAllotedException(studentId);
		}
		return true;
	}
	
	
	/**
	 * Method to view all the enrolled students
	 * //@param courseId: Course id
	 * @return List of enrolled students
	 */
	
	@Override
	public List<EnrolledStudent> viewEnrolledStudents(String profId) throws SQLException{
		logger.info("viewEnrolledStudents()");
		logger.debug(profId);
		List<EnrolledStudent> enrolledStudents=new ArrayList<EnrolledStudent>();
		try
		{
			enrolledStudents=professorDAOInterface.getEnrolledStudents(profId);
		}
		catch(Exception ex)
		{
			throw ex;
		}
		return enrolledStudents;
	}

	
	/**
	 * Method to get list of all course a professor is teaching
	 * @param profId: professor id 
	 * @return List of courses the professor is teaching
	 */
	
	@Override
	public List<Course> viewCourses(String profId) {
		logger.info("viewCourses()");
		logger.debug(profId);
		//call the DAO class
		//get the courses for the professor
		List<Course> coursesOffered=new ArrayList<Course>();
		try
		{
			coursesOffered=professorDAOInterface.getCoursesByProfessor(profId);
		}
		catch(Exception ex)
		{
			throw ex;
		}
		return coursesOffered;
	}
	
	
	@Override
	public String getProfessorById(String profId)
	{
		logger.info("getProfessorById()");
		logger.debug(profId);
		return professorDAOInterface.getProfessorById(profId);
	}

	
}