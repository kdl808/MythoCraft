package kdlalp.mod.mythocraft.api.crafting;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;

public class AltarRecipes
{
	/** The static instance of this class */
    private static final AltarRecipes instance = new AltarRecipes();
    /** A list of all the recipes added */
    private List<IAltarRecipe> recipes = new ArrayList<IAltarRecipe>();

    /**
     * Returns the static instance of this class
     */
    public static final AltarRecipes getInstance()
    {
        /** The static instance of this class */
        return instance;
    }

    private AltarRecipes()
    {
    	addRecipe(RecipeHelper.getShapelessRecipe(new ItemStack(Items.coal, 64), Items.blaze_powder), 1, 0, 1600);
    	addRecipe(RecipeHelper.getShapelessRecipe(new ItemStack(Items.blaze_powder), Items.coal), 0, 2, 0);
    }
    
    
    public IAltarRecipe findMatchingRecipe(InventoryCrafting inv, EntityPlayer player)
    {
        for(int i = 0; i < recipes.size(); i++)
        {
            IAltarRecipe recipe = recipes.get(i);

            if(recipe.matches(player, inv))//Moved ichor and tier requirement checks to InventoryAltarOut to allow the Altar GUI bars to work
            {
                return recipe;
            }
        }
        return null;
    }

    /**
     * Adds a wrapped Crafting recipe to the list of recipes
     * @param recipe The crafting recipe to match
     * @param tierReward The tier increase a player gains for crafting this recipe
     * @param tierRequired The required tier a player needs in order to craft using this recipe
     * @param ichorCost The amount of ichor (in milibuckets) required to craft using this recipe
     */
    public void addRecipe(IRecipe recipe, int tierReward, int tierRequired, int ichorCost)
    {
    	addRecipe(recipe, null, tierReward, tierRequired, ichorCost);
    }
    
    /**
     * Adds a wrapped Crafting recipe to the list of recipes
     * @param recipe The crafting recipe to match
     * @param results The list of results from crafting, if null the standard IRecipe output will be used
     * @param tierReward The tier increase a player gains for crafting this recipe
     * @param tierRequired The required tier a player needs in order to craft using this recipe
     * @param ichorCost The amount of ichor (in milibuckets) required to craft using this recipe
     */
    public void addRecipe(IRecipe recipe, ItemStack[] results, int tierReward, int tierRequired, int ichorCost)
    {
    	recipes.add(new AltarCraftingRecipe(recipe, results, tierReward, tierRequired, ichorCost));
    }

    /**
     * returns the List<> of all recipes
     */
    public List<IAltarRecipe> getRecipeList()
    {
        return recipes;
    }
}
