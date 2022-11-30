
// DFA FUNZIONANTE 

public class Es1_10 {

    public static boolean scan(String s) {

        int state = 0;
        int i = 0;
        while (state >= 0 && i < s.length()) {
            final char ch = s.charAt(i++);

           switch(state){
            
            


            case 0 : 
             if (  Character.isLetter(ch))
                state = 1 ; 
             else  if ( ch == '/')  ;
                   state =  1; 
              break ; 
            
            case 1 : 
            if ( ch == 'a')
             state =1 ;     
             break ; 

        }
    }
        return state == 3;

      }
    
 
    public static void main(String[] args) {
        System.out.println("-------------TEST-CONSEGNA------------------ ");
        System.out.println(scan("aaa/****/aa") ? "OK" : "NOPE");// atteso--->ok
        System.out.println(scan("aa/*a*a*/ ") ? "OK" : "NOPE");// atteso--->OK
        System.out.println(scan("aaa") ? "OK" : "NOPE");// atteso-->Ok
        System.out.println(scan("///") ? "OK" : "NOPE");// atteso-->ok
        System.out.println(scan("/****/") ? "OK":"NOPE"); //Atteso --> ok 
        System.out.println(scan("/*aa*/") ? "OK":"NOPE");//Atteso -->  ok 
        System.out.println(scan(",") ? "OK":"NOPE");//Atteso -- >  ok 
        System.out.println(scan(" a/**/***a") ? "OK":"NOPE");// Atteso --> OK 
        System.out.println(scan("a/**/***/a” ") ? "OK":"NOPE");// Atteso --> OK 
        System.out.println(scan(" a/**/aa/***/a ") ? "OK":"NOPE");// Atteso --> OK
        System.out.println(scan("aaa/*/aa ") ? "OK":"NOPE");// Atteso --> Nope
        System.out.println(scan(" aa/*aa ") ? "OK":"NOPE");// Atteso --> Nope


        System.out.println("--------------TEST AGGIUNTIVI ---------------------");
        System.out.println(scan("Ciao")? "Ok": "NOPE"); //Atteso --> NOPE
        System.out.println(scan("1Porco2deo")? "OK": "NOPE"); //Atteso --> Nope  //DA FIXARE POICHE' ANCHE CON LETTERE DOPO COGNOME ENTRA NELLO STATO 3
        System.out.println(scan("212334FIKA") ? "OK": "NOPE"); //Atteso--> OK
        System.out.println(scan("0000000")? "OK": "NOPE") ; //ATTESO --> NOPE




    }

}


/*COMMENTO FUNZIONAMENTO */ 

