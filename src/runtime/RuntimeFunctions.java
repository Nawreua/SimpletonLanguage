package runtime;

import java.util.List;

public class RuntimeFunctions {

    public static Object dispatchPrimitive(String primitive, List<Object> values) {
        if (primitive.equals("println"))
            return println((Integer) values.get(0));
        if (primitive.equals("not"))
            return not((Boolean)values.get(0));
        if (primitive.equals("add"))
            return add((Integer) values.get(0), (Integer)values.get(1));
        if (primitive.equals("eq"))
            return eq((Integer)values.get(0), (Integer)values.get(1));
        if (primitive.equals("mul"))
            return mul((Integer)values.get(0), (Integer)values.get(1));
        if (primitive.equals("sub"))
            return sub((Integer)values.get(0), (Integer)values.get(1));
        return null;
    }

    private static boolean println(int i) {
        System.out.println(i);
        return true;
    }

    private static boolean not(boolean b) {
        return !b;
    }

    private static int add(int l, int r) {
        return l + r;
    }

    private static int sub(int l, int r) { return l - r; }

    private static boolean eq(int l, int r) { return  l == r; }

    private static int mul(int l, int r) { return l * r; }
}
