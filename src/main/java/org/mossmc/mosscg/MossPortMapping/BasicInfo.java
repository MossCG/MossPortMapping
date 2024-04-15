package org.mossmc.mosscg.MossPortMapping;

import org.mossmc.mosscg.MossLib.Object.ObjectLogger;

public class BasicInfo {
    public static String version = "V1.0.0.0.0000";
    public static String author = "MossCG";

    public static mode runMode;
    public enum mode {
        SERVER,CLIENT
    }

    public static ObjectLogger logger;
}
