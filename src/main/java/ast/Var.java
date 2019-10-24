package ast;

import visitor.DefaultVisitor;

public class Var extends Exp {
    private final String id;
    private Arg def;

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

    public Arg getDef() {
        return def;
    }

    public void setDef(Arg def) {
        this.def = def;
    }
}
