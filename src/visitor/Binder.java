package visitor;

import ast.*;
import common.CommonMethods;

import java.util.HashMap;
import java.util.Map;

public class Binder extends DefaultVisitor {

    private Map<String, Function> functions;
    private Map<String, Arg> parameters;

    public Binder() {
        functions = new HashMap<>();
        parameters = new HashMap<>();
    }

    @Override
    public void visit(Arg e) {
        if (parameters.containsKey(e.getId()))
            CommonMethods.errAndExit("binding", "parameter " + e.getId() + " previously defined", 4);
        parameters.put(e.getId(), e);
        e.getTypeId().accept(this);
    }

    @Override
    public void visit(CallExp e) {
        if (functions.containsKey(e.getId())) {
            e.setDef(functions.get(e.getId()));
        } else
            CommonMethods.errAndExit("binding", "function " + e.getId() + " undefined", 4);
        super.visit(e);
    }

    @Override
    public void visit(Function e) {
        for (var arg : e.getArguments())
            arg.accept(this);
        if (functions.containsKey(e.getId()))
            CommonMethods.errAndExit("binding", "function " + e.getId() + " previously defined", 4);
        functions.put(e.getId(), e);
        if (e.getBody() != null)
            e.getBody().accept(this);
        parameters.clear();
    }

    @Override
    public void visit(Var e) {
        if (parameters.containsKey(e.getId())) {
            e.setDef(parameters.get(e.getId()));
        } else {
            CommonMethods.errAndExit("binding", "parameter " + e.getId() + " undefined", 4);
        }
    }
}
