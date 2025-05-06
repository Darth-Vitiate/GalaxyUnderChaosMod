package server.galaxyunderchaos.lightsaber;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.player.Player;
import net.neoforged.neoforge.capabilities.RegisterCapabilitiesEvent;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import server.galaxyunderchaos.galaxyunderchaos;

/**
 * Registers our LightsaberFormCapability against Player with LightsaberFormProvider.
 */
@EventBusSubscriber(modid = galaxyunderchaos.MODID, bus = EventBusSubscriber.Bus.MOD)
public final class LightsaberFormCapabilityHandler {
    private LightsaberFormCapabilityHandler() {}

    @SubscribeEvent
    public static void onRegisterCaps(RegisterCapabilitiesEvent event) {
        event.registerEntity(
                LightsaberFormCapability.CAPABILITY,
                EntityType.PLAYER,
                new LightsaberFormProvider()
        );
    }
}