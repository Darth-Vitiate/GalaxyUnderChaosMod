// File: server/galaxyunderchaos/lightsaber/PlayerLoginHandler.java
package server.galaxyunderchaos.lightsaber;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerPlayer;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;
import server.galaxyunderchaos.galaxyunderchaos;

/**
 * Syncs and persists LightsaberFormCapability on player login, respawn, save, and load.
 */
@EventBusSubscriber(modid = galaxyunderchaos.MODID, bus = EventBusSubscriber.Bus.GAME)
public class PlayerLoginHandler {

    // -- Sync on join & respawn ----------------------------------------------------------------
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

    // -- Persist forms on save & restore on load -----------------------------------------------

    @SubscribeEvent
    public static void onPlayerSave(PlayerEvent.SaveToFile event) {
                ServerPlayer player = (ServerPlayer) event.getEntity();
        LightsaberFormCapability cap = player.getCapability(LightsaberFormCapability.CAPABILITY);
        if (cap != null) {
            CompoundTag tag = cap.serializeNBT(null);
            player.getPersistentData().put("GalaxyUnderChaos_Forms", tag);
            galaxyunderchaos.LOGGER.debug("Saved lightsaber forms for {}", player.getScoreboardName());
        }
    }

   @SubscribeEvent
    public static void onPlayerLoad(PlayerEvent.LoadFromFile event) {
                ServerPlayer player = (ServerPlayer) event.getEntity();
        CompoundTag persisted = player.getPersistentData().getCompound("GalaxyUnderChaos_Forms");
        if (!persisted.isEmpty()) {
            LightsaberFormCapability cap = player.getCapability(LightsaberFormCapability.CAPABILITY);
            if (cap != null) {
                cap.deserializeNBT(null, persisted);
                galaxyunderchaos.LOGGER.debug("Loaded lightsaber forms for {}", player.getScoreboardName());
            }
        }
    }
}
