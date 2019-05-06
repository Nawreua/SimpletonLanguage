package ast;

import visitor.DefaultVisitor;

public class IntExp extends Exp {
    private final int value;

    public IntExp(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    @Override
    public void accept(DefaultVisitor visitor) {
        visitor.visit(this);
    }
}
