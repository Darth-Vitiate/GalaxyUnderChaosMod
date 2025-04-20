
package client.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import server.galaxyunderchaos.data.ModDataComponentTypes;

public class DoubleBladedSaberRenderer extends BlockEntityWithoutLevelRenderer {
    public DoubleBladedSaberRenderer() {
        super(null, null);
    }

    @Override
    public void renderByItem(ItemStack stack, ItemDisplayContext context, PoseStack poseStack,
                             MultiBufferSource buffer, int light, int overlay) {
        if (!stack.getItem().toString().contains("_and_")) return;

        String id = stack.getItem().toString().replace("_double_lightsaber", "").replace("Item{", "").replace("}", "");
        String[] parts = id.split("_and_");
        if (parts.length != 2) return;

        String[] left = parts[0].split("_", 2);
        String[] right = parts[1].split("_", 2);
        if (left.length != 2 || right.length != 2) return;

        String leftColor = left[0];
        String leftHilt = left[1];
        String rightColor = right[0];
        String rightHilt = right[1];

        ItemRenderer itemRenderer = Minecraft.getInstance().getItemRenderer();
        boolean active = stack.getOrDefault(ModDataComponentTypes.ACTIVE.get(), false);

        ItemStack leftStack = new ItemStack(Minecraft.getInstance().level.registryAccess()
                .registryOrThrow(net.minecraft.core.registries.Registries.ITEM)
                .get(ResourceLocation.fromNamespaceAndPath("galaxyunderchaos", leftColor + "_" + leftHilt + "_lightsaber")));
        leftStack.set(ModDataComponentTypes.ACTIVE.get(), active);

        ItemStack rightStack = new ItemStack(Minecraft.getInstance().level.registryAccess()
                .registryOrThrow(net.minecraft.core.registries.Registries.ITEM)
                .get(ResourceLocation.fromNamespaceAndPath("galaxyunderchaos", rightColor + "_" + rightHilt + "_lightsaber")));
        rightStack.set(ModDataComponentTypes.ACTIVE.get(), active);

        poseStack.pushPose();
        poseStack.translate(0.0, 0.0, 0.0);

        // LEFT
        poseStack.pushPose();
        poseStack.translate(0.0, 0.0, -0.4);
        itemRenderer.renderStatic(null, leftStack, context, false, poseStack, buffer,
                Minecraft.getInstance().level, light, OverlayTexture.NO_OVERLAY, 0);
        poseStack.popPose();

        // RIGHT
        poseStack.pushPose();
        poseStack.translate(0.0, 0.0, 0.4);
        poseStack.mulPose(Axis.YP.rotationDegrees(180F));
        itemRenderer.renderStatic(null, rightStack, context, false, poseStack, buffer,
                Minecraft.getInstance().level, light, OverlayTexture.NO_OVERLAY, 1);
        poseStack.popPose();

        poseStack.popPose();
    }
}
