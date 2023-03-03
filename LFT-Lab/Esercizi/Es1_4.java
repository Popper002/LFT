/* 
Esercizio 1.4. Modificare l’automa dell’esercizio precedente in modo che riconosca le combi-
nazioni di matricola e cognome degli studenti del corso A che hanno un numero di matricola
pari oppure a studenti del corso B che hanno un numero di matricola dispari, dove il numero
di matricola e il cognome possono essere separati da una sequenza di spazi, e possono essere
precedute e/o seguite da sequenze eventualmente vuote di spazi. Per esempio, l’automa deve
accettare la stringa “654321 Rossi” e “ 123456 Bianchi ” (dove, nel secondo esempio, ci
sono spazi prima del primo carattere e dopo l’ultimo carattere), ma non “1234 56Bianchi” e
“123456Bia nchi”. Per questo esercizio, i cognomi composti (con un numero arbitrario di par-
ti) possono essere accettati: per esempio, la stringa “123456De Gasperi” deve essere accettato.
Modificare l’implementazione Java dell’automa di conseguenza.
*/

public class Es1_4 {
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


                  case 1: // SPACE COMMAND  Pari
                   if(ch==' ')
                   state=3;
                   else 
                    state=-1; 
                    break;  
                  
                  case 2 :  //SPACE COMMAND Dispari 
                   if(ch==' ')
                   state =4; 
                   else 
                    state =-1; 
                    break;  

                case 3: //Pari Corso A 

                    if (Character.isDigit(ch) && Integer.parseInt(Character.toString(ch)) % 2 == 0)// n pari
                        state = 1;
                    else if (Character.isDigit(ch) && Integer.parseInt(Character.toString(ch)) % 2 == 1)// n dispari
                        state = 2;
                    else if (ch >= 65 && ch <= 75)// ch€[A-K]
                        state = 5;
                    //else if( ch==' ')
                    //state= 3; 

                    // else if (ch >= 76 && ch <= 90)
                    // state = 4;
                    else
                        state = -1;
                    break;

                case 4: // Dispari Corso B 

                    if (Character.isDigit(ch) && Integer.parseInt(Character.toString(ch)) % 2 == 0) // Pari 
                        state = 1;
                    else if (Character.isDigit(ch) && Integer.parseInt(Character.toString(ch)) % 2 == 1) // Dispari 
                       state = 2;
                    else if (ch >= 76 && ch <= 90)// ch € [L-Z]
                        state = 5;
                    //else if(ch==' ') 
                    //state =3;  
                    else
                        state = -1;
                    break;
                case 5: 
                 state=5; 
                    break;
               
            }

        }

        return state == 5;                             

    }
    public static void main(String[] args) {
        System.out.println("-------------TEST-CONSEGNA------------------ ");
        System.out.println(scan("654321 Rossi") ? "OK" : "NOPE");// atteso--->OK 
        System.out.println(scan("123456 Bianchi") ? "OK" : "NOPE");// atteso--->OK       
        System.out.println(scan("1234 56Bianchi") ? "OK" : "NOPE");// atteso-->NOPE
        System.out.println(scan("123456Bia nchi") ? "OK" : "NOPE");// atteso-->NOPE
        System.out.println(scan("1234 56Bianchi") ? "OK":"NOPE"); // atteso--> NOPE 
        /*System.out.println("--------------TEST AGGIUNTIVI ---------------------");
        System.out.println(scan("Rossi") ? "OK" : "NOPE");// atteso--->Nope
        System.out.println(scan("2Bianchi") ? "OK" : "NOPE");// atteso--->OK
        System.out.println(scan("122B") ? "OK" : "NOPE");// atteso-->NOPE
        System.out.println(scan("654322") ? "OK" : "NOPE");// atteso-->NOPE
        System.out.println(scan("654321Rossi") ? "OK":"NOPE"); //DA VERIFICARE
        System.out.println(scan("654321Bianchi") ? "OK":"NOPE");//DA VERIFICARE
        System.out.println(scan("123456Rossi") ? "OK":"NOPE");//DA VERIFICARE
        System.out.println(scan("123456Bianchi") ? "OK":"NOPE");//DA VERIFICARE
*/
    }
    
}
