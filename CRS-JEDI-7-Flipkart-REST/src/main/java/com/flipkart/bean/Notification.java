/**
 * 
 */
package com.flipkart.bean;

import com.flipkart.constant.NotificationTypeConstant;

/**
 * @author Aditya
 *
 */

//GLOBAL VAR

public class Notification 
{
	private int notiID;
	private int stdID;
	private NotificationTypeConstant type;
	private String referID;
	
	/**
	 * @return the notiID
	 */
	public int getNotiID() 
	{
		return notiID;
	}
	
	
	/**
	 * @param notiID the notiID to set
	 */
	public void setNotiID(int notiID) 
	{
		this.notiID = notiID;
	}
	
	
	
	/**
	 * @return the stdID
	 */
	public int getStdID() 
	{
		return stdID;
	}
	
	
	/**
	 * @param stdID the stdID to set
	 */
	public void setStdID(int stdID)
	{
		this.stdID = stdID;
	}
	
	
	
	/**
	 * @return the type
	 */
	public NotificationTypeConstant getType() 
	{
		return type;
	}
	
	
	
	/**
	 * @param type the type to set
	 */
	public void setType(NotificationTypeConstant type) 
	{
		this.type = type;
	}
	
	
	/**
	 * @return the referID
	 */
	public String getReferID() 
	{
		return referID;
	}
	
	
	/**
	 * @param referID the referID to set
	 */
	public void setReferID(String referID) 
	{
		this.referID = referID;
	}
	
	
}
