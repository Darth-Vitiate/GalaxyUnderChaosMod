package server.galaxyunderchaos.item;

import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;

public class LightsaberTier implements Tier {

    @Override
    public int getUses() {
        return 2500; // Durability of the lightsaber
    }

    @Override
    public float getSpeed() {
        return 15.0F; // Mining speed
    }

    @Override
    public float getAttackDamageBonus() {
        return 20.0F; // Additional attack damage
    }

    @Override
    public int getEnchantmentValue() {
        return 25; // Enchantment value
    }

    @Override
    public Ingredient getRepairIngredient() {
        // Use a futuristic material for repairs; obsidian as a placeholder
        return Ingredient.of(Blocks.OBSIDIAN);
    }

    @Override
    public TagKey<Block> getIncorrectBlocksForDrops() {
        // Return null to indicate no blocks are incorrectly dropped
        return null;
    }
}
