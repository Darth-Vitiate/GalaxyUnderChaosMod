//package client.renderer;
//
//import com.mojang.blaze3d.vertex.PoseStack;
//import com.mojang.blaze3d.vertex.VertexConsumer;
//import net.minecraft.client.Minecraft;
//import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
//import net.minecraft.client.renderer.MultiBufferSource;
//import net.minecraft.client.renderer.RenderType;
//import net.minecraft.resources.ResourceLocation;
//import net.minecraft.world.item.ItemStack;
//import server.galaxyunderchaos.item.LightsaberItem;
//
//public class LightsaberRenderer extends BlockEntityWithoutLevelRenderer {
//
//    public static final LightsaberRenderer INSTANCE = new LightsaberRenderer();
//
//    private LightsaberRenderer() {
//        super(Minecraft.getInstance().getBlockEntityRenderDispatcher(),
//                Minecraft.getInstance().getEntityModels());
//    }
//
//    @Override
//    public void renderByItem(ItemStack stack, PoseStack poseStack, MultiBufferSource buffer, int combinedLight, int combinedOverlay) {
//        if (stack.getItem() instanceof LightsaberItem lightsaber) {
//            String bladeColor = lightsaber.getBladeColor();
//
//            // Render the hilt
//            renderHilt(poseStack, buffer, combinedLight, combinedOverlay);
//
//            // Render the plasma blade
//            renderBlade(poseStack, buffer, combinedLight, combinedOverlay, bladeColor);
//        }
//    }
//
//    private void renderHilt(PoseStack poseStack, MultiBufferSource buffer, int combinedLight, int combinedOverlay) {
//        ResourceLocation hiltTexture = ResourceLocation.fromNamespaceAndPath("galaxyunderchaos", "textures/item/default_hilt.png");
//        VertexConsumer vertexConsumer = buffer.getBuffer(RenderType.entitySolid(hiltTexture));
//
//        // Add hilt rendering logic (if using baked models or custom geometry)
//    }
//
//    private void renderBlade(PoseStack poseStack, MultiBufferSource buffer, int combinedLight, int combinedOverlay, String bladeColor) {
//        // Use a custom RenderType for glowing plasma effect
//        VertexConsumer vertexConsumer = buffer.getBuffer(CustomRenderTypes.PLASMA_BLADE);
//
//        // Push PoseStack for blade positioning
//        poseStack.pushPose();
//
//        // Translate the blade to position it above the hilt
//        poseStack.translate(0, 1.5, 0); // Adjust the height above the hilt
//
//        // Define blade dimensions
//        float bladeLength = 4.0F;
//        float bladeWidth = 0.1F;
//
//        // Get the RGB color components for the blade
//        int[] color = getColorComponents(bladeColor);
//
//        // Render the blade quad with dynamic coloring
//        renderColoredQuad(vertexConsumer, poseStack, -bladeWidth, 0, bladeWidth, bladeLength, combinedLight, color);
//
//        // Pop PoseStack to revert transformations
//        poseStack.popPose();
//    }
//
//    private void renderColoredQuad(VertexConsumer vertexConsumer, PoseStack poseStack, float x1, float y1, float x2, float y2, int light, int[] color) {
//        var matrix = poseStack.last().pose(); // Retrieve the transformation matrix from PoseStack
//
//        // Define vertices for the quad with dynamic color
//        vertexConsumer.vertex(matrix, x1, y1, 0).color(color[0], color[1], color[2], 255).uv(0, 0).overlayCoords(0, 10).uv2(light).normal(0, 0, 1).endVertex();
//        vertexConsumer.vertex(matrix, x2, y1, 0).color(color[0], color[1], color[2], 255).uv(1, 0).overlayCoords(0, 10).uv2(light).normal(0, 0, 1).endVertex();
//        vertexConsumer.vertex(matrix, x2, y2, 0).color(color[0], color[1], color[2], 255).uv(1, 1).overlayCoords(0, 10).uv2(light).normal(0, 0, 1).endVertex();
//        vertexConsumer.vertex(matrix, x1, y2, 0).color(color[0], color[1], color[2], 255).uv(0, 1).overlayCoords(0, 10).uv2(light).normal(0, 0, 1).endVertex();
//    }
//
//    private int[] getColorComponents(String bladeColor) {
//        return switch (bladeColor) {
//            case "blood_orange" -> new int[]{255, 69, 0};   // Orange-Red
//            case "blue" -> new int[]{0, 0, 255};            // Blue
//            case "cyan" -> new int[]{0, 255, 255};          // Cyan
//            case "green" -> new int[]{0, 255, 0};           // Green
//            case "lime_green" -> new int[]{50, 205, 50};    // Lime Green
//            case "magenta" -> new int[]{255, 0, 255};       // Magenta
//            case "orange" -> new int[]{255, 165, 0};        // Orange
//            case "pink" -> new int[]{255, 182, 193};        // Pink
//            case "purple" -> new int[]{128, 0, 128};        // Purple
//            case "red" -> new int[]{255, 0, 0};             // Red
//            case "turquoise" -> new int[]{64, 224, 208};    // Turquoise
//            case "white" -> new int[]{255, 255, 255};       // White
//            case "yellow" -> new int[]{255, 255, 0};        // Yellow
//            default -> new int[]{128, 128, 128};            // Default Gray
//        };
//    }
//}
