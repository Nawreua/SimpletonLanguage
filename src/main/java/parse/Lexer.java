package parse;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Lexer {
    private final BufferedReader reader;
    private List<String> line;
    private Token token;

    public Lexer(BufferedReader reader) {
        this.reader = reader;
    }

    public Token getToken() {
        if (token == null)
            token = getNextToken();
        return token;
    }

    public void resetToken() {
        token = null;
    }

    public Token resetAndGetToken() {
        token = null;
        return getToken();
    }

    private Token getNextToken() {
        if (line == null || line.isEmpty()) {
            String optLine = null;
            try {
                do {
                    optLine = reader.readLine();
                    if (optLine == null)
                        return new Token(Token.TokenType.EOF);
                    optLine = optLine.trim();
                } while (optLine.equals(""));
            } catch (IOException e) {
                e.printStackTrace();
                System.exit(1);
            }
            line = new ArrayList(Arrays.asList(optLine.split("[ \t]+")));
        }
        var tok = line.remove(0);
        var token = Token.TokenType.fromStringToTokenType(tok);
        if (token != null)
            return new Token(token);
        if (tok.matches("[0-9]+")) {
            return new Token(Token.TokenType.INT, Integer.parseInt(tok));
        }
        if (tok.matches("[a-zA-Z][a-zA-Z0-9_]*")) {
            return new Token(Token.TokenType.ID, tok);
        }
        System.err.println("Invalid characters: " + tok);
        System.exit(2);
        return null;
    }
}
