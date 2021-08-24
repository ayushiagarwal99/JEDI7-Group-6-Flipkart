package com.flipkart.bean;
import java.util.ArrayList;

public class Course
{
    private int courseId;
    private String courseName;
    private ArrayList<Integer> StudentsList;
    private int seats = 10; 
    private int semester;
    private String prerequisite;

    public Course(){}

    /*
        parameterized constructor
    */
    public Course(int courseId, String courseName, String prerequisite, int semester) 
	{
		this.courseId = courseId;
		this.courseName = courseName;
		this.prerequisite = prerequisite;
		this.semester = semester;
	}
	
    
    public int getSemester() {
		return semester;
	}

	public void setSemester(int semester) {
		this.semester = semester;
	}

	public String getPrerequisite() {
		return prerequisite;
	}

	public void setPrerequisite(String prerequisite) {
		this.prerequisite = prerequisite;
	}

	/**
	 * Method to get Course Code
	 * @return Course Code
	 */
	public int getCourseCode() {
		return this.courseId;
	}
	
	/**
	 * Method to set Course Code
	 * @param courseCode
	 */
	public void setCourseCode(int courseId) {
		this.courseId = courseId;
	}
	
	/**
	 * Method to get Course Name
	 * @return Course Name
	 */
	public String getCourseName() {
		return courseName;
	}
	
	/**
	 * Method to set Course Name
	 * @param courseName
	 */
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	
}