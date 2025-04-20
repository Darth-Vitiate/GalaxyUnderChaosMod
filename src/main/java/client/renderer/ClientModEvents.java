package client.renderer;

import net.minecraft.client.renderer.entity.player.PlayerRenderer;
import net.minecraft.client.resources.PlayerSkin;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.CustomizeGuiOverlayEvent;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import server.galaxyunderchaos.galaxyunderchaos;

@Mod.EventBusSubscriber(modid = galaxyunderchaos.MODID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ClientModEvents {

    @SubscribeEvent
    public static void onAddLayers(EntityRenderersEvent.AddLayers event) {
        for (PlayerSkin.Model skin : event.getSkins()) {
            var renderer = event.getSkins();
            if (renderer instanceof PlayerRenderer playerRenderer) {
                playerRenderer.addLayer(new LightsaberPoseLayer<>(playerRenderer));
            }
        }
    }
}

