package com.flipkart.bean;

public class Notification {
    private int StudentID;
    public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	private int NotificationID;
    private String message; 
    
     public Notification(){}
     
      public Notification(int StudentID, int NotificationID) {
        this.StudentID=StudentID;
        this.NotificationID=NotificationID;
    }

    public void setStudentID(int StudentID) {
		this.StudentID=StudentID;
	}
    
    public int getStudentID() {
        return StudentID;
    }

    public void setNotificationID(int NotificationID) {
		this.NotificationID = NotificationID;
	}

     public int getNotificationID() {
        return  NotificationID;
    }
}