package server.galaxyunderchaos.item;

import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;

import java.util.function.Consumer;

public class LightsaberItem extends Item {
    private final String bladeColor;

    public LightsaberItem(String bladeColor, Properties properties) {
        super(properties);
        this.bladeColor = bladeColor;
    }

    public String getBladeColor() {
        return bladeColor;
    }

//    @Override
//    public void initializeClient(Consumer<IClientItemExtensions> consumer) {
//        consumer.accept(new IClientItemExtensions() {
//            @Override
//            public BlockEntityWithoutLevelRenderer getCustomRenderer() {
//                return LightsaberRenderer.INSTANCE;
//            }
//        });
//    }

}
