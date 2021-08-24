package com.flipkart.business;

import com.flipkart.bean.Course;

public interface CourseInterface {
    public String viewDetails(int courseId);
    public int availableSeats(int courseId);
    public void addStudentList(int courseID, int studentID);
    public int addNewCourse(Course course);
    public void removeCourse(int courseID);
}
