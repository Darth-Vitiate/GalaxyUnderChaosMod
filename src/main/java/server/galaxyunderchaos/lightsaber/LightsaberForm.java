package server.galaxyunderchaos.lightsaber;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import server.galaxyunderchaos.galaxyunderchaos;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Enum representing every canonical lightsaber form supported by the mod.
 * <p>
 * Each constant carries its own:
 * <ul>
 *     <li>Display‑friendly name</li>
 *     <li>Primary {@link AttributeModifier} applied when the form is active</li>
 *     <li>Reference to a server‑side + client‑side animation/pose file</li>
 * </ul>
 * This keeps all hard‑coded data in one tidy place, avoids string‑based bugs,
 * and makes future refactors painless.
 */
public enum LightsaberForm {

    // ―――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――
    //    id              display‑name   attribute‑modifier                                  animation file
    // ―――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――

    SHII_CHO(
            "Shii‑Cho",
            new AttributeModifier(
                    ResourceLocation.fromNamespaceAndPath(galaxyunderchaos.MODID, "shii_cho_speed"),
                    0.10, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL),
            rl("animation/lightsaber/stance/shii_cho.anim")),

    MAKASHI(
            "Makashi",
            new AttributeModifier(
                    ResourceLocation.fromNamespaceAndPath(galaxyunderchaos.MODID, "makashi_damage"),
                    2.0, AttributeModifier.Operation.ADD_VALUE),
            rl("animation/lightsaber/stance/makashi.anim")),

    SORESU(
            "Soresu",
            new AttributeModifier(
                    ResourceLocation.fromNamespaceAndPath(galaxyunderchaos.MODID, "soresu_defense"),
                    -0.20, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL),
            rl("animation/lightsaber/stance/soresu.anim")),

    ATARU(
            "Ataru",
            new AttributeModifier(
                    ResourceLocation.fromNamespaceAndPath(galaxyunderchaos.MODID, "ataru_attack_speed"),
                    0.15, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL),
            rl("animation/lightsaber/stance/ataru.anim")),

    SHIEN(
            "Shien / Djem So",
            new AttributeModifier(
                    ResourceLocation.fromNamespaceAndPath(galaxyunderchaos.MODID, "shien_counter"),
                    1.5, AttributeModifier.Operation.ADD_VALUE),
            rl("animation/lightsaber/stance/shien.anim")),

    NIMAN(
            "Niman",
            new AttributeModifier(
                    ResourceLocation.fromNamespaceAndPath(galaxyunderchaos.MODID, "niman_balance"),
                    0.05, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL),
            rl("animation/lightsaber/stance/niman.anim")),

    JUYO(
            "Juyo / Vaapad",
            new AttributeModifier(
                    ResourceLocation.fromNamespaceAndPath(galaxyunderchaos.MODID, "juyo_power"),
                    3.0, AttributeModifier.Operation.ADD_VALUE),
            rl("animation/lightsaber/stance/juyo.anim"));

    // ---------------------------------------------------------------------
    private final String displayName;
    private final AttributeModifier modifier;
    private final ResourceLocation animation;

    LightsaberForm(String displayName, AttributeModifier modifier, ResourceLocation animation) {
        this.displayName = displayName;
        this.modifier = modifier;
        this.animation = animation;
    }

    /* ------------------------------------------------------------------ */
    /* Getters                                                            */
    /* ------------------------------------------------------------------ */

    /**
     * Stable identifier saved in NBT & packets (enum constant name).
     */
    public String getId() {
        return name();
    }

    public String getDisplayName() {
        return displayName;
    }

    public AttributeModifier getModifier() {
        return modifier;
    }

    public ResourceLocation getAnimation() {
        return animation;
    }

    /* ------------------------------------------------------------------ */
    /* Helpers                                                             */
    /* ------------------------------------------------------------------ */

    private static ResourceLocation rl(String path) {
        return ResourceLocation.fromNamespaceAndPath(galaxyunderchaos.MODID, path);
    }

    // Quick lookup for deserialization / commands
    private static final Map<String, LightsaberForm> BY_ID =
            Stream.of(values()).collect(Collectors.toUnmodifiableMap(LightsaberForm::getId, f -> f));

    /**
     * Converts the stored id back into the enum, falling back to SHII_CHO if
     * the id is unknown (corrupted save, removed mod, etc.).
     */
    public static LightsaberForm fromId(String id) {
        return BY_ID.getOrDefault(id, SHII_CHO);
    }

    /**
     * Cycle helper used by {@code SwitchLightsaberFormPacket} so the packet
     * doesn’t have to clone & sort the enum every time.
     */
    public LightsaberForm next() {
        int nxt = (ordinal() + 1) % values().length;
        return values()[nxt];
    }
}
