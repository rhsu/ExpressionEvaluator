import java.util.LinkedList;
import java.util.Stack;

/**
 * A Java Implementation of the ShuntingYard algorithm described at Wikipedia
 * @author RHsu
 */
public class ShuntingYard 
{
	/**
	 * @param operator
	 * @return the precedence of the operator
	 */
	private static int getPrecedence(String operator)
	{	
		if((operator.equals("*")) || (operator.equals("/")))
		{
			return 3;
		}
		else if ((operator.equals("+")) || (operator.equals("-")))
		{
			return 2;
		}
		else 
		{
			return 0;
		}
	}
	
	/**
	 * @param s
	 * @return true
	 */
	private static boolean isLeftAssociative(String s)
	{   
		//TODO: Add in other operators. This function is incomplete
		return true;
	}

	/**
	 * @param expressionString the string to be parsed
	 * @return a LinkedList of the expression in Reverse Polish Notation
	 */
	private static LinkedList<String> Parse(String expressionString)
	{
		Stack<String>s_operator = new Stack<String>();
		LinkedList<String>s_output = new LinkedList<String>();

		//Split the string into tokens. 
		String[] expression = expressionString.split("\\s+");

		//Begin the Shunting Yard Alogrithm
		for (String c : expression) 
		{
			//If the token is a number, then add it to the output queue
			if(PublicFunctions.isInteger(c))
			{
				s_output.offer(c);
			}
			//Paranthesis Handler
			else if(c.equals("("))
			{
				s_operator.push(c);
			}
			else if(c.equals(")"))
			{
				while(!s_operator.peek().equals("("))
				{
					s_output.offer(s_operator.pop());
				}
				s_operator.pop(); //pop the left paran off
			}

			//If the token is an operator - o1
			else
			{
				String o1 = c; //For consistency purposes.

				//while there is an operator-o2 at the top of the stack
				while(!s_operator.empty())
				{
					String o2 = s_operator.peek();
					int o1_p = getPrecedence(o1);
					int o2_p = getPrecedence(o2);

					if(( isLeftAssociative(o1) && (o1_p <= o2_p) ) || (o1_p < o2_p))
					{
						s_output.offer(s_operator.pop());
					}
					else
					{
						break;
					}
				}
				s_operator.push(o1);
			}
		}	

		while(!s_operator.empty())
		{
			s_output.offer(s_operator.pop());
		}

		return s_output;
	}

	/**
	 * @deprecated
	 * @param expressionString the expression to be parsed
	 * @return the expression string parsed to Reverse Polish Notation
	 */
	public static String getString(String expressionString)
	{
		return Parse(expressionString).toString();
	}

	/**
	 * 
	 * @param expressionString the expression to be parsed
	 * @return the expression string parsed to Reverse Polish Notation
	 */
	public static LinkedList<String> getList(String expressionString)
	{
		return Parse(expressionString);
	}
}