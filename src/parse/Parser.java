package parse;

import ast.*;
import runtime.DefaultModules;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Parser {

    private void assertToken(Token.TokenType actual, Token.TokenType expected) {
        if (expected != actual) {
            System.err.println("Error while parsing: expected: " + expected + ", actual: " + actual);
            System.exit(3);
        }
    }

    private List<AST> handleImport(String module) {
        try {
            var module_file = new File(module + ".singleton");
            Lexer lexer;
            if (module_file.exists())
                lexer = new Lexer(new BufferedReader(new FileReader(module_file)));
            else
                lexer = new Lexer(new BufferedReader(new StringReader(DefaultModules.modules.get(module))));
            var parser = new Parser(lexer);
            var ast = parser.parse();
            if (ast instanceof Exps)
                return ((Exps) ast).getNodes();
            else
                return List.of(ast);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return List.of();
    }

    private final Lexer lexer;

    public Parser(Lexer lexer) {
        this.lexer = lexer;
    }

    public AST parse() {
        return ruleExps();
    }

    private Exps ruleExps() {
        var token = lexer.getToken();
        var res = new ArrayList<AST>();
        while (token.getType() != Token.TokenType.EOF) {
            if (token.getType() == Token.TokenType.IMPORT) {
                var id = lexer.resetAndGetToken();
                assertToken(id.getType(), Token.TokenType.ID);
                res.addAll(handleImport(id.getStringValue()));
            } else if (token.getType() == Token.TokenType.FUNCTION) {
                var id = lexer.resetAndGetToken();
                assertToken(id.getType(), Token.TokenType.ID);
                token = lexer.resetAndGetToken();
                assertToken(token.getType(), Token.TokenType.TAKES);
                lexer.resetToken();
                var args = ruleArgs();
                token = lexer.getToken();
                assertToken(token.getType(), Token.TokenType.COMPUTES);
                lexer.resetToken();
                var body = ruleExp();
                token = lexer.getToken();
                assertToken(token.getType(), Token.TokenType.END);
                res.add(new Function(id.getStringValue(), args, body));
            } else if (token.getType() == Token.TokenType.PRIMITIVE) {
                var id = lexer.resetAndGetToken();
                assertToken(id.getType(), Token.TokenType.ID);
                token = lexer.resetAndGetToken();
                assertToken(token.getType(), Token.TokenType.TAKES);
                lexer.resetToken();
                var args = ruleArgs();
                token = lexer.getToken();
                assertToken(token.getType(), Token.TokenType.END);
                res.add(new Function(id.getStringValue(), args, null));
            } else {
                res.add(ruleExp());
            }
            token = lexer.resetAndGetToken();
        }
        return new Exps(res);
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
            assertToken(id.getType(), Token.TokenType.ID);
            token = lexer.resetAndGetToken();
            assertToken(token.getType(), Token.TokenType.WITH);
            lexer.resetToken();
            var parameters = ruleParams();
            token = lexer.getToken();
            assertToken(token.getType(), Token.TokenType.END);
            lexer.resetToken();
            return new CallExp(id.getStringValue(), parameters);
        }
        if (token.getType() == Token.TokenType.IF) {
            lexer.resetToken();
            var cond = ruleExp();
            token = lexer.getToken();
            assertToken(token.getType(), Token.TokenType.THEN);
            lexer.resetToken();
            var then = ruleExp();
            token = lexer.getToken();
            if (token.getType() == Token.TokenType.END) {
                lexer.resetToken();
                return new IfExp(cond, then);
            }
            assertToken(token.getType(), Token.TokenType.ELSE);
            lexer.resetToken();
            var optElse = ruleExp();
            token = lexer.getToken();
            assertToken(token.getType(), Token.TokenType.END);
            lexer.resetToken();
            return new IfExp(cond, then, optElse);
        }
        System.err.println("Error while parsing: rule exp does not match for token " + token.getType());
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
            assertToken(token.getType(), Token.TokenType.BEING);
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
        System.err.println("Error while parsing: rule arg does not match for token " + id.getType());
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
        assertToken (token.getType(), Token.TokenType.ID);
        lexer.resetToken();
        return new TypeId(token.getStringValue());
    }
}
