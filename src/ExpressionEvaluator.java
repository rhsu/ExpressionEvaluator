import java.math.BigInteger;
import java.util.LinkedList;

public class ExpressionEvaluator 
{
	//<editor-fold defaultstate="collapsed" desc="Private Variables">
	
	/**
	 * A linked list representing the expression to be evaluated
	 */
	private final LinkedList<String> expression;
	
	/**
	 * The stack for computing the expression
	 */
	private final LinkedList<String> stack;
	
	/**
	 * The radix to use
	 */
	private Radix radix;
	
	/**
	 * The format of the expression: Either BigInteger, BigDecimal, Integer, or Decimal
	 */
	private ExpressionFormat format;
	
	//</editor-fold>
	
	/**
	 * Initializes the expression evaluator with default settings (Integer and Decimal)
	 */
	public ExpressionEvaluator()
	{
		radix = Radix.DECIMAL;
		format = ExpressionFormat.INTEGER;
		expression = new LinkedList<>();
		stack = new LinkedList<>(); 
	}
	
	/**
	 * Test constructor
	 * @param radix		set a radix
	 * @param format	set a format
	 */
	public ExpressionEvaluator(Radix radix, ExpressionFormat format)
	{
		this.radix = radix;
		this.format = format;
		expression = new LinkedList<>();
		stack = new LinkedList<>(); 
	}
	
	/**
	 * Sets the expression to be evaluated
	 * @param expressionString		the expression to set
	 */
	public void SetExpression(String expressionString)
	{
		ShuntingYard.getList(expressionString);
	}
    
	/**
	 * @param radix		The radix to be set
	 */
	public void SetRadix(Radix radix)
	{
		this.radix = radix;
	}
	
	/**
	 * @param format	The format to be set
	 */
	public void SetFormat(ExpressionFormat format)
	{
		this.format = format;
	}
	
	/**
	 * clears the expression
	 */
	public void ClearExpression()
	{
		expression.clear();
	}
    	
	private String GetValue(String a, String b, String operator) throws Exception
	{		
		switch(format)
		{
			case INTEGER:
			{
				int result = 0;
				
				if(operator.equals("+"))
				{
					result = Integer.parseInt(b, radix.GetIndex()) + Integer.parseInt(a, radix.GetIndex());
				}
				else if (operator.equals("-"))
				{
					result = Integer.parseInt(b, radix.GetIndex()) - Integer.parseInt(a, radix.GetIndex());
				}
				else if (operator.equals("*"))
				{					
					result = Integer.parseInt(b, radix.GetIndex()) * Integer.parseInt(a, radix.GetIndex());
				}
				else if (operator.equals("/"))
				{
					try
					{
					result = Integer.parseInt(b, radix.GetIndex()) / Integer.parseInt(a, radix.GetIndex());
					}
					catch(ArithmeticException divideByZeroException)
					{
						throw divideByZeroException;
					}
				}
				
				return Integer.toString(result);
			}
			
			case BIGINTEGER:
			{
				BigInteger result = new BigInteger(b, radix.GetIndex());
				
				if(operator.equals("+"))
				{
					result = result.add(new BigInteger(a, radix.GetIndex()));
				}
				else if (operator.equals("-"))
				{
					result = result.subtract(new BigInteger(a, radix.GetIndex()));
				}
				else if (operator.equals("*"))
				{					
					result = result.multiply(new BigInteger(a, radix.GetIndex()));
				}
				else if (operator.equals("/"))
				{
					result = result.divide(new BigInteger(a, radix.GetIndex()));
				}
				
				return result.toString();
			}
			default:
			{
				return "SHOULD NOT GET HERE";
			}
			//case BIGDECIMAL:
			//case DECIMAL:
		}

	}
		
	/**
	 * Evaluates the expression
	 * @return The result of the evaluated expression as a string
	 * @throws Exception	a number overflow expression or a divide by zero expression
	 */
	public String evaluate() throws Exception
	{
		while(!expression.isEmpty())
		{
			String top = expression.pop();

			if(PublicFunctions.isInteger(top))
			{
				stack.push(top);
			}

			else
			{
				String val = "0";
				String a;
				String b;

				try
				{
					a = stack.pop();
					b = stack.pop();
					val = GetValue(a,b,top);
				}
				catch(Exception e)
				{
					throw e;
				}
				stack.push(val);
			}			
		}
		return stack.pop();
	}
	
	
	public static void main(String[] args)
	{
		System.out.println("hello world");
	}
}
