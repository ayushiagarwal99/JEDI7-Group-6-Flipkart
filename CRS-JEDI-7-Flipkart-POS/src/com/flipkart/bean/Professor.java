package com.flipkart.bean;

public class Professor extends User {

    private int professorId;
    
    public Professor() {}
    
	public Professor(int userId, String password, String name, String address, String emailID, int professorId) {
		super(userId, password, name, address, emailID);
		this.professorId = professorId;
	}

	public void setProfessorId(int professorId) {
        this.professorId = professorId;
    }

    public int getProfessorId() {
        return this.professorId;
    }
    
}