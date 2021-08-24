package com.flipkart.DAO;

import java.util.HashMap;

import com.flipkart.bean.Student;

public class StudentRepository 
{
    HashMap<Integer, Student> StudentList = new HashMap<>();
    int countId = 10001;
    StudentRepository(){
        Student student1 = new Student(101, "admin1", "Kaisar", "Tumkur", "kaisar@gmail.com", countId, "CSE", 2);
        StudentList.put(countId, student1);
        countId++;
    }

    void addStudent(Student student)
    {
        StudentList.put(countId, student);
        countId++;
    }

    void deleteStudent(int studentId)
    {
        StudentList.remove(studentId);
    }
}
