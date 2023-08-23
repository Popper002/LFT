import java.io.BufferedReader;



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
                    int label_start_while = code.newLabel();
                    int label_true_while  = code.newLabel();
                    int label_false_while = code.newLabel(); //gestisce l'end_while
                    code.emitLabel(label_start_while);
                    bexpr(label_true_while, label_false_while);
                    code.emitLabel(label_true_while);
                    match(')');
                    stat(); //gestisce le istruzioni
                    code.emit(OpCode.GOto, label_start_while);
                    code.emitLabel(label_false_while);
              /* inserire print ,label * flusso while  */
                    break;
            case Tag.COND:
                        match(Tag.COND);
                        match('['); 
                        int label_start_if = code.newLabel();
                        int label_else =code.newLabel();
                        int label_and=code.newLabel();
                        oplist(label_and);
                        match(']'); 

                        /* inserire altre label  */
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
            case '-': //case sub
                match('-');
                expr(label_condition);
                expr(label_condition);
                code.emit(OpCode.isub);
                break;
            case '+': //case sum 
                match('+');
                expr(label_condition);
                expr(label_condition);
                code.emit(OpCode.iadd);
                break;
            case '*': //case moltiplication
                    match('*');
                    expr(label_condition);
                    expr(label_condition);
                    code.emit(OpCode.imul);
            case Tag.ID:
                        Token id = look; 
                        match(Tag.ID);
                        code.emit(OpCode.iload, st.lookupAddress(((Word)id).lexeme));
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
    private void oplist( int label_condition)
    {
        optiterm(label_condition);
        optlistp(label_condition);
    }
    private void optlistp(int label_condition)
    {
            if(look.tag==Tag.OPTION)
            {
                optiterm(label_condition);
                optlistp(label_condition);
            }
    }
    private void optiterm(int label_condition)
    {
        switch(look.tag)
        {
            case Tag.OPTION:
                int true_label_opt = code.newLabel() ; 
                int false_label_opt=  code.newLabel(); 
                match(Tag.OPTION);
                match('(');
                bexpr(true_label_opt , false_label_opt);
                match(')');
                match(Tag.DO);
                code.emitLabel(true_label_opt);
                stat();
                code.emit(OpCode.GOto, label_condition);
                code.emitLabel(false_label_opt);
                break;
        }
    }
    private void bexpr(int label_true , int label_false)
    {
            if(look==Word.eq)
            {
                    match(Tag.RELOP); 
                    expr();
                    expr();
                    code.emit(OpCode.if_icmpeq, label_true);
                    code.emit(OpCode.GOto, label_false);
            }
            if(look==Word.ge)
            {
                match(Tag.RELOP);
                expr();
                expr();
                code.emit(OpCode.if_icmpge, label_true);
                code.emit(OpCode.GOto,label_false); 
            }
            if(look==Word.gt)
            {
                match(Tag.RELOP);
                expr();
                expr();
                code.emit(OpCode.if_icmpgt, label_true);
                code.emit(OpCode.GOto,label_false);  

            }
            if(look==Word.le)
            {
              match(Tag.RELOP);
                expr();
                expr();
                code.emit(OpCode.if_icmple, label_true);
                code.emit(OpCode.GOto,label_false); 
                
            }
            if(look==Word.lt)
            {
                match(Tag.RELOP);
                expr();
                expr();
                code.emit(OpCode.if_icmplt, label_true);
                code.emit(OpCode.GOto,label_false); 
            }
            if(look==Word.ne)
            {
                match(Tag.RELOP);
                expr();
                expr();
                code.emit(OpCode.if_icmpne, label_true);
                code.emit(OpCode.GOto,label_false); 
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
