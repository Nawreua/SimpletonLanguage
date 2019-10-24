package parse;

public class Token {
    public enum TokenType {
        FUNCTION,
        PRIMITIVE,
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

        public static TokenType fromStringToTokenType(String tok) {
            switch (tok) {
                case "function":
                    return TokenType.FUNCTION;
                case "primitive":
                    return TokenType.PRIMITIVE;
                case "takes":
                    return TokenType.TAKES;
                case "computes":
                    return TokenType.COMPUTES;
                case "end":
                    return TokenType.END;
                case "true":
                    return TokenType.TRUE;
                case "false":
                    return TokenType.FALSE;
                case "call":
                    return TokenType.CALL;
                case "with":
                    return TokenType.WITH;
                case "if":
                    return TokenType.IF;
                case "then":
                    return TokenType.THEN;
                case "else":
                    return TokenType.ELSE;
                case "nothing":
                    return TokenType.NOTHING;
                case "being":
                    return TokenType.BEING;
                case "and":
                    return TokenType.AND;
                case "import":
                    return TokenType.IMPORT;
            }
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
