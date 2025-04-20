//
//package client.renderer;
//
//import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
//import net.minecraftforge.api.distmarker.Dist;
//import net.minecraftforge.client.event.RegisterClientReloadListenersEvent;
//import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
//import net.minecraftforge.fml.common.Mod;
//import net.minecraftforge.eventbus.api.SubscribeEvent;
//import server.galaxyunderchaos.galaxyunderchaos;
//import net.minecraft.world.item.ItemStack;
//import net.minecraftforge.client.extensions.common.IClientItemExtensions;
//
//@Mod.EventBusSubscriber(modid = galaxyunderchaos.MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
//public class ClientRendererHook {
//
//    @SubscribeEvent
//    public static void onClientSetup(FMLClientSetupEvent event) {
//        event.enqueueWork(() -> {
//            galaxyunderchaos.DOUBLE_BLADED_LIGHTSABERS.values().forEach(itemReg -> {
//                ItemStack dummy = new ItemStack(itemReg.get());
//                IClientItemExtensions.of(dummy).initializeClient(ext -> {});
//            });
//        });
//    }
//}
