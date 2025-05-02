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

        if (stack.getItem() instanceof LightsaberItem) {
            boolean isActive = stack.getOrDefault(ModDataComponentTypes.ACTIVE.get(), false);

            // Render the hilt (and inactive blade)
            renderer.render(stack, displayContext, false, poseStack, buffer, light, overlay, model);

            if (isActive) {
                // Render the colored glow layer
                renderColoredGlow(poseStack, buffer);
            }
        } else {
            // Other items use default rendering
            renderer.render(stack, displayContext, false, poseStack, buffer, light, overlay, model);
        }
    }

    /**
     * Renders a glow quad tinted by ClientEventSubscriber.glowR/G/B
     */
    private void renderColoredGlow(PoseStack poseStack, MultiBufferSource buffer) {
        VertexConsumer vertexConsumer = buffer.getBuffer(
                RenderType.entityTranslucent(
                        ResourceLocation.parse("galaxyunderchaos:textures/misc/glow.png")
                )
        );

        // Use dynamic color from client subscriber
        float red   = ClientEventSubscriber.glowR;
        float green = ClientEventSubscriber.glowG;
        float blue  = ClientEventSubscriber.glowB;
        float alpha = 0.7F; // Glow transparency

        poseStack.pushPose();
        poseStack.scale(1.3F, 1.3F, 1.3F);

        PoseStack.Pose pose = poseStack.last();

        int fullOverlay = 15728880; // Full brightness overlay
        int fullLight   = 240;      // Max block light

        // Bottom-left
        vertexConsumer.addVertex(pose, -0.3F, -0.3F, 0.0F);
        vertexConsumer.setColor(red, green, blue, alpha);
        vertexConsumer.setUv(0.0F, 0.0F);
        vertexConsumer.setOverlay(fullOverlay);
        vertexConsumer.setLight(fullLight);
        vertexConsumer.setNormal(pose, 0.0F, 1.0F, 0.0F);

        // Bottom-right
        vertexConsumer.addVertex(pose, 0.3F, -0.3F, 0.0F);
        vertexConsumer.setColor(red, green, blue, alpha);
        vertexConsumer.setUv(1.0F, 0.0F);
        vertexConsumer.setOverlay(fullOverlay);
        vertexConsumer.setLight(fullLight);
        vertexConsumer.setNormal(pose, 0.0F, 1.0F, 0.0F);

        // Top-right
        vertexConsumer.addVertex(pose, 0.3F, 0.3F, 0.0F);
        vertexConsumer.setColor(red, green, blue, alpha);
        vertexConsumer.setUv(1.0F, 1.0F);
        vertexConsumer.setOverlay(fullOverlay);
        vertexConsumer.setLight(fullLight);
        vertexConsumer.setNormal(pose, 0.0F, 1.0F, 0.0F);

        // Top-left
        vertexConsumer.addVertex(pose, -0.3F, 0.3F, 0.0F);
        vertexConsumer.setColor(red, green, blue, alpha);
        vertexConsumer.setUv(0.0F, 1.0F);
        vertexConsumer.setOverlay(fullOverlay);
        vertexConsumer.setLight(fullLight);
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
