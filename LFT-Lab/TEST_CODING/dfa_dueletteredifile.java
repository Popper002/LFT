
public class dfa_dueletteredifile {
    public static boolean scan(String s) {

        int state = 0;
        int i = 0;
        while (state >= 0 && i < s.length()) {
            final char ch = s.charAt(i++);

            switch (state) {
            
            case 0: 
                 if(ch == 'a')
                    state = 1; 
                else if( ch =='b')
                    state =2; 
                else if( ch=='c')
                    state =3;
                break; 
            case 1: 
            if(ch == 'a')
                state = 4;
                else
                state =0;
                break;
            case 2: 
                if(ch=='b')
                    state =4;
                else
                state =0;
            case 3:
                if(ch=='c')
                state =4;
                else
                state =0;
    
            case 4: 
                state =4; 
                break ; 
            }

        }

        return state == 4;

    }

    public static void main(String[] args) {
        System.out.println("-------------TEST-CONSEGNA------------------ ");
        System.out.println(scan("aabb") ? "OK" : "NOPE");// Atteso-->ok
        System.out.println(scan("abc") ? "OK" : "NOPE");// atteso--->no
        System.out.println(scan("122B") ? "OK" : "NOPE");// atteso-->no
        System.out.println(scan("abaa") ? "OK" : "NOPE");// atteso-->ok
        System.out.println(scan("cbcc") ? "OK":"NOPE"); //Atteso --> ok 
        System.out.println(scan("abba") ? "OK":"NOPE");//Atteso --> ok
        System.out.println(scan("1234") ? "OK":"NOPE");//Atteso -- >  NOPE 
        System.out.println(scan("ababababababccc") ? "OK":"NOPE");// Atteso --> OK 
        System.out.println("--------------TEST AGGIUNTIVI ---------------------");
        System.out.println(scan("Ciao")? "Ok": "NOPE"); //Atteso --> NOPE
        System.out.println(scan("1Porco2deo")? "OK": "NOPE"); //Atteso --> Nope  //DA FIXARE POICHE' ANCHE CON LETTERE DOPO COGNOME ENTRA NELLO STATO 3
        System.out.println(scan("212334FIKA") ? "OK": "NOPE"); //Atteso--> OK
        System.out.println(scan("0000000")? "OK": "NOPE") ; //ATTESO --> NOPE
        System.out.println(scan("0000000CIAO2121")? "OK": "NOPE") ; //ATTESO --> NOPE
        System.out.println("------\n");

        String t0 = "abcabcbabcaab";
        String t1 = "abacbabcbaaaabbc";

        String t6 = "bca";
        String t7 = "bbcaccabb";

        System.out.println(t0 + "\nATTESO: \tAccettato \nRISULTATO:\t" + (scan(t0) ? "Accettato" : "Errore") + "\n");
        System.out.println(t1 + "\nATTESO: \tAccettato \nRISULTATO:\t" + (scan(t1) ? "Accettato" : "Errore") + "\n");
        System.out.println(t6 + "\nATTESO: \tErrore \nRISULTATO:\t" + (scan(t6) ? "Accettato" : "Errore") + "\n");
        System.out.println(t7 + "\nATTESO: \tErrore \nRISULTATO:\t" + (scan(t7) ? "Accettato" : "Errore") + "\n");


    }

}






