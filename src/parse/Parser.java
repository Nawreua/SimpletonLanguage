package parse;

import ast.*;

import java.util.ArrayList;
import java.util.List;

public class Parser {
    private final Lexer lexer;

    public Parser(Lexer lexer) {
        this.lexer = lexer;
    }

    public List<AST> parse() {
        return ruleExps();
    }

    private List<AST> ruleExps() {
        var token = lexer.getToken();
        var res = new ArrayList<AST>();
        while (token.getType() != Token.TokenType.EOF) {
            if (token.getType() == Token.TokenType.IMPORT) {
              var id = lexer.resetAndGetToken();
              if (id.getType() != Token.TokenType.ID) {
                  System.exit(3);
              }
              res.add(new Import(id.getStringValue()));
            } else if (token.getType() == Token.TokenType.FUNCTION) {
                var id = lexer.resetAndGetToken();
                if (id.getType() != Token.TokenType.ID) {
                    System.exit(3);
                }
                token = lexer.resetAndGetToken();
                if (token.getType() != Token.TokenType.TAKES) {
                    System.exit(3);
                }
                lexer.resetToken();
                var args = ruleArgs();
                token = lexer.getToken();
                if (token.getType() != Token.TokenType.COMPUTES) {
                    System.exit(3);
                }
                lexer.resetToken();
                var body = ruleExp();
                token = lexer.getToken();
                if (token.getType() != Token.TokenType.END) {
                    System.exit(3);
                }
                res.add(new Function(id.getStringValue(), args, body));
            } else {
                res.add(ruleExp());
            }
            token = lexer.resetAndGetToken();
        }
        return res;
    }

    private Exp ruleExp() {
        var token = lexer.getToken();
        if (token.getType() == Token.TokenType.ID) {
            lexer.resetToken();
            return new Var(token.getStringValue());
        }
        if (token.getType() == Token.TokenType.INT) {
            lexer.resetToken();
            return new IntExp(token.getIntValue());
        }
        if (token.getType() == Token.TokenType.TRUE) {
            lexer.resetToken();
            return new BoolExp(true);
        }
        if (token.getType() == Token.TokenType.FALSE) {
            lexer.resetToken();
            return new BoolExp(false);
        }
        if (token.getType() == Token.TokenType.CALL) {
            var id = lexer.resetAndGetToken();
            if (id.getType() != Token.TokenType.ID)
                System.exit(3);
            token = lexer.resetAndGetToken();
            if (token.getType() != Token.TokenType.WITH)
                System.exit(3);
            lexer.resetToken();
            var parameters = ruleParams();
            token = lexer.getToken();
            if (token.getType() != Token.TokenType.END)
                System.exit(3);
            lexer.resetToken();
            return new CallExp(id.getStringValue(), parameters);
        }
        if (token.getType() == Token.TokenType.IF) {
            lexer.resetToken();
            var cond = ruleExp();
            token = lexer.getToken();
            if (token.getType() != Token.TokenType.THEN)
                System.exit(3);
            lexer.resetToken();
            var then = ruleExp();
            token = lexer.getToken();
            if (token.getType() == Token.TokenType.END) {
                lexer.resetToken();
                return new IfExp(cond, then);
            }
            if (token.getType() != Token.TokenType.ELSE) {
                System.exit(3);
            }
            lexer.resetToken();
            var optElse = ruleExp();
            token = lexer.getToken();
            if (token.getType() != Token.TokenType.END) {
                System.exit(3);
            }
            lexer.resetToken();
            return new IfExp(cond, then, optElse);
        }
        System.exit(3);
        return null;
    }

    private List<Arg> ruleArgs() {
        var token = lexer.getToken();
        if (token.getType() == Token.TokenType.NOTHING) {
            lexer.resetToken();
            return new ArrayList<>();
        }
        return ruleArg();
    }

    private List<Arg> ruleArg() {
        var id = lexer.getToken();
        if (id.getType() == Token.TokenType.ID) {
            var token = lexer.resetAndGetToken();
            if (token.getType() != Token.TokenType.BEING)
                System.exit(3);
            lexer.resetToken();
            var typeId = ruleTypeId();
            token = lexer.getToken();
            if (token.getType() != Token.TokenType.AND) {
                var list = new ArrayList<Arg>();
                list.add(new Arg(id.getStringValue(), typeId));
                return list;
            }
            lexer.resetToken();
            var list = ruleArg();
            list.add(0, new Arg(id.getStringValue(), typeId));
            return list;
        }
        System.exit(3);
        return null;
    }

    private List<Exp> ruleParams() {
        var token = lexer.getToken();
        if (token.getType() == Token.TokenType.NOTHING) {
            lexer.resetToken();
            return new ArrayList<>();
        }
        return ruleParam();
    }

    private List<Exp> ruleParam() {
        var token = lexer.getToken();
        var exp = ruleExp();
        token = lexer.getToken();
        if (token.getType() != Token.TokenType.AND) {
            var list = new ArrayList<Exp>();
            list.add(exp);
            return list;
        }
        lexer.resetToken();
        var list = ruleParam();
        list.add(0, exp);
        return list;
    }

    private TypeId ruleTypeId() {
        var token = lexer.getToken();
        if (token.getType() != Token.TokenType.ID) {
            System.exit(3);
        }
        lexer.resetToken();
        return new TypeId(token.getStringValue());
    }
}
