import java.io.BufferedReader;



public class Translator {
    private Lexer lex;
    private BufferedReader pbr;
    private Token look;
    int  label_condition =0 ; // la inizializzo a true 
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
                expr(label_condition); 
                match(Tag.TO);
                idlist();
                break;
            case Tag.PRINT:
                    match(Tag.PRINT);
                    match('[');
                    exprlist(label_condition);
                    match(']');
                    code.emit(OpCode.invokestatic,1);
                    break;
            case Tag.WHILE:
                    match(Tag.WHILE);
                    match('(');
                    bexpr(label_condition);
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
                            code.emitLabel(label_condition);
                            match(Tag.END);
                            break;
                        }
            case '{':
                            match('{'); 
                            statlist();
                            match('}'); 
                            break;
                        default:
                                error("ERROR STAT_FUNCTION\n");
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
    private void expr( int label_condition ) {
        switch(look.tag) {
	// ... completare ...
            case '-':
                match('-');
                expr(label_condition);
                expr(label_condition);
                code.emit(OpCode.isub);
                break;
	// ... completare ...
        }
    }
    private void exprlist(int label_condition)
    {
            expr(label_condition);
            exprlistp(label_condition);
    }
    private void exprlistp(int label_condition)
    {
        switch(look.tag)
        {
            case ',':
                    match(',');
                    expr(label_condition);
                    exprlistp(label_condition);
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
                bexpr(label_condition);
                match(')');
                match(Tag.DO);
                stat();
                break;
        }
    }
    private void bexpr(int label_condition)
    {
            if(look==Word.eq)
            {
                    match(Tag.RELOP); 
                    expr(label_condition);
                    expr(label_condition);
                    code.emit(OpCode.if_icmpeq, label_condition);
                    label_condition=1;
                    code.emit(OpCode.GOto, label_condition);
            }
            if(look==Word.ge)
            {
                match(Tag.RELOP);
                expr(label_condition);
                expr(label_condition);
                code.emit(OpCode.if_icmpge, label_condition);
                label_condition=1;

                code.emit(OpCode.GOto,label_condition); 
            }
            if(look==Word.gt)
            {
                match(Tag.RELOP);
                expr(label_condition);
                expr(label_condition);
                code.emit(OpCode.if_icmpgt, label_condition);
                label_condition=1;

                code.emit(OpCode.GOto, label_condition); 

            }
            if(look==Word.le)
            {
                match(Tag.RELOP);
                expr(label_condition);
                expr(label_condition);
                code.emit(OpCode.if_icmple, label_condition);
                label_condition=1;

                code.emit(OpCode.GOto ,label_condition);
                
            }
            if(look==Word.lt)
            {
                match(Tag.RELOP);
                expr(label_condition);
                expr(label_condition);
                code.emit(OpCode.if_icmplt, label_condition);
                label_condition=1;

                code.emit(OpCode.GOto, label_condition);
            }
            if(look==Word.ne)
            {
                match(Tag.RELOP);
                expr(label_condition);
                expr(label_condition);
                code.emit(OpCode.if_icmpne, label_condition);
                label_condition=1;//false 
                code.emit(OpCode.GOto, label_condition);
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
