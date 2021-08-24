package com.flipkart.bean;

import java.util.HashMap;

public class GradeCard {
    private Integer student_ID;
    private String semester;
    private double cgpa;
    private HashMap<Integer, Integer> map; // {courseID, score}
    private boolean isApproved = false;

    public GradeCard() {}

    public boolean isApproved() {
        return isApproved;
    }

    public void setApproved(boolean isApproved) {
        this.isApproved = isApproved;
    }

    public GradeCard(Integer student_ID, String semester, double cgpa, HashMap<Integer, Integer> map) {
        this.student_ID = student_ID;
        this.semester = semester;
        this.cgpa = cgpa;
        this.map = map;
    }

    public Integer getStudent_ID() {
        return student_ID;
    }

    public void setStudent_ID(Integer student_ID) {
        this.student_ID = student_ID;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public double getCgpa() {
        return cgpa;
    }

    public void setCgpa(double cgpa) {
        this.cgpa = cgpa;
    }

    public HashMap<Integer, Integer> getMap() {
        return map;
    }

    public void setMap(HashMap<Integer, Integer> map) {
        this.map = map;
    }
}