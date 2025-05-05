package client.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.PlayerModel;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import server.galaxyunderchaos.item.LightsaberItem;
import server.galaxyunderchaos.lightsaber.LightsaberFormCapability;

public class LightsaberFirstPersonLayer
        extends RenderLayer<AbstractClientPlayer, PlayerModel<AbstractClientPlayer>> {

    private final ItemRenderer itemRenderer = Minecraft.getInstance().getItemRenderer();

    public LightsaberFirstPersonLayer(
            RenderLayerParent<AbstractClientPlayer, PlayerModel<AbstractClientPlayer>> parent) {
        super(parent);
    }

    @Override
    @SuppressWarnings("resource")
    public void render(PoseStack poseStack,
                       MultiBufferSource buf,
                       int packedLight,
                       AbstractClientPlayer player,
                       float limbSwing,
                       float limbSwingAmount,
                       float partialTicks,
                       float ageInTicks,
                       float netHeadYaw,
                       float headPitch) {

        /* Only render when the player is holding an active lightsaber */
        ItemStack stack = player.getMainHandItem();
        if (!(stack.getItem() instanceof LightsaberItem)) return;

        /* Get the currently selected form */
        var cap  = player.getCapability(LightsaberFormCapability.CAPABILITY);
        String form = cap != null ? cap.getSelectedForm() : "";
        if (form == null || form.isEmpty()) {
            return;
        }
        /* Idle sway */
        float time = player.tickCount + partialTicks;
        float idle = Mth.sin(time * 0.2f) * 5f;

        /* Position hilt */
        poseStack.pushPose();
        getParentModel().translateToHand(HumanoidArm.RIGHT, poseStack);
        poseStack.translate(-0.1, 0.4, -0.2);

        /* Form‑specific rotations */
        switch (form) {
            case "Shii-Cho" -> {
                poseStack.mulPose(Axis.XP.rotationDegrees(-5f + idle));
                poseStack.mulPose(Axis.YP.rotationDegrees(15f));
            }
            case "Makashi" -> {
                poseStack.mulPose(Axis.YP.rotationDegrees(-60f));
                poseStack.mulPose(Axis.ZP.rotationDegrees(idle * 3f));
            }
            case "Soresu" -> {
                poseStack.mulPose(Axis.XP.rotationDegrees(30f + idle * 2f));
                poseStack.mulPose(Axis.YP.rotationDegrees(10f));
            }
            case "Ataru" -> {
                poseStack.mulPose(Axis.XP.rotationDegrees(45f + idle * 10f));
                poseStack.mulPose(Axis.ZP.rotationDegrees(idle * 5f));
            }
            case "Shien" -> {
                poseStack.mulPose(Axis.ZP.rotationDegrees(180f));
                poseStack.mulPose(Axis.XP.rotationDegrees(10f + idle * 3f));
            }
            case "Niman" -> {
                poseStack.mulPose(Axis.XP.rotationDegrees(-10f + idle * 2f));
                poseStack.mulPose(Axis.YP.rotationDegrees(-20f));
            }
            case "Juyo / Vaapad" -> {
                poseStack.mulPose(Axis.XP.rotationDegrees(-90f + idle * 15f));
                poseStack.mulPose(Axis.YP.rotationDegrees(10f));
            }
            default -> poseStack.mulPose(Axis.XP.rotationDegrees(idle));
        }

        /* Render lightsaber */
        itemRenderer.renderStatic(
                stack,
                ItemDisplayContext.FIRST_PERSON_RIGHT_HAND,
                packedLight,
                OverlayTexture.NO_OVERLAY,
                poseStack,
                buf,
                player.level(),
                player.getId());

        poseStack.popPose();
    }
}
