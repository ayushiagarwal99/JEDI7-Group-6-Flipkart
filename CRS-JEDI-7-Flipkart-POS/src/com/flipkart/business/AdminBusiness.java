package com.flipkart.business;

import java.util.ArrayList;
import java.util.HashMap;

import com.flipkart.bean.Course;
import com.flipkart.bean.GradeCard;
import com.flipkart.bean.Professor;

public class AdminBusiness implements AdminInterface {

    HashMap<Integer, Course> courseList = new HashMap<>();
    CourseInterface courseInterface = new CourseBusiness();
    StudentInterface studentInterface = new StudentBusiness();
    ProfessorInterface professorInterface = new ProfessorBusiness();
    GradeCardInterface gradeCardInterface = new GradeCardBusiness();

    @Override
    public int addCourse(int courseID, String name, String prerequisite, int semester) 
    {
        // Course course1 = new Course(1001, "SDLC", "Software engineering", 4);
        // Course course2 = new Course(1002, "Computer Networks", "OS, DBMS", 7);
        // Course course3 = new Course(1003, "Image Processing", "Python", 8);
        // courseList.put(1001, course1);
        // courseList.put(1002, course2);
        // courseList.put(1003, course3);
        Course course = new Course(courseID, name, prerequisite, semester);
        courseInterface.addNewCourse(course);
        return courseID;
    }

    @Override
    public int dropCourse(int courseID) 
    {
        courseInterface.removeCourse(courseID);
        return 0;
    }

    @Override
    public ArrayList<Integer> approveRegistration(int studentID) 
    {
        ArrayList<Integer> unapprovedCourses = new ArrayList<>();
        ArrayList<Integer> courseList = studentInterface.getListOfRequestedCourses(studentID);
        for(int courseId : courseList)
        {
            if(courseInterface.availableSeats(courseId) > 0)
            {
                courseInterface.addStudentList(courseId, studentID);
                studentInterface.addToApprovedList(studentID, courseId);
            }
            else
                unapprovedCourses.add(courseId);
        }
        return unapprovedCourses;
    }

    @Override
    public int addProfessor(Professor professor) {
        professorInterface.addProfessor(professor);
        return professor.getProfessorId();
    }

    @Override
    public GradeCard generateGrades(int studentID) 
    {
        gradeCardInterface.approveGradeCard(studentID);   
        return gradeCardInterface.getGradeCardByStudentId(studentID);
    }
    
}
