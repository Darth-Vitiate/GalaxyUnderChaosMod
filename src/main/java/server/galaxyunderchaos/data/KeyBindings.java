package server.galaxyunderchaos.data;

import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.KeyMapping;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RegisterKeyMappingsEvent;
import org.lwjgl.glfw.GLFW;
import server.galaxyunderchaos.galaxyunderchaos;

/** Client‑side key bindings for Galaxy Under Chaos. */
@EventBusSubscriber(
        modid = galaxyunderchaos.MODID,
        bus   = EventBusSubscriber.Bus.MOD,
        value = Dist.CLIENT
)
public final class KeyBindings {

    public static final KeyMapping SWITCH_FORM_KEY = new KeyMapping(
            "key.galaxyunderchaos.switch_form",
            InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_V, "key.categories.galaxyunderchaos");

    public static final KeyMapping CYCLE_FORCE_POWER = new KeyMapping(
            "key.galaxyunderchaos.cycle_force_power",
            InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_G, "key.categories.galaxyunderchaos");

    public static final KeyMapping USE_FORCE_POWER = new KeyMapping(
            "key.galaxyunderchaos.use_force_power",
            InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_R, "key.categories.galaxyunderchaos");

    @SubscribeEvent
    public static void registerKeyMappings(RegisterKeyMappingsEvent event) {
        System.out.println("[DEBUG] Registering GalaxyUnderChaos KeyMappings");
        event.register(SWITCH_FORM_KEY);
        event.register(CYCLE_FORCE_POWER);
        event.register(USE_FORCE_POWER);
    }

    private KeyBindings() { } // utility class
}
