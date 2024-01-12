public class Es1_10
{
  public static boolean scan(String s)
  {
    int state =0; 
    int i =0; 
    while( state >=0 && i< s.length())
    {
      final char ch = s.charAt(i++);
      switch(state )
      {
        case 0: 
            if(ch =='/')
            state =1; 
            else if( ch =='*')
            state =1; 
            else if(ch=='a');
            state =1;
        break;
        
        case 1: 
          if(ch == '/')
          state = 2;
          else if( ch =='*')
          state =2; 
          else if ( ch == 'a')
          state =2;
          break;
        case 2: 
          if(ch =='/')
          state =2;
          else if( ch == '*')
          state =3; 
          else if( ch =='a')
          state =4; 
          break;
        case 3: 
          if ( ch == '/')
          state =4; 
          else if( ch =='*')
          state =3;
          else if( ch =='a')
          state =4; 
          break; 
          case 4: 
           state =4;
           break;
      }
    }
    return state ==4 ;

  }
    public static void main(String[] args) {
      System.out.println("-------------TEST-CONSEGNA------------------ ");
      System.out.println(scan("aaa/****/aa") ? "OK" : "NOPE");// atteso--->ok
      System.out.println(scan("aa/*a*a*/") ? "OK" : "NOPE");// atteso--->OK
      System.out.println(scan("aaaa") ? "OK" : "NOPE");// atteso-->Ok
      System.out.println(scan("/****/") ? "OK" : "NOPE");// atteso-->ok
      System.out.println(scan("/*aa*/") ? "OK":"NOPE"); //Atteso --> ok 
      System.out.println(scan("*/a") ? "OK":"NOPE");//Atteso --> ok
      System.out.println(scan("a/**/***a") ? "OK":"NOPE");//Atteso -- >   ok
      System.out.println(scan("a/**/***/a") ? "OK":"NOPE");// Atteso --> OK 
      System.out.println("--------------TEST AGGIUNTIVI ---------------------");
      System.out.println(scan("“a/**/aa/***/a")? "Ok": "NOPE"); //Atteso --> NOPE
      System.out.println(scan(" ")? "OK": "NOPE"); //Atteso --> Nope  
      System.out.println(scan("212334FIKA") ? "OK": "NOPE"); //Atteso--> NOPE
      System.out.println(scan("0000000")? "OK": "NOPE") ; //ATTESO --> NOPE
      System.out.println(scan("0000000CIAO2121")? "OK": "NOPE") ; //ATTESO --> NOPE
      System.out.println(scan("aaa/*/aa")? "OK": "NOPE") ; //ATTESO --> NOPE
      System.out.println(scan("a/**//***a")? "OK": "NOPE") ; //ATTESO --> NOPE
      System.out.println(scan("aa/*aa")? "OK": "NOPE") ; //ATTESO --> NOPE
      System.out.println(scan("bbbahsd*/**/")? "OK": "NOPE") ; //ATTESO --> NOPE



  }  
}