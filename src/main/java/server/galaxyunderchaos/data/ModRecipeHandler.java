package server.galaxyunderchaos.data;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.ItemLike;
import net.neoforged.neoforge.common.conditions.IConditionBuilder;
import server.galaxyunderchaos.galaxyunderchaos;

import java.util.List;
import java.util.concurrent.CompletableFuture;
public class ModRecipeHandler extends RecipeProvider implements IConditionBuilder {
    public ModRecipeHandler(PackOutput pOutput, CompletableFuture<HolderLookup.Provider> pRegistries) {
        super(pOutput, pRegistries);
    }
    @Override
    protected void buildRecipes(RecipeOutput pRecipeOutput) {
        List<ItemLike> TITANIUM_SMELTABLES = List.of(galaxyunderchaos.TITANIUM_INGOT.get(),
                galaxyunderchaos.TITANIUM_ORE.get(), galaxyunderchaos.TITANIUM_DEEPSLATE_ORE.get());
        List<ItemLike> CHROMIUM_SMELTABLES = List.of(galaxyunderchaos.CHROMIUM_INGOT.get(),
                galaxyunderchaos.CHROMIUM_ORE.get(), galaxyunderchaos.CHROMIUM_DEEPSLATE_ORE.get());
        List<ItemLike> CHITIN_SMELTABLES = List.of(galaxyunderchaos.ACID_FORGED_PLATE.get(),
                galaxyunderchaos.CHITIN_FRAGMENTS.get());
        oreSmelting(pRecipeOutput, CHROMIUM_SMELTABLES, RecipeCategory.MISC, galaxyunderchaos.CHROMIUM_INGOT.get(), 0.25f, 200, "chromium");
        oreSmelting(pRecipeOutput, TITANIUM_SMELTABLES, RecipeCategory.MISC, galaxyunderchaos.TITANIUM_INGOT.get(), 0.25f, 200, "titanium");
        oreBlasting(pRecipeOutput, TITANIUM_SMELTABLES, RecipeCategory.MISC, galaxyunderchaos.TITANIUM_INGOT.get(), 0.25f, 100, "titanium");
        oreBlasting(pRecipeOutput, CHROMIUM_SMELTABLES, RecipeCategory.MISC, galaxyunderchaos.CHROMIUM_INGOT.get(), 0.25f, 100, "chromium");
        oreBlasting(pRecipeOutput, CHITIN_SMELTABLES, RecipeCategory.MISC, galaxyunderchaos.CHITIN_FRAGMENTS.get(), 0.25f, 100, "chitin_fragments");
        oreSmelting(pRecipeOutput, CHITIN_SMELTABLES, RecipeCategory.MISC, galaxyunderchaos.CHITIN_FRAGMENTS.get(), 0.25f, 200, "chitin_fragments");
    }
    protected static void oreSmelting(RecipeOutput recipeOutput, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult,
                                      float pExperience, int pCookingTIme, String pGroup) {
        oreCooking(recipeOutput, RecipeSerializer.SMELTING_RECIPE, SmeltingRecipe::new, pIngredients, pCategory, pResult,
                pExperience, pCookingTIme, pGroup, "_by_smelting");
    }
    protected static void oreBlasting(RecipeOutput recipeOutput, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult,
                                      float pExperience, int pCookingTime, String pGroup) {
        oreCooking(recipeOutput, RecipeSerializer.BLASTING_RECIPE, BlastingRecipe::new, pIngredients, pCategory, pResult,
                pExperience, pCookingTime, pGroup, "_by_blasting");
    }
    protected static <T extends AbstractCookingRecipe> void oreCooking(RecipeOutput recipeOutput, RecipeSerializer<T> pCookingSerializer, AbstractCookingRecipe.Factory<T> factory,
                                                                       List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult, float pExperience, int pCookingTime, String pGroup, String pRecipeName) {
        for(ItemLike itemlike : pIngredients) {
            SimpleCookingRecipeBuilder.generic(Ingredient.of(itemlike), pCategory, pResult, pExperience, pCookingTime, pCookingSerializer, factory).group(pGroup).unlockedBy(getHasName(itemlike), has(itemlike))
                    .save(recipeOutput, galaxyunderchaos.MODID + ":" + getItemName(pResult) + pRecipeName + "_" + getItemName(itemlike));
        }
    }
}