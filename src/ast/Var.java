package ast;

import visitor.DefaultVisitor;

public class Var extends Exp {
    private final String id;

    public Var(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    @Override
    public void accept(DefaultVisitor visitor) {
        visitor.visit(this);
    }
}
