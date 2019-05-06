package parse;

public class Token {
    public enum TokenType {
        FUNCTION,
        TAKES,
        COMPUTES,
        END,
        TRUE,
        FALSE,
        CALL,
        WITH,
        IF,
        THEN,
        ELSE,
        NOTHING,
        BEING,
        AND,
        IMPORT,
        ID,
        INT,
        EOF;

        static TokenType fromStringToTokenType(String tok) {
            if (tok.equals("function"))
                return TokenType.FUNCTION;
            else if (tok.equals("takes"))
                return TokenType.TAKES;
            else if (tok.equals("computes"))
                return TokenType.COMPUTES;
            else if (tok.equals("end"))
                return TokenType.END;
            else if (tok.equals("true"))
                return TokenType.TRUE;
            else if (tok.equals("false"))
                return TokenType.FALSE;
            else if (tok.equals("call"))
                return TokenType.CALL;
            else if (tok.equals("with"))
                return TokenType.WITH;
            else if (tok.equals("if"))
                return TokenType.IF;
            else if (tok.equals("then"))
                return TokenType.THEN;
            else if (tok.equals("else"))
                return TokenType.ELSE;
            else if (tok.equals("nothing"))
                return TokenType.NOTHING;
            else if (tok.equals("being"))
                return TokenType.BEING;
            else if (tok.equals("and"))
                return TokenType.AND;
            else if (tok.equals("import"))
                return TokenType.IMPORT;
            return null;
        }
    }

    private final TokenType type;
    private final String stringValue;
    private final int intValue;

    Token(TokenType type) {
        this.type = type;
        this.stringValue = "";
        this.intValue = 0;
    }

    Token(TokenType type, String stringValue) {
        this.type = type;
        this.stringValue = stringValue;
        this.intValue = 0;
    }

    Token(TokenType type, int intValue) {
        this.type = type;
        this.stringValue = "";
        this.intValue = intValue;
    }

    public TokenType getType() {
        return type;
    }

    public String getStringValue() {
        return stringValue;
    }

    public int getIntValue() {
        return intValue;
    }
}
