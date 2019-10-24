package visitor;

import ast.*;

public class PrettyPrinter extends DefaultVisitor {

    @Override
    public void visit(Exps e) {
        for (var exp : e.getNodes()) {
            exp.accept(this);
            System.out.println();
        }
    }

    @Override
    public void visit(Arg e) {
        System.out.print(e.getId() + " being ");
        e.getTypeId().accept(this);
    }

    @Override
    public void visit(BoolExp e) {
        System.out.print(e.getValue());
    }

    @Override
    public void visit(CallExp e) {
        System.out.print("call " + e.getId() + " with ");
        for (int i = 0; i < e.getParameters().size(); i++) {
            e.getParameters().get(i).accept(this);
            if (i != e.getParameters().size() - 1)
                System.out.print(" and ");
        }
        System.out.print(" end");
    }

    @Override
    public void visit(Function e) {
        if (e.getBody() != null)
            System.out.print("function " + e.getId());
        else
            System.out.print("primitive " + e.getId());
        System.out.print(" being ");
        e.getTypeId().accept(this);
        System.out.print(" takes ");
        for (int i = 0; i < e.getArguments().size(); i++) {
            e.getArguments().get(i).accept(this);
            if (i != e.getArguments().size() - 1)
                System.out.print(" and ");
        }
        if (e.getBody() != null) {
            System.out.println(" computes");
            e.getBody().accept(this);
            System.out.println(System.lineSeparator() + "end");
        }
    }

    @Override
    public void visit(IfExp e) {
        System.out.print("if ");
        e.getCondExp().accept(this);
        System.out.print(" then ");
        e.getThenExp().accept(this);
        System.out.print(" else ");
        e.getElseExp().accept(this);
        System.out.print(" end");
    }

    @Override
    public void visit(IntExp e) {
        System.out.print(e.getValue());
    }

    @Override
    public void visit(TypeId e) {
        System.out.print(e.getId());
    }

    @Override
    public void visit(Var e) {
        System.out.print(e.getId());
    }

    @Override
    public void visit(Import e) {
        System.out.println("import " + e.getImported());
    }
}
