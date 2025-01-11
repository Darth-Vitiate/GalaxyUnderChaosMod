//package client.renderer;
//
//import com.mojang.blaze3d.vertex.PoseStack;
//import net.minecraft.client.Minecraft;
//import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
//import net.minecraft.client.renderer.MultiBufferSource;
//import net.minecraft.world.item.ItemStack;
//import net.minecraft.world.item.ItemDisplayContext;
//import server.galaxyunderchaos.item.LightsaberItem;
//import server.galaxyunderchaos.lightsaber.BladeColorRegistry;
//import java.awt.Color;
//
//public class LightsaberRenderer extends BlockEntityWithoutLevelRenderer {
//
//    public static final LightsaberRenderer INSTANCE = new LightsaberRenderer();
//    public LightsaberRenderer() {
//        super(Minecraft.getInstance().getBlockEntityRenderDispatcher(),
//                Minecraft.getInstance().getEntityModels());
//    }
//    @Override
//    public void renderByItem(ItemStack itemStack, ItemDisplayContext context, PoseStack poseStack, MultiBufferSource buffer, int combinedLight, int combinedOverlay) {
//        if (!(itemStack.getItem() instanceof LightsaberItem lightsaberItem)) {
//            return;
//        }
//        String bladeColorName = lightsaberItem.getBladeColor();
//        Color bladeColor = BladeColorRegistry.getColorAsRGB(bladeColorName);
//        poseStack.pushPose();
//        Minecraft.getInstance().getItemRenderer().renderStatic(null, itemStack, context, false, poseStack, buffer, null, combinedLight, combinedOverlay, 0);
//        poseStack.popPose();
//        renderBlade(poseStack, buffer, bladeColor, combinedLight);
//    }
//    private void renderBlade(PoseStack poseStack, MultiBufferSource buffer, Color bladeColor, int light) {
//        System.out.println("Rendering blade with color: " + bladeColor);
//    }
//}
