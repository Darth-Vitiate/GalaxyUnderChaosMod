package server.galaxyunderchaos.entity;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import server.galaxyunderchaos.block.TreeHangingSign;
import server.galaxyunderchaos.block.TreeStandingSign;
import server.galaxyunderchaos.block.TreeWallHangingSign;
import server.galaxyunderchaos.block.TreeWallSign;
import server.galaxyunderchaos.galaxyunderchaos;

/** Holds every BlockEntityType for GalaxyUnderChaos (NeoForge edition). */
public final class ModBlockEntities {

    /** Master deferred-register for BE types. */
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(BuiltInRegistries.BLOCK_ENTITY_TYPE, galaxyunderchaos.MODID);

    /* ───── Ak standing / wall sign ───── */
    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<ModSignBlockEntity>>
            AK_SIGN_BE = BLOCK_ENTITIES.register("ak_sign",
            () -> BlockEntityType.Builder.of(
                    ModSignBlockEntity::new,
                    galaxyunderchaos.AK_SIGN.get(),
                    galaxyunderchaos.AK_WALL_SIGN.get()
            ).build(null));

    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<ModHangingSignBlockEntity>>
            AK_HANGING_SIGN_BE = BLOCK_ENTITIES.register("ak_hanging_sign",
            () -> BlockEntityType.Builder.of(
                    ModHangingSignBlockEntity::new,
                    galaxyunderchaos.AK_HANGING_SIGN.get(),
                    galaxyunderchaos.AK_WALL_HANGING_SIGN.get()
            ).build(null));

    /* ───── Heart-Berry standing / wall sign ───── */
    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<ModSignBlockEntityHB>>
            HEART_BERRY_SIGN_BE = BLOCK_ENTITIES.register("heart_berry_sign",
            () -> BlockEntityType.Builder.of(
                    ModSignBlockEntityHB::new,
                    galaxyunderchaos.HEART_BERRY_SIGN.get(),
                    galaxyunderchaos.HEART_BERRY_WALL_SIGN.get()
            ).build(null));

    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<ModHangingSignBlockEntityHB>>
            HEART_BERRY_HANGING_SIGN_BE = BLOCK_ENTITIES.register("heart_berry_hanging_sign",
            () -> BlockEntityType.Builder.of(
                    ModHangingSignBlockEntityHB::new,
                    galaxyunderchaos.HEART_BERRY_HANGING_SIGN.get(),
                    galaxyunderchaos.HEART_BERRY_WALL_HANGING_SIGN.get()
            ).build(null));

    private ModBlockEntities() {} // Prevent instantiation
}
