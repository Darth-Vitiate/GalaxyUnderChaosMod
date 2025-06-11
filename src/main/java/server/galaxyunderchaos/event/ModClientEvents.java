//// src/main/java/server/galaxyunderchaos/event/ModClientEvents.java
//package server.galaxyunderchaos.event;
//
//import client.model.AcidSpiderModel;
//import client.model.WingmawModel;
//import net.neoforged.api.distmarker.Dist;
//import net.neoforged.bus.api.SubscribeEvent;
//import net.neoforged.fml.common.EventBusSubscriber;
//import net.neoforged.neoforge.client.event.EntityRenderersEvent;
//import static server.galaxyunderchaos.galaxyunderchaos.MODID;
//
//@EventBusSubscriber(
//        modid = MODID,
//        bus   = EventBusSubscriber.Bus.MOD,
//        value = Dist.CLIENT
//)
//public class ModClientEvents {
//    @SubscribeEvent
//    public static void registerLayers(EntityRenderersEvent.RegisterLayerDefinitions event) {
//        event.registerLayerDefinition(
//                AcidSpiderModel.LAYER_LOCATION,
//                AcidSpiderModel::createBodyLayer
//        );
//        event.registerLayerDefinition(
//                WingmawModel.LAYER_LOCATION,
//                WingmawModel::createBodyLayer
//        );
//    }
//}
