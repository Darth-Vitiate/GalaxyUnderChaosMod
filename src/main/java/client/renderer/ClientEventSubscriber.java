package client.renderer;

import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LightBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.TickEvent.RenderTickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import server.galaxyunderchaos.item.LightsaberItem;

@Mod.EventBusSubscriber(modid = "galaxyunderchaos", value = Dist.CLIENT)
public class ClientEventSubscriber {
    /** Tracks the last placed light-block position so we can clean it up */
    private static BlockPos lastPos;

    /** Exposed glow tint for your ModItemRenderer to pick up */
    public static float glowR = 1f, glowG = 1f, glowB = 1f;

    /**
     * Map blade-color IDs to ARGB tints for the emissive render layer.
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
     * Every client render-tick (END phase), place or remove an invisible light-block
     * at the saber’s position, compute the ARGB tint, and expose normalized RGB
     * for your custom renderer to consume.
     */
    @SubscribeEvent
    public static void onRenderTick(RenderTickEvent event) {
        if (event.phase != TickEvent.Phase.END) return;

        LocalPlayer player = Minecraft.getInstance().player;
        if (player == null) return;

        // Check both hands for an active saber (main-hand preferred)
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
            // Saber off or not held → remove old light-block & reset tint
            if (lastPos != null) {
                Minecraft.getInstance().level.removeBlock(lastPos, false);
                lastPos = null;
            }
            glowR = glowG = glowB = 1f;
            return;
        }

        // Compute a BlockPos at sword-height; tweak Y-offset as needed
        BlockPos lightPos = player.blockPosition().above(1);

        // Place vanilla LIGHT block at level 15 for world illumination (always white)
        BlockState lightState = Blocks.LIGHT
                .defaultBlockState()
                .setValue(LightBlock.LEVEL, LightsaberItem.getLightLevel(activeStack));
        Minecraft.getInstance().level.setBlock(lightPos, lightState, 2);

        // Clean up prior one if we moved
        if (lastPos != null && !lastPos.equals(lightPos)) {
            Minecraft.getInstance().level.removeBlock(lastPos, false);
        }
        lastPos = lightPos;

        // Compute custom emissive tint from blade color
        int argb = getGlowColor(LightsaberItem.getBladeColor(activeStack));
        glowR = ((argb >> 16) & 0xFF) / 255f;
        glowG = ((argb >> 8)  & 0xFF) / 255f;
        glowB = ( argb        & 0xFF) / 255f;
    }
}
