package server.galaxyunderchaos.data;

import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.KeyMapping;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.lwjgl.glfw.GLFW;

@Mod.EventBusSubscriber(modid = "galaxyunderchaos", value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public final class KeyBindings {

    public static final KeyMapping SWITCH_FORM_KEY = new KeyMapping(
            "key.galaxyunderchaos.switch_form",
            InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_V, "key.categories.galaxyunderchaos"
    );

    public static final KeyMapping CYCLE_FORCE_POWER = new KeyMapping(
            "key.galaxyunderchaos.cycle_force_power",
            InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_G, "key.categories.galaxyunderchaos"
    );
//    public static final KeyMapping TOGGLE_LIGHTSABER = new KeyMapping(
//            "key.galaxyunderchaos.toggle_lightsaber",
//            InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_UP, "key.categories.galaxyunderchaos"
//    );
    public static final KeyMapping USE_FORCE_POWER = new KeyMapping(
            "key.galaxyunderchaos.use_force_power",
            InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_R, "key.categories.galaxyunderchaos"
    );

    @SubscribeEvent
    public static void registerKeyMappings(RegisterKeyMappingsEvent event) {
        System.out.println("[DEBUG] Registering GalaxyUnderChaos KeyMappings");
        event.register(SWITCH_FORM_KEY);
        event.register(CYCLE_FORCE_POWER);
        event.register(USE_FORCE_POWER);
//        event.register(TOGGLE_LIGHTSABER);

    }

    private KeyBindings() {} // Utility class - prevent instantiation
}