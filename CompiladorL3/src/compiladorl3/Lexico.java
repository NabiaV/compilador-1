package compiladorl3;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Lexico {

    BufferedReader reader;
    char c;
    List<Token> tokenList = new ArrayList<>();

    private char[] conteudo;
    private int indiceConteudo;

    public static final String PALAVRAS_CHAVES[] = new String[]{
        "import", "class", "while", "if", "else", "public",
        "private", "protected", "switch", "case", "super",
        "static", "implements", "interface", "package", "new",
        "continue", "try", "this", "final", "byte", "int", "char",
        "String", "float", "double", "boolean", "return"};


        public Lexico(String caminhoCodigoFonte){
            try {
                String conteudoStr;
                conteudoStr = new String(Files.readAllBytes(Paths.get(caminhoCodigoFonte)));
                this.conteudo = conteudoStr.toCharArray();
                this.indiceConteudo = 0;                        
            } catch (IOException ex) {
                ex.printStackTrace();
            }        
        }

    List<Token> generateTokens() {
        Token token = lerNextToken();
        while (token != null) {
            tokenList.add(token);
            token = lerNextToken();
        }
        return tokenList;
    }

    Token lerNextToken() {
        int estado = 1;

        while (true) {
            if (c == (char) (-1)) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return null;
            }

            switch (estado) {
                case 1: {
                    if (c == ' ' || c == '\n' || c == '\t'
                            || c == '\f' || c == '\b' || c == '\r') {
                        c = lerNextChar();
                        continue;
                    } else if (c == ';') {
                        c = lerNextChar();
                        return new Token("Ponto e virgula", ";");
                    } else if (c == '+') {
                        c = lerNextChar();
                        return new Token("Adição", "+");
                    } else if (c == '-') {
                        c = lerNextChar();
                        return new Token("Subtração", "-");
                    } else if (c == '*') {
                        c = lerNextChar();
                        return new Token("Multiplicação", "*");
                    } else if (c == '/') {
                        c = lerNextChar();

                        return new Token("Divisão", "/");
                    } else if (c == '%') {
                        c = lerNextChar();
                        return new Token("Percentagem", "%");
                    } else if (c == '{') {
                        c = lerNextChar();
                        return new Token("Chave esquerda", "{");
                    } else if (c == '}') {
                        c = lerNextChar();
                        return new Token("Chave direita", "}");
                    } else if (c == '(') {
                        c = lerNextChar();
                        return new Token("Parentese esquerdo", "(");
                    } else if (c == ')') {
                        c = lerNextChar();
                        return new Token("Parentese direito", ")");
                    } else if (c == ',') {
                        c = lerNextChar();
                        return new Token("Virgula", ",");
                    } else if (c == '=') {
                        c = lerNextChar();
                        if (c == '=') {
                            c = lerNextChar();
                            return new Token("Igualdade", "==");
                        } else {
                            return new Token("Operador de atribuição", "=");
                        }
                    } else if (c == '!') {
                        c = lerNextChar();
                        if (c == '=') {
                            c = lerNextChar();
                            return new Token("Negação ", "!=");
                        } else {
                            return new Token("Não definido", "!");
                        }
                    } else if (c == '&') {
                        c = lerNextChar();
                        if (c == '&') {
                            c = lerNextChar();
                            return new Token("Condicional e", "&&");
                        } else {
                            return new Token("Não definido", "&");
                        }
                    } else if (c == '|') {
                        c = lerNextChar();
                        if (c == '|') {
                            c = lerNextChar();
                            return new Token("Condicional ou", "||");
                        } else {
                            return new Token("Não definido", "|");
                        }
                    } else {
                        estado = 2;
                        continue;
                    }

                }
                case 2: {
                    if (ehNumero(c)) {
                        String num = String.valueOf(c);
                        for (;;) {
                            c = lerNextChar();
                            if (ehNumero(c) || c == '.') {
                                num += String.valueOf(c);
                            } else {
                                if (num.contains(".")) {
                                    return new Token("Decimal", num);
                                } else {
                                    return new Token("Inteiro", num);
                                }
                            }
                        }
                    } else {
                        estado = 3;
                    }
                }
                case 3: {
                    if (ehLetra(c) || c == '_') {
                        String word = String.valueOf(estado);
                        for (;;) {
                            c = lerNextChar();
                            if (ehLetra(c) || c == '_' || ehNumero(c)) {
                                word += String.valueOf(c);

                            } else {
                                List palavras_chaves = Arrays.asList(PALAVRAS_CHAVES);

                                if (palavras_chaves.contains(word)) {
                                    return new Token("Palavra", word);
                                } else {
                                    return new Token("Palavra reservada", word);
                                }
                            }
                        }
                    } else {
                        c = lerNextChar();
                        return new Token(c + "Erro", "Não definido");
                    }
                }
            }
        }
    }
    
    char lerNextChar() {
        return this.conteudo[this.indiceConteudo++];
    }

    boolean ehNumero(char c) {
        if (c >= '0' && c <= '9') {
            return true;
        }

        return false;
    }

    boolean ehLetra(char c) {
        if (c >= 'a' && c <= 'z') {
            return true;
        }
        if (c >= 'A' && c <= 'Z') {
            return true;
        }

        return false;
    }
}
