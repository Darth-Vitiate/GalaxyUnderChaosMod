package server.galaxyunderchaos.data;

import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.KeyMapping;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.lwjgl.glfw.GLFW;
import server.galaxyunderchaos.lightsaber.LightsaberFormNetworking;
import server.galaxyunderchaos.lightsaber.SwitchLightsaberFormPacket;

/**
 * Handles the client‑side hot‑key that cycles the player’s current lightsaber form.
 *
 * Because the whole class is annotated with {@link Mod.EventBusSubscriber} using
 * Bus.FORGE, every static {@link SubscribeEvent} method below is automatically
 * registered without any manual MinecraftForge.EVENT_BUS.register(…).
 */
@Mod.EventBusSubscriber(
        modid = "galaxyunderchaos",
        value  = Dist.CLIENT,
        bus    = Mod.EventBusSubscriber.Bus.FORGE   // listen on the Forge bus
)
public final class KeyBindings {

    /**
     * V‑key in the normal “Gameplay” controls category.
     */
    public static final KeyMapping SWITCH_FORM_KEY = new KeyMapping(
            "key.galaxyunderchaos.switch_form",
            GLFW.GLFW_KEY_V,
            "key.categories.gameplay"
    );

    /* ───────────────────────────────────────────────────────────────────────── */

    /** Registers the key mapping on the client (runs once at startup). */
    @SubscribeEvent
    public static void registerKeyMappings(RegisterKeyMappingsEvent event) {
        event.register(SWITCH_FORM_KEY);
    }

    /** Fires every render‑tick when any key is pressed or released. */
    @SubscribeEvent
    public static void onKeyInput(InputEvent.Key event) {
        // consumeClick() returns true exactly *once* per key‑press
        if (SWITCH_FORM_KEY.consumeClick()) {
            //System.out.println("[CLIENT] V pressed – sending SwitchLightsaberFormPacket");
            LightsaberFormNetworking.sendToServer(new SwitchLightsaberFormPacket());
        }
    }

    // utility class – no instances
    private KeyBindings() {}
}
