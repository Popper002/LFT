
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
public class Es1__3 {

    public static boolean scan(String s) {

        int state = 0;
        int i = 0;
        while (state >= 0 && i < s.length()) {
            final char ch = s.charAt(i++);

            switch (state) {
                case 0:
                    if (Character.isDigit(ch) && Integer.parseInt(Character.toString(ch)) % 2 == 0)// n pari
                        state = 1;
                    else if (Character.isDigit(ch) && Integer.parseInt(Character.toString(ch)) % 2 == 1)// n dispari
                        state = 2;
                    else
                        state = -1;
                    break;
                case 1: //Pari Corso A 

                    if (Character.isDigit(ch) && Integer.parseInt(Character.toString(ch)) % 2 == 0)// n pari
                        state = 1;
                    else if (Character.isDigit(ch) && Integer.parseInt(Character.toString(ch)) % 2 == 1)// n dispari
                        state = 0;
                    else if (ch >= 65 && ch <= 75)// ch€[A-K]
                        state = 3;
                    // else if (cch <= 90h >= 76 && )
                    // state = 4;
                    else
                        state = -1;
                    break;

                case 2: // Dispari Corso B 

                    if (Character.isDigit(ch) && Integer.parseInt(Character.toString(ch)) % 2 == 0) // Pari 
                        state = 1;
                    else if (Character.isDigit(ch) && Integer.parseInt(Character.toString(ch)) % 2 == 1) // Dispari 
                       state = 2;
                    else if (ch >= 76 && ch <= 90)// ch € [L-Z]
                        state = 3;
                    else
                        state = -1;
                    break;
                case 3:
                 if(Character.isDigit(ch)) 
                        state=0; 
                        else if( Character.isLetter(ch) && ch >=65 &&ch<=75)
                        state =1;
                        else if(Character.isLetter(ch) && ch >=76 &&ch <=90 )
                        state =2; 
                    else 
                     state= -1; 
                    break;

            }

        }

        return state == 3;

    }

    public static void main(String[] args) {
        System.out.println("-------------TEST-CONSEGNA------------------ ");
        System.out.println(scan("Rossi") ? "OK" : "NOPE");// atteso--->Nope
        System.out.println(scan("2Bianchi") ? "OK" : "NOPE");// atteso--->OK
        System.out.println(scan("122B") ? "OK" : "NOPE");// atteso-->Ok
        System.out.println(scan("654322") ? "OK" : "NOPE");// atteso-->NOPE
        System.out.println(scan("654321Rossi") ? "OK":"NOPE"); //Atteso --> NOPE --> dispari + R non va bene 
        System.out.println(scan("654321Bianchi") ? "OK":"NOPE");//Atteso --> NOPE
        System.out.println(scan("123456Rossi") ? "OK":"NOPE");//Atteso -- >  NOPE 
        System.out.println(scan("123456Bianchi") ? "OK":"NOPE");// Atteso --> OK 
        System.out.println("--------------TEST AGGIUNTIVI ---------------------");
        System.out.println(scan("Ciao")? "Ok": "NOPE"); //Atteso --> NOPE
        System.out.println(scan("1Porco2deo")? "OK": "NOPE"); //Atteso --> Nope  //DA FIXARE POICHE' ANCHE CON LETTERE DOPO COGNOME ENTRA NELLO STATO 3
        System.out.println(scan("212334FIKA") ? "OK": "NOPE"); //Atteso--> OK
        System.out.println(scan("0000000")? "OK": "NOPE") ; //ATTESO --> NOPE
        System.out.println(scan("0000000CIAO2121")? "OK": "NOPE") ; //ATTESO --> NOPE
        




    }

}


/*COMMENTO FUNZIONAMENTO 


LEGGO SE HO UNA SEQUENZA DI NUMERI CONTROLLO SE E' PARI O DISPARI, E POI CONTROLLO SE IN BASE AL SUCCESSIVA STRINGA DI CARATTERI SONO DEL CORSO A O B 
IL DFA  ACCETTA SOLO LE SEGUENTI COMBINAZIONI 

PARI+LETTERA [A-K]
DISPARI + LETTERA [L-Z] LE LETTERE LE RAPPRESENTO SECONDO STANDARD ASCHII. 
TUTTO ALTRO NON VIENE ACCETTATO.  

STATO 0: Se l'imput è Una strigna di caratteri l'automa non effettuarà un cambio di stato ma rimarrà allo stato 0 

STATO 1: Stato che appartiene ai numeri pari poichè una dei criteri del corso A è quello di avere il numero di matricola pari eguito da un cognome che inizia con le lettere comprese tra A-K

STATO 2 : Stato che appartiene ai numeri dispari poichè una dei criteri del corso B è quello di avere il numero di matricola Dispari seguito da un cognome che inizia con le lettere comprese tra L-Z 

STATO 3 : Stato finale /stato pozzo. 

STATO -1: Stato d'errore in cui vengono indirizzati tutte le condizioni non accettate dal DFA (Simboli o altro ). 
*/


