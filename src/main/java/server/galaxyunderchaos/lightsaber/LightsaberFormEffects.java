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

    // Pairs a Holder<Attribute> with its corresponding AttributeModifier
    private static record FormEffect(Holder<Attribute> attributeHolder, AttributeModifier modifier) {}

    static {
        FORM_EFFECTS.put("Shii-Cho", new FormEffect(
                Attributes.MOVEMENT_SPEED,
                new AttributeModifier(
                        ResourceLocation.fromNamespaceAndPath("galaxyunderchaos", "shii_cho_speed"),
                        0.15,  // +15% movement speed
                        AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL
                )
        ));
        FORM_EFFECTS.put("Makashi", new FormEffect(
                Attributes.ATTACK_DAMAGE,
                new AttributeModifier(
                        ResourceLocation.fromNamespaceAndPath("galaxyunderchaos", "makashi_damage"),
                        4.0,   // +4 attack damage
                        AttributeModifier.Operation.ADD_VALUE
                )
        ));
        FORM_EFFECTS.put("Soresu", new FormEffect(
                Attributes.ARMOR,
                new AttributeModifier(
                        ResourceLocation.fromNamespaceAndPath("galaxyunderchaos", "soresu_defense"),
                        0.5,  // +50% base armor
                        AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL
                )
        ));
        FORM_EFFECTS.put("Ataru", new FormEffect(
                Attributes.ATTACK_SPEED,
                new AttributeModifier(
                        ResourceLocation.fromNamespaceAndPath("galaxyunderchaos", "ataru_attack_speed"),
                        0.30,  // +30% attack speed
                        AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL
                )
        ));
        FORM_EFFECTS.put("Shien / Djem So", new FormEffect(
                Attributes.KNOCKBACK_RESISTANCE,
                new AttributeModifier(
                        ResourceLocation.fromNamespaceAndPath("galaxyunderchaos", "shien_counter"),
                        0.25,  // +25% knockback resistance
                        AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL
                )
        ));
        FORM_EFFECTS.put("Niman", new FormEffect(
                Attributes.ATTACK_KNOCKBACK,
                new AttributeModifier(
                        ResourceLocation.fromNamespaceAndPath("galaxyunderchaos", "niman_balance"),
                        0.15,  // +15% attack knockback
                        AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL
                )
        ));
        FORM_EFFECTS.put("Juyo / Vaapad", new FormEffect(
                Attributes.ATTACK_DAMAGE,
                new AttributeModifier(
                        ResourceLocation.fromNamespaceAndPath("galaxyunderchaos", "juyo_power"),
                        6.0,   // +6 attack damage
                        AttributeModifier.Operation.ADD_VALUE
                )
        ));
    }

    /**
     * Clears existing form modifiers and applies the one corresponding to the selected form.
     */
    public static void applyEffects(Player player, String form) {
        removeAllEffects(player);
        FormEffect effect = FORM_EFFECTS.get(form);
        if (effect != null) {
            AttributeInstance inst = player.getAttribute(effect.attributeHolder());
            if (inst != null && !inst.hasModifier(effect.modifier().id())) {
                inst.addTransientModifier(effect.modifier());
            }
        }
    }

    /**
     * Removes every form modifier from its associated attribute.
     */
    public static void removeAllEffects(Player player) {
        for (FormEffect effect : FORM_EFFECTS.values()) {
            AttributeInstance inst = player.getAttribute(effect.attributeHolder());
            if (inst != null && inst.hasModifier(effect.modifier().id())) {
                inst.removeModifier(effect.modifier().id());
            }
        }
    }
}
