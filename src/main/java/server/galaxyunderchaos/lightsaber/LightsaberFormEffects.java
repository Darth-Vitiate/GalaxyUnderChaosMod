package server.galaxyunderchaos.lightsaber;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import java.util.HashMap;
import java.util.Map;

public class LightsaberFormEffects {
    private static final Map<String, AttributeModifier> FORM_MODIFIERS = new HashMap<>();

    static {
        FORM_MODIFIERS.put("Shii-Cho", new AttributeModifier(ResourceLocation.fromNamespaceAndPath("galaxyunderchaos", "shii_cho_speed"), 0.1, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL));
        FORM_MODIFIERS.put("Makashi", new AttributeModifier(ResourceLocation.fromNamespaceAndPath("galaxyunderchaos", "makashi_damage"), 2.0, AttributeModifier.Operation.ADD_VALUE));
        FORM_MODIFIERS.put("Soresu", new AttributeModifier(ResourceLocation.fromNamespaceAndPath("galaxyunderchaos", "soresu_defense"), -0.2, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL));
        FORM_MODIFIERS.put("Ataru", new AttributeModifier(ResourceLocation.fromNamespaceAndPath("galaxyunderchaos", "ataru_attack_speed"), 0.15, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL));
        FORM_MODIFIERS.put("Shien", new AttributeModifier(ResourceLocation.fromNamespaceAndPath("galaxyunderchaos", "shien_counter"), 1.5, AttributeModifier.Operation.ADD_VALUE));
        FORM_MODIFIERS.put("Niman", new AttributeModifier(ResourceLocation.fromNamespaceAndPath("galaxyunderchaos", "niman_balance"), 0.05, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL));
        FORM_MODIFIERS.put("Juyo", new AttributeModifier(ResourceLocation.fromNamespaceAndPath("galaxyunderchaos", "juyo_power"), 3.0, AttributeModifier.Operation.ADD_VALUE));
    }

    public static void applyEffects(Player player, String form) {
        removeAllEffects(player);
        if (FORM_MODIFIERS.containsKey(form)) {
            player.getAttribute(Attributes.ATTACK_DAMAGE).addTransientModifier(FORM_MODIFIERS.get(form));
        }
    }

    public static void removeAllEffects(Player player) {
        for (AttributeModifier modifier : FORM_MODIFIERS.values()) {
            player.getAttribute(Attributes.ATTACK_DAMAGE).removeModifier(modifier.id());
        }
    }
}
