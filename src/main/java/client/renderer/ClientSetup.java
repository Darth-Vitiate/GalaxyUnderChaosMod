//package client.renderer;
//
//import net.minecraft.client.gui.GuiGraphics;
//import net.neoforged.api.distmarker.Dist;
//import net.neoforged.bus.api.SubscribeEvent;
//import net.neoforged.fml.common.EventBusSubscriber;
//import net.neoforged.neoforge.client.event.CustomizeGuiOverlayEvent;
//import net.neoforged.neoforge.common.NeoForge;
//import server.galaxyunderchaos.galaxyunderchaos;
//
//@EventBusSubscriber(modid = galaxyunderchaos.MODID, value = Dist.CLIENT, bus = EventBusSubscriber.Bus.MOD)
//public class ClientSetup {
//
//    private static final HyperspaceOverlay hyperspaceOverlay = new HyperspaceOverlay();
//
//    @SubscribeEvent
//    public static void onRenderGuiOverlay(CustomizeGuiOverlayEvent event) {
//        System.out.println("Rendering Hyperspace Overlay..."); // Debugging
//
//        float partialTicks = event.getPartialTick().tickFraction(); // ✅ Extract float tick fraction
//
//        hyperspaceOverlay.render(event.getGuiGraphics(), 0, 0, partialTicks);
//    }
//
//    public static void setup() {
//        System.out.println("Registering ClientSetup...");
//        NeoForge.EVENT_BUS.register(ClientSetup.class);
//    }
//}
