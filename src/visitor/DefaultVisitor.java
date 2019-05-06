package visitor;

import ast.*;

import java.util.List;

public class DefaultVisitor {

    public void visit(AST e) {
        e.accept(this);
    }

    public void visit(Arg e) {
        e.getTypeId().accept(this);
    }

    public void visit(BoolExp e) {
    }

    public void visit(CallExp e) {
        for (var exp: e.getParameters()) {
            exp.accept(this);
        }
    }

    public void visit(Function e) {
        for (var arg: e.getArguments())
            arg.accept(this);
        e.getBody().accept(this);
    }

    public void visit(IfExp e) {
        e.getCondExp().accept(this);
        e.getThenExp().accept(this);
        e.getElseExp().ifPresent(x -> x.accept(this));
    }

    public void visit(IntExp e) {
    }

    public void visit(TypeId e) {
    }

    public void visit(Var e) {
    }

    public void visit(Import e) {
    }
}
