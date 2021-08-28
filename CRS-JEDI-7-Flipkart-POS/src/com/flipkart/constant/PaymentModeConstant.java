package com.flipkart.constant;

/**
 * 
 * @author JEDI-03
 * Enumeration class for Mode of Payments
 *
 */
public enum PaymentModeConstant {
	
	CREDIT_CARD,NET_BANKING,DEBIT_CARD;
	
	/**
	 * Method to get Mode of Payment
	 * @param value
	 * @return Mode of Payment
	 */
	public static PaymentModeConstant getPaymentMode(int value)
	{
		switch(value)
		{
			case 1:
				return PaymentModeConstant.CREDIT_CARD;
			case 2:
				return PaymentModeConstant.NET_BANKING;
			case 3:
				return PaymentModeConstant.DEBIT_CARD;
			default:
				return null;
				
		}
			
	}
	
}