/* DA FINIRE*/ 


public class Es1_5 {

    public static boolean scan(String s) {

        int state = 0;
        int i = 0;
        while (state >= 0 && i < s.length()) {
            final char ch = s.charAt(i++);
            
        switch(state){ 

        case 0:
         
        if(ch>='A'&& ch<='K')
           state =1;
        //else if(ch>='a'&& ch<='k') 
          // state =2 ; 
        else if(ch>='L'&& ch<='Z') 
          state =2; 
        //else if(ch>='l'&& ch<='z')
          // state=4; 
           else 
             state=-1; 
          break; 
        
        case 1: //Corso A MAISCULE  
          if(Character.isDigit(ch)&&ch%2==0)
            state =5; 
        else
           state =-1; 
           break; 
        
        case 2 : //Corso A minuscole 

        if(Character.isDigit(ch)&&ch%2==0)
           state=5;
         else 
            state=0;  
            break; 
        case 3: //DISPARI MAISCULO 
        if(Character.isDigit(ch)&&ch%2==1)
            state=6; 
        else 
         state =-1; 
            break; 
        
        case 4 : //DISPARI MINUSCOLO
         if(Character.isDigit(ch)&&ch%2==1)
         state=6; 
           break;
           
        case 5:  // STATO DI UNIONE TRA I PARI(A)
           if(Character.isDigit(ch)&&ch%2==0)
            state =7; 
             break; 

        case 6: //STATO DI UNIONE TRA I DISPARI(B)
        if(Character.isDigit(ch)&&ch%2==0)
          state =5; 
          if(Character.isDigit(ch)&&ch%2==1)
              state =7;
              break;  
            } 
        } 
        return state == 7; 
  }

//DA FIXARE LE MAIUSCOLE NON ENTRANO 

  public static void main(String[] args) {
      System.out.println("---------------TEST-CONSEGNA-------------------");
      System.out.println(scan("azzeno1212")? "OK": "NOPE");
      System.out.println(scan("lorenzato9193")? "OK": "NOPE");
      System.out.println(scan("zano431")? "OK": "NOPE"); 
      System.out.println(scan("toma4544442")? "OK": "NOPE");
      System.out.println(scan("231233423")? "OK": "NOPE");
      System.out.println(scan("Azzeno1212")?"OK":"NOPE"); //--> DA FIXARE QUESTO STATO 
      System.out.println(scan("tano3")?"OK": "NOPE");
      System.out.println(scan("cerio43214")?"OK": "NOPE");
      System.out.println(scan("bruno321144")?"OK": "NOPE");
      System.out.println(scan("Bruno321144")?"OK": "NOPE"); 
  }
}
