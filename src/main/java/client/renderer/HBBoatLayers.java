package client.renderer;

import net.minecraft.client.model.BoatModel;
import net.minecraft.client.model.ChestBoatModel;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import server.galaxyunderchaos.entity.HeartBerryBoat;
import server.galaxyunderchaos.galaxyunderchaos;

@EventBusSubscriber(
        modid = galaxyunderchaos.MODID,
        bus   = EventBusSubscriber.Bus.MOD,
        value = Dist.CLIENT)
public final class HBBoatLayers {

    @SubscribeEvent
    public static void registerLayers(EntityRenderersEvent.RegisterLayerDefinitions e) {

        for (HeartBerryBoat.Type type : HeartBerryBoat.Type.values()) {

            // normal boat
            e.registerLayerDefinition(
                    HBBoatRenderer.createBoatModelName(type),
                    () -> BoatModel.createBodyModel()
            );

            // chest‑boat
            e.registerLayerDefinition(
                    HBBoatRenderer.createChestBoatModelName(type),
                    () -> ChestBoatModel.createBodyModel()
            );
        }
    }
}
