package com.flipkart.business;
import com.flipkart.bean.GradeCard;

public interface AdminInterface {
    public int addCourse(int courseID, String name, String prerequisite, String semester);
    public int dropCourse(int courseID);
    public void approveRegistration(int studentID);
    public int addProfessor(int professorID);
    public GradeCard generateGrades(int studentID);
}