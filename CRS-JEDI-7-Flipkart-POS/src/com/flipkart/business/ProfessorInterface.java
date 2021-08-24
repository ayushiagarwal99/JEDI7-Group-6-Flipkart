
package com.flipkart.business;
import java.util.ArrayList;

import com.flipkart.bean.Course;
import com.flipkart.bean.GradeCard;
import com.flipkart.bean.Student;

public interface ProfessorInterface {
    public ArrayList<Course> viewCatalog();
    public GradeCard assignGrades(int studentId, int courseId, GradeCard gradeCard);
    public ArrayList<GradeCard> viewStudentGrades(int courseId);
    public ArrayList<Student> viewStudentList(int courseId);
}