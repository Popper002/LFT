
/* Esercizio 1.2.
                Progettare e implementare un DFA che riconosca il linguaggio degli identificatori in un linguaggio in stile Java: 
                un identificatore`e una sequenza non vuota di lettere, numeri, ed ilsimbolo di “underscore”_che non comincia con un numero e
                che non pu`o essere composto solo dal simbolo "_" 
                Compilare e testare il suo funzionamento su un insieme significativo di esempi.
                Esempi di stringhe accettate:“x”, “flag1”,  “x2y2”,  “x1”, 
                “lftlab”,  “temp”,  “x1y2”,“x”, “5”.
                    
                  CORRECT TEST:

                  •deve accettare le stringhe:   “x ”, “flag1 ”, “x2y2 ”, “x_1 ”, “lft_lab ”,  “ temp_”, “x_1_ y_2_”, “x__”,  “_ _ _ Pippo
                 

                  NOT CORRECT:
                    •ma non deve accettare: “5”,  “221B”, “123”, “9_to_5”,  “_ _ _” , “_”, “_ _”

*/


// ESERCIZIO FUNZIONANTE

public class Es1_2 {

    public static boolean scan(String s) {

        int state = 0;
        int i = 0;
        while (state >= 0 && i < s.length()) {
            final char ch = s.charAt(i++);

            switch (state) {

                case -1:// caso non accettato
                    if (Character.isDigit(ch))// se è un numero
                        state = -1;// Non cambia stato
                    break;

                case 0:

                    if (ch == '_')
                        state = 1;
                    else if (Character.isLetter(ch))
                        state = 1;
                    else if (Character.isDigit(ch))
                        state = -1;
                    break;

                case 1:
                    if (Character.isDigit(ch))
                        state = 1;
                    else if (Character.isLetter(ch))
                        state = 1;
                    else if (ch == '_')
                        state = 1;
                    else if (ch == '_' && Character.isDigit(ch))
                        state = 1;

                    break;

            }
        }

        return state == 1;
    }

    public static void main(String[] args) {

        // Test per verificarne la funzionalità

        // Test aggiuntivi per la vertifica del dfa
        System.out.println("----------------TEST-AGGIUNTIVI------------------------");
        System.out.println(scan("____1") ? "OK" : "NOPE");// atteso-->OK
        System.out.println(scan("__A") ? "OK" : "NOPE"); // atteso--> Ok
        System.out.println(scan("A__1") ? "OK" : "NOPE"); // atteso--> ok
        System.out.println(scan("99") ? "OK" : "NOPE");// atteso-->nope
        // Test consegna
        System.out.println("----------------TEST-CONSEGNA----------------------------");
        System.out.println(scan("x") ? "OK" : "NOPE");// atteso-->ok
        System.out.println(scan("flag1") ? "OK" : "NOPE");// atteso-->ok
        System.out.println(scan("x2y2") ? "OK" : "NOPE");// atteso-->ok
        System.out.println(scan("x_1 ") ? "OK" : "NOPE");// atteso-->ok
        System.out.println(scan("temp_") ? "OK" : "NOPE");// atteso-->ok
        System.out.println(scan("x_1_ y_2_") ? "OK" : "NOPE");// atteso-->ok
        System.out.println(scan("x__") ? "OK" : "NOPE"); // atteso-->ok
        System.out.println(scan("_ _ _ Pippo") ? "OK" : "NOPE");// atteso-->ok

    }

}
// ESERCIZIO FUNZIONANTE