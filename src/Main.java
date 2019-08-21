import parse.Lexer;
import parse.Parser;
import visitor.Binder;
import visitor.Interpreter;
import visitor.PrettyPrinter;
import visitor.TypeChecker;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class Main {
    public static void main(String[] args) {
        try {
            Lexer lexer = new Lexer(new BufferedReader(new FileReader(args[0])));
            Parser parser = new Parser(lexer);
            var ast = parser.parse();
            //PrettyPrinter prettyPrinter = new PrettyPrinter();
            //prettyPrinter.visit(ast);
            Binder binder = new Binder();
            binder.visit(ast);
            TypeChecker typeChecker = new TypeChecker();
            typeChecker.visit(ast);
            Interpreter interpreter = new Interpreter();
            interpreter.visit(ast);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
