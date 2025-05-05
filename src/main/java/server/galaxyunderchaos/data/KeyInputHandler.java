package server.galaxyunderchaos.data;

import net.minecraft.client.Minecraft;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.ClientTickEvent;
import server.galaxyunderchaos.galaxyunderchaos;
import server.galaxyunderchaos.lightsaber.LightsaberFormNetworking;
import server.galaxyunderchaos.lightsaber.SwitchLightsaberFormPacket;

/** Polls the key‑mappings once per client tick and sends packets. */
@EventBusSubscriber(
        modid = galaxyunderchaos.MODID,
        value = Dist.CLIENT,
        bus   = EventBusSubscriber.Bus.GAME          // ← TickEvent is on the FORGE bus
)
public final class KeyInputHandler {

    private KeyInputHandler() { }

    @SubscribeEvent
    public static void onClientTick(ClientTickEvent.Post event) {
        if (Minecraft.getInstance().player == null) return;

        /* -- Cycle lightsaber form ------------------------------------ */
        while (KeyBindings.SWITCH_FORM_KEY.consumeClick()) {
            LightsaberFormNetworking.sendToServer(new SwitchLightsaberFormPacket());
        }


        /* Uncomment when packets are ready
        while (KeyBindings.CYCLE_FORCE_POWER.consumeClick()) {
            GalaxyUnderChaosNetworking.sendToServer(new CycleForcePowerPacket());
        }
        while (KeyBindings.USE_FORCE_POWER.consumeClick()) {
            GalaxyUnderChaosNetworking.sendToServer(new UseForcePowerPacket());
        } */
    }
}
