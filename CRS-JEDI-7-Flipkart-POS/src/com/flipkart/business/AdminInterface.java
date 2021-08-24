package com.flipkart.business;
import java.util.ArrayList;

import com.flipkart.bean.GradeCard;
import com.flipkart.bean.Professor;

public interface AdminInterface {
    public int addCourse(int courseID, String name, String prerequisite, int semester);
    public int dropCourse(int courseID);
    public ArrayList<Integer> approveRegistration(int studentID);
    public int addProfessor(Professor professor);
    public GradeCard generateGrades(int studentID);
}