package com.flipkart.bean;

import java.util.List;

public class GradeCard 
{
	Student stud;
	int sem;
	float cgpa;
	List<RegisteredCourse> reg_list ;
	
	/*public float calCGPA()
	{
		return 0;
	}*/

	/**
	 * @return the stud
	 */
	public Student getStud() {
		return stud;
	}

	/**
	 * @param stud the stud to set
	 */
	public void setStud(Student stud) {
		this.stud = stud;
	}

	/**
	 * @return the sem
	 */
	public int getSem() {
		return sem;
	}

	/**
	 * @param sem the sem to set
	 */
	public void setSem(int sem) {
		this.sem = sem;
	}

	/**
	 * @return the cgpa
	 */
	public float getCgpa() {
		return cgpa;
	}

	/**
	 * @param cgpa the cgpa to set
	 */
	public void setCgpa(float cgpa) {
		this.cgpa = cgpa;
	}

	/**
	 * @return the reg_list
	 */
	public List<RegisteredCourse> getReg_list() {
		return reg_list;
	}

	/**
	 * @param reg_list the reg_list to set
	 */
	public void setReg_list(List<RegisteredCourse> reg_list) {
		this.reg_list = reg_list;
	}
	
	
}
