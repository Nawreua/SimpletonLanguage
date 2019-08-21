package ast;

import visitor.DefaultVisitor;

public class BoolExp extends Exp {
    private final boolean value;

    public BoolExp(boolean value) {
        this.value = value;
    }

    public boolean getValue() {
        return value;
    }

    @Override
    public void accept(DefaultVisitor visitor) {
        visitor.visit(this);
    }
}
