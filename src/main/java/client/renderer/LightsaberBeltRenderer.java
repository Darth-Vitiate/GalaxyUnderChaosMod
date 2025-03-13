//package client.renderer;
//
//import com.mojang.blaze3d.vertex.PoseStack;
//import net.minecraft.client.Minecraft;
//import net.minecraft.client.renderer.MultiBufferSource;
//import net.minecraft.client.renderer.RenderType;
//import net.minecraft.client.renderer.texture.TextureManager;
//import net.minecraft.resources.ResourceLocation;
//import net.minecraft.server.packs.resources.ResourceManager;
//import net.minecraft.world.entity.player.Player;
//import net.minecraft.world.item.ItemStack;
//import net.neoforged.api.distmarker.Dist;
//import net.neoforged.client.event.RenderPlayerEvent;
//import net.neoforged.eventbus.api.SubscribeEvent;
//import net.neoforged.fml.common.EventBusSubscriber;
//import net.neoforged.fml.common.Mod;
//import server.galaxyunderchaos.item.LightsaberItem;
//
//@EventBusSubscriber(modid = "galaxyunderchaos", bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
//public class LightsaberBeltRenderer {
//
//    @SubscribeEvent
//    public static void onPlayerRender(RenderPlayerEvent.Post event) {
//        Player player = event.getEntity();
//        PoseStack poseStack = event.getPoseStack();
//        MultiBufferSource bufferSource = event.getMultiBufferSource();
//        TextureManager textureManager = Minecraft.getInstance().getTextureManager();
//        ResourceManager resourceManager = Minecraft.getInstance().getResourceManager();
//
//        for (ItemStack itemStack : player.getInventory().items) {
//            if (itemStack.getItem() instanceof LightsaberItem lightsaberItem) {
//                // Skip rendering if the item is in the player's hand
//                if (player.getMainHandItem() == itemStack || player.getOffhandItem() == itemStack) {
//                    continue;
//                }
//
//                // Get hilt texture
//                String hilt = lightsaberItem.getHilt(itemStack);
//                ResourceLocation hiltTexture = ResourceLocation.fromNamespaceAndPath("galaxyunderchaos", "textures/item/" + hilt + "_hilt.png");
//
//                // Check if texture exists, fallback if not
//                if (resourceManager.getResource(hiltTexture).isEmpty()) {
//                    System.out.println("Missing texture for hilt: " + hilt);
//                    hiltTexture = ResourceLocation.fromNamespaceAndPath("galaxyunderchaos", "textures/item/unknown_hilt.png");
//                }
//
//                poseStack.pushPose();
//
//                // Position and scale
//                poseStack.translate(0.0, 0.8, 0.2); // Adjust for belt position
//                poseStack.scale(0.5F, 0.5F, 0.5F);  // Adjust size
//
//                // Bind texture and render
//                textureManager.bindForSetup(hiltTexture);
//                RenderType renderType = RenderType.entityCutout(hiltTexture);
//                bufferSource.getBuffer(renderType);
//
//                poseStack.popPose();
//                break; // Render only one hilt
//            }
//        }
//    }
//}
