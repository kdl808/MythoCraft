package kdlalp.mod.mythocraft.crafting;

import kdlalp.mod.mythocraft.items.MythoCraftItems;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.common.registry.GameRegistry;

public class MythoCraftRecipes
{
	public static void init()
	{
		initCrafting();
		//initAltar(); Moved to AltarRecipes()
	}

	private static void initCrafting()
	{
		GameRegistry.addShapedRecipe(new ItemStack(Blocks.obsidian), "AA", "AA", 'A', MythoCraftItems.obsidianShard);
		//GameRegistry.addShapelessRecipe(new ItemStack(MythoCraftItems.obsidianShard, 4), Blocks.obsidian);
		GameRegistry.addShapelessRecipe(new ItemStack(MythoCraftItems.duskIron), MythoCraftItems.obsidianShard, Items.iron_ingot);
		GameRegistry.addShapelessRecipe(new ItemStack(MythoCraftItems.dawnGold), Items.quartz, Items.gold_ingot);
	}

	/*private static void initAltar()
	{
    	AltarRecipes.getInstance().addRecipe(RecipeHelper.getShapelessRecipe(new ItemStack(Items.coal, 64), Items.blaze_powder), 1, 0, 1600);
    	AltarRecipes.getInstance().addRecipe(RecipeHelper.getShapelessRecipe(new ItemStack(Items.blaze_powder), Items.coal), 0, 2, 0);
	}*/
}
