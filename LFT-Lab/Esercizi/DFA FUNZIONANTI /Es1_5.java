public class Es1_5 {
    

    public static boolean scan(String s) {

        int state = 0;
        int i = 0;
        while (state >= 0 && i < s.length()) {
          final char ch = s.charAt(i++);    
          switch(state) 
          { 
        case -1:
            state =-1;
            break;
    case 0:
          if(ch>='A'&&ch<='K')
          {
            state =1;
          }  
          if(ch>='L'&&ch<='Z')
          {
            state =2;
          }
            break;
    case 1: 
          if(Character.isDigit(ch)&&ch%2==0)
          {
            state =3;
          }
          if(Character.isDigit(ch)&&ch%2!=0)
          {
            state =1;
          }
          break;
    case 2:
         if(Character.isDigit(ch)&&ch%2!=0)
         {
           state =3;
         }
         if(Character.isDigit(ch)&&ch%2==0)
         {
            state =2;
         }
         break;
         }
        }
    
        return state ==3;
    }
    public static void main(String[] args) {
      System.out.println("---------------TEST-CONSEGNA-------------------");
      System.out.println(scan("Azzeno1212")? "OK": "NOPE");
      System.out.println(scan("Lorenzato9193")? "OK": "NOPE");
      System.out.println(scan("Zano431")? "OK": "NOPE"); 
      System.out.println(scan("Toma4544442")? "OK": "NOPE");
      System.out.println(scan("231233423")? "OK": "NOPE");//NOPE
      System.out.println(scan("Azzeno1212")?"OK":"NOPE"); 
      System.out.println(scan("Tano3")?"OK": "NOPE");
      System.out.println(scan("Verio43214")?"OK": "NOPE");
      System.out.println(scan("Bruno321144")?"OK": "NOPE");
    }
}
