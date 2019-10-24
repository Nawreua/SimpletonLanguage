package ast;

import type.Type;
import visitor.DefaultVisitor;

public abstract class AST {
    protected Type type;
    public abstract void accept(DefaultVisitor visitor);

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }
}
