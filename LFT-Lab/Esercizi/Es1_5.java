/* DA FINIRE*/ 


public class Es1_5 {
  public static boolean scan(String s) {

  int state = 0;
  int i = 0;
  while (state >= 0 && i < s.length()) {
    final char ch = s.charAt(i++);    
    switch(state) { 
      case -1: 
        System.out.println("STRINGA NON ACCETTATA DAL DFA\n");
        state =-1; 
        break; 
      case 0: 
        if((ch>=65&&ch<=75)||(ch>=97&&ch<=107)){
          state =1;
        } else if (ch>=77&&ch<=90||ch>=108&&ch<=122) {
          state = 2;
        } else state =-1; 
        break;
      case 1:
        if(i==(s.length() - 1)) {
          if(ch%2==0){
            state =3;
          } else state = -1;
        } else state = 1;
        break;
      case 2:
        if(i==(s.length() - 1)) {
          if(ch%2==0){
            state =3;
          } else state = -1;
        } else state = 1;
        break;
    }
  }
  return state == 3;
}

//DA FIXARE LE MAIUSCOLE NON ENTRANO 

  public static void main(String[] args) {
      System.out.println("---------------TEST-CONSEGNA-------------------");
      System.out.println(scan("Azzeno1212")? "OK": "NOPE");
      System.out.println(scan("Lorenzato9193")? "OK": "NOPE");
      System.out.println(scan("Zano431")? "OK": "NOPE"); 
      System.out.println(scan("Toma4544442")? "OK": "NOPE");
      System.out.println(scan("231233423")? "OK": "NOPE");
      System.out.println(scan("Azzeno1212")?"OK":"NOPE"); //--> DA FIXARE QUESTO STATO 
      System.out.println(scan("Tano3")?"OK": "NOPE");
      System.out.println(scan("Verio43214")?"OK": "NOPE");
      System.out.println(scan("Bruno321144")?"OK": "NOPE");
  }
}
