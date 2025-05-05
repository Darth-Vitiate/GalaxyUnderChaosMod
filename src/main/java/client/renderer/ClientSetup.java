//package client.renderer;
//
//import net.neoforged.bus.api.SubscribeEvent;
//import net.neoforged.fml.common.EventBusSubscriber;
//import net.neoforged.neoforge.client.event.CustomizeGuiOverlayEvent;
//import net.neoforged.neoforge.common.NeoForge;
//import server.galaxyunderchaos.galaxyunderchaos;
//
//@EventBusSubscriber(
//        modid = galaxyunderchaos.MODID,
//        bus   = EventBusSubscriber.Bus.GAME,
//        value = net.neoforged.api.distmarker.Dist.CLIENT
//)
//public class ClientSetup {
//
//    private static final HyperspaceOverlay hyperspaceOverlay = new HyperspaceOverlay();
//
//    @SubscribeEvent
//    public static void onRenderGuiOverlay(CustomizeGuiOverlayEvent event) { // ✅ Correct event type
//        //System.out.println("Rendering Hyperspace Overlay..."); // Debugging
//        hyperspaceOverlay.render(event.getGuiGraphics(), 0, 0, event.getPartialTick());
//    }
//
//    public static void setup() {
//        //System.out.println("Registering ClientSetup...");
//        NeoForge.EVENT_BUS.register(ClientSetup.class);
//    }
//}