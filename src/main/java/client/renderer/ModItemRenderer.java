package client.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;
import server.galaxyunderchaos.data.ModDataComponentTypes;
import server.galaxyunderchaos.item.LightsaberItem;

import java.util.function.Consumer;

public class ModItemRenderer extends BlockEntityWithoutLevelRenderer {

    public ModItemRenderer() {
        super(null, null);
    }

    @Override
    public void renderByItem(ItemStack stack, ItemDisplayContext displayContext, PoseStack poseStack, MultiBufferSource buffer, int light, int overlay) {
        ItemRenderer renderer = net.minecraft.client.Minecraft.getInstance().getItemRenderer();
        BakedModel model = renderer.getModel(stack, null, null, 0);

        if (stack.getItem() instanceof LightsaberItem saber) {
            boolean isActive = stack.getOrDefault(ModDataComponentTypes.ACTIVE.get(), false);

            // Render hilt
            renderer.render(stack, displayContext, false, poseStack, buffer, light, overlay, model);

            if (isActive) {
                // Render glow
                int glowColor = getGlowColor(LightsaberItem.getBladeColor(stack));
                renderColoredGlow(poseStack, buffer, glowColor);
            }
        }
    }

    private int getGlowColor(String bladeColor) {
        return switch (bladeColor) {
            case "red" -> 0xFF0000;
            case "blue" -> 0x0000FF;
            case "green" -> 0x00FF00;
            case "yellow" -> 0xFFFF00;
            case "cyan" -> 0x00FFFF;
            case "white" -> 0xFFFFFF;
            case "magenta" -> 0xFF00FF;
            case "purple" -> 0x8000FF;
            case "pink" -> 0xFF69B4;
            case "lime_green" -> 0x32CD32;
            case "turquoise" -> 0x40E0D0;
            case "orange" -> 0xFFA500;
            case "blood_orange" -> 0xCC5500;
            default -> 0xFFFFFF;
        };
    }

    private void renderColoredGlow(PoseStack poseStack, MultiBufferSource buffer, int color) {
        VertexConsumer vertexConsumer = buffer.getBuffer(RenderType.energySwirl(ResourceLocation.parse("textures/misc/glow.png"), 0, 0));

        float red = (color >> 16 & 255) / 255.0F;
        float green = (color >> 8 & 255) / 255.0F;
        float blue = (color & 255) / 255.0F;
        float alpha = 0.7F; // Glow transparency

        poseStack.pushPose();
        poseStack.scale(1.3F, 1.3F, 1.3F); // Slightly larger than the saber blade

        float minX = -0.3F, maxX = 0.3F;
        float minY = -0.3F, maxY = 0.3F;
        float z = 0.0F; // Glow positioning

        int overlay = 15728880; // Full brightness
        int light = 240; // Max brightness for glow

        // **Retrieve correct normal transformation**
        PoseStack.Pose lastPose = poseStack.last();

        // **⚡ FIXED: Using `setNormal(PoseStack.Pose, float, float, float)` correctly**
        vertexConsumer.addVertex(lastPose.pose(), minX, minY, z)
                .setColor(red, green, blue, alpha)
                .setUv(0, 0)
                .setOverlay(overlay)
                .setLight(light)
                .setNormal(lastPose, 0.0F, 1.0F, 0.0F);

        vertexConsumer.addVertex(lastPose.pose(), maxX, minY, z)
                .setColor(red, green, blue, alpha)
                .setUv(1, 0)
                .setOverlay(overlay)
                .setLight(light)
                .setNormal(lastPose, 0.0F, 1.0F, 0.0F);

        vertexConsumer.addVertex(lastPose.pose(), maxX, maxY, z)
                .setColor(red, green, blue, alpha)
                .setUv(1, 1)
                .setOverlay(overlay)
                .setLight(light)
                .setNormal(lastPose, 0.0F, 1.0F, 0.0F);

        vertexConsumer.addVertex(lastPose.pose(), minX, maxY, z)
                .setColor(red, green, blue, alpha)
                .setUv(0, 1)
                .setOverlay(overlay)
                .setLight(light)
                .setNormal(lastPose, 0.0F, 1.0F, 0.0F);

        poseStack.popPose();
    }




    public static void registerItemRenderer(Consumer<IClientItemExtensions> consumer) {
        consumer.accept(new IClientItemExtensions() {
            private final BlockEntityWithoutLevelRenderer customRenderer = new ModItemRenderer();

            @Override
            public BlockEntityWithoutLevelRenderer getCustomRenderer() {
                return customRenderer;
            }
        });
    }
}
