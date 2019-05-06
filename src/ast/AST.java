package ast;

import visitor.DefaultVisitor;

public abstract class AST {
    public abstract void accept(DefaultVisitor visitor);
}
