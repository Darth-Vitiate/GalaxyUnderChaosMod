package server.galaxyunderchaos.event;
import client.model.AcidSpiderModel;
import client.model.WingmawModel;
import net.minecraft.world.entity.SpawnPlacementTypes;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.levelgen.Heightmap;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.event.entity.EntityAttributeCreationEvent;
import net.neoforged.neoforge.event.entity.RegisterSpawnPlacementsEvent;
import net.neoforged.fml.common.Mod;
import server.galaxyunderchaos.entity.AcidSpiderEntity;
import server.galaxyunderchaos.entity.WingmawEntity;
import server.galaxyunderchaos.galaxyunderchaos;

@EventBusSubscriber(
        modid = galaxyunderchaos.MODID,
        bus   = EventBusSubscriber.Bus.MOD,
        value = Dist.CLIENT)
public class ModEventBusEvents {
    @SubscribeEvent
    public static void registerLayers(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(AcidSpiderModel.LAYER_LOCATION, AcidSpiderModel::createBodyLayer);
        event.registerLayerDefinition(WingmawModel.LAYER_LOCATION, WingmawModel::createBodyLayer);
    }
    @SubscribeEvent
    public static void registerSpawnPlacements(RegisterSpawnPlacementsEvent event) {
        event.register(galaxyunderchaos.ACID_SPIDER.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                Monster::checkMonsterSpawnRules, RegisterSpawnPlacementsEvent.Operation.REPLACE);
    }
    @SubscribeEvent
    public static void registerAttributes(EntityAttributeCreationEvent event) {
        event.put(galaxyunderchaos.WINGMAW.get(), WingmawEntity.createAttributes().build());
        event.put(galaxyunderchaos.ACID_SPIDER.get(), AcidSpiderEntity.createAttributes().build());
    }
}