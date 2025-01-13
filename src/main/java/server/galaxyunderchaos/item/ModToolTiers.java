package server.galaxyunderchaos.item;

import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.ForgeTier;
import server.galaxyunderchaos.datagen.ModTags;
import server.galaxyunderchaos.galaxyunderchaos;

public class ModToolTiers {
    public static final Tier LIGHTSABER = new ForgeTier(2500, 4, 20f, 20,
            ModTags.Blocks.NEEDS_LIGHTSABER_TOOL, () -> Ingredient.of(galaxyunderchaos.AEGIS_HILT.get()),
            ModTags.Blocks.INCORRECT_FOR_LIGHTSABER_TOOL);
}