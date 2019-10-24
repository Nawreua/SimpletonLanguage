package type;

public class IntType extends Type {
    private IntType() {}
    public static IntType instance = new IntType();

    @Override
    public String toString() {
        return "int";
    }
}
