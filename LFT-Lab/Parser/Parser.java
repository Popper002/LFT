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
	} else error("syntax error");
    }

    public void start() {
	// ... completare ...
	expr();
	match(Tag.EOF);
	// ... completare ...
    }

    private void expr() {
	// ... completare ...
    term();
    exprp();
    }

    private void exprp() {
	switch (look.tag) {
        case '+':
            match('+');
            term();
            exprp();
            break;
        case '-':
            match('-');
            term();
            exprp();
            break;
        case ')', Tag.EOF:
            break;
        default:
                expr();
	    }
    }

    private void term() {
        // ... completare ...
        fact();
        termp();
        
    }

    private void termp() {
        // ... completare ...
        switch(look.tag)
        {
            case '*':
                match('*'); 
                fact();
                termp();
                break;
            case '/': 
                match('/'); 
                fact();
                termp();
                break;
            case '+', '-', ')', Tag.EOF:
                break;
            default:
                expr();
        }
    }
    private void fact() {
        // ... completare ...
            switch(look.tag)
            {
                case '(':
                    match('(');
                    expr();
                    match(')');
                    break;
                case Tag.NUM: 
                    match(Tag.NUM);
                    break;
                default:
                    break;
            }
    }
		
    public static void main(String[] args) {
        Lexer lex = new Lexer();
        String path = "/Users/popper/Documents/Uni/secondo anno /LFT/GIT/LFT/LFT-Lab/Parser/input.txt"; // il percorso del file da leggere
        try {
            BufferedReader br = new BufferedReader(new FileReader(path));
            Parser parser = new Parser(lex, br);
            parser.start();
            System.out.println("Input OK");
            br.close();
        } catch (IOException e) {e.printStackTrace();}
    }
}