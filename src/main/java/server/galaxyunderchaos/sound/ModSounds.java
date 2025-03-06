package server.galaxyunderchaos.sound;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.common.util.ForgeSoundType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import server.galaxyunderchaos.galaxyunderchaos;

public class ModSounds {
    public static final DeferredRegister<SoundEvent> SOUND_EVENTS =
            DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, galaxyunderchaos.MODID);
    public static final RegistryObject<SoundEvent> LIGHTSABER_HIT = registerSoundEvent("lightsaber_hit");
    public static final RegistryObject<SoundEvent> LIGHTSABER_SWING = registerSoundEvent("lightsaber_swing");
    public static final RegistryObject<SoundEvent> LIGHTSABER_IDLE = registerSoundEvent("lightsaber_idle");
    public static final RegistryObject<SoundEvent> LIGHTSABER_TURN_ON = registerSoundEvent("lightsaber_on");
    public static final RegistryObject<SoundEvent> LIGHTSABER_TURN_OFF = registerSoundEvent("lightsaber_off");
    public static final RegistryObject<SoundEvent> HYPERSPACE = registerSoundEvent("hyperspace");
    public static final RegistryObject<SoundEvent> LIGHTSABER_DEFLECT = registerSoundEvent("lightsaber_deflect");
    private static RegistryObject<SoundEvent> registerSoundEvent(String name) {
        return SOUND_EVENTS.register(name, () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(galaxyunderchaos.MODID, name)));
    }
    public static void register(IEventBus eventBus) {
        SOUND_EVENTS.register(eventBus);
    }
}