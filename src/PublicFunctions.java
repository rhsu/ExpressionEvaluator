

public class PublicFunctions
{	
	/**
	 * Tests if a string is an integer
	 * @param s
	 * @return true or false whether the string is an integer
	 */
	public static boolean isInteger(String s)
	{
		return s.matches("-?[0-9]+");
	}

	/**
	 * @param token
	 * @return True or false whether the string is a valid token
	 */
	public static boolean isValidToken(String token)
	{
		if
		(        
		   token.equals("+") || 
		   token.equals("-") || 
		   token.equals("*") || 
		   token.equals("/") ||
		   token.equals("(") ||
		   token.equals(")")
		 ) 
		 {
			return true;
		 }
		 return false;
	}
}