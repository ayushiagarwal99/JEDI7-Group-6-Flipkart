package com.flipkart.constant;

/**
 * 
 * @author Aditya
 * Enumeration class for GradeConstant
 * 
 */
public enum GradeConstant {
    A(10),
    A_MINUS(9),
    B(8),
    B_MINUS(7),
    C(6),
	C_MINUS(5),
    D(4),
    E(3),
    NOT_GRADED(0);

    final private int value;

    /**
     * Parameterized Constructor
     * @param value
     */
    private GradeConstant(int value) {
        this.value = value;
    }

    /**
     * Method to get GradeConstant Value
     * @return GradeConstant Value
     */
    public int hasValue() {
        return this.value;
    }

    /**
     * Method to convert GradeConstant enum to String
     * @return GradeConstant in String
     */
    @Override
    public String toString() {
    	
        final String name = name();
        
         
        if (name.contains("MINUS")) 
            return name.charAt(0) + "-"; 
        else 
            return name;
    }

}