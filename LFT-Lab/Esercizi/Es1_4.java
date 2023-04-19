
public class Es1_4{

    public static boolean scan(String s) {

        int state = 0;
        int i = 0;
        while (state >= 0 && i < s.length()) {
            final char ch = s.charAt(i++);

            switch (state) {
            
                case 0: 
                    if(ch >= 48 && ch <= 57){
                        if(ch % 2 == 0){
                            state = 1;
                        } else state = 2;
                    } else state = -1;
                    break;

                    case 1: 
                    if(ch >= 48 && ch <= 57){
                        if(ch % 2 == 0){
                            state = 1;
                        }else if( ch ==' ')
                        state =3; 

                    } else state = -1;
                    break;

                    case 2: 
                    if(ch >= 48 && ch <= 57){
                        if(ch % 2 == 0){
                            state = -1;
                        } 
                        else if(ch == ' '){
                        state =4; 
                        }
                    }
                        else state = 2;
                    break;

                
                        case 3:
                        if(ch == ' ')
                            state =3 ; 
                            else if ((ch >= 97 && ch <= 107) || (ch >=65 && ch <= 75)) 
                            state =5; 
                            else state=-1; 
                            break;
            }

        }

        return state == 5;

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
        System.out.println("------\n");

        String t0 = "654321Rossi";
        String t1 = "123456Bianchi";

        String t6 = "654321Bianchi";
        String t7 = "123456Rossi";

        System.out.println(t0 + "\nATTESO: \tAccettato \nRISULTATO:\t" + (scan(t0) ? "Accettato" : "Errore") + "\n");
        System.out.println(t1 + "\nATTESO: \tAccettato \nRISULTATO:\t" + (scan(t1) ? "Accettato" : "Errore") + "\n");
        System.out.println(t6 + "\nATTESO: \tErrore \nRISULTATO:\t" + (scan(t6) ? "Accettato" : "Errore") + "\n");
        System.out.println(t7 + "\nATTESO: \tErrore \nRISULTATO:\t" + (scan(t7) ? "Accettato" : "Errore") + "\n");


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


