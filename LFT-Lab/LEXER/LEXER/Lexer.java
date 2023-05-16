package LEXER;

import java.io.*;

public class Lexer {

    public static int line = 1;
    private char peek = ' ';
    
    private void readch(BufferedReader br) {
        try {
            peek = (char) br.read();
        } catch (IOException exc) {
            peek = (char) -1; // ERROR
        }
    }

    public Token lexical_scan(BufferedReader br) {
        while (peek == ' ' || peek == '\t' || peek == '\n'  || peek == '\r') {
            if (peek == '\n') line++;
            readch(br);
        }
        
        switch (peek) {
            case '!':
               readch(br);
                peek = ' ';
                return Token.not;

            case '(':
                    readch(br);
                        peek=' '; 
                        return Token.lpt;
            case ')':
                    readch(br);
                    peek=' '; 
                    return Token.rpt;
        
           case '[':
                    readch(br);
                    peek=' ';
                    return Token.lpg;
            case ']':
                readch(br);
                peek=' '; 
                return Token.rpq;
            case '{':
                    readch(br);
                    peek=' '; 
                    return Token.lpg; 
            case '}':
                    readch(br);
                    peek=' '; 
                    return Token.rpg;
            case '+':
                readch(br);
                peek=' '; 
                return Token.plus; 
            case '-':
                readch(br);
                peek=' '; 
                return Token.minus;
            case '*':
                    readch(br);
                    peek=' '; 
                    return Token.mult;
            case '/':
                    readch(br);
                    peek=' '; 
                    return Token.div; 
            case ';':
                    readch(br);
                    peek = ' '; 
                    return Token.semicolon;
            case ',':
                    readch(br);
                    peek= ' '; 
                    return Token.comma;


	// ... gestire i casi di ( ) [ ] { } + - * / ; , ... //
	
            case '&':
                readch(br);
                if (peek == '&') {
                    peek = ' ';
                    return Word.and;
                } else {
                    System.err.println("Erroneous character"
                            + " after & : "  + peek );
                    return null;
                }
            case '<':
                    readch(br);
                    if(peek=='='){
                    peek=' '; 
                    return Word.le;
                    }
                    else if(peek=='>')
                    {
                        peek =' '; 
                        return Word.ne;
                    }
                    else{
                        return Word.lt;
                    }
            case '>':
                    readch(br);
                    if(peek=='=')
                    {
                        peek=' ';
                        return Word.ge;
                    }
                     else if(peek=='<')
                    {
                        peek=' '; 
                        return Word.ne;
                    }
                    else{
                        return Word.gt;
                    }
            case '=':
                    readch(br);
                    if(peek=='=')
                    {
                        peek=' '; 
                        return Word.eq;
                    }        

                case '|':
                    readch(br);
                    if(peek=='|')
                    {
                        peek=' ';
                        return Word.or; 
                    }
                    
             
	// ... gestire i casi di || < > <= >= == <> ... //
          

            case (char)-1:
                return new Token(Tag.EOF);

            default:
                if (Character.isLetter(peek)) {

	// ... gestire il caso degli identificatori e delle parole chiave //

                } else if (Character.isDigit(peek)) {

	// ... gestire il caso dei numeri ... //

                } else {
                        System.err.println("Erroneous character: " 
                                + peek );
                        return null;
                }
         }
    }
		
    public static void main(String[] args) {
        Lexer lex = new Lexer();
        String path = "...path..."; // il percorso del file da leggere
        try {
            BufferedReader br = new BufferedReader(new FileReader(path));
            Token tok;
            do {
                tok = lex.lexical_scan(br);
                System.out.println("Scan: " + tok);
            } while (tok.tag != Tag.EOF);
            br.close();
        } catch (IOException e) {e.printStackTrace();}    
    }

}
