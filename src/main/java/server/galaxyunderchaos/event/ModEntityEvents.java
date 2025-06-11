//// src/main/java/server/galaxyunderchaos/event/ModEntityEvents.java
//package server.galaxyunderchaos.event;
//
//import net.minecraft.world.entity.SpawnPlacementTypes;
//import net.minecraft.world.entity.monster.Monster;
//import net.minecraft.world.level.levelgen.Heightmap;
//import net.neoforged.bus.api.SubscribeEvent;
//import net.neoforged.fml.common.EventBusSubscriber;
//import net.neoforged.neoforge.event.entity.EntityAttributeCreationEvent;
//import net.neoforged.neoforge.event.entity.RegisterSpawnPlacementsEvent;
//import server.galaxyunderchaos.entity.AcidSpiderEntity;
//import server.galaxyunderchaos.entity.WingmawEntity;
//import server.galaxyunderchaos.galaxyunderchaos;
//
//@EventBusSubscriber(
//        modid = galaxyunderchaos.MODID,
//        bus   = EventBusSubscriber.Bus.MOD
//)
//public class ModEntityEvents {
//
//    @SubscribeEvent
//    public static void registerAttributes(EntityAttributeCreationEvent event) {
//        event.put(galaxyunderchaos.ACID_SPIDER.get(), AcidSpiderEntity.createAttributes().build());
//        event.put(galaxyunderchaos.WINGMAW.get(), WingmawEntity.createAttributes().build());
//
//
//    }
//
//    @SubscribeEvent
//    public static void registerSpawnPlacements(RegisterSpawnPlacementsEvent event) {
//        event.register(
//                galaxyunderchaos.ACID_SPIDER.get(),
//                SpawnPlacementTypes.ON_GROUND,
//                Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
//                Monster::checkMonsterSpawnRules,
//                RegisterSpawnPlacementsEvent.Operation.REPLACE
//        );
//        event.register(
//                galaxyunderchaos.WINGMAW.get(),
//                SpawnPlacementTypes.ON_GROUND,
//                Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
//                Monster::checkMonsterSpawnRules,
//                RegisterSpawnPlacementsEvent.Operation.REPLACE
//        );
//    }
//}
