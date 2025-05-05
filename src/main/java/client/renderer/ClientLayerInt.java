package client.renderer;

import net.minecraft.client.renderer.entity.player.PlayerRenderer;
import net.minecraft.client.resources.PlayerSkin;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import server.galaxyunderchaos.galaxyunderchaos;

/** Registers the custom lightsaber pose layer on every player model. */
@EventBusSubscriber(
        modid = galaxyunderchaos.MODID,
        bus   = EventBusSubscriber.Bus.MOD,
        value = Dist.CLIENT
)
public final class ClientLayerInt {

    private ClientLayerInt() { }   // no instances
    private static boolean DONE = false;          // duplicate‑guard

    @SubscribeEvent
    public static void addLayers(EntityRenderersEvent.AddLayers event) {
        if (DONE) return;
        DONE = true;

        /* Iterate registered player skin models (DEFAULT, SLIM, etc.) */
        for (PlayerSkin.Model model : event.getSkins()) {
            PlayerRenderer renderer = event.getSkin(model);
            if (renderer != null) {
                renderer.addLayer(new LightsaberFirstPersonLayer(renderer));
            }
        }
    }
}
