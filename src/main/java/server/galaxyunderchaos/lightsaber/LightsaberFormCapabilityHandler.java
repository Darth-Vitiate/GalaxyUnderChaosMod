// File: server/galaxyunderchaos/lightsaber/LightsaberFormCapabilityHandler.java
package server.galaxyunderchaos.lightsaber;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.EntityType;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.capabilities.RegisterCapabilitiesEvent;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.EntityJoinLevelEvent;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;
import server.galaxyunderchaos.galaxyunderchaos;

/**
 * 1) Registers the LightsaberFormCapability on the MOD bus.
 * 2) Listens on the GAME bus to copy & sync on respawn / world-join.
 */
@EventBusSubscriber(modid = galaxyunderchaos.MODID, bus = EventBusSubscriber.Bus.MOD)
public final class LightsaberFormCapabilityHandler {
    private LightsaberFormCapabilityHandler() {
    }

    @SubscribeEvent
    public static void onRegisterCaps(RegisterCapabilitiesEvent event) {
        event.registerEntity(
                LightsaberFormCapability.CAPABILITY,
                EntityType.PLAYER,
                (player, ctx) -> new LightsaberFormCapability()
        );
    }

    /**
     * Runtime events must go on the GAME bus to actually fire in‐world
     */
    @EventBusSubscriber(modid = galaxyunderchaos.MODID, bus = EventBusSubscriber.Bus.GAME)
    public static final class RuntimeEvents {
        @SubscribeEvent
        public static void onJoin(EntityJoinLevelEvent event) {
            if (event.getEntity() instanceof ServerPlayer player && !player.level().isClientSide) {
                LightsaberFormCapabilityManager.syncCapability(player);
            }
        }

        @SubscribeEvent
        public static void onClone(PlayerEvent.Clone event) {
            var oldCap = event.getOriginal().getCapability(LightsaberFormCapability.CAPABILITY);
            var newCap = event.getEntity().getCapability(LightsaberFormCapability.CAPABILITY);

            if (oldCap != null && newCap != null) {
                newCap.deserializeNBT(null, oldCap.serializeNBT(null));
            }
        }
    }
}
