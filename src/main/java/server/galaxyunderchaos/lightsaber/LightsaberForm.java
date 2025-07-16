package server.galaxyunderchaos.lightsaber;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import server.galaxyunderchaos.galaxyunderchaos;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Enum representing every canonical lightsaber form supported by the mod.
 *
 * Each constant carries its own:
 * <ul>
 *     <li>Display‑friendly name</li>
 *     <li>Primary {@link AttributeModifier} applied when the form is active</li>
 *     <li>Reference to a server‑side + client‑side animation file under resources/assets/<modid>/animations</li>
 * </ul>
 */
public enum LightsaberForm {

    SHII_CHO(
            "Shii-Cho",
            new AttributeModifier(
                    ResourceLocation.fromNamespaceAndPath(galaxyunderchaos.MODID, "shii_cho_speed"),
                    0.10, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL
            ),
            rl("lightsaber/stance/shii_cho.anim")
    ),

    MAKASHI(
            "Makashi",
            new AttributeModifier(
                    ResourceLocation.fromNamespaceAndPath(galaxyunderchaos.MODID, "makashi_damage"),
                    2.0, AttributeModifier.Operation.ADD_VALUE
            ),
            rl("lightsaber/stance/makashi.anim")
    ),

    SORESU(
            "Soresu",
            new AttributeModifier(
                    ResourceLocation.fromNamespaceAndPath(galaxyunderchaos.MODID, "soresu_defense"),
                    0.20, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL
            ),
            rl("lightsaber/stance/soresu.anim")
    ),

    ATARU(
            "Ataru",
            new AttributeModifier(
                    ResourceLocation.fromNamespaceAndPath(galaxyunderchaos.MODID, "ataru_attack_speed"),
                    0.15, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL
            ),
            rl("lightsaber/stance/ataru.anim")
    ),

    SHIEN(
            "Shien / DjemSo",
            new AttributeModifier(
                    ResourceLocation.fromNamespaceAndPath(galaxyunderchaos.MODID, "shien_counter"),
                    1.50, AttributeModifier.Operation.ADD_VALUE
            ),
            rl("lightsaber/stance/shien.anim")
    ),

    NIMAN(
            "Niman",
            new AttributeModifier(
                    ResourceLocation.fromNamespaceAndPath(galaxyunderchaos.MODID, "niman_balance"),
                    0.05, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL
            ),
            rl("lightsaber/stance/niman.anim")
    ),

    JUYO(
            "Juyo / Vaapad",
            new AttributeModifier(
                    ResourceLocation.fromNamespaceAndPath(galaxyunderchaos.MODID, "juyo_power"),
                    3.00, AttributeModifier.Operation.ADD_VALUE
            ),
            rl("lightsaber/stance/juyo.anim")
    );

    private final String displayName;
    private final AttributeModifier modifier;
    private final ResourceLocation animation;

    LightsaberForm(String displayName, AttributeModifier modifier, ResourceLocation animation) {
        this.displayName = displayName;
        this.modifier = modifier;
        this.animation = animation;
    }

    public String getDisplayName() { return displayName; }
    public AttributeModifier getModifier() { return modifier; }
    public ResourceLocation getAnimation() { return animation; }

    private static ResourceLocation rl(String path) {
        // Animations should be placed under resources/assets/<modid>/animations/
        return ResourceLocation.fromNamespaceAndPath(galaxyunderchaos.MODID, "animations/" + path);
    }

    private static final Map<String, LightsaberForm> BY_ID =
            Stream.of(values()).collect(Collectors.toUnmodifiableMap(Enum::name, f -> f));

    public static LightsaberForm fromId(String id) {
        return BY_ID.getOrDefault(id, SHII_CHO);
    }

    public LightsaberForm next() {
        LightsaberForm[] vals = values();
        return vals[(ordinal() + 1) % vals.length];
    }
}
