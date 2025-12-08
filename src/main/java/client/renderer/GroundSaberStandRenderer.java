package client.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.world.item.ItemDisplayContext;
import server.galaxyunderchaos.entity.GroundSaberStandBlockEntity;

import static com.mojang.math.Axis.YP;

public class GroundSaberStandRenderer implements BlockEntityRenderer<GroundSaberStandBlockEntity> {

    private final ItemRenderer itemRenderer;

    public GroundSaberStandRenderer(BlockEntityRendererProvider.Context ctx) {
        this.itemRenderer = ctx.getItemRenderer();
    }

    @Override
    public void render(GroundSaberStandBlockEntity stand,
                       float partialTick,
                       PoseStack poseStack,
                       MultiBufferSource bufferSource,
                       int packedLight,
                       int packedOverlay) {

        if (stand.isEmpty()) {
            return;
        }

        poseStack.pushPose();

        // Center & above the plate
        poseStack.translate(0.5D, 0.2D, 0.5D);
        poseStack.mulPose(YP.rotationDegrees(45.0F));
        poseStack.scale(0.8F, 0.8F, 0.8F);

        itemRenderer.renderStatic(
                stand.getItem(),
                ItemDisplayContext.GROUND,
                packedLight,
                OverlayTexture.NO_OVERLAY,
                poseStack,
                bufferSource,
                stand.getLevel(),
                0
        );

        poseStack.popPose();
    }
}
