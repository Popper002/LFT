import java.io.*;

public class Valutatore {
    private Lexer lex;
    private BufferedReader pbr;
    private Token look;

    public Valutatore(Lexer l, BufferedReader br) { 
	    lex = l; 
	    pbr = br;
	    move(); 
    }
   
    void move() { 
	// come in Esercizio 3.1
    look=lex.lexical_scan(pbr);
    System.out.println("TOKEN = "+look);
    }

    void error(String s) { 
	// come in Esercizio 3.1
        throw new Error("LINE " +lex.line +":" +s);

    }

    void match(int t) {
	// come in Esercizio 3.1
            if(look.tag==t)
             {
                if(look.tag !=Tag.EOF)
                {
                    move();
                }else{
                    error("ERROR DURING MATCH : " +look.tag+"," +t);
                }

            }
    }

    public void start() { 
	int expr_val;
            switch(look.tag)
            {
                case Tag.NUM ,'(':
                    expr_val = expr();
                    System.out.println(expr_val);
                    break;
                case Tag.EOF:
                        match(Tag.EOF);
                        break;
                default:
                    error("START FUNCTION ERROR\n");
            }
    	// ... completare ...

 

	// ... completare ...
    }

    private int expr() { 
	    int term_val=0, exprp_val=0,expr_val=0;
        term_val=term();
        exprp_val=exprp(term_val);
        expr_val=exprp_val;
            return exprp_val;

    }
	// ... completare ...
    private int term()
    {
        int term_val;
    	term_val = termp(fact());
            return term_val;
    }
   
    private int exprp(int exprp_i) {
	int term_val, exprp_val=0;
	switch (look.tag) {
	case '+':
            match('+');
            term_val = term();
            exprp_val = exprp(exprp_i + term_val);
            return exprp_val;
            
    case '-':
            match('-');
            term_val=term();
            exprp_val=exprp(exprp_i - term_val);
            return exprp_val;
    default:
            exprp_val = exprp_i;
            return exprp_val;
    	
	 }
    }

    private int termp(int termp_i) { 
	// ... completare ...
                int fact_val; 
                int termp_val =0; 
                switch(look.tag)
                {
                    case '*': 
                        match('*');
                        fact_val = fact(); 
                        termp_val = termp(termp_i * fact_val); 
                        return termp_val;
                    case '/':
                            match('/');
                            fact_val =fact();
                            termp_val=termp(termp_i / fact_val); 
                            return termp_val;
                    default:
                        termp_val = termp_i;
                        return termp_val; 

                }
    }
    
    private int fact() { 
	// ... completare ...
            int fact_val;
            int expr_val = 0;
            switch(look.tag)
            {
                case '(':
                        match('(');
                        expr_val = expr(); 
                        if(look.tag ==')')
                        {
                            match(')');
                            fact_val = expr_val;
                            break;
                        }else{
                            error("SYNTAX ERROR\n"); 
                        }
                case  Tag.NUM:
                            fact_val = Lexer.get_NUM();
                            match(Tag.NUM);
                            break;
                default:
                            fact_val = 0; 
                            break;

            }
            return fact_val;
    }

    public static void main(String[] args) {
        Lexer lex = new Lexer();
        String path = "/Users/popper/Documents/Uni/secondo anno /LFT/GIT/LFT/LFT-Lab/Valutatore/input.txt"; // il percorso del file da leggere
        try {
            BufferedReader br = new BufferedReader(new FileReader(path));
            Valutatore valutatore = new Valutatore(lex, br);
            valutatore.start();
            br.close();
        } catch (IOException e) {e.printStackTrace();}
    }
}
