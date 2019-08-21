package ast;

import visitor.DefaultVisitor;

import java.util.List;

public class Exps extends AST {
    private final List<AST> nodes;

    public Exps(List<AST> nodes) {
        this.nodes = nodes;
    }

    public List<AST> getNodes() {
        return nodes;
    }

    @Override
    public void accept(DefaultVisitor visitor) {
        visitor.visit(this);
    }
}
