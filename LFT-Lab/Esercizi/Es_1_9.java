/*
Esercizio 1.9. Progettare e implementare un DFA con alfabeto {/, *, a}che riconosca il linguag-
gio di “commenti” delimitati da /* (all’inizio) e */
// (alla fine): cio`e l’automa deve accettare le
//stringhe che contengono almeno 4 caratteri che iniziano con /*, che finiscono con */, e che con-
//tengono una sola occorrenza della sequenza */, quella finale (dove l’asterisco della sequenza */
//non deve essere in comune con quello della sequenza /* all’inizio).
//Esempi di stringhe accettate: “/****/”, “/*a*a*/”, “/*a/**/”, “/**a///a/a**/”, “/**/”,
//“/*/*/”
//Esempi di stringhe non accettate: “/*/”, “/**/***/”

public class Es_1_9 {
    public static boolean scan(String s) {

        int state = 0;
        int i = 0;
        while (state >= 0 && i < s.length()) {
            final char ch = s.charAt(i++);
            switch (state) {
                case 0:
                    if (ch == '/') {
                        state = 1;
                    } else {
                        state = -1;
                    }
                    break;
                case 1:
                    if (ch == '*') {
                        state = 2;
                    } else {
                        state = -1;
                    }
                    break;
                case 2:
                    if (ch == '*') {
                        state = 4;
                    } else if (Character.isLetter(ch)) {
                        state = 3;
                    } else {
                        state = -1;
                    }

                    break;
                case 3:
                    if (ch == '/') {
                        state = 4;
                    } else {
                        state = 3;
                    }
                    break;
                case 4:
                    state = 4;
                    break;
            }
        }
        return state == 4;

    }

    public static void main(String[] args) {
        System.out.println(scan("/****/") ? "OK" : "NOPE");// atteso--->ok
        System.out.println(scan("/*a*a*/") ? "OK" : "NOPE");// atteso--->OK
        System.out.println(scan("/*a/**/") ? "OK" : "NOPE");// atteso-->Ok
        System.out.println(scan("/**/***/") ? "OK" : "NOPE");// atteso-->NOPE NON VA SOLO QUESTO DA CONTROLLARE
        System.out.println(scan("/*/") ? "OK" : "NOPE"); // Nope

    }
}
