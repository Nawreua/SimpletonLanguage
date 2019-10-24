package runtime;

import java.util.Map;

public class DefaultModules {
    public static String operators =
            "primitive add being int takes l being int and r being int end\n" +
                    "primitive sub being int takes l being int and r being int end\n" +
                    "primitive div being int takes l being int and r being int end\n" +
                    "primitive mul being int takes l being int and r being int end\n" +
                    "primitive mod being int takes l being int and r being int end\n" +
                    "primitive not being boolean takes b being boolean end\n" +
                    "primitive land being boolean takes l being boolean and r being boolean end\n" +
                    "primitive lor being boolean takes l being boolean and r being boolean end\n" +
                    "primitive eq being boolean takes l being int and r being int end\n" +
                    "primitive ne being boolean takes l being int and r being int end\n" +
                    "primitive lt being boolean takes l being int and r being int end\n" +
                    "primitive le being boolean takes l being int and r being int end\n" +
                    "primitive gt being boolean takes l being int and r being int end\n" +
                    "primitive ge being boolean takes l being int and r being int end";

    public static String output =
            "primitive print being boolean takes i being int end\n" +
                    "primitive lprint being boolean takes b being boolean end\n" +
                    "primitive println being boolean takes i being int end\n" +
                    "primitive lprintln being boolean takes b being boolean end";
}
