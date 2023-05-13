
// DFA FUNZIONANTE 
/* 
Esercizio 1.3. Progettare e implementare un DFA che riconosca il linguaggio di stringhe che
contengono un numero di matricola seguito (subito) da un cognome, dove la combinazione di
matricola e cognome corrisponde a studenti del corso A che hanno un numero di matricola pari
oppure a studenti del corso B che hanno un numero di matricola dispari. Si ricorda che gli
studenti del corso A sono quelli con cognomi la cui iniziale `e compresa tra A e K, e gli studenti
del corso B sono quelli con cognomi la cui iniziale `e compresa tra L e Z.
Per esempio, “123456Bianchi” e “654321Rossi” sono stringhe del linguaggio, mentre
“654321Bianchi” e “123456Rossi” no. Nel contesto di questo esercizio, un numero di ma-
tricola non ha un numero prestabilito di cifre (ma deve essere composto di almeno una cifra). Un
cognome corrisponde a una sequenza di lettere, e deve essere composto di almeno una lettera.
Quindi l’automa deve accettare le stringhe “2Bianchi” e “122B” ma non “654322” e “Rossi”.
Assicurarsi che il DFA sia minimo.
*/
public class Es1_4 {
    public static boolean scan(String s) {

        int state = 0;
        int i = 0;
        while (state >= 0 && i < s.length()) {
            final char ch = s.charAt(i++);
    switch(state){
            case 0:
            if(ch == 32){
                state = 0;
            }  else if (ch >= 48 && ch <= 57) {
                state = 1;
            } else {
                state = -1;
            }
            break;

        case 1:
            if(ch >= 48 && ch <= 57){
                if(ch % 2 != 0){
                    state = 2;
                } else {
                    state = 3;
                }
            } else {
                state = -1;
            }
            break;

        case 2:
            if(ch>= 48 && ch <= 57){
                if(ch%2 != 0){
                    state = 2;
                } else {
                    state = 3;
                }
            } else if(ch == 32){
                state = 4;
            }
            break;

        case 3:
            if(ch >= 48 && ch <= 57){
                if(ch%2 != 0){
                    state = 2;
                } else {
                    state = 3;
                }
            } else if(ch == 32){
                state = 5;
            }
            break;

        case 4:
            if(ch== 32){
                state = 4;
            } else if(ch >= 76 && ch <= 90){
                state = 6;
            }
            break;

        case 5:
            if(ch == 32){
                state = 5;
            } else if(ch >= 65 && ch <= 75){
                state = 6;
            }
            break;

        case 6:
            if((ch >= 97 && ch <= 122) || (ch >= 65 && ch <= 90) || ch == 32){
                state = 6;
            } else {
                state = -1;
            }
            break;

    }
}
return state == 6;
}
    public static void main(String[] args) {
        System.out.println("-------------TEST-CONSEGNA------------------ ");
        System.out.println(scan("21212 Rossi") ? "OK" : "NOPE");// atteso--->Nope
        System.out.println(scan("2 Bianchi") ? "OK" : "NOPE");// atteso--->OK
        System.out.println(scan("122 B") ? "OK" : "NOPE");// atteso-->Ok
        System.out.println(scan("654322 Ciao") ? "OK" : "NOPE");// atteso-->NOPE
        System.out.println(scan("654321 Rossi") ? "OK":"NOPE"); //Atteso --> NOPE --> dispari + R non va bene 
        System.out.println(scan("654321 Bianchi") ? "OK":"NOPE");//Atteso --> NOPE
        System.out.println(scan("123456 Rossi") ? "OK":"NOPE");//Atteso -- >  NOPE 
        System.out.println(scan("123456 Bianchi") ? "OK":"NOPE");// Atteso --> OK 
        System.out.println("--------------TEST AGGIUNTIVI ---------------------");
        System.out.println(scan("Ciao")? "Ok": "NOPE"); //Atteso --> NOPE
        System.out.println(scan("0000000")? "OK": "NOPE") ; //ATTESO --> NOPE
        System.out.println(scan("0000000CIAO2121")? "OK": "NOPE") ; //ATTESO --> NOPE
        System.out.println("------\n");

        String t0 = "654321 Rossi";
        String t1 = "123456 Bianchi";

        String t6 = "654321 Bianchi";
        String t7 = "123456 Rossi";

        System.out.println(t0 + "\nATTESO: \tAccettato \nRISULTATO:\t" + (scan(t0) ? "Accettato" : "Errore") + "\n");
        System.out.println(t1 + "\nATTESO: \tAccettato \nRISULTATO:\t" + (scan(t1) ? "Accettato" : "Errore") + "\n");
        System.out.println(t6 + "\nATTESO: \tErrore \nRISULTATO:\t" + (scan(t6) ? "Accettato" : "Errore") + "\n");
        System.out.println(t7 + "\nATTESO: \tErrore \nRISULTATO:\t" + (scan(t7) ? "Accettato" : "Errore") + "\n");


    }

}


/*COMMENTO FUNZIONAMENTO 

*/