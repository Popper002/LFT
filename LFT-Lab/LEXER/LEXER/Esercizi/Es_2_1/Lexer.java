package LEXER.Esercizi.Es_2_1;

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
                peek = ' ';
                return Token.not;

	// ... gestire i casi di ( ) { } + - * / ; , ... //
            case('('):
              peek=' ';
              return Token.lpt; 
              
            case(')'):
                peek=' '; 
                return Token.rpt;
            case('{'):
                peek=' '; 
                    return Token.lpg;
            case('}'):
                peek=' '; 
                return Token.lpg;
            case('+'):
                    peek=' '; 
                    return Token.plus;
            case('-'):
                peek=' '; 
                return Token.minus;
            case('*'):
                peek=' ';
                return Token.mult;
            case('/'):
                peek=' '; 
                return Token.div;
            case(';'):
                peek=' '; 
                return Token.semicolon;
            case(','):
                peek=' ';
                return Token.comma;
               
            
	
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
            case '|': 
                readch(br);
                if (peek == '|'){
                    peek = ' ';
                    return Word.or;
                } else {
                    System.err.println("Erroneous character"
                            + " after | : "  + peek );
                    return null;
                }
            case '<': 
                readch(br); 
                if(peek=='<'){ //caso <==
                    peek='=';
                    return Word.le; 
                }
                else if (peek =='>') //caso <>
                {
                    return Word.ne;
                }
                else{   //caso < 
                    peek=' ';
                    return Word.gt;
                  }
                
            case '>':
                readch(br); 
                if(peek =='='){
                    peek = ' ';
                    return Word.ge;
                } else {
                    peek=' ';
                    return Word.gt;
                }
            
                               

            
             

	// ... gestire i casi di || < > <= >= == <> ... //
          
            case (char)-1:
                return new Token(Tag.EOF); 
                if (Character.isLetter(peek)) {
                   /*  
                    switch()
                    {
                        case "if":
                            return Word.iftok;
                        case "else":
                            return Word.elsetok;
                        case "while":
                            return Word.whiletok;
                        case "begin":
                            return Word.begin;
                        case "end":
                            return Word.end;
                        case "print":
                            return Word.print;
                        case "read":
                            return Word.read;
                        case "to":  
                            return Word.to;
                        case "assign":
                            return Word.assign;
                            */
                        default:
                            StringBuilder str = new StringBuilder(peek);
                            boolean letter = false; 
                            boolean number = false;
                            while(Character.isLetter(peek)||Character.isDigit(peek))
                            {
                                 
                                readch(br);
                                if(Character.isLetter(peek))
                                {
                                    letter=true;
                                    number=false; 
                                }
                                if(Character.isDigit(peek))
                                {
                                    letter =false;
                                    number = true; 
                                }
                                //confronto il contenuto in stringa con gli effettivi tag 
                                if(str.equals("print"))
                                {
                                    return Word.print;
                                }
                                if(str.equals("if"))
                                {
                                    return Word.iftok;
                                }
                                if(str.equals("else"))
                                {
                                    return Word.elsetok;
                                }
                                if(str.equals("read"))
                                {
                                    return Word.read;
                                }
                                if(str.equals("while"))
                                {
                                    return Word.whiletok; 
                                }

                            } 


                            return new Word(Tag.ID,str.toString());
                    }
                   

	// ... gestire il caso degli identificatori e delle parole chiave //

                } else if (Character.isDigit(peek)) {
            
                   str_= str_+peek; 
                     readch(br);
                     while(Character.isLetter(peek) || Character.isDigit(peek)){
                        str_+= peek;
                        readch(br);
                    }
                    return new Tag();

	// ... gestire il caso dei numeri ... //

                } else {
                        System.err.println("Erroneous character: " 
                                + peek );
                        return  new NumberTok(Tag.NUM, Integer.parseInt(str_));
                }
         }
    }    

		
    public static void main(String[] args) {
        Lexer lex = new Lexer();
        String path = "./Esercizi/Input.txt"; // il percorso del file da leggere
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