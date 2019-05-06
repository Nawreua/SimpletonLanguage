import parse.Lexer;
import parse.Parser;
import visitor.PrettyPrinter;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class Main {
    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Need a file");
            System.exit(1);
        }
        try {
            Lexer lexer = new Lexer(new BufferedReader(new FileReader(args[0])));
            Parser parser = new Parser(lexer);
            var exps = parser.parse();
            PrettyPrinter prettyPrinter = new PrettyPrinter();
            for (var exp : exps) {
                prettyPrinter.visit(exp);
                System.out.println();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
