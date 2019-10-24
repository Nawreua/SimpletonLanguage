package ast;

import visitor.DefaultVisitor;

import java.util.List;

public class CallExp extends Exp {
    private final String id;
    private final List<Exp> parameters;
    private Function def;

    public CallExp(String id, List<Exp> parameters) {
        this.id = id;
        this.parameters = parameters;
    }

    public String getId() {
        return id;
    }

    public List<Exp> getParameters() {
        return parameters;
    }

    @Override
    public void accept(DefaultVisitor visitor) {
        visitor.visit(this);
    }

    public Function getDef() {
        return def;
    }

    public void setDef(Function def) {
        this.def = def;
    }
}
