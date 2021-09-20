package codehouse.assignment.business.utilities.businessRules;

public class Rules 
{
	public static boolean checkFirstName(String firstName) 
	{		
		if (firstName.length()<2) 
		{			
			return false;
		}
		else 
		{
			return true;
		}			
	}
	
	public static boolean checkLastName(String lastName)
	{		
		if (lastName.length()<2)
		{			
			return false;
		}
		else
		{
			return true;
		}			
	}
	
	public static boolean checkPhone(String phone)
	{		
		if (phone.length()<7)
		{			
			return false;
		}
		else
		{
			return true;
		}			
	}
}