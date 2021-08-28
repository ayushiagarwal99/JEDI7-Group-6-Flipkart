package com.flipkart.restcontroller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ValidationException;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

//import org.hibernate.validator.constraints.Email;

import com.flipkart.bean.Course;
import com.flipkart.bean.EnrolledStudent;
import com.flipkart.service.ProfessorInterface;
import com.flipkart.service.ProfessorOperation;
import com.flipkart.validator.ProfessorValidator;

@Path("/professor")
public class ProfessorRestAPI {
	ProfessorInterface professorInterface=ProfessorOperation.getInstance();
	
	@GET
	@Path("/getEnrolledStudents/{profId}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<EnrolledStudent> viewEnrolledStudents(
			@NotNull
			
			@PathParam("profId") String profId) throws ValidationException	{
		
		List<EnrolledStudent> students=new ArrayList<EnrolledStudent>();
		try
		{
			students=professorInterface.viewEnrolledStudents(profId);
		}
		catch(Exception ex)
		{
			return null;
		}	
		return students;
	}
	
	@GET
	@Path("/getCourses/{profId}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Course> getCourses(
			@NotNull
			
			@PathParam("profId") String profId) throws ValidationException	{
		
		List<Course> courses=new ArrayList<Course>();
		try
		{
			courses=professorInterface.viewCourses(profId);	
		}
		catch(Exception ex)
		{
			return null;
		}
		return courses;
	
	}
	
	@POST
	@Path("/addGrade/{studentId}/{courseCode}/{profId}/{grade}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addGrade(
			@NotNull
			
		
			
			@PathParam("studentId") String studentId,
			
			@NotNull
			
			@PathParam("courseCode") String courseCode,
			
			@NotNull
			
			@PathParam("profId") String profId,
			
			@QueryParam("grade") String grade) throws ValidationException  	{
		
		try
		{
			List<EnrolledStudent> enrolledStudents=new ArrayList<EnrolledStudent>();
			enrolledStudents=professorInterface.viewEnrolledStudents(profId);
			List<Course> coursesEnrolled=new ArrayList<Course>();
			coursesEnrolled	=professorInterface.viewCourses(profId);
			if(!(ProfessorValidator.isValidStudent(enrolledStudents, studentId) && ProfessorValidator.isValidCourse(coursesEnrolled, courseCode)))
			{
				professorInterface.addGrade(studentId, courseCode, grade);
			}
			else
			{
				//error code
				return Response.status(500).entity( "Something went wrong, Please Try Again ! ").build();
			}
		}
		catch(Exception ex)
		{
			return Response.status(500).entity( "Something went wrong, Please Try Again ! ").build();
		}
		return Response.status(200).entity( "Grade updated for student: "+studentId).build();
		
	}
	
	
}