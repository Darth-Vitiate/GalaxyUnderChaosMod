package server.galaxyunderchaos.data;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import server.galaxyunderchaos.data.KeyBindings;
import server.galaxyunderchaos.lightsaber.LightsaberFormNetworking;
import server.galaxyunderchaos.lightsaber.SwitchLightsaberFormPacket;

@Mod.EventBusSubscriber(modid = "galaxyunderchaos", value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class KeyInputHandler {

    @SubscribeEvent
    public static void onKeyInput(InputEvent.Key event) {
        if (KeyBindings.SWITCH_FORM_KEY.consumeClick()) {
            LightsaberFormNetworking.sendToServer(new SwitchLightsaberFormPacket());
        }
//        if (KeyBindings.CYCLE_FORCE_POWER.consumeClick()) {
//            System.out.println("Cycle force power key pressed!");
//            GalaxyUnderChaosNetworking.sendToServer(new CycleForcePowerPacket());
//        }
//        if (KeyBindings.USE_FORCE_POWER.consumeClick()) {
//            System.out.println("Cycle force power key pressed!");
//            GalaxyUnderChaosNetworking.sendToServer(new UseForcePowerPacket());
//        }
    }
}
