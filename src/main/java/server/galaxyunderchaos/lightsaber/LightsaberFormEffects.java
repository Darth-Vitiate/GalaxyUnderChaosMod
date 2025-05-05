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

    // Pairs a Holder<Attribute> with its corresponding AttributeModifier
    private static record FormEffect(Holder<Attribute> attributeHolder, AttributeModifier modifier) {}

    static {
        // NOTE: using enum.name() as the key
        FORM_EFFECTS.put(LightsaberForm.SHII_CHO.name(), new FormEffect(
                Attributes.MOVEMENT_SPEED,
                new AttributeModifier(
                        ResourceLocation.fromNamespaceAndPath("galaxyunderchaos", "shii_cho_speed"),
                        0.15,
                        AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL
                )
        ));
        FORM_EFFECTS.put(LightsaberForm.MAKASHI.name(), new FormEffect(
                Attributes.ATTACK_DAMAGE,
                new AttributeModifier(
                        ResourceLocation.fromNamespaceAndPath("galaxyunderchaos", "makashi_damage"),
                        4.0,
                        AttributeModifier.Operation.ADD_VALUE
                )
        ));
        FORM_EFFECTS.put(LightsaberForm.SORESU.name(), new FormEffect(
                Attributes.ARMOR,
                new AttributeModifier(
                        ResourceLocation.fromNamespaceAndPath("galaxyunderchaos", "soresu_defense"),
                        0.5,
                        AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL
                )
        ));
        FORM_EFFECTS.put(LightsaberForm.ATARU.name(), new FormEffect(
                Attributes.ATTACK_SPEED,
                new AttributeModifier(
                        ResourceLocation.fromNamespaceAndPath("galaxyunderchaos", "ataru_attack_speed"),
                        0.30,
                        AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL
                )
        ));
        FORM_EFFECTS.put(LightsaberForm.SHIEN.name(), new FormEffect(
                Attributes.KNOCKBACK_RESISTANCE,
                new AttributeModifier(
                        ResourceLocation.fromNamespaceAndPath("galaxyunderchaos", "shien_counter"),
                        0.25,
                        AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL
                )
        ));
        FORM_EFFECTS.put(LightsaberForm.NIMAN.name(), new FormEffect(
                Attributes.ATTACK_KNOCKBACK,
                new AttributeModifier(
                        ResourceLocation.fromNamespaceAndPath("galaxyunderchaos", "niman_balance"),
                        0.15,
                        AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL
                )
        ));
        FORM_EFFECTS.put(LightsaberForm.JUYO.name(), new FormEffect(
                Attributes.ATTACK_DAMAGE,
                new AttributeModifier(
                        ResourceLocation.fromNamespaceAndPath("galaxyunderchaos", "juyo_power"),
                        6.0,
                        AttributeModifier.Operation.ADD_VALUE
                )
        ));
    }

    public static void applyEffects(Player player, String formName) {
        clearEffects(player);
        FormEffect effect = FORM_EFFECTS.get(formName);
        if (effect != null) {
            AttributeInstance inst = player.getAttribute(effect.attributeHolder());
            if (inst != null && !inst.hasModifier(effect.modifier().id())) {
                inst.addTransientModifier(effect.modifier());
            }
        }
    }

    public static void clearEffects(Player player) {
        for (FormEffect effect : FORM_EFFECTS.values()) {
            AttributeInstance inst = player.getAttribute(effect.attributeHolder());
            if (inst != null && inst.hasModifier(effect.modifier().id())) {
                inst.removeModifier(effect.modifier().id());
            }
        }
    }

    public static boolean isValidForm(String formName) {
        return FORM_EFFECTS.containsKey(formName);
    }
}
