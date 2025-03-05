package client.renderer;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Overlay;
import net.minecraft.resources.ResourceLocation;

public class HyperspaceOverlay extends Overlay {
    private static final ResourceLocation HYPERSPACE_TEXTURE =
            ResourceLocation.fromNamespaceAndPath("galaxyunderchaos", "textures/gui/hyperspace.png");

    private static boolean isWarping = false;
    private static boolean isOverlayActive = false;
    private static long startTime;
    private static final long warpDuration = 5000; // Warp effect duration in milliseconds

    // Start warp effect when hyperspace starts
    public static void startWarpEffect() {
        System.out.println("Hyperspace overlay started!"); // Debugging statement
        isWarping = true;
        startTime = System.currentTimeMillis();
        isOverlayActive = true;
    }

    // Start overlay manually (if needed)
    public static void startOverlay() {
        System.out.println("Hyperspace overlay manually started!"); // Debugging statement
        isOverlayActive = true;
    }

    // Stop warp effect
    public static void stopWarpEffect() {
        System.out.println("Hyperspace overlay stopped!"); // Debugging statement
        isWarping = false;
        isOverlayActive = false;
    }

    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks) {
        if (!isWarping && !isOverlayActive) return;

        System.out.println("Rendering hyperspace overlay..."); // Debugging statement

        long elapsed = System.currentTimeMillis() - startTime;
        if (elapsed > warpDuration) {
            stopWarpEffect();
            return;
        }

        Minecraft minecraft = Minecraft.getInstance();
        int width = minecraft.getWindow().getGuiScaledWidth();
        int height = minecraft.getWindow().getGuiScaledHeight();

        RenderSystem.enableBlend();
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F); // Ensure full visibility
        guiGraphics.blit(HYPERSPACE_TEXTURE, 0, 0, 0, 0, width, height, width, height);
        RenderSystem.disableBlend();
    }
}