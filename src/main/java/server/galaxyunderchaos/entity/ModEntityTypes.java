package server.galaxyunderchaos.entity;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import server.galaxyunderchaos.galaxyunderchaos;

/** All custom EntityType registrations (NeoForge edition). */
public final class ModEntityTypes {

    /** Master deferred-register for entity types. */
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES =
            DeferredRegister.create(BuiltInRegistries.ENTITY_TYPE, galaxyunderchaos.MODID);

    /* ───── Ak Boats ─────────────────────────────────────── */
    public static final DeferredHolder<EntityType<?>, EntityType<AkBoat>> AK_BOAT =
            ENTITY_TYPES.register("ak_boat",
                    () -> EntityType.Builder.<AkBoat>of(AkBoat::new, MobCategory.MISC)
                            .sized(1.375F, 0.5625F)
                            .build("ak_boat"));

    public static final DeferredHolder<EntityType<?>, EntityType<AkChestBoat>> AK_CHEST_BOAT =
            ENTITY_TYPES.register("ak_chest_boat",
                    () -> EntityType.Builder.<AkChestBoat>of(AkChestBoat::new, MobCategory.MISC)
                            .sized(1.375F, 0.5625F)
                            .build("ak_chest_boat"));

    /* ───── Heart-Berry Boats ────────────────────────────── */
    public static final DeferredHolder<EntityType<?>, EntityType<HeartBerryBoat>> HEART_BERRY_BOAT =
            ENTITY_TYPES.register("heart_berry_boat",
                    () -> EntityType.Builder.<HeartBerryBoat>of(HeartBerryBoat::new, MobCategory.MISC)
                            .sized(1.375F, 0.5625F)
                            .build("heart_berry_boat"));

    public static final DeferredHolder<EntityType<?>, EntityType<HeartBerryChestBoat>> HEART_BERRY_CHEST_BOAT =
            ENTITY_TYPES.register("heart_berry_chest_boat",
                    () -> EntityType.Builder.<HeartBerryChestBoat>of(HeartBerryChestBoat::new, MobCategory.MISC)
                            .sized(1.375F, 0.5625F)
                            .build("heart_berry_chest_boat"));

    private ModEntityTypes() {} // no instantiation
}
