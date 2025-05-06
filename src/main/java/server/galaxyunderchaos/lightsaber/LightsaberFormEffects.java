// server/galaxyunderchaos/lightsaber/LightsaberFormEffects.java
package server.galaxyunderchaos.lightsaber;

import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;

import java.util.HashMap;
import java.util.Map;

public class LightsaberFormEffects {

    private static final Map<String, FormEffect> FORM_EFFECTS = new HashMap<>();

    private static record FormEffect(Holder<net.minecraft.world.entity.ai.attributes.Attribute> attrHolder,
                                     AttributeModifier modifier) {}

    static {
        // NOTE: using enum.name() as the key
        FORM_EFFECTS.put("Shii-Cho", new FormEffect(
                Attributes.MOVEMENT_SPEED,
                new AttributeModifier(ResourceLocation.fromNamespaceAndPath("galaxyunderchaos","shii_cho_speed"),
                        0.15,
                        AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL)
        ));
        FORM_EFFECTS.put("Makashi", new FormEffect(
                Attributes.ATTACK_DAMAGE,
                new AttributeModifier(ResourceLocation.fromNamespaceAndPath("galaxyunderchaos", "makashi_damage"),
                        4.0,
                        AttributeModifier.Operation.ADD_VALUE)
        ));
        FORM_EFFECTS.put("Soresu", new FormEffect(
                Attributes.ARMOR,
                new AttributeModifier(
                        ResourceLocation.fromNamespaceAndPath("galaxyunderchaos", "soresu_defense"),
                        0.5,
                        AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL
                )
        ));
        FORM_EFFECTS.put("Ataru", new FormEffect(
                Attributes.ATTACK_SPEED,
                new AttributeModifier(
                        ResourceLocation.fromNamespaceAndPath("galaxyunderchaos", "ataru_attack_speed"),
                        0.30,
                        AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL
                )
        ));
        FORM_EFFECTS.put("Shien / Djem So", new FormEffect(
                Attributes.KNOCKBACK_RESISTANCE,
                new AttributeModifier(
                        ResourceLocation.fromNamespaceAndPath("galaxyunderchaos", "shien_counter"),
                        0.25,
                        AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL
                )
        ));
        FORM_EFFECTS.put("Niman", new FormEffect(
                Attributes.ATTACK_KNOCKBACK,
                new AttributeModifier(
                        ResourceLocation.fromNamespaceAndPath("galaxyunderchaos", "niman_balance"),
                        0.15,
                        AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL
                )
        ));
        FORM_EFFECTS.put("Juyo / Vaapad", new FormEffect(
                Attributes.ATTACK_DAMAGE,
                new AttributeModifier(
                        ResourceLocation.fromNamespaceAndPath("galaxyunderchaos", "juyo_power"),
                        6.0,
                        AttributeModifier.Operation.ADD_VALUE
                )
        ));
    }

    public static void applyEffects(Player player, String formName) {
        FORM_EFFECTS.forEach((name, effect) -> {
            // Remove all modifiers from every form first:
            player.getAttribute(effect.attrHolder()).removeModifier(effect.modifier());
        });

        FormEffect fx = FORM_EFFECTS.get(formName);
        if (fx != null) {
            player.getAttribute(fx.attrHolder()).addTransientModifier(fx.modifier());
        }
    }

    /** Clears all form modifiers (e.g. when no form selected). */
    public static void clearEffects(Player player) {
        FORM_EFFECTS.values().forEach(effect ->
                player.getAttribute(effect.attrHolder()).removeModifier(effect.modifier())
        );
    }


    public static boolean isValidForm(String formName) {
        return FORM_EFFECTS.containsKey(formName);
    }
}
