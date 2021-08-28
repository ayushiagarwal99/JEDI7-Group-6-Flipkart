package com.flipkart.bean;

public class Payment 
{
	Student stud;
	String InvoiceID;
	float amount;
	boolean status;
	
	
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
	 * @return the invoiceID
	 */
	public String getInvoiceID() {
		return InvoiceID;
	}
	
	
	/**
	 * @param invoiceID the invoiceID to set
	 */
	public void setInvoiceID(String invoiceID) {
		InvoiceID = invoiceID;
	}
	
	
	/**
	 * @return the amount
	 */
	public float getAmount() {
		return amount;
	}
	
	
	/**
	 * @param amount the amount to set
	 */
	public void setAmount(float amount) {
		this.amount = amount;
	}
	
	
	/**
	 * @return the status
	 */
	public boolean isStatus() {
		return status;
	}
	
	
	/**
	 * @param status the status to set
	 */
	public void setStatus(boolean status) {
		this.status = status;
	}
	
	
	
}
