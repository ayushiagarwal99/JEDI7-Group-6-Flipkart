package com.flipkart.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.flipkart.bean.Course;
import com.flipkart.bean.Grade;
import com.flipkart.constant.SQLQueriesConstant;
import com.flipkart.exception.CourseNotFoundException;
import com.flipkart.utils.DBUtils;
import org.apache.log4j.Logger;


/**
 * 
 * @author Suicide Squad
 * Class to implement Registration Dao Operations
 * This class communicates with the database.
 *
 */
public class RegistrationDaoOperation implements RegistrationDaoInterface{

	private static Logger logger = Logger.getLogger(RegistrationDaoOperation.class);
	private static volatile RegistrationDaoOperation instance=null;

	private PreparedStatement stmt = null;
	
	/**
	 * Default Constructor
	 */
	private RegistrationDaoOperation() {}
	
	/**
	 * Method to make RegistrationDaoOperation Singleton
	 * @return
	 */
	public static RegistrationDaoOperation getInstance()
	{
		if(instance==null)
		{
			synchronized(RegistrationDaoOperation.class){
				instance=new RegistrationDaoOperation();
			}
		}
		return instance;
	}


	@Override
	public boolean addCourse(String courseCode, String studentId) throws SQLException{
		logger.info("addCourse()");
		logger.debug(studentId + " " + courseCode);
		Connection conn = DBUtils.getConnection();
		
		try 
		{
			stmt = conn.prepareStatement(SQLQueriesConstant.ADD_COURSE);
			stmt.setString(1, studentId);
			stmt.setString(2, courseCode);
			stmt.setString(3, "-");
			stmt.executeUpdate();
			
			stmt = conn.prepareStatement(SQLQueriesConstant.DECREMENT_COURSE_SEATS);
			stmt.setString(1, courseCode);
			stmt.executeUpdate();
			return true;
		}
		catch (SQLException e) 
		{
			System.out.println(e.getMessage());
		}
		finally
		{
			stmt.close();
			conn.close();
		}
		return false;
		
	}
	
	
	/**
	 * Number of registered courses for a student
	 * @param studentId
	 * @return Number of registered courses for a student
	 * @throws SQLException 
	 */
	@Override
	public int numOfRegisteredCourses(String studentId) throws SQLException{
		logger.info("numOfRegisteredCourses()");
		logger.debug(studentId);
		Connection conn = DBUtils.getConnection();
		
		int count = 0;
		try {

			stmt = conn.prepareStatement(SQLQueriesConstant.NUMBER_OF_REGISTERED_COURSES);
			stmt.setString(1, studentId);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				count++;
			}
			return count;

		}
		catch (SQLException se) 
		{

			System.out.println(se.getMessage());

		} 
		catch (Exception e)
		{

			System.out.println(e.getMessage());
		}
		finally
		{
			stmt.close();
			conn.close();
		}
		
		return count;
	}


	/**
	 * Check if seat is available for that particular course
	 * @param courseCode
	 * @return status of seat availablity
	 * @throws SQLException 
	 */
	@Override
	public boolean seatAvailable(String courseCode) throws SQLException {
		logger.info("seatAvailable()");
		logger.debug(courseCode);
		Connection conn = DBUtils.getConnection();
		try 
		{
			stmt = conn.prepareStatement(SQLQueriesConstant.GET_SEATS);
			stmt.setString(1, courseCode);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				return (rs.getInt("seats") > 0);
			}

		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		finally
		{
			stmt.close();
			conn.close();
		}
		
		return true;
		

	}
	


	/**
	 * Method checks if the student is registered for that course
	 * @param courseCode
	 * @param studentId
	 * @return Students registration status
	 * @throws SQLException 
	 */
	@Override
	public boolean isRegistered(String courseCode, String studentId) throws SQLException{
		logger.info("isRegistered()");
		logger.debug(courseCode + " " + studentId);
		Connection conn = DBUtils.getConnection();
		
		boolean check = false;
		try
		{
			stmt = conn.prepareStatement(SQLQueriesConstant.IS_REGISTERED);
			stmt.setString(1, courseCode);
			stmt.setString(2, studentId);
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next())
			{
				check = true;
			}
		}
		catch(Exception e)
		{
			System.out.println(e.getClass());
			System.out.println(e.getMessage());
		}
		finally
		{
			stmt.close();
			conn.close();
		}
		
		return check;
		
	}


	/**
	 * Drop Course selected by student
	 * @param courseCode : code for selected course
	 * @param studentId
	 * @return status of drop course operation
	 * @throws CourseNotFoundException 
	 */
	@Override
	public boolean dropCourse(String courseCode, String studentId) throws SQLException {
		logger.info("dropCourse()");
		logger.debug(courseCode + " " + studentId);
		Connection conn = DBUtils.getConnection();
		
		
			try
			{
				stmt = conn.prepareStatement(SQLQueriesConstant.DROP_COURSE_QUERY);
				stmt.setString(1, courseCode);
				stmt.setString(2, studentId);
				stmt.execute();
				
				stmt = conn.prepareStatement(SQLQueriesConstant.INCREMENT_SEAT_QUERY);
				stmt.setString(1, courseCode);
				stmt.execute();
				
				stmt.close();
				
				return true;
			}
			catch(Exception e)
			{
				System.out.println("Exception found" + e.getMessage());
			}
			finally
			{
	
				stmt.close();
				conn.close();
			}
			
		
		return false;
		
	}
	
	/**
	 * Method to retrieve fee for the selected courses from the database and calculate total fee
	 * @param studentId
	 * @return Fee Student has to pay
	 * @throws SQLException 
	 */
	
	@Override
	public double calculateFee(String studentId) throws SQLException
	{
		logger.info("calculateFee()");
		logger.debug( studentId);
		Connection conn = DBUtils.getConnection();
		double fee = 0;
		try
		{
			stmt = conn.prepareStatement(SQLQueriesConstant.CALCULATE_FEES);
			stmt.setString(1, studentId);
			ResultSet rs = stmt.executeQuery();
			rs.next();
			fee = rs.getDouble(1);
		}
		catch(SQLException e)
		{
			System.out.println(e.getErrorCode());
			System.out.println(e.getMessage());
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
		finally
		{
			stmt.close();
			conn.close();
		}
		
		return fee;
	}

	/**
	 * Method to view grade card of the student
	 * @param studentId
	 * @throws SQLException 
	 * @return Studen's grade card
	 */
	@Override
	public List<Grade> viewGradeCard(String studentId) throws SQLException {
		logger.info("viewGradeCard()");
		logger.debug( studentId);
		Connection conn = DBUtils.getConnection();
		List<Grade> grade_List = new ArrayList<Grade>();
		try
		{
			stmt = conn.prepareStatement(SQLQueriesConstant.VIEW_GRADE);
			stmt.setString(1, studentId);
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next())
			{
				String courseCode = rs.getString("courseCode");
				String courseName = rs.getString("courseName");
				String grade = rs.getString("grade");
				Grade obj = new Grade(courseCode, courseName,grade);
				grade_List.add(obj);
			}
		}
		catch(SQLException e)
		{
			System.out.println(e.getMessage());
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
		finally
		{
			stmt.close();
			conn.close();
			
		}
		
		return grade_List;
	}

	/**
	 * Method to get the list of courses available from course catalog 
	 * @param studentId
	 * @return list of courses
	 * @throws SQLException
	 */
	@Override
	public List<Course> viewCourses(String studentId) throws SQLException {
		logger.info("viewCourses()");
		logger.debug( studentId);
		List<Course> availableCourseList = new ArrayList<>();
		Connection conn = DBUtils.getConnection();
		
		try 
		{
			stmt = conn.prepareStatement(SQLQueriesConstant.VIEW_AVAILABLE_COURSES);
			stmt.setString(1, studentId);
			//stmt.setBoolean(2, true);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				availableCourseList.add(new Course(rs.getString("courseCode"), rs.getString("courseName"),
						rs.getString("professorId"), rs.getInt("seats")));

			}
			

		} 
		catch (SQLException e) 
		{
			System.out.println(e.getMessage());
		} 
		catch (Exception e)
		{
			System.out.println(e.getMessage());
		}
		finally
		{
			stmt.close();
			conn.close();
		}
		
		return availableCourseList;
		
	}

	/**
	 * Method to get the list of courses registered by the student
	 * @param studentId
	 * @return list of courses registered by student
	 * @throws SQLException 
	 */
	@Override
	public List<Course> viewRegisteredCourses(String studentId) throws SQLException {
		logger.info("viewRegisteredCourses()");
		logger.debug( studentId);
		Connection conn = DBUtils.getConnection();
		List<Course> registeredCourseList = new ArrayList<>();
		try 
		{
			stmt = conn.prepareStatement(SQLQueriesConstant.VIEW_REGISTERED_COURSES);
			stmt.setString(1, studentId);

			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {
				registeredCourseList.add(new Course(rs.getString("courseCode"), rs.getString("courseName"),
						rs.getString("professorId"), rs.getInt("seats")));

			}
		} 
		catch (SQLException e) 
		{
			System.out.println(e.getMessage());

		} 
		finally
		{
			stmt.close();
			conn.close();
		}
		
		return registeredCourseList;
	}

	/**
	 * Method to retrieve Student's registration status
	 * @param studentId
	 * @return Student's registration status
	 * @throws SQLException
	 */
	@Override
	public boolean getRegistrationStatus(String studentId) throws SQLException
	{
		logger.info("getRegistrationStatus()");
		logger.debug( studentId);
		Connection conn = DBUtils.getConnection();
		boolean status = false;
		try 
		{
			stmt = conn.prepareStatement(SQLQueriesConstant.GET_REGISTRATION_STATUS);
			stmt.setString(1, studentId);
			ResultSet rs = stmt.executeQuery();
			rs.next();
			status = rs.getBoolean(1);
			//System.out.println(status);	
		} 
		catch (SQLException e) 
		{
			System.out.println(e.getMessage());

		} 
		finally
		{
			stmt.close();
			conn.close();
		}

		return status;
	}
	/**
	 * Method to set Student's registration status
	 * @param studentId
	 * @throws SQLException
	 */
	@Override
	public void setRegistrationStatus(String studentId) throws SQLException
	{
		logger.info("setRegistrationStatus()");
		logger.debug( studentId);
		Connection conn = DBUtils.getConnection();
		try 
		{
			stmt = conn.prepareStatement(SQLQueriesConstant.SET_REGISTRATION_STATUS);
			stmt.setString(1, studentId);
			stmt.executeUpdate();

		} 
		catch (SQLException e) 
		{
			System.out.println(e.getMessage());

		} 
		finally
		{
			stmt.close();
			conn.close();
		}

	}

	@Override
	public boolean isReportGenerated(String studentId) throws SQLException
	{
		logger.info("isReportGenerated()");
		logger.debug( studentId);
		Connection conn = DBUtils.getConnection();
		boolean status = false;
		try 
		{
			stmt = conn.prepareStatement(SQLQueriesConstant.GET_GENERATED_REPORT_CARD_TRUE);
			stmt.setString(1, studentId);
			ResultSet rs = stmt.executeQuery();
			rs.next();
			status = rs.getBoolean(1);
			//System.out.println(status);	
		} 
		catch (SQLException e) 
		{
			System.out.println(e.getMessage());
	
		} 
		finally
		{
			stmt.close();
			conn.close();
		}
	
		return status;
	}

	@Override
	public boolean getPaymentStatus(String studentId) throws SQLException 
	{
		logger.info("getPaymentStatus()");
		logger.debug( studentId);
		{
			Connection conn = DBUtils.getConnection();
			boolean status = false;
			try 
			{
				stmt = conn.prepareStatement(SQLQueriesConstant.GET_PAYMENT_STATUS);
				stmt.setString(1, studentId);
				ResultSet rs = stmt.executeQuery();
				rs.next();
				status = rs.getBoolean(1);
				//System.out.println(status);	
			} 
			catch (SQLException e) 
			{
				System.out.println(e.getMessage());

			} 
			finally
			{
				stmt.close();
				conn.close();
			}

			return status;
	}


	}

	@Override
	public void setPaymentStatus(String studentId) throws SQLException {
		logger.info("setPaymentStatus()");
		logger.debug( studentId);
		Connection conn = DBUtils.getConnection();
		try 
		{
			stmt = conn.prepareStatement(SQLQueriesConstant.SET_PAYMENT_STATUS);
			stmt.setString(1, studentId);
			stmt.executeUpdate();

		} 
		catch (SQLException e) 
		{
			System.out.println(e.getMessage());

		} 
		finally
		{
			stmt.close();
			conn.close();
		}

	}
}