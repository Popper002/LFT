import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;



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
        throw new Error("TRASLATOR : NEAR LINE"+lex.line +";"+s);
    }

    void match(int t) {
	// come in Esercizio 3.1
        if(look.tag==t){
            if(look.tag!=Tag.EOF)
            {
                move();
            }
            else{
                error("TRASLATOR: SYNTAX ERROR, searching for " + look.tag + ", found " + t);
            }
        }
    }

    public void prog() {         /*DA RIVEDERE  */
	// ... completare ...
    int lnext_prog = code.newLabel();
        statlist(lnext_prog);
        code.emitLabel(lnext_prog);
       // match(Tag.EOF);
        try {
        	code.toJasmin();
        }
        catch(java.io.IOException e) {
        	System.out.println("TRANSLATOR : IO error\n");
        };
	// ... completare ...
    }

    public void stat(int label_condition ) {
        switch(look.tag) {
	// ... completare ...
            case Tag.READ:
                match(Tag.READ);
                match('[');
	            idlist(0);
                match(']');
                break;
            case Tag.ASSIGN:
                int label_start_assign  = code.newLabel(); 
                match(Tag.ASSIGN); 
                code.emitLabel(label_start_assign);
                expr(label_condition); 
                match(Tag.TO);
                idlist(1);
                break;
            case Tag.PRINT:
                    match(Tag.PRINT);
                    match('[');
                    exprlist(label_condition);
                    code.emit(OpCode.invokestatic,1);
                    match(']');
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
                    stat(label_condition); //gestisce le istruzioni
                    code.emit(OpCode.GOto, label_start_while);
                    code.emitLabel(label_false_while);
                    break;
            case Tag.COND:
                        match(Tag.COND);
                        match('['); 
                        int label_else =code.newLabel();
                        int label_end=code.newLabel();
                        oplist(label_end);
                        match(']'); 
                        ll(label_else,label_end); 
                        break;

                        /* inserire altre label  */
            case '{':
                            match('{'); 
                            statlist(label_condition);
                            match('}'); 
                            break;
                        default:
                               
                                break;
            
                 
	// ... completare ...
        }
     }
     public void ll( int label_else,int label_end)
     {
        switch(look.tag)
        {
            case Tag.ELSE:
                match(Tag.ELSE);
               code.emitLabel(label_else);
                stat(label_else);
            case Tag.END:
                match(Tag.END);
                code.emitLabel(label_end);
                break;
        }
     }
    private void idlist(int label_condition) {
        switch(look.tag) {
	    case Tag.ID:
        	int id_addr = st.lookupAddress(((Word)look).lexeme);
                if (id_addr==-1) {
                    id_addr = count;
                    st.insert(((Word)look).lexeme,count++);
                }
                match(Tag.ID);
            switch(label_condition)
            {
                case 0 : 
                    code.emit(OpCode.invokestatic,0);
                    code.emit(OpCode.istore , id_addr); 
                    break;
                case 1 : 
                    code. emit(OpCode.istore, id_addr);
                    break  ;    
                default:
                    break;
            }

    	}
    }
    private void idlistp(int code_emit)
    {
        switch(look.tag)
        {
           
         case ',':
                match(',');
                switch(look.tag) {
	            case Tag.ID:
                    int id_addr = st.lookupAddress(((Word)look).lexeme);
                    if (id_addr==-1) {
                        id_addr = count;
                        st.insert(((Word)look).lexeme,count++);
                    }
                    match(Tag.ID);           
                    switch(code_emit)
                    {
                        case 0 : 
                            code.emit(OpCode.invokestatic,0);
                            code.emit(OpCode.istore, id_addr);
                            break;
                        case 1: 
                            code.emit(OpCode.istore, id_addr);
                            break;
                        default: 
                                break;
                    
                    }
                }      
            break;

        case Tag.EOF,'}',';':
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
                match('(');
                exprlist(label_condition);
                match(')');
                code.emit(OpCode.iadd);
                break;
            case '*': //case moltiplication
                    match('*');
                    match('(');
                    exprlist(label_condition);
                    match(')');
                    code.emit(OpCode.imul);
                    break;
            case '/': 
                    match('/');
                    expr(label_condition);
                    expr(label_condition);
                    code.emit(OpCode.idiv);
                    break;
            case Tag.ID:
                        Token id = look; 
                        match(Tag.ID);
                        code.emit(OpCode.iload, st.lookupAddress(((Word)id).lexeme));/*cerca varibile in tabella ,se non è presente returna errore */
                        break;
            case Tag.NUM:
                     code.emit(OpCode.ldc, Lexer.getNUM());
                     match(Tag.NUM);
                     break;              
            default: 
                    break;            // ... completare ...
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
            case Tag.EOF,'}',';':
                    break;
            default:
                    break;
        }
    }
    private void statlist(int label_condition)
    {
        stat(label_condition);
        statlistp(label_condition);
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
                stat(label_condition);
                code.emit(OpCode.GOto, label_condition);
                code.emitLabel(false_label_opt);
                break;
            default:
                break;  
        }
    }
    private void bexpr(int label_true , int label_false)
    {
            if(look==Word.eq)
            {
                match(Tag.RELOP); 
                expr(label_true);
                expr(label_false);
                code.emit(OpCode.if_icmpeq, label_true);
                code.emit(OpCode.GOto, label_false);
            }
            if(look==Word.ge)
            {
                match(Tag.RELOP);
                expr(label_true);
                expr(label_false);
                code.emit(OpCode.if_icmpge, label_true);
                code.emit(OpCode.GOto,label_false); 
            }
            if(look==Word.gt)
            {
                match(Tag.RELOP);
                expr(label_true);
                expr(label_false);
                code.emit(OpCode.if_icmpgt, label_true);
                code.emit(OpCode.GOto,label_false);  

            }
            if(look==Word.le)
            {
              match(Tag.RELOP);
                expr(label_true);
                expr(label_false);
                code.emit(OpCode.if_icmple, label_true);
                code.emit(OpCode.GOto,label_false); 
                
            }
            if(look==Word.lt)
            {
                match(Tag.RELOP);
                expr(label_true);
                expr(label_false);
                code.emit(OpCode.if_icmplt, label_true);
                code.emit(OpCode.GOto,label_false); 
            }
            if(look==Word.ne)
            {
                match(Tag.RELOP);
                expr(label_true);
                expr(label_false);
                code.emit(OpCode.if_icmpne, label_true);
                code.emit(OpCode.GOto,label_false); 
            }
            
    }
    private void statlistp(int label_condition)
    {
        switch(look.tag)
        {
            case ';':
                    match(';');
                    stat(label_condition);
                    statlist(label_condition);
                    break;
            case Tag.EOF,'}':
            break;

            default:
                    break;
        }
    }
  
    
 public static void main(String[] args) {
                Lexer lex = new Lexer();
                String path = "/Users/popper/Documents/Uni/secondo anno /LFT/GIT/LFT/LFT-Lab/ByteCode/inqput.lft"; // il percorso del file da leggere
                try {
                    BufferedReader br = new BufferedReader(new FileReader(path));
                    Token tok;
                    Translator translator = new Translator(lex, br);
                    translator.prog();
                    do {
                        tok = lex.lexical_scan(br);
                        System.out.println("Scan: " + tok);
                    } while (tok.tag != Tag.EOF);
                    br.close();
                } catch (IOException e) {e.printStackTrace();}    
            }
}



/* BUG  DA FIXARE : 
 *  1) LA GRAMMATICA NON PREVEDE LA SEGUENTE NOTAZIONE PER PRINT E READ 
 *  print(1,(a,1)); ma --> print[1,[a,1]]; uguale per le READ 
 *  2) VEDENDO L'ESEMPIO DI SPROSTRON LUI NELL'OUTPUT SU FILE HA CHE AD OGNI LINEA UN GOTO ALLA LABEL SUCCESSIVA 
 *  OVVERO OGNI ; LANCIA UN GOTO .
 */