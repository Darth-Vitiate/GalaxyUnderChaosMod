package client.renderer;

import net.minecraft.client.model.BoatModel;
import net.minecraft.client.model.ChestBoatModel;
import net.minecraft.client.model.geom.builders.LayerDefinition;

import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import server.galaxyunderchaos.entity.AkBoat;
import server.galaxyunderchaos.galaxyunderchaos;
@EventBusSubscriber(
        modid = galaxyunderchaos.MODID,
        bus   = EventBusSubscriber.Bus.MOD,
        value = Dist.CLIENT)
public final class AkBoatLayers {

    @SubscribeEvent
    public static void registerLayers(EntityRenderersEvent.RegisterLayerDefinitions e) {

        for (AkBoat.Type type : AkBoat.Type.values()) {

            // normal boat
            e.registerLayerDefinition(
                    AkBoatRenderer.createBoatModelName(type),
                    () -> BoatModel.createBodyModel()
            );

            // chest‑boat
            e.registerLayerDefinition(
                    AkBoatRenderer.createChestBoatModelName(type),
                    () -> ChestBoatModel.createBodyModel()
            );
        }
    }
}
