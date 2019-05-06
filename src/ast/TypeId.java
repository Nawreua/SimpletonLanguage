package ast;

import visitor.DefaultVisitor;

public class TypeId extends AST {
    private final String id;

    public TypeId(String id) {
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
