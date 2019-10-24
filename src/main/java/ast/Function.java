package ast;

import type.Type;
import visitor.DefaultVisitor;

import java.util.List;

public class Function extends AST {
    private final String id;
    private final TypeId typeId;
    private final List<Arg> arguments;
    private final Exp body;

    public Function(String id, TypeId typeId, List<Arg> arguments, Exp body) {
        this.id = id;
        this.typeId = typeId;
        this.arguments = arguments;
        this.body = body;
    }

    public String getId() {
        return id;
    }

    public TypeId getTypeId() { return typeId; }

    public List<Arg> getArguments() {
        return arguments;
    }

    public Exp getBody() {
        return body;
    }

    @Override
    public void accept(DefaultVisitor visitor) {
        visitor.visit(this);
    }

}
