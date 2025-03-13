package server.galaxyunderchaos.item;

import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;
import net.neoforged.neoforge.common.SimpleTier;
import server.galaxyunderchaos.datagen.ModTags;
import server.galaxyunderchaos.galaxyunderchaos;

public class ModToolTiers {
    public static final Tier LIGHTSABER = new SimpleTier(ModTags.Blocks.INCORRECT_FOR_LIGHTSABER_TOOL,
            1400, 4f, 3f, 28, () -> Ingredient.of(galaxyunderchaos.AEGIS_HILT));
}