package com.flipkart.constant;

/**
 * 
 * @author Aditya
 * Enumeration class for GenderConstant
 *
 */
public enum GenderConstant {
	MALE(1),FEMALE(2),OTHER(3);
	private final int gender;
	
	/**
	 * Parameterized Constructor
	 * @param gender
	 */
	private GenderConstant(int gender)
	{
		this.gender=gender;
	}
	
	/**
	 * Method to return gender type in String
	 * @return GenderConstant name in String
	 */
	@Override
	public String toString()
	{
		final String name=name();
		return name; 
	}
	
	/**
	 * Method to get GenderConstant object depending upon user input
	 * @param val
	 * @return GenderConstant object
	 */
	public static GenderConstant getName(int val)
	{
		GenderConstant gender=GenderConstant.OTHER;
		switch(val)
		{
		case 1:
			gender=GenderConstant.MALE;
			break;
		case 2:
			gender=GenderConstant.FEMALE;
			break;
			
		}
		return gender;
	}
	
	/**
	 * Method to convert String to GenderConstant object
	 * @param val
	 * @return GenderConstant object
	 */
	public static GenderConstant stringToGender(String val)
	{
		GenderConstant gender=GenderConstant.OTHER;
		if(val.equalsIgnoreCase("male"))
			gender=GenderConstant.MALE;
		else if(val.equalsIgnoreCase("female"))
			gender=GenderConstant.FEMALE;
		else if(val.equalsIgnoreCase("other"))
			gender=GenderConstant.OTHER;
		
		return gender;
	}
	
}