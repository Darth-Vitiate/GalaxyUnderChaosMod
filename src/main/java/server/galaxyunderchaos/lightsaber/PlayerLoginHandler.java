package server.galaxyunderchaos.lightsaber;

import net.neoforged.bus.api.SubscribeEvent;
import net.minecraft.server.level.ServerPlayer;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;
import server.galaxyunderchaos.galaxyunderchaos;

@EventBusSubscriber(modid = galaxyunderchaos.MODID, bus = EventBusSubscriber.Bus.GAME)
public class PlayerLoginHandler {

    @SubscribeEvent
    public static void onPlayerJoin(PlayerEvent.PlayerLoggedInEvent event) {
        if (event.getEntity() instanceof ServerPlayer player) {
            LightsaberFormCapabilityManager.syncCapability(player);
            galaxyunderchaos.LOGGER.debug("Synced lightsaber capability on login for {}", player.getScoreboardName());
        }
    }

    @SubscribeEvent
    public static void onPlayerRespawn(PlayerEvent.PlayerRespawnEvent event) {
        if (event.getEntity() instanceof ServerPlayer player) {
            LightsaberFormCapabilityManager.syncCapability(player);
            galaxyunderchaos.LOGGER.debug("Synced lightsaber capability on respawn for {}", player.getScoreboardName());
        }
    }

}
