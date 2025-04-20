package server.galaxyunderchaos.data;

import com.mojang.serialization.Codec;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.Registries;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import server.galaxyunderchaos.galaxyunderchaos;

import java.util.function.UnaryOperator;

public class ModDataComponentTypes {
    public static final DeferredRegister<DataComponentType<?>> DATA_COMPONENT_TYPES =
            DeferredRegister.create(Registries.DATA_COMPONENT_TYPE, galaxyunderchaos.MODID);
    public static final RegistryObject<DataComponentType<DoubleSaberData>> DOUBLE_SABER_DATA =
            DATA_COMPONENT_TYPES.register("double_saber_data", () -> DataComponentType.<DoubleSaberData>builder().persistent(DoubleSaberData.CODEC).build());
    public static final RegistryObject<DataComponentType<Boolean>> ACTIVE = register("active",
            builder -> builder.persistent(Codec.BOOL));

    private static <T> RegistryObject<DataComponentType<T>> register(String name, UnaryOperator<DataComponentType.Builder<T>> builderOperator) {
        return DATA_COMPONENT_TYPES.register(name, () -> builderOperator.apply(DataComponentType.builder()).build());
    }

    public static void register(IEventBus eventBus) {
        DATA_COMPONENT_TYPES.register(eventBus);
    }
}
