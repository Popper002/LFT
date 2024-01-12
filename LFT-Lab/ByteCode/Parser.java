
import java.io.*;
public class Parser {
    private Lexer lex;
    private BufferedReader pbr;
    private Token look;

    public Parser(Lexer l, BufferedReader br) {
        lex = l;
        pbr = br;
        move();
    }

    void move() {
        look = lex.lexical_scan(pbr);
        System.out.println("token = " + look);
    }

    void error(String s) {
	throw new Error("near line " + lex.line + ": " + s);
    }

    void match(int t) {
	if (look.tag == t) {
	    if (look.tag != Tag.EOF) move();
	} else error("syntax error, expected " + (char)t + " - found " + (char)look.tag);
    }

    private void expr() {
    switch(look.tag)
    {
        case '+':
            match('+');
            exprlist();
            break;
        case '-':
            match('-');
            expr();
            expr();
            break;
        case '*':
            match('*');
            exprlist();
            break;
        case '/':
            match('/');
            expr();
            expr();
            break;
        case Tag.NUM:
            match(Tag.NUM); 
            break; 
        case Tag.ID:
            match(Tag.ID);
            break;
            default:
                break;
       }
    }
        private void prog()
        {
            statlist();
            match(Tag.EOF);
        }
        private void statlist()
        {
            stat();
            statlistp();
        }
        private void statlistp()
        {
            switch(look.tag)
            {
                case ';':
                    match(';');
                    stat();
                    statlistp();
                    break;
                case Tag.EOF, '}':
                    break;
                default:
                    error(" PARSER : ERROR IN STATLISTP\n");

            }
        }
        private void stat()
        {
            switch(look.tag)
            {
                case Tag.ASSIGN:
                    match(Tag.ASSIGN);
                    expr();
                    match(Tag.TO);
                    idlist();
                    break;
                case Tag.PRINT:
                    match(Tag.PRINT); 
                    match('[');
                    exprlist(); 
                    match(']');
                    break;
                case Tag.READ:
                    match(Tag.READ);
                    match('[');
                    idlist();
                    match(']');
                    break;
                case Tag.WHILE:
                    match(Tag.WHILE);
                    match('(');
                    bexpr();
                    match(')');
                    stat();
                    break;
                case Tag.COND:
                    match(Tag.COND);
                    match('[');
                    oplist();
                    match(']');
                    if(look.tag==Tag.END)
                    {
                        match(Tag.END);
                        break;
                    }else{
                        match(Tag.ELSE);
                        stat();
                        match(Tag.END);
                        break;
                    }
                case '{':
                    match('{');
                    statlist();
                    match('}');
                    break;
                  
                default:
                    error("PARSER : ERROR\n"); 
                    break;
                }
            }
        
        private void idlist()
        {
            match(Tag.ID);
            idlistp();
        }
        private void idlistp()
        {
            switch(look.tag)
            {
                case ',':
                    match(',');
                    match(Tag.ID);
                    idlistp();
                    break;
                default:
                    break;
                
            }
        }
        private void oplist()
        {
            optiterm();
            optlistp(); 

        }
        private void optlistp()
        {
            if(look.tag==Tag.OPTION)
            {
            optiterm();
            optlistp();
           
            }
        }
        private void optiterm()
        {
            switch(look.tag)
            {
            case Tag.OPTION:
             match(Tag.OPTION);
             match('(');
             bexpr();
             match(')');
             match(Tag.DO);
             stat();
                break;
            }
        }
        private void bexpr()
        {
            if(look.tag==Tag.RELOP){
             match(Tag.RELOP);
             expr();
             expr();    
            }
        }
    
    private void exprlist()
    {
        expr();
        exprlistp();
    }
    private void exprlistp()
    {
        switch(look.tag)
        {
            case ',':
                match(',');
                expr();
                exprlistp();
                break;
            default:
                break;
        }
    }

    public static void main(String[] args) {
        Lexer lex = new Lexer();
        String path = "/Users/popper/Documents/Uni/secondo anno /LFT/GIT/LFT/LFT-Lab/Parser/3_2/input.txt"; // il percorso del file da leggere
        try {
            BufferedReader br = new BufferedReader(new FileReader(path));
            Parser parser = new Parser(lex, br);
            parser.prog();
            System.out.println("Input OK");
            br.close();
        } catch (IOException e) {e.printStackTrace();}
    }
}