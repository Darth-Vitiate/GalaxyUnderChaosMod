//package server.galaxyunderchaos.lightsaber;
//
//import net.minecraft.server.level.ServerPlayer;
//import net.neoforged.bus.api.SubscribeEvent;
//import net.neoforged.fml.common.EventBusSubscriber;
//import net.neoforged.neoforge.event.entity.player.PlayerEvent;
//import server.galaxyunderchaos.galaxyunderchaos;
//
//@EventBusSubscriber(modid = galaxyunderchaos.MODID, bus = EventBusSubscriber.Bus.MOD)
//public class PlayerCloneHandler {
//
//    @SubscribeEvent
//    public static void onClone(PlayerEvent.Clone event) {
//        if (event.isWasDeath()) {
//            event.getOriginal().getCapability(LightsaberFormCapability.CAPABILITY).ifPresent(oldCap ->
//                    event.getEntity().getCapability(LightsaberFormCapability.CAPABILITY).ifPresent(newCap ->
//                            newCap.deserializeNBT(null, oldCap.serializeNBT(null))
//                    )
//            );
//        }
//    }
//}
