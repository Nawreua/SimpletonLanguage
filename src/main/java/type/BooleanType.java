package type;

public class BooleanType extends Type {
    private BooleanType() {}
    public static BooleanType instance = new BooleanType();

    @Override
    public String toString() {
        return "boolean";
    }
}
