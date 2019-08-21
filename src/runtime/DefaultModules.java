package runtime;

import java.util.Map;

public class DefaultModules {
    private static String operators =
            "primitive add being int takes l being int and r being int end\n" +
                    "primitive sub being int takes l being int and r being int end\n" +
                    "primitive div being int takes l being int and r being int end\n" +
                    "primitive mul being int takes l being int and r being int end\n" +
                    "primitive mod being int takes l being int and r being int end\n" +
                    "primitive not being boolean takes b being boolean end\n" +
                    "primitive land being boolean takes l being boolean and r being boolean end\n" +
                    "primitive lor being boolean takes l being boolean and r being boolean end";

    private static String output =
            "primitive print being boolean takes i being int end\n" +
                    "primitive lprint being boolean takes b being boolean end\n" +
                    "primitive println being boolean takes i being int end\n" +
                    "primitive lprintln being boolean takes b being boolean end";

    public static Map<String, String> modules = Map.of(
            "operators", operators,
            "output", output
    );
}
