package server.galaxyunderchaos.sound;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;
import server.galaxyunderchaos.galaxyunderchaos;

import java.util.function.Supplier;

public class ModSounds {
    public static final DeferredRegister<SoundEvent> SOUND_EVENTS =
            DeferredRegister.create(BuiltInRegistries.SOUND_EVENT, galaxyunderchaos.MODID);
    public static final Supplier<SoundEvent> LIGHTSABER_HIT = registerSoundEvent("lightsaber_hit");
    public static final Supplier<SoundEvent> LIGHTSABER_SWING = registerSoundEvent("lightsaber_swing");
    public static final Supplier<SoundEvent> LIGHTSABER_IDLE = registerSoundEvent("lightsaber_idle");
    public static final Supplier<SoundEvent> LIGHTSABER_TURN_ON = registerSoundEvent("lightsaber_on");
    public static final Supplier<SoundEvent> LIGHTSABER_TURN_OFF = registerSoundEvent("lightsaber_off");
    public static final Supplier<SoundEvent> HYPERSPACE = registerSoundEvent("hyperspace");
    public static final Supplier<SoundEvent> LIGHTSABER_DEFLECT = registerSoundEvent("lightsaber_deflect");
    private static Supplier<SoundEvent> registerSoundEvent(String name) {
        ResourceLocation id = ResourceLocation.fromNamespaceAndPath(galaxyunderchaos.MODID, name);
        return SOUND_EVENTS.register(name, () -> SoundEvent.createVariableRangeEvent(id));
    }

    public static void register(IEventBus eventBus) {
        SOUND_EVENTS.register(eventBus);
    }
}