package client.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.client.extensions.common.IClientItemExtensions;
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
                overlay = OverlayTexture.NO_OVERLAY;

                // Render glow
                int glowColor = getGlowColor(LightsaberItem.getBladeColor(stack));
                renderColoredGlow(poseStack, buffer, glowColor);
            }
        }
    }

    private int getGlowColor(String bladeColor) {
        return switch (bladeColor) {
            case "red" -> 0xFFA91B23;
            case "blue" -> 0xFF2985D0;
            case "green" -> 0xFF8AED54;
            case "yellow" -> 0xFFFFF645;
            case "cyan" -> 0xFF29C8D0;
            case "white" -> 0xFFDDDDDD;
            case "magenta" -> 0xFFD835B6;
            case "purple" -> 0xFFAC2FC1;
            case "pink" -> 0xFFDD7BAC;
            case "lime_green" -> 0xFFCAD95B;
            case "turquoise" -> 0xFF4AAA92;
            case "orange" -> 0xFFE58416;
            case "blood_orange" -> 0xFFCC2C25;
            default -> 0xFFDDDDDD;
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
