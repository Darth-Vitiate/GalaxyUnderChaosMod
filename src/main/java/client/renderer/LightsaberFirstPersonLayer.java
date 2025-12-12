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
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemDisplayContext;
import server.galaxyunderchaos.item.LightsaberItem;
import server.galaxyunderchaos.lightsaber.LightsaberForm;
import server.galaxyunderchaos.lightsaber.LightsaberFormProvider;

public class LightsaberFirstPersonLayer
        extends RenderLayer<AbstractClientPlayer, PlayerModel<AbstractClientPlayer>> {

    private final ItemRenderer itemRenderer = Minecraft.getInstance().getItemRenderer();

    public LightsaberFirstPersonLayer(RenderLayerParent<AbstractClientPlayer, PlayerModel<AbstractClientPlayer>> parent) {
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

        // Only render when the player is holding an active lightsaber
        ItemStack stack = player.getMainHandItem();
        if (!(stack.getItem() instanceof LightsaberItem)) return;

        String formName = player.getCapability(
                        LightsaberFormProvider.LIGHTSABER_FORM_CAPABILITY)
                .map(cap -> cap.getSelectedForm())
                .orElse("");
        if (formName.isEmpty()) return;

        LightsaberForm form = LightsaberForm.fromDisplayName(formName);

        // Position the hilt in first person
        poseStack.pushPose();
        getParentModel().translateToHand(HumanoidArm.RIGHT, poseStack);

        LightsaberFormAnimations.apply(poseStack, player, partialTicks, form);

        // Render the saber in first person
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
