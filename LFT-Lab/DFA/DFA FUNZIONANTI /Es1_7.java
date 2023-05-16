public class Es1_7 {
    


    public static boolean scan(String s) {

        int state = 0;
        int i = 0;
        while (state >= 0 && i < s.length()) {
            final char ch = s.charAt(i++);
        switch(state){
            case -1:
                state =-1; 
            break;
            case 0:
                if(ch=='R')
                {
                    state =1;
                } 
                if(ch!='R')
                {
                    state =8;
                }
            break;
            case 1:
                if(ch=='i')
                {
                    state=2;
                }
                if(ch!='i')
                {
                    state =9;
                }
            break;
            case 2:
                    if(ch!='c')
                    {
                        state =10;
                    }
                    if(ch=='c')
                    {
                        state=3;
                    }
            break;
            case 3:
                if(ch!='c')
                 {
                  state =11;
                 }
                if(ch=='c')
                  {
                     state=4;
                  }
            break;
            case 4:
                  if(ch=='a')
                  {
                    state =5;
                  }
                  if(ch!='a')
                  {
                    state=12;
                  }
            break;
            case 5:
                  if(ch=='r')
                  {
                    state=6;
                  }
                  if(ch!='r')
                  {
                    state=13;
                  }
            break;
            case 6:
                  if(ch=='d')
                  {
                    state=7;
                  }
                  if(ch!='d')
                  {
                    state =14;
                  }
            break;
            case 7:
                  if(ch=='o')
                  {
                    state =15;
                  }
                  if(ch!='o')
                  {
                    state=14;
                  }
            break;
            case 8:
                  if(ch!='i')
                  {
                    state= -1;
                  }
                  if(ch=='i')
                  {
                    state =9;
                  }
            break;
            case 9:
                  if(ch!='c')
                  {
                    state =-1;
                  }
                  if(ch=='c'){
                     state =10;
                  }
            break;
            case 10:
                  if(ch=='c')
                  {
                    state =11;
                  }
                  if(ch!='c')
                  {
                    state =-1;
                  }
            break;
            case 11:
                  if(ch=='a')
                  {
                    state=12;
                  }
                  if(ch!='a')
                  {
                    state=-1;
                  }
            break;
            case 12:
                  if(ch=='r')
                  {
                    state=13;
                  }
                  if(ch!='r')
                  {
                    state=-1;
                  }
            break;
            case 13:
                  if(ch=='d')
                  {
                    state=14;
                  }
                  if(ch!='d')
                  {
                    state=-1;
                  }
            break;
            case 14:
                  if(ch=='o')
                  {
                    state=15;
                  }
                  if(ch!='o')
                  {
                    state=-1;
                  }
            break;
            case 15:
                  state =15;
                  break;
       
       }

    }
    return state ==15;
    }
    public static void main(String[] args) {
        

        System.out.println(scan("Riccardo")? "OK":"NOPE");
        System.out.println(scan("R3ccardo")? "OK":"NOPE");
        System.out.println(scan("Ricchhjs")? "OK":"NOPE");


        System.out.println(scan("R122324")? "OK":"NOPE");
        System.out.println(scan("Ricxxardo")? "OK":"NOPE");
        System.out.println(scan("Riczardo")? "OK":"NOPE");


        System.out.println(scan("Rick")? "OK":"NOPE");
        System.out.println(scan("Riko")? "OK":"NOPE");
        System.out.println(scan("Ri%cardo")? "OK":"NOPE");
        
    }
}
