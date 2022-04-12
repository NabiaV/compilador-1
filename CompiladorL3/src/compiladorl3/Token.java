package compiladorl3;

public class Token {

    String token;
    String lexema;

    public Token(String token, String lexema) {
        this.token = token;
        this.lexema = lexema;
    }

    public String toString() {
        return formateOutPut(lexema, token);
    }

    String formateOutPut(String l, String t) {
        String outPut = l;
        for (int i = l.length(); i < 16; i++) {
            outPut += ' ';
        }
        return outPut + token;
    }

}