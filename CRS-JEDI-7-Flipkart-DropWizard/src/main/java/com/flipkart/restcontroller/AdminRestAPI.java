/**
 * 
 */
package com.flipkart.restcontroller;

import java.util.List;

import javax.validation.Valid;
import javax.validation.ValidationException;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.flipkart.utils.UserAuth;
import org.hibernate.validator.constraints.Email;

import com.flipkart.bean.Course;
import com.flipkart.bean.Professor;
import com.flipkart.bean.Student;
import com.flipkart.exception.CourseExistsAlreadyException;
//import com.flipkart.exception.CourseFoundException;
import com.flipkart.exception.CourseNotFoundException;
import com.flipkart.exception.UserIdAlreadyInUseException;
import com.flipkart.exception.ProfessorNotAddedException;
import com.flipkart.exception.StudentNotFoundForApprovalException;
import com.flipkart.exception.UserNotFoundException;
import com.flipkart.service.AdminInterface;
import com.flipkart.service.AdminOperation;

/**
 * @author Rag_Patel and Anurag
 *
 */
@Path("/admin")
public class AdminRestAPI {
	
	AdminInterface adminOperation = AdminOperation.getInstance();
	
	/**
	 * /admin/assignCourseToProfessor
	 * REST-service for assigning course to professor
	 * @param courseCode
	 * @param professorId
	 * @return
	 */
	@POST
	@Path("/assignCourseToProfessor/{courseCode}/{professorId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response assignCourseToProfessor(
			
			@NotNull
			@PathParam("courseCode") String courseCode, 
			
			@NotNull
			@PathParam("professorId") String professorId) throws ValidationException {
		
			try {
				
				adminOperation.assignCourse(courseCode, professorId);
				return Response.status(201).entity("courseCode: " + courseCode + " assigned to professor: " + professorId).build();
				
			} catch (CourseNotFoundException | UserNotFoundException e) {
				
				return Response.status(409).entity(e.getMessage()).build();
				
			}
	}
	
	/**
	 * /admin/addProfessor
	 * REST-service for addding a new professor
	 * @param professor
	 * @return
	 */
	@POST
	@Path("/addProfessor")
	@Consumes("application/json")
	@Produces(MediaType.APPLICATION_JSON)
	public Response addProfessor(@Valid Professor professor, @HeaderParam("authKey") String authKey) throws ValidationException{
		if(UserAuth.isAdminLogin(authKey) == null){
			return Response.status(403).entity("Access Denied").build();
		}
		try {
			adminOperation.addProfessor(professor);
			return Response.status(201).entity("Professor with professorId: " + professor.getUserId() + " added").build();
			
		} catch (ProfessorNotAddedException | UserIdAlreadyInUseException e) {
			
			return Response.status(409).entity(e.getMessage()).build();
			
		}
		
	}
	
	/**
	 * /admin/viewPendingAdmissions
	 * REST-service for getting all pending-approval of students
	 * @return
	 */
	@GET
	@Path("/viewPendingAdmissions")
	@Produces(MediaType.APPLICATION_JSON)
	public Object viewPendingAdmissions(@HeaderParam("authKey") String authKey) {
		if(UserAuth.isAdminLogin(authKey) == null){
			return Response.status(403).entity("Access Denied").build();
		}
		
		return adminOperation.viewPendingAdmissions();
		
	}
	
	/**
	 * /admin/approveStudent
	 * REST-service for approving the student admission
	 * @param studentId
	 * @return
	 */
	@PUT
	@Path("/approveStudent/{studentId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response approveStudent(
			
			@NotNull
			@PathParam("studentId") String studentId, @HeaderParam("authKey") String authKey) throws ValidationException{
		if(UserAuth.isAdminLogin(authKey) == null){
			return Response.status(403).entity("Access Denied").build();
		}
		List<Student> studentList = adminOperation.viewPendingAdmissions();
		
		try {
			
			adminOperation.approveStudent(studentId, studentList);
			return Response.status(201).entity("Student with studentId: " + studentId + " approved").build();
		
		} catch (StudentNotFoundForApprovalException e) {
			
			return Response.status(409).entity(e.getMessage()).build();
		
		}
		
	}
	
	/**
	 * /admin/viewCoursesInCatalogue
	 * REST-service for getting courses in the catalog
	 * @return
	 */
	@GET
	@Path("/viewCoursesInCatalogue")
	@Produces(MediaType.APPLICATION_JSON)
	public Object viewCoursesInCatalogue(@HeaderParam("authKey") String authKey) {
		if(UserAuth.isAdminLogin(authKey) == null){
			return Response.status(403).entity("Access Denied").build();
		}
		
		return adminOperation.viewCourses();
		
	}
	
	/**
	 * /admin/deleteCourse
	 * REST-services for dropping a course from catalog
	 * @param courseCode
	 * @return
	 */
	@PUT
	@Path("/deleteCourse/{courseCode}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteCourse(
			//@Size(min = 4 , max = 10 , message = "Course Code length should be between 4 and 10 character")
			@NotNull
			@PathParam("courseCode") String courseCode, @HeaderParam("authKey") String authKey) throws ValidationException{
		if(UserAuth.isAdminLogin(authKey) == null){
			return Response.status(403).entity("Access Denied").build();
		}
		List<Course> courseList = adminOperation.viewCourses();
		
		try {
			
			adminOperation.deleteCourse(courseCode, courseList);
			return Response.status(201).entity("Course with courseCode: " + courseCode + " deleted from catalog").build();
		
		} catch (CourseNotFoundException e) {
			
			return Response.status(409).entity(e.getMessage()).build();
		
		}	
	}
	
	/**
	 * /admin/addCourse
	 * REST-service for adding a new course in catalog
	 * @param course
	 * @return
	 * @throws CourseExistsAlreadyException 
	 */
	@POST
	@Path("/addCourse")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response addCourse(@Valid Course course, @HeaderParam("authKey") String authKey) throws ValidationException, CourseNotFoundException, CourseExistsAlreadyException{
		if(UserAuth.isAdminLogin(authKey) == null){
			return Response.status(403).entity("Access Denied").build();
		}
		List<Course> courseList = adminOperation.viewCourses();
		
		adminOperation.addCourse(course, courseList);
		return Response.status(201).entity("Course with courseCode: " + course.getCourseCode() + " added to catalog").build();
	}
}