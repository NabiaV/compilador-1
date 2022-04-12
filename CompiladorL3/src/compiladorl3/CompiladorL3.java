package compiladorl3;

/**
 *
 * @author tarci
 */
public class CompiladorL3 {

    public static void main(String[] args) {
        Lexico lexico = new Lexico("/workspace/compilador-1/CompiladorL3/src/compiladorl3/codigo.txt");
        Token t = null;
        while((t = lexico.lerNextToken()) != null){
            System.out.println(t.toString());
        }


    }
    
}
