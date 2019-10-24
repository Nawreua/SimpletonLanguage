package ast;

import visitor.DefaultVisitor;

public class IfExp extends Exp {
    private final Exp condExp;
    private final Exp thenExp;
    private final Exp elseExp;

    public IfExp(Exp condExp, Exp thenExp, Exp elseExp) {
        this.condExp = condExp;
        this.thenExp = thenExp;
        this.elseExp = elseExp;
    }

    public Exp getCondExp() {
        return condExp;
    }

    public Exp getThenExp() {
        return thenExp;
    }

    public Exp getElseExp() {
        return elseExp;
    }

    @Override
    public void accept(DefaultVisitor visitor) {
        visitor.visit(this);
    }
}
