package client.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;
import server.galaxyunderchaos.item.LightsaberItem;

import java.util.function.Consumer;

public class ModItemRenderer extends BlockEntityWithoutLevelRenderer {

    public ModItemRenderer() {
        super(Minecraft.getInstance().getBlockEntityRenderDispatcher(), Minecraft.getInstance().getEntityModels());
    }

    @Override
    public void renderByItem(ItemStack stack, ItemDisplayContext displayContext, PoseStack poseStack, MultiBufferSource buffer, int light, int overlay) {
        Minecraft mc = Minecraft.getInstance();
        ItemRenderer renderer = mc.getItemRenderer();
        BakedModel model = renderer.getModel(stack, null, null, 0);

        if (stack.getItem() instanceof LightsaberItem lightsaber) {
            boolean isActive = lightsaber.isActive();

            // ✅ Always render hilt
            renderer.render(stack, displayContext, false, poseStack, buffer, light, overlay, model);

            if (isActive) {
                // ✅ Render blade only when active
                renderer.render(stack, displayContext, false, poseStack, buffer, 15728880, overlay, model);
            }
        }
    }







    public static void registerItemRenderer(Consumer<IClientItemExtensions> consumer) {
        consumer.accept(new IClientItemExtensions() {
            private final BlockEntityWithoutLevelRenderer customRenderer = new ModItemRenderer();

            @Override
            public BlockEntityWithoutLevelRenderer getCustomRenderer() {
                return customRenderer;
            }
        });
    }
}
