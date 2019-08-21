package visitor;

import ast.*;
import runtime.RuntimeFunctions;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;
import java.util.stream.Collectors;

public class Interpreter extends DefaultVisitor {
    public Interpreter() {
        args = new Stack<>();
    }

    private Stack<Map<Arg, Object>> args;
    private Object value;

    private Object interpret(AST e) {
        e.accept(this);
        return value;
    }

    @Override
    public void visit(Function e) { }

    @Override
    public void visit(BoolExp e) {
        value = e.getValue();
    }

    @Override
    public void visit(CallExp e) {
        var function = e.getDef();
        if (function.getBody() != null) {
            args.push(args.empty() ? new HashMap<>() : new HashMap<>(args.peek()));
            for (int i = 0; i < e.getParameters().size(); i++)
                args.peek().put(function.getArguments().get(i), interpret(e.getParameters().get(i)));
            value = interpret(function.getBody());
            args.pop();
        } else {
            value = RuntimeFunctions.dispatchPrimitive(e.getId(),
                    e.getParameters().stream().map(this::interpret).collect(Collectors.toList()));
        }
    }

    @Override
    public void visit(IfExp e) {
        if ((boolean)interpret(e.getCondExp())) {
            value = interpret(e.getThenExp());
        } else {
            value = interpret(e.getElseExp());
        }
    }

    @Override
    public void visit(IntExp e) {
        value = e.getValue();
    }

    @Override
    public void visit(Var e) {
        value = args.peek().get(e.getDef());
    }
}
