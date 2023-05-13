package LEXER.Esercizi.Es_2_1;



public class NumberTok extends Token {
	public String Lexema = "";
	public NumberTok(int t , String s)
	{
		super(t);
		Lexema=s;
	 }
   
  @Override
	 public String toString()
	 {
		return "<"+tag+","+Lexema+">";
	 }
	} 
	


