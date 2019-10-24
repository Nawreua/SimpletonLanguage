package common;

public class CommonMethods {
    public static void errAndExit(String step, String error, int code) {
        System.err.println("Error while " + step + ": " + error);
        System.exit(code);
    }
}
