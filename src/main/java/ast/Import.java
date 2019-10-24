package ast;

import visitor.DefaultVisitor;

public class Import extends AST {
    private final String imported;

    public Import(String imported) {
        this.imported = imported;
    }

    public String getImported() {
        return imported;
    }

    @Override
    public void accept(DefaultVisitor visitor) {
        visitor.visit(this);
    }
}
