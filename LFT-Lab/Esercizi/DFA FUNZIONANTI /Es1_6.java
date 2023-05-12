//DA RIFARE --> CAPIRE COME CONTROLLARE L'PENULTIMA CIFRA E LETTERE MAIUSCOLE 
/* 
Esercizio 1.6. Progettare e implementare un DFA che riconosca il linguaggio di stringhe che con-
tengono un numero di matricola seguito (subito) da un cognome, dove la combinazione di ma-
tricola e cognome corrisponde a studenti del turno T2 o del turno T3 del laboratorio di Linguaggi
Formali e Traduttori. Si ricorda le regole per suddivisione di studenti in turni:
• Turno T1: cognomi la cui iniziale `e compresa tra A e K, e la penultima cifra del numero di
matricola `e dispari;
• Turno T2: cognomi la cui iniziale `e compresa tra A e K, e la penultima cifra del numero di
matricola `e pari;
• Turno T3: cognomi la cui iniziale `e compresa tra L e Z, e la penultima cifra del numero di
matricola `e dispari;
• Turno T4: cognomi la cui iniziale `e compresa tra L e Z, e la penultima cifra del numero di
matricola `e pari.
4
Un numero di matricola deve essere composto di almeno due cifre, ma (come in Esercizio 1.3)
non ha un numero massimo prestabilito di cifre. Assicurarsi che il DFA sia minimo.
Esempi di stringhe accettate: “654321Bianchi”, “123456Rossi”, “221B”
Esempi di stringhe non accettate: “123456Bianchi”, “654321Rossi”, “5”, “654322”, “Rossi”,
“2Bianchi”
*/
public class Es1_6 {

    public static boolean scan(String s) {

        int state = 0;
        int i = 0;
        while (state >= 0 && i < s.length()) {
            final char ch = s.charAt(i++);
        switch(state){ 
    case 0: 
           if(Character.isDigit(ch)&&ch%2==0)
           {
            state =2;
           }
           if(Character.isDigit(ch)&&ch%2!=0)
           {
            state =1;
           }
           if(!Character.isDigit(ch))
           {
            state =-1;
           }
                 break;
    case 1://dispari
           if(Character.isDigit(ch)&&ch%2!=0)
           {
            state =3; 
           }
           if(Character.isDigit(ch)&&ch%2==0)
           {
            state =4;
           }
          if(!Character.isDigit(ch))
          {
            state =-1;
          }
           break;
        
    case 2:
            if(Character.isDigit(ch)&&ch%2==0)
            {
                state =5;

            }   
            if(Character.isDigit(ch)&&ch%2!=0)
            {
                state =6;
            }
            if(!Character.isDigit(ch))
            {
              state =-1;
            }
            break;
       case 3://dispari A-K
            if(Character.isDigit(ch)&&ch!=0)
            {
                state =3;
            }
            if(Character.isDigit(ch)&&ch%2==0)
            {
                state =4;
            }
            if(ch>='A'&&ch<='K')
                {
                    state =-1;
                }
            if(ch>='L'&&ch<='Z')
            {
                            
            state =7; // L-Z
            }
                break;
    case 4:
        if(Character.isDigit(ch)&&ch!=0)
        {
          state =6;
        }
        if(Character.isDigit(ch)&&ch%2==0)
          {
             state =5;
          }
          if(ch>='L'&&ch<='Z')
          {
            state =7; 
          }
          if(ch>='A'&&ch<='K')
          {
              state =-1;
          }
          break;
    case 5:
          if(Character.isDigit(ch)&&ch%2==0)
          {
            state =5;
          }
          if(Character.isDigit(ch)&&ch%2!=0)
          {
            state =6;
          }
          if(ch>='A'&&ch<='K')
          {
            state =7;
          }
          if(ch>='L'&&ch<='Z')
          {
            state =-1; 
          }
          break;
    case 6: 
          if(Character.isDigit(ch)&&ch%2==0)
          {
            state =4;
          }
          if(Character.isDigit(ch)&&ch%2!=0)
          {
            state =3;
          }
          if(ch>='L'&&ch<='Z')
          {
            state =-1;
          }
          if(ch>='A'&&ch<='K')
          {
            state =7;
          }
            break;
        case 7:
                state =7;
                break;
        }  
    } 
        return state==7; 
    } 


    public static void main(String[] args) {
   
       
      
        System.out.println(scan("654321Bianchi")? "OK":"NOPE");
        System.out.println(scan("123456Rossi")? "OK":"NOPE");
        System.out.println(scan("221B")? "OK":"NOPE");


        System.out.println(scan("123456Bianch")? "OK":"NOPE");
        System.out.println(scan("654321Rossi")? "OK":"NOPE");
        System.out.println(scan("654322")? "OK":"NOPE");

        System.out.println(scan("5")? "OK":"NOPE");
        System.out.println(scan("Rossi")? "OK":"NOPE");
        System.out.println(scan("2Bianchi")? "OK":"NOPE");
        
    

    }
}



/*DFA FUNZIONANTE  */