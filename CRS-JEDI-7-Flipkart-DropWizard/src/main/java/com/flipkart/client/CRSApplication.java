/**
 * 
 */
package com.flipkart.client;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import com.flipkart.constant.GenderConstant;
import com.flipkart.constant.NotificationTypeConstant;
import com.flipkart.exception.StudentNotRegisteredException;
import com.flipkart.exception.UserNotFoundException;

import com.flipkart.service.NotificationInterface;
import com.flipkart.service.NotificationOperation;


import com.flipkart.service.StudentInterface;
import com.flipkart.service.StudentOperation;
import com.flipkart.service.UserInterface;
import com.flipkart.service.UserOperation;

/**
 * @author Goenka
 *
 */
public class CRSApplication {


	static boolean loggedin = false;
	StudentInterface studentInterface=StudentOperation.getInstance();
	UserInterface userInterface =UserOperation.getInstance();
	NotificationInterface notificationInterface=NotificationOperation.getInstance();
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		CRSApplication crsApplication=new CRSApplication();
		int userInput;	
		//create the main menu
		createMainMenu();
		userInput=sc.nextInt();
		try
		{
			
		//until user do not exit the application
		while(userInput!=4)
		{
			switch(userInput)
			{	
				case 1:
					//login
					crsApplication.loginUser();
					break;
				case 2:
					//student registration
					crsApplication.registerStudent();
					break;	
				case 3:
					crsApplication.updatePassword();
					break;
				default:
					System.out.println("Invalid Input");
			}
			createMainMenu();
			userInput=sc.nextInt();
		}
		}
		catch(Exception ex)
		{
			System.out.println("Error occured "+ex);
		}
		finally
		{
			sc.close();
		}
	}
	
	/**
	 * Method to Create Main Menu
	 */
	public static void createMainMenu()
	{
		System.out.println("----------Welcome to Course Management System---------");
		System.out.println("1. Login");
		System.out.println("2. Student Registration");
		System.out.println("3. Update password");
		System.out.println("4. Exit");
		System.out.println("Enter user input");
	}
	
	/**
	 * Method for Login function2ality
	 */
	public void loginUser()
	{
		//multiple exceptions are possible
		//invalid credential exception
		//user not found exception
		//user not approved exception
		Scanner in = new Scanner(System.in);

		String userId,password;
		try
		{
			System.out.println("-----------------Login------------------");
			System.out.println("Email:");
			userId = in.next();
			System.out.println("Password:");
			password = in.next();
			loggedin = userInterface.verifyCredentials(userId, password);
			
			//2 cases1
			
			
			//true->role->student->approved
			if(loggedin)
			{
				 
				 DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");  
				 
				 LocalDateTime myDateObj = LocalDateTime.now();
				   
				 String formattedDate = myDateObj.format(myFormatObj);  
				
				 String role = userInterface.getRole(userId);
			
			
				 
				 
				switch(role) {
				case "ADMIN":
					System.out.println(formattedDate + " Login Successful");
					AdminCRSMenu adminMenu = new AdminCRSMenu();
					adminMenu.createMenu();
					break;
				case "PROFESSOR":
					System.out.println(formattedDate + " Login Successful");
					ProfessorCRSMenu professorMenu=new ProfessorCRSMenu();
					professorMenu.createMenu(userId);
					
					break;
				case "STUDENT":
					
					String studentId = userId;
					boolean isApproved=studentInterface.isApproved(studentId);
					if(isApproved) {
						System.out.println(formattedDate + " Login Successful");
						StudentCRSMenu studentMenu=new StudentCRSMenu();
						studentMenu.create_menu(studentId);
						
					} else {
						System.out.println("Failed to login, you have not been approved by the administration!");
						loggedin=false;
					}
					break;
				}
				
				
			}
			else
			{
				System.out.println("Invalid Credentials!");
			}
			
		}
		catch(UserNotFoundException ex)
		{
			System.out.println(ex.getMessage());
		}
		
	}
	
	/**
	 * Method to help Student register themselves, pending admin approval
	 */
	public void registerStudent()
	{
		Scanner sc=new Scanner(System.in);

		String userId,name,password,address,branchName;
		GenderConstant gender;
		int genderV, batch;
		try
		{
			//input all the student details
			System.out.println("---------------Student Registration-------------");
			System.out.println("Name:");
			name=sc.nextLine();
			System.out.println("Email:");
			userId=sc.next();
			System.out.println("Password:");
			password=sc.next();
			System.out.println("GenderConstant: \t 1: Male \t 2.Female\t 3.Other");
			genderV=sc.nextInt();
			sc.nextLine();
			
			switch(genderV)
			{
			case 1:
				gender=GenderConstant.MALE;
				break;
			case 2:
				gender=GenderConstant.FEMALE;
				break;
				
			case 3:
				gender=GenderConstant.OTHER;
				break;
			default: 
				gender=GenderConstant.OTHER;
			}
			
			System.out.println("Branch:");
			branchName=sc.nextLine();
			System.out.println("Batch:");
			batch=sc.nextInt();
			sc.nextLine();
			System.out.println("Address:");
			address=sc.nextLine();
			
			
			String newStudentId = studentInterface.register(name, userId, password, gender, batch, branchName, address);
			
			//notificationInterface.sendNotification(NotificationTypeConstant.REGISTRATION, newStudentId, null,0);
			
		}
		catch(StudentNotRegisteredException ex)
		{
			System.out.println("Something went wrong! "+ex.getStudentName() +" not registered. Please try again");
		}
		//sc.close();
	}
	
	/**
	 * Method to update password of User
	 */
	public void updatePassword() {
		Scanner in = new Scanner(System.in);
		String userId,newPassword;
		try {
			System.out.println("------------------Update Password--------------------");
			System.out.println("Email");
			userId=in.next();
			System.out.println("New Password:");
			newPassword=in.next();
			boolean isUpdated=userInterface.updatePassword(userId, newPassword);
			if(isUpdated)
				System.out.println("Password updated successfully!");

			else
				System.out.println("Something went wrong, please try again!");
		} catch(Exception ex) {
			System.out.println("Error Occured "+ex.getMessage());
		}
		
	}
		
}
