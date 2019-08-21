package runtime;

import java.util.Map;

public class DefaultModules {
    private static String operators =
            "primitive add takes l being int and r being int end\n" +
                    "primitive sub takes l being int and r being int end\n" +
                    "primitive div takes l being int and r being int end\n" +
                    "primitive mul takes l being int and r being int end\n" +
                    "primitive mod takes l being int and r being int end\n" +
                    "primitive not takes b being boolean end\n" +
                    "primitive land takes l being boolean and r being bool end\n" +
                    "primitive lor takes l being boolean and r being bool end";

    public static Map<String, String> modules = Map.of(
      "operators", operators
    );
}
