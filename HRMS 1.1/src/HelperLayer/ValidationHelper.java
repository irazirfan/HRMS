package HelperLayer;

import DAOLayer.UserDao;
import EntityLayer.Users;

public class ValidationHelper {
	
	public static boolean IsValidInt(String value)
	{
		try
		{
			Integer.parseInt(value);
			return true;
		}
		catch(Exception ex)
		{
			return false;
		}
	}
	
	public static boolean IsValidFloat(String value)
	{
		try
		{
			Float.parseFloat(value);
			return true;
		}
		catch(Exception ex)
		{
			return false;
		}
	}
	
	public static boolean IsValidMonth(String value)
	{
		try
		{
			if (Integer.parseInt(value)>0 && Integer.parseInt(value)<13) {
				
				return true;
			}
			return false;
		}
		catch(Exception ex)
		{
			return false;
		}
	}
	
	public static boolean IsValidYear(String value)
	{
		try
		{
			Integer t = Integer.parseInt(value);
			
			if (t.SIZE==4)
				return true;
			else
				return false;
		}
		catch(Exception ex)
		{
			return false;
		}
	}
	
	public static boolean IsValidEmployee(String value)
	{
		try
		{
			if (new UserDao().getByID(value).equals("admin")) {
				
				return false;
			}
			return true;
		}
		catch(Exception ex)
		{
			return false;
		}
	}
	
}
