package server.galaxyunderchaos.data;

import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.client.settings.KeyConflictContext;
import net.minecraft.client.KeyMapping;
import org.lwjgl.glfw.GLFW;
import server.galaxyunderchaos.item.LightsaberItem;
import server.galaxyunderchaos.sound.ModSounds;

@Mod.EventBusSubscriber(modid = "galaxyunderchaos", bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class KeyBindings {

    // Define the key mapping for toggling the lightsaber
    public static final KeyMapping TOGGLE_LIGHTSABER = new KeyMapping(
            "key.galaxyunderchaos.toggle_lightsaber", // Translation key
            KeyConflictContext.IN_GAME,             // Context where the keybind is valid
            InputConstants.Type.KEYSYM,             // Type of input (keyboard key)
            GLFW.GLFW_KEY_R,                        // Default key: R
            "key.categories.galaxyunderchaos"       // Custom keybinding category
    );

    /**
     * Register the keybinding and event listeners.
     */
    public static void init() {
        MinecraftForge.EVENT_BUS.register(new KeyBindings());  // Ensure this gets registered
    }

    /**
     * Register key mappings during the appropriate Forge event.
     */
    @SubscribeEvent
    public static void registerKeyMappings(RegisterKeyMappingsEvent event) {
        event.register(TOGGLE_LIGHTSABER);
    }

    /**
     * Handle key press events to toggle the lightsaber.
     */
    @SubscribeEvent
    public void onKeyInput(InputEvent.Key event) {
        if (TOGGLE_LIGHTSABER.isDown()) {
            Minecraft minecraft = Minecraft.getInstance();
            LocalPlayer player = minecraft.player;

            if (player != null) {
                ItemStack stack = player.getMainHandItem();

                // Check if the item is a Lightsaber
                if (stack.getItem() instanceof LightsaberItem lightsaber) {
                    // Toggle the active state of the lightsaber
                    boolean currentlyActive = lightsaber.isActive();
                    lightsaber.setActive(!currentlyActive);

                    // Play the appropriate sound for activation or deactivation
                    player.level().playSound(
                            null,
                            player.getX(), player.getY(), player.getZ(),
                            currentlyActive ? ModSounds.LIGHTSABER_TURN_OFF.get() : ModSounds.LIGHTSABER_TURN_ON.get(),
                            SoundSource.PLAYERS,
                            1.0F, // Volume
                            1.0F  // Pitch
                    );
                }
            }
        }
    }

}
