package ast;

import visitor.DefaultVisitor;

import java.util.Optional;

public class IfExp extends Exp {
    private final Exp condExp;
    private final Exp thenExp;
    private final Optional<Exp> elseExp;

    public IfExp(Exp condExp, Exp thenExp) {
        this.condExp = condExp;
        this.thenExp = thenExp;
        this.elseExp = Optional.empty();
    }

    public IfExp(Exp condExp, Exp thenExp, Exp elseExp) {
        this.condExp = condExp;
        this.thenExp = thenExp;
        this.elseExp = Optional.ofNullable(elseExp);
    }

    public Exp getCondExp() {
        return condExp;
    }

    public Exp getThenExp() {
        return thenExp;
    }

    public Optional<Exp> getElseExp() {
        return elseExp;
    }

    @Override
    public void accept(DefaultVisitor visitor) {
        visitor.visit(this);
    }
}
