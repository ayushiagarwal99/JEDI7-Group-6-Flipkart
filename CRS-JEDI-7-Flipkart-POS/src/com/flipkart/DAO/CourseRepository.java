package com.flipkart.DAO;

import java.util.HashMap;

import com.flipkart.bean.Course;

public class CourseRepository 
{
    HashMap<Integer, Course> courseList = new HashMap<>();
    int countId = 1001;
    CourseRepository()
    {
        Course course1 = new Course(1001, "SDLC", "Software engineering", 4);
        Course course2 = new Course(1002, "Computer Networks", "OS, DBMS", 7);
        Course course3 = new Course(1003, "Image Processing", "Python", 8);
        courseList.put(1001, course1);
        courseList.put(1002, course2);
        courseList.put(1003, course3);
    }    

    void addCourse(Course course)
    {
        courseList.put(countId, course);
        countId++;
    }

    void deleteCourse(int courseId)
    {
        courseList.remove(courseId);
    }

    Course getCourse(int courseId)
    {
        return courseList.get(courseId);
    }
}
