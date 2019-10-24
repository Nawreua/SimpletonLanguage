package runtime;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Stack;

public class RuntimeFunctions {

    private static Stack<Object> arguments = new Stack<>();

    public static Object dispatchPrimitive(String primitive, List<Object> values) {
        values.forEach(arguments::push);
        Object value = null;
        try {
            value = RuntimeFunctions.class.getDeclaredMethod(primitive).invoke(null);
        } catch (IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < values.size(); i++) arguments.pop();
        return value;
    }

    private static boolean print() {
        System.out.print(arguments.peek());
        return true;
    }

    private static boolean println() {
        System.out.println(arguments.peek());
        return true;
    }

    private static boolean lprint() {
        System.out.print(arguments.peek());
        return true;
    }

    private static boolean lprintln() {
        System.out.println(arguments.peek());
        return true;
    }

    private static boolean not() {
        return !(Boolean)arguments.peek();
    }

    private static int add() {
        var r = (Integer)arguments.get(arguments.size() - 1);
        var l = (Integer)arguments.get(arguments.size() - 2);
        return l + r;
    }

    private static int sub() {
        var r = (Integer)arguments.get(arguments.size() - 1);
        var l = (Integer)arguments.get(arguments.size() - 2);
        return l - r;
    }

    private static boolean land() {
        var r = (Boolean)arguments.get(arguments.size() - 1);
        var l = (Boolean)arguments.get(arguments.size() - 2);
        return l && r;
    }

    private static boolean lor() {
        var r = (Boolean)arguments.get(arguments.size() - 1);
        var l = (Boolean)arguments.get(arguments.size() - 2);
        return l || r;
    }

    private static boolean eq() {
        var r = (Integer)arguments.get(arguments.size() - 1);
        var l = (Integer)arguments.get(arguments.size() - 2);
        return l.equals(r);
    }

    private static int mul() {
        var r = (Integer)arguments.get(arguments.size() - 1);
        var l = (Integer)arguments.get(arguments.size() - 2);
        return l * r;
    }

    private static int div() {
        var r = (Integer)arguments.get(arguments.size() - 1);
        var l = (Integer)arguments.get(arguments.size() - 2);
        return l / r;
    }

    private static int mod() {
        var r = (Integer)arguments.get(arguments.size() - 1);
        var l = (Integer)arguments.get(arguments.size() - 2);
        return l % r;
    }

    private static boolean ne() {
        var r = (Integer)arguments.get(arguments.size() - 1);
        var l = (Integer)arguments.get(arguments.size() - 2);
        return !l.equals(r);
    }

    private static boolean lt() {
        var r = (Integer)arguments.get(arguments.size() - 1);
        var l = (Integer)arguments.get(arguments.size() - 2);
        return l < r;
    }

    private static boolean le() {
        var r = (Integer)arguments.get(arguments.size() - 1);
        var l = (Integer)arguments.get(arguments.size() - 2);
        return l <= r;
    }

    private static boolean gt() {
        var r = (Integer)arguments.get(arguments.size() - 1);
        var l = (Integer)arguments.get(arguments.size() - 2);
        return l > r;
    }

    private static boolean ge() {
        var r = (Integer)arguments.get(arguments.size() - 1);
        var l = (Integer)arguments.get(arguments.size() - 2);
        return l >= r;
    }
}
