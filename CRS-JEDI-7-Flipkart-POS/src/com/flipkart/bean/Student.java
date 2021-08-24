package com.flipkart.bean;

import java.util.ArrayList;

public class Student extends User{

    private int studentId;
    private String branchName;
    private int semester;
    private ArrayList<Integer> approvedCourses;
    
    public Student() {}

    public ArrayList<Integer> getApprovedCourses() {
        return approvedCourses;
    }

    public void setApprovedCourses(ArrayList<Integer> approvedCourses) {
        this.approvedCourses = approvedCourses;
    }

    public Student(int userId, String password, String name, String address, String emailID, int studentId,
			String branchName, int semester) {
		super(userId, password, name, address, emailID);
		this.studentId = studentId;
		this.branchName = branchName;
		this.semester = semester;
	}

	public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public int getSemester() {
        return semester;
    }

    public void setSemester(int semester) {
        this.semester = semester;
    }
}