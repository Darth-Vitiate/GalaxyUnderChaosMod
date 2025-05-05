package client.renderer;

import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LightBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.ClientTickEvent;
import server.galaxyunderchaos.galaxyunderchaos;
import server.galaxyunderchaos.item.LightsaberItem;

@EventBusSubscriber(
        modid = galaxyunderchaos.MODID,
        bus   = EventBusSubscriber.Bus.GAME,
        value = Dist.CLIENT
)
public class ClientEventSubscriber {
    /** Tracks the last placed light‐block position so we can clean it up */
    private static BlockPos lastPos;

    /** Exposed glow tint for your ModItemRenderer to pick up */
    public static float glowR = 1f, glowG = 1f, glowB = 1f;

    /**
     * Map blade‐color IDs to ARGB tints for the emissive render layer.
     */
    private static int getGlowColor(String bladeColor) {
        return switch (bladeColor) {
            case "red"          -> 0xFFAE2623;
            case "blue"         -> 0xFF2985D0;
            case "green"        -> 0xFF8AED54;
            case "yellow"       -> 0xFFFFF645;
            case "cyan"         -> 0xFF29C8D0;
            case "white"        -> 0xFFFFFFFF;
            case "magenta"      -> 0xFFD029D0;
            case "purple"       -> 0xFFAC2FC0;
            case "pink"         -> 0xFFFF69B4;
            case "lime_green"   -> 0xFFCAD95B;
            case "turquoise"    -> 0xFF4AAA92;
            case "orange"       -> 0xFFE58416;
            case "blood_orange" -> 0xFFCC2C25;
            default             -> 0xFFFFFFFF;
        };
    }

    /**
     * Runs each client tick (POST) to place/remove a light‐block
     * and compute our emissive tint.
     */
    @SubscribeEvent
    public static void onClientTick(ClientTickEvent.Post event) {
        LocalPlayer player = Minecraft.getInstance().player;
        if (player == null) return;

        // Check both hands for an active saber (main‐hand preferred)
        ItemStack mainStack = player.getMainHandItem();
        ItemStack offStack  = player.getOffhandItem();

        boolean mainActive = mainStack.getItem() instanceof LightsaberItem lsMain && lsMain.isActive(mainStack);
        boolean offActive  = offStack.getItem()  instanceof LightsaberItem lsOff  && lsOff.isActive(offStack);

        ItemStack activeStack;
        if (mainActive) {
            activeStack = mainStack;
        } else if (offActive) {
            activeStack = offStack;
        } else {
            // Saber off → remove old light‐block & reset tint
            if (lastPos != null) {
                Minecraft.getInstance().level.removeBlock(lastPos, false);
                lastPos = null;
            }
            glowR = glowG = glowB = 1f;
            return;
        }

        // Compute light‐block position at player head height
        BlockPos lightPos = player.blockPosition().above(1);

        // Place vanilla LIGHT block for illumination
        BlockState lightState = Blocks.LIGHT
                .defaultBlockState()
                .setValue(LightBlock.LEVEL, LightsaberItem.getLightLevel(activeStack));
        Minecraft.getInstance().level.setBlock(lightPos, lightState, 2);

        // Remove previous if moved
        if (lastPos != null && !lastPos.equals(lightPos)) {
            Minecraft.getInstance().level.removeBlock(lastPos, false);
        }
        lastPos = lightPos;

        // Compute and expose emissive tint
        int argb = getGlowColor(LightsaberItem.getBladeColor(activeStack));
        glowR = ((argb >> 16) & 0xFF) / 255f;
        glowG = ((argb >> 8)  & 0xFF) / 255f;
        glowB = ( argb        & 0xFF) / 255f;
    }
}
