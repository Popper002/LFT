public class NumberTok extends Token {
	// ... completare ...
	public String lexeme =""; 
	public NumberTok(int t , String s )
	{
		super(t); 
		lexeme=s;
	
	}
	public String ToString()
	{
		return "<"+tag+","+lexeme+">";
	}


}
