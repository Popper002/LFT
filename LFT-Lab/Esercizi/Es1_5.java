/* DA FINIRE*/ 


public class Es1_5 {

    public static boolean scan(String s) {

        int state = 0;
        int i = 0;
        while (state >= 0 && i < s.length()) {
            final char ch = s.charAt(i++);    
        switch(state)
        { 
        case 0:
        if(ch>=65&& ch<=75)
           state =1; 
        else if(ch>=76&& ch<=90) 
          state =2; 
          break; 
        
        case 1://minuscole   
        if(ch>=65& ch<=75) //A-K 
          state =1; 
        else if(ch>=97&& ch<=107) //a-k 
          state =4; 
          break;
        
        case 2 : //Corso A minuscole 
            if( ch >= 76 && ch <= 108)
                state =2;
              else if ( ch>=108 && ch <= 122)
              state =3; 
              break;  
          case 3: 
            while(i < s.length()-1)
            {
              if(ch >= 48 && ch <= 57)
              {
                if(ch % 2 == 0){
                    state = 5;
            }else 
              state =4; 
          }
        }
            break; 
       case 4: 
       while( i< s.length()-1)
       {
        if(ch >= 48 && ch <= 57)
          {
        if(ch % 2 != 0)
        {
            state = 5;
        } else 
        state =3;
          }
      } 
    }
  }
        return state == 5; 
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
