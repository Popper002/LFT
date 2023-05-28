import java.io.*;

public class Translator {
    private Lexer lex;
    private BufferedReader pbr;
    private Token look;
    
    SymbolTable st = new SymbolTable();
    CodeGenerator code = new CodeGenerator();
    int count=0;

    public Translator(Lexer l, BufferedReader br) {
        lex = l;
        pbr = br;
        move();
    }

    void move() { 
	// come in Esercizio 3.1
        look=lex.lexical_scan(pbr);
        System.out.println("TOKEN = " +look);
    }

    void error(String s) { 
	// come in Esercizio 3.1
        throw new Error("NEAR LINE"+lex.line +";"+s);
    }

    void match(int t) {
	// come in Esercizio 3.1
        if(look.tag==t){
            if(look.tag!=Tag.EOF)
            {
                move();
            }
            else{
                error("SYNTAX ERROR\n");
            }
        }
    }

    public void prog() {        
	// ... completare ...
    int lnext_prog = code.newLabel();
        statlist();
        code.emitLabel(lnext_prog);
        match(Tag.EOF);
        try {
        	code.toJasmin();
        }
        catch(java.io.IOException e) {
        	System.out.println("IO error\n");
        };
	// ... completare ...
    }

    public void stat( /* completare */ ) {
        switch(look.tag) {
	// ... completare ...
            case Tag.READ:
                match(Tag.READ);
                match('[');
	            idlist(/* completare */);
                match(']');
                code.emit(OpCode.invokestatic,0);
                break;
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
                        }
                        else{
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
                                error("ERROR\n");
                                break;
                        
                 
	// ... completare ...
        }
     }

    private void idlist(/* completare */) {
        switch(look.tag) {
	    case Tag.ID:
        	int id_addr = st.lookupAddress(((Word)look).lexeme);
                if (id_addr==-1) {
                    id_addr = count;
                    st.insert(((Word)look).lexeme,count++);
                }
                match(Tag.ID);
	// ... completare ...
    	}
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
    private void expr( /* completare */ ) {
        switch(look.tag) {
	// ... completare ...
            case '-':
                match('-');
                expr();
                expr();
                code.emit(OpCode.isub);
                break;
	// ... completare ...
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
    private void statlist()
    {
        stat();
        statlist();
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
        if(look.tag==Tag.RELOP)
        {
            match(Tag.RELOP);
            expr();
            expr();
        }

    }
    private void statlistp()
    {
        switch(look.tag)
        {
            case ';':
                    match(';');
                    stat();
                    statlist();
                    break;
            case Tag.EOF,'}':
            break;
            default:
                error("ERROR IN TRANSLATOR_STATLIST\n");
        }
    }
  
    
// ... completare ...
}
