package client.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.PlayerModel;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.world.item.ItemStack;
import server.galaxyunderchaos.item.LightsaberItem;

public class LightsaberPoseLayer<T extends AbstractClientPlayer> extends RenderLayer<T, PlayerModel<T>> {

    public LightsaberPoseLayer(RenderLayerParent<T, PlayerModel<T>> parent) {
        super(parent);
    }

    @Override
    public void render(PoseStack poseStack, MultiBufferSource buffer, int packedLight, T player,
                       float limbSwing, float limbSwingAmount, float partialTicks,
                       float ageInTicks, float netHeadYaw, float headPitch) {

        ItemStack mainHand = player.getMainHandItem();
        if (mainHand.getItem() instanceof LightsaberItem) {
            PlayerModel<T> model = getParentModel();

            // Two-handed stance pose
            model.rightArm.xRot = -1.2F;
            model.leftArm.xRot = -1.2F;
            model.rightArm.yRot = -0.4F;
            model.leftArm.yRot = 0.4F;
        }
    }
}
