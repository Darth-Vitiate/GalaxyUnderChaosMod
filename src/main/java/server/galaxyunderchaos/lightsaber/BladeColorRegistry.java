package server.galaxyunderchaos.lightsaber;

import java.util.HashMap;
import java.util.Map;

public class BladeColorRegistry {
    private static final Map<String, String> colorMapping = new HashMap<>();

    static {
        colorMapping.put("red_kyber", "red");
        colorMapping.put("blue_kyber", "blue");
        colorMapping.put("green_kyber", "green");
        colorMapping.put("yellow_kyber", "yellow");
        colorMapping.put("cyan_kyber", "cyan");
        colorMapping.put("white_kyber", "white");
        colorMapping.put("magenta_kyber", "magenta");
        colorMapping.put("purple_kyber", "purple");
        colorMapping.put("pink_kyber", "pink");
        colorMapping.put("lime_green_kyber", "lime_green");
        colorMapping.put("turquoise_kyber", "turquoise");
        colorMapping.put("blood_orange_kyber", "blood_orange");
        colorMapping.put("orange_kyber", "orange");
    }

    public static String getBladeColor(String kyberCrystal) {
        return colorMapping.getOrDefault(kyberCrystal, "unknown");
    }
}
