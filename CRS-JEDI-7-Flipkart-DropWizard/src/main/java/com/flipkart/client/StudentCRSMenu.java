/**
 * 
 */
package com.flipkart.client;

import com.flipkart.bean.Course;
import com.flipkart.bean.Grade;
import com.flipkart.constant.NotificationTypeConstant;
import com.flipkart.constant.PaymentModeConstant;
import com.flipkart.exception.CourseLimitExceededException;
import com.flipkart.exception.CourseNotFoundException;
import com.flipkart.exception.SeatNotAvailableException;
import com.flipkart.service.*;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

/**
 * @author vijayrathod
 *
 */
public class StudentCRSMenu {
	
	Scanner sc = new Scanner(System.in);
	RegistrationInterface registrationInterface = RegistrationOperation.getInstance();
	AdminInterface adminInterface = AdminOperation.getInstance();
	ProfessorInterface professorInterface = ProfessorOperation.getInstance();
	NotificationInterface notificationInterface = NotificationOperation.getInstance();
	private boolean is_registered;
	

	
	public void create_menu(String studentId) {
		
		is_registered = getRegistrationStatus(studentId);
		
		while(CRSApplication.loggedin) {
			
				System.out.println("*****************************");
				System.out.println("**********Student Menu*********");
				System.out.println("*****************************");
				System.out.println("1. Course Registration");
				System.out.println("2. Add Course");
				System.out.println("3. Drop Course");
				System.out.println("4. View Course");
				System.out.println("5. View Registered Courses");
				System.out.println("6. View grade card");
				System.out.println("7. Make Payment");
				System.out.println("8. Logout");
				System.out.println("*****************************");
			
				int choice = sc.nextInt();
			
				switch (choice) {
				
				case 1: 
					registerCourses(studentId);
					break;
					
				case 2:
					addCourse(studentId);
					break;
					
				case 3:
					dropCourse(studentId);
					break;
					
				case 4:
					viewCourse(studentId);
					break;
					
				case 5:
					viewRegisteredCourse(studentId);
					break;
					
				case 6:
					viewGradeCard(studentId);
					break;
					
				case 7:
					make_payment(studentId);
					break;
					
				case 8:
					CRSApplication.loggedin = false;
					break;			
					
				default:
					System.out.println("Incorrect Choice!");
		
		
			}
			
		}
		
	}






private void registerCourses(String studentId)
{
	
	
	if(is_registered)
	{
		System.out.println(" Registration is already completed");
		return;
	}
	
	int count = 0;
	while(count < 6)
	{
		try
		{
			List<Course> courseList=viewCourse(studentId);
			
			if(courseList==null)
				return;
			
			System.out.println("Enter Course Code : " + (count+1));
			String courseCode = sc.next();
			
			if(registrationInterface.addCourse(courseCode,studentId,courseList))
			{
				System.out.println("Course " + courseCode + " registered sucessfully.");
				count++;
			}
			else
			{
				System.out.println(" You have already registered for Course : " + courseCode);
			}
		}	
		catch(CourseNotFoundException | CourseLimitExceededException | SQLException e)
		{
			System.out.println(e.getMessage());
		} catch (SeatNotAvailableException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
	}
	
	System.out.println("\n*******************************************************");
	System.out.println("Registration Successful");
	System.out.println("*******************************************************\n");
	
	try {
		registrationInterface.setRegistrationStatus(studentId);
	}
	catch(SQLException e)
	{
		System.out.println(e.getMessage());
	}
    is_registered = true;
    
//    try 
//    {
//		registrationInterface.setRegistrationStatus(studentId);
//	} 
//    catch (SQLException e) 
//    {
//    	System.out.println(e.getMessage());
//	}
}


private void addCourse(String studentId) {
	if(is_registered)
	{
		List<Course> availableCourseList=viewCourse(studentId);
		
		if(availableCourseList==null)
			return;

		try
		{
			System.out.println("Enter Course Code : " );
			String courseCode = sc.next();
			if(registrationInterface.addCourse(courseCode, studentId,availableCourseList))
			{
				System.out.println(" You have successfully registered for Course : " + courseCode);
			}
			else
			{
				System.out.println(" You have already registered for Course : " + courseCode);
			}
		}
		 catch(SQLException e)
		{
			System.out.println(e.getMessage());
		} catch (CourseNotFoundException e) {
			System.out.println(e.getMessage());
			
		} catch (CourseLimitExceededException e) {
			System.out.println(e.getMessage());
			
		} catch (SeatNotAvailableException e) {
			System.out.println(e.getMessage());
			
		}
	}
	else 
	{
		System.out.println("Please complete registration");
	}

}

/**
 * Method to check if student is already registered
 * @param studentId
 * @return Registration Status
 */
private boolean getRegistrationStatus(String studentId)
{
	try 
	{
		return registrationInterface.getRegistrationStatus(studentId);
	} 
	catch (SQLException e)
	{
		System.out.println(e.getMessage());
	}
	return false;
}


/**
 * Drop Course
 * @param studentId
 */
private void dropCourse(String studentId) {
	if(is_registered)
	{
		List<Course> registeredCourseList=viewRegisteredCourse(studentId);
		
		if(registeredCourseList==null)
			return;
		
		System.out.println("Enter the Course Code : ");
		String courseCode = sc.next();
		
		try
		{
			registrationInterface.dropCourse(courseCode, studentId,registeredCourseList);
			System.out.println("You have successfully dropped Course : " + courseCode);
			
		}
		catch(CourseNotFoundException e)
		{
			System.out.println("You have not registered for course : " + e.getCourseCode());
		} 
		catch (SQLException e) 
		{

			System.out.println(e.getMessage());
		}
	}
	else
	{
		System.out.println("Please complete registration");
	}
}


/**
 * View all available Courses 
 * @param studentId
 * @return List of available Courses 
 */
private List<Course> viewCourse(String studentId){
	List<Course> course_available=null;
	
	
	try 
	{
		course_available = registrationInterface.viewCourses(studentId);
	}
	catch (SQLException e) 
	{

		System.out.println(e.getMessage());
	}


	if(course_available.isEmpty())
	{
		System.out.println("NO COURSE AVAILABLE");
		return null;
	}
	

	System.out.println(String.format("%-20s %-20s %-20s %-20s","COURSE CODE", "COURSE NAME", "INSTRUCTOR", "SEATS"));
	for(Course obj : course_available)
	{
		System.out.println(String.format("%-20s %-20s %-20s %-20s",obj.getCourseCode(), obj.getCourseName(),obj.getInstructorId(), obj.getSeats()));
	}
	
	return course_available;
}


/**
 * View Registered Courses
 * @param studentId
 * @return List of Registered Courses
 */
private List<Course> viewRegisteredCourse(String studentId){
	List<Course> course_registered=null;
	try 
	{
		course_registered = registrationInterface.viewRegisteredCourses(studentId);
	} 
	catch (SQLException e) 
	{

		System.out.println(e.getMessage());
	}
	
	if(course_registered.isEmpty())
	{
		System.out.println("You haven't registered for any course");
		return null;
	}
	
	System.out.println(String.format("%-20s %-20s %-20s","COURSE CODE", "COURSE NAME", "INSTRUCTOR"));
	
	for(Course obj : course_registered)
	{
		 
		
		System.out.println(String.format("%-20s %-20s %-20s ",obj.getCourseCode(), obj.getCourseName(),obj.getInstructorId()));
	}
	
	return course_registered;
}

/**
 * View grade card for particular student  
 * @param studentId
 */
private void viewGradeCard(String studentId) {
	List<Grade> grade_card=null;
	boolean isReportGenerated = false;
	
	try 
	{
		isReportGenerated = registrationInterface.isReportGenerated(studentId);
		if(isReportGenerated) {
			grade_card = registrationInterface.viewGradeCard(studentId);
			System.out.println(String.format("%-20s %-20s %-20s","COURSE CODE", "COURSE NAME", "GRADE"));
			
			if(grade_card.isEmpty())
			{
				System.out.println("You haven't registered for any course");
				return;
			}
			
			for(Grade obj : grade_card)
			{
				System.out.println(String.format("%-20s %-20s %-20s",obj.getCrsCode(), obj.getCrsName(),obj.getGrade()));
			}
		}
		else
			System.out.println("Report card not yet generated");
	} 
	catch (SQLException e) 
	{

		System.out.println(e.getMessage());
	}
	
	
}

private void make_payment(String studentId)
{
	
	double fee = 1000.0;
	boolean isreg = false;
	boolean ispaid = false;
	try
	{
		isreg = registrationInterface.getRegistrationStatus(studentId);
		ispaid = registrationInterface.getPaymentStatus(studentId);
//		fee=registrationInterface.calculateFee(studentId);
	} 
	catch (SQLException e) 
	{

        System.out.println(e.getMessage());
	}

	
	if(!isreg)
	{
		System.out.println("You have not registered yet");
	}
	else if(isreg && !ispaid)
	{
		
		System.out.println("Your total fee  = " + fee);
		System.out.println("Want to continue Fee Payment(y/n)");
		String ch = sc.next();
		if(ch.equals("y"))
		{
			System.out.println("Select Mode of Payment:");
			
			int index = 1;
			for(PaymentModeConstant mode : PaymentModeConstant.values())
			{
				System.out.println(index + " " + mode);
				index = index + 1;
			}
			
			PaymentModeConstant mode = PaymentModeConstant.getPaymentMode(sc.nextInt());
			
			if(mode == null)
				System.out.println("Invalid Input");
			else
			{
				try 
				{
					notificationInterface.sendNotification(NotificationTypeConstant.PAYED, studentId, mode, fee);
					System.out.println("Payment Successful by StudentId :" + studentId);
					registrationInterface.setPaymentStatus(studentId);				
				}
				catch (Exception e) 
				{

		            System.out.println(e.getMessage());
				}
			}
			
			
			
				
		}
		
	}
	
	else
	{
		System.out.println("You have already paid the fees");
	}
	
}
}