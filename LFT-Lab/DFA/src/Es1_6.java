

public class Es1_6 {
    public static boolean scan(String s)
     {

        int state = 0;
        int i = 0;
        while (state >= 0 && i < s.length())
         {
            final char ch = s.charAt(i++);
        switch(state){ 
                case 0: 
                   if(ch=='a')
                   {
                    state =0;
                   }
                   if(ch=='b')
                   {
                    state =1;
                   }
                   break;
                case 1:
                   if(ch=='b')
                   {
                    state =2;
                   }
                   if(ch=='a')
                   {
                    state =0;
                   }
                   break;
                case 2: 
                    if(ch=='a')
                    {
                        state =0;
                    }
                    if(ch=='b')
                    {
                        state =3;
                    }
                    break;
                    case 3:
                        if(ch=='b')
                        {
                            state =3;
                        }
                    break;
        }
   
     }
     return state==0 || state ==2;
    }  
     public static void main(String[] args) {
        System.out.println(scan("abb")? "OK":"NOPE");
        System.out.println(scan("bbaba")? "OK":"NOPE");
        System.out.println(scan("baaaaaaa")? "OK":"NOPE");


        System.out.println(scan("aaaaaaaaa")? "OK":"NOPE");
        System.out.println(scan("a")? "OK":"NOPE");
        System.out.println(scan("ba")? "OK":"NOPE");
        System.out.println(scan("aa")? "OK":"NOPE");
        System.out.println(scan("bba")? "OK":"NOPE");

       
        System.out.println(scan("abbbbbbbbbbb")? "OK":"NOPE");

        System.out.println(scan("bbabbbbbbbb”")? "OK":"NOPE");
        System.out.println(scan("bbbbbbbbbbbbbb")? "OK":"NOPE");
        System.out.println(scan("abbb")? "OK":"NOPE");
        System.out.println(scan("aaaaaabbbbbbbbb")? "OK":"NOPE");
        System.out.println(scan("bbbbbbbbbbbbbb")? "OK":"NOPE");
        System.out.println(scan("abbaaaabbababbabb")? "OK":"NOPE");
        System.out.println(scan("aaaaabbabbabababbaaa")? "OK":"NOPE");
        System.out.println(scan("abbbbbbbabbbabbbb")? "OK":"NOPE");








     }
    }


