package com.flipkart.business;
import java.util.ArrayList;

import com.flipkart.bean.Course;
import com.flipkart.bean.GradeCard;
import com.flipkart.bean.Notification;

public interface StudentInterface {
    public void registerCourse(int courseId);
    public void addCourse(int courseId);
    public void dropCourse(int courseId);
    public ArrayList<Course> getCatalogue();
    public Notification payFees();
    public GradeCard viewGrades();
    public int getStudentId();
    public ArrayList<Integer> getListOfRequestedCourses(int studentID);
    public void addToApprovedList(int courseID, int studentID);
}
