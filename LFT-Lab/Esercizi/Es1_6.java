//DA RIFARE --> CAPIRE COME CONTROLLARE L'PENULTIMA CIFRA E LETTERE MAIUSCOLE 


public class Es1_6 {

    public static boolean scan(String s) {

        int state = 0;
        int i = 0;
        while (state >= 0 && i < s.length()) {
            final char ch = s.charAt(i++);
            
        switch(state){ 

      
        }  
    } 
        return; 
    } 


    public static void main(String[] args) {
        System.out.println(scan("654322bianchi")? "OK":"NOPE");
        System.out.println(scan("123451rossi")? "OK":"NOPE");
        System.out.println(scan("222b")? "OK":"NOPE");
        System.out.println(scan("")? "OK":"NOPE");
        System.out.println(scan("")? "OK":"NOPE");



    }
}



