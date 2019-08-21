package visitor;

import ast.*;
import type.BooleanType;
import type.IntType;
import type.Type;

public class TypeChecker extends DefaultVisitor {

    private Type type(AST e) {
        if (e.getType() == null)
            e.accept(this);
        return e.getType();
    }

    private void assertType(Type actual, Type expected) {
        if (expected != actual) {
            System.err.println("Error while typing: expected: " + expected + ", actual: " + actual);
            System.exit(5);
        }
    }

    @Override
    public void visit(Arg e) {
        e.setType(type(e.getTypeId()));
    }

    @Override
    public void visit(BoolExp e) {
        e.setType(BooleanType.instance);
    }

    @Override
    public void visit(CallExp e) {
        if (e.getParameters().size() != e.getDef().getArguments().size()) {
            System.err.println("Error while typing: invalid number of arguments for call to " + e.getId());
            System.exit(5);
        }
        for (int i = 0; i < e.getParameters().size(); i++)
            assertType(type(e.getParameters().get(i)), type(e.getDef().getArguments().get(i)));
        e.setType(type(e.getDef()));
    }

    @Override
    public void visit(Function e) {
        e.setType(type(e.getTypeId()));
        e.getArguments().forEach(this::type);
        if (e.getBody() != null)
            assertType(type(e.getBody()), e.getType());
    }

    @Override
    public void visit(IfExp e) {
        assertType(type(e.getCondExp()), BooleanType.instance);
        e.setType(type(e.getThenExp()));
        assertType(type(e.getElseExp()), type(e.getThenExp()));
    }

    @Override
    public void visit(IntExp e) {
        e.setType(IntType.instance);
    }

    @Override
    public void visit(TypeId e) {
        if (e.getId().equals("int"))
            e.setType(IntType.instance);
        else if (e.getId().equals("boolean"))
            e.setType(BooleanType.instance);
        else {
            System.err.println("Error while typing: unknown type: " + e.getId());
            System.exit(5);
        }
    }

    @Override
    public void visit(Var e) {
        e.setType(type(e.getDef()));
    }
}
