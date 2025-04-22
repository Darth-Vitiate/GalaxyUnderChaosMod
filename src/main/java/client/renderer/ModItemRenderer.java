package client.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.Minecraft;
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
        super(Minecraft.getInstance().getBlockEntityRenderDispatcher(), Minecraft.getInstance().getEntityModels());
    }

    @Override
    public void renderByItem(ItemStack stack, ItemDisplayContext displayContext, PoseStack poseStack, MultiBufferSource buffer, int light, int overlay) {
        ItemRenderer renderer = Minecraft.getInstance().getItemRenderer();
        BakedModel model = renderer.getModel(stack, null, null, 0);

        if (stack.getItem() instanceof LightsaberItem saber) {
            boolean isActive = stack.getOrDefault(ModDataComponentTypes.ACTIVE.get(), false);

            // Render the hilt
            renderer.render(stack, displayContext, false, poseStack, buffer, light, overlay, model);

            if (isActive) {
                int glowColor = getGlowColor(LightsaberItem.getBladeColor(stack));
                renderColoredGlow(poseStack, buffer, glowColor);
            }
        } else {
            renderer.render(stack, displayContext, false, poseStack, buffer, light, overlay, model);
        }
    }

    private int getGlowColor(String bladeColor) {
        return switch (bladeColor) {
            case "red" -> 0xFFAE2623;
            case "blue" -> 0xFF2985D0;
            case "green" -> 0xFF8AED54;
            case "yellow" -> 0xFFFFF645;
            case "cyan" -> 0xFF29C8D0;
            case "white" -> 0xFFFFFFFF;
            case "magenta" -> 0xFFD029D0;
            case "purple" -> 0xFFAC2FC0;
            case "pink" -> 0xFFFF69B4;
            case "lime_green" -> 0xFFCAD95B;
            case "turquoise" -> 0xFF4AAA92;
            case "orange" -> 0xFFE58416;
            case "blood_orange" -> 0xFFCC2C25;
            default -> 0xFFFFFFFF;
        };
    }

    private void renderColoredGlow(PoseStack poseStack, MultiBufferSource buffer, int color) {
        VertexConsumer vertexConsumer = buffer.getBuffer(
                RenderType.entityTranslucent(ResourceLocation.parse("galaxyunderchaos:textures/misc/glow.png"))
        );

        float red = (color >> 16 & 255) / 255.0F;
        float green = (color >> 8 & 255) / 255.0F;
        float blue = (color & 255) / 255.0F;
        float alpha = 0.7F; // Glow transparency

        poseStack.pushPose();
        poseStack.scale(1.3F, 1.3F, 1.3F); // Slightly larger than saber blade

        PoseStack.Pose pose = poseStack.last();

        int overlay = 15728880; // Full overlay brightness
        int light = 240;        // Max glow brightness

        // Bottom-left vertex
        vertexConsumer.addVertex(pose, -0.3F, -0.3F, 0.0F);
        vertexConsumer.setColor(red, green, blue, alpha);
        vertexConsumer.setUv(0.0F, 0.0F);
        vertexConsumer.setOverlay(overlay);
        vertexConsumer.setLight(light);
        vertexConsumer.setNormal(pose, 0.0F, 1.0F, 0.0F);

        // Bottom-right vertex
        vertexConsumer.addVertex(pose, 0.3F, -0.3F, 0.0F);
        vertexConsumer.setColor(red, green, blue, alpha);
        vertexConsumer.setUv(1.0F, 0.0F);
        vertexConsumer.setOverlay(overlay);
        vertexConsumer.setLight(light);
        vertexConsumer.setNormal(pose, 0.0F, 1.0F, 0.0F);

        // Top-right vertex
        vertexConsumer.addVertex(pose, 0.3F, 0.3F, 0.0F);
        vertexConsumer.setColor(red, green, blue, alpha);
        vertexConsumer.setUv(1.0F, 1.0F);
        vertexConsumer.setOverlay(overlay);
        vertexConsumer.setLight(light);
        vertexConsumer.setNormal(pose, 0.0F, 1.0F, 0.0F);

        // Top-left vertex
        vertexConsumer.addVertex(pose, -0.3F, 0.3F, 0.0F);
        vertexConsumer.setColor(red, green, blue, alpha);
        vertexConsumer.setUv(0.0F, 1.0F);
        vertexConsumer.setOverlay(overlay);
        vertexConsumer.setLight(light);
        vertexConsumer.setNormal(pose, 0.0F, 1.0F, 0.0F);

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
