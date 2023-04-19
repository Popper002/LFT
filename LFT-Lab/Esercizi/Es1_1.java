public class Es1_1 {
    

//DA RIFARE --> CAPIRE COME CONTROLLARE L'PENULTIMA CIFRA E LETTERE MAIUSCOLE 




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
                if (ch =='0'| ch =='1')
                state =1; 
                break; 
         case 1: 
            if (ch =='0'| ch =='1')
                state =2; 
         case 2: 
                if (ch=='1')
                state =3; 
                if(ch == '0')
                state =-1; 
                break;
        case 3: 
            if ( ch == '0')
            state = -1; 
            break ; 

        }  
    } 
        return state ==3; 
    } 


    public static void main(String[] args) {
        System.out.println(scan("010101")? "OK":"NOPE");
        System.out.println(scan("1100011001")? "OK":"NOPE");
        System.out.println(scan("0001111")? "OK":"NOPE");
        System.out.println(scan("111010101")? "OK":"NOPE");
        System.out.println(scan("123")? "OK":"NOPE");
        System.out.println(scan("00000000")? "OK":"NOPE");
        System.out.println(scan("11111111")? "OK":"NOPE");
        System.out.println(scan("1111001111000")? "OK":"NOPE");
        System.out.println(scan("11001010101")? "OK":"NOPE");




    }
}



