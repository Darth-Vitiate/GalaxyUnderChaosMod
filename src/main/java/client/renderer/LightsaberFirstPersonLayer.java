package client.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.PlayerModel;
import net.minecraft.client.player.AbstractClientPlayer;   // ← keep AbstractClientPlayer
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
import server.galaxyunderchaos.lightsaber.LightsaberFormProvider;

public class LightsaberFirstPersonLayer
        extends RenderLayer<AbstractClientPlayer,              // ← keep AbstractClientPlayer
        PlayerModel<AbstractClientPlayer>> {

    private final ItemRenderer itemRenderer =
            Minecraft.getInstance().getItemRenderer();

    public LightsaberFirstPersonLayer(
            RenderLayerParent<AbstractClientPlayer,
                    PlayerModel<AbstractClientPlayer>> parent) {
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

        /* --- Only render when the player is holding a lightsaber ----------------------- */
        ItemStack stack = player.getMainHandItem();
        if (!(stack.getItem() instanceof LightsaberItem)) return;

        /* --- Query the selected form from the capability ------------------------------ */
        String form = player.getCapability(
                        LightsaberFormProvider.LIGHTSABER_FORM_CAPABILITY)
                .map(cap -> cap.getSelectedForm())
                .orElse("");
        if (form.isEmpty()) return;

        /* --- Small idle wiggle --------------------------------------------------------- */
        float time = player.tickCount + partialTicks;
        float idle = Mth.sin(time * 0.15f);

        /* --- Position the item at the right hand -------------------------------------- */
        poseStack.pushPose();
        getParentModel().translateToHand(HumanoidArm.RIGHT, poseStack);
        poseStack.translate(-0.05, 0.50, -0.20);

        switch (form) {
            case "Shii-Cho" -> poseStack.mulPose(Axis.XP.rotationDegrees(-10f + idle * 2f));
            case "Makashi"  -> {
                poseStack.mulPose(Axis.YP.rotationDegrees(-40f));
                poseStack.mulPose(Axis.ZP.rotationDegrees(idle * 8f));
            }
            case "Soresu"   -> {
                poseStack.translate(0.05, 0.00, -0.15);
                poseStack.mulPose(Axis.ZP.rotationDegrees(90f));
                poseStack.mulPose(Axis.XP.rotationDegrees(-15f + idle * 1.5f));
            }
            case "Ataru"    -> {
                poseStack.mulPose(Axis.YP.rotationDegrees(-80f));
                poseStack.mulPose(Axis.XP.rotationDegrees( 25f + idle * 5f));
            }
            case "Shien"    -> {
                poseStack.mulPose(Axis.ZP.rotationDegrees(180f));
                poseStack.mulPose(Axis.YP.rotationDegrees(15f + idle * 4f));
            }
            case "Niman"    -> {
                poseStack.mulPose(Axis.XP.rotationDegrees(-20f + idle * 3f));
                poseStack.mulPose(Axis.YP.rotationDegrees(-15f));
            }
            case "Juyo"     -> {
                poseStack.mulPose(Axis.YP.rotationDegrees(-100f + idle * 10f));
                poseStack.mulPose(Axis.XN.rotationDegrees(  35f + idle * 6f));
            }
        }

        /* --- Render the item ----------------------------------------------------------- */
        itemRenderer.renderStatic(
                stack,
                ItemDisplayContext.THIRD_PERSON_RIGHT_HAND,
                packedLight,
                OverlayTexture.NO_OVERLAY,
                poseStack,
                buf,
                player.level(),
                player.getId());

        poseStack.popPose();
    }
}
