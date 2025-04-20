package client.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.PlayerModel;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.entity.player.PlayerRenderer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RenderLivingEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import server.galaxyunderchaos.item.LightsaberItem;

@Mod.EventBusSubscriber(modid = "galaxyunderchaos", bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
public class LightsaberRenderHandler {

    @SubscribeEvent
    public static void onRenderPlayer(RenderLivingEvent.Pre<?, ?> event) {
        if (!(event.getEntity() instanceof AbstractClientPlayer player)) return;
        ItemStack mainHand = player.getMainHandItem();

        if (mainHand.getItem() instanceof LightsaberItem) {
            LivingEntityRenderer<?, ?> renderer = event.getRenderer();
            if (renderer instanceof PlayerRenderer playerRenderer) {
                PlayerModel<AbstractClientPlayer> model = playerRenderer.getModel();

                // Custom pose: bring both hands together
                model.rightArm.xRot = -1.2F;
                model.leftArm.xRot = -1.2F;
                model.rightArm.yRot = -0.4F;
                model.leftArm.yRot = 0.4F;
            }
        }
    }

    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
        Player player = event.player;
        if (player.swinging && player.getMainHandItem().getItem() instanceof LightsaberItem) {
            // TODO: Add actual arm animation (interpolation or keyframe-based logic)
        }
    }
}