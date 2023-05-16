package LEXER;

import LEXER.Esercizi.Es_2_1.Tag;
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
            if(Character.isLetter(peek))
            {      
                String src = new String(); 
                 while (Character.isLetter(peek) || peek=='_' ||Character.isDigit(peek))
                  {
                    src = src + peek ; 
                    readch(br);
                   }
             if(src.compareTo("conditional")==0)
             {
                return Word.conditional;
             }
             if(src.compareTo("else")==0)
             {
                return Word.elsetok; 
             }
             if(src.compareTo("while")==0)
             {
                return Word.whiletok; 
             }
             if(src.compareTo("begin")==0)
             {
                return Word.begin; 
             }
             if(src.compareTo("read")==0)
             {
                return Word.read;
             }
             if(src.compareTo("print")==0)
             {
                return Word.print;        
             }
             if(src.compareTo("to")==0)
             {
                return Word.to;
             }
             if(src.compareTo("end")==0)
             {
                return Word.end;
             }
             if(src.compareTo("option")==0)
             {
                return Word.option;
             }
             if(src.compareTo("assign")==0)
             {
                return Word.assign;
             }
             if(src.compareTo("do")==0)
             {
                return Word.dotok;
             }
             return new Word(Tag.ID, src);
            
             
             

	// ... gestire il caso degli identificatori e delle parole chiave //
                
          
                    } else if (Character.isDigit(peek)) 

                    {
                        String src_num = new String(); 
                        while(Character.isDigit(peek))
                        {
                            src_num = src_num + peek ; 
                            readch(br);
                        }
                        return new NumberTok(Tag.NUM,src_num); 
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
        String path = "/Users/popper/Documents/Uni/secondo anno /LFT/GIT/LFT/LFT-Lab/LEXER/LEXER/input.txt"; // il percorso del file da leggere
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
