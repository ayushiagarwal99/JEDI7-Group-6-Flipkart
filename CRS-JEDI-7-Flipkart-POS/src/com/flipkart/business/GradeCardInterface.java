package com.flipkart.business;

import com.flipkart.bean.GradeCard;

public interface GradeCardInterface 
{
    GradeCard getGradeCardByStudentId(int studentID);
    void approveGradeCard(int studentID);
}
