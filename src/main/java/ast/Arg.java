package ast;

import visitor.DefaultVisitor;

public class Arg extends AST {
    private final String id;
    private final TypeId typeId;

    public Arg(String id, TypeId typeId) {
        this.id = id;
        this.typeId = typeId;
    }

    public String getId() {
        return id;
    }

    public TypeId getTypeId() {
        return typeId;
    }

    @Override
    public void accept(DefaultVisitor visitor) {
        visitor.visit(this);
    }
}
