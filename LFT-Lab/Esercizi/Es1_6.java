//DA RIFARE --> CAPIRE COME CONTROLLARE L'PENULTIMA CIFRA E LETTERE MAIUSCOLE 


public class Es1_6 {

    public static boolean scan(String s) {

        int state = 0;
        int i = 0;
        while (state >= 0 && i < s.length()) {
            final char ch = s.charAt(i++);
            
        switch(state){ 

        case 0:
         if(Character.isDigit(ch)&&ch%2==0) 
          state=1; 
          else if (Character.isDigit(ch)&&ch%2==1) 
          state=2;
          else 
           state =-1; 
           break; 

        case 1: 
        
         if(ch>='a'&& ch<='k') 
         state =3 ;
         else if(ch>='A'&& ch<='K') 
         state =4; 
         else 
          state =0; 
          break ; 
        
        case 2: 
        if(ch>='l'&& ch<='z') 
        state =6 ;
        else if(ch>='L'&& ch<='Z') 
        state =5 ;
           break; 

        case 3: 
           if(ch>='a'&& ch<='k') 
         state =7;
          else 
           state =0; 
            break; 
        case 4: 
            if(ch>='A'&& ch<='K') 
                state =7;
            else 
                state =0; 
                    break ;  
         
        case 5: 
            if(ch>='L'&& ch<='Z') 
                state =7 ;
            else 
                state =0; 
                    break;
        case 6: 
            if(ch>='l'&& ch<='z') 
                state =7;
                 state =0; 
                 break; 
        case 7: 
         
           state=7; 
            break; 
        

        }  
    } 
        return state ==7 ; 
    } 


    public static void main(String[] args) {
        System.out.println(scan("654322bianchi")? "OK":"NOPE");
        System.out.println(scan("123451rossi")? "OK":"NOPE");
        System.out.println(scan("222b")? "OK":"NOPE");
        System.out.println(scan("")? "OK":"NOPE");
        System.out.println(scan("")? "OK":"NOPE");



    }
}



