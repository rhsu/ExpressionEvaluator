

public enum Radix 
{
	BINARY(2),
	DECIMAL(10),
	OCTAL(8),
	HEXIDECIMAL(6);
	
	private final int index;
	
	Radix(int index)
	{
		this.index = index;
	}
	
	public int GetIndex()
	{
		return index;
	}
	
}
