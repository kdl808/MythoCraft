package kdlalp.mod.mythocraft.crafting;

import cpw.mods.fml.common.registry.GameRegistry;
import kdlalp.mod.mythocraft.blocks.MythoCraftBlocks;
import kdlalp.mod.mythocraft.items.MythoCraftItems;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class MythoCraftRecipes
{
	public static void init()
	{
		initCrafting();
		initAltar();
	}

	private static void initCrafting()
	{
		GameRegistry.addShapedRecipe(new ItemStack(Blocks.obsidian), "AA", "AA", 'A', MythoCraftItems.obsidianShard);
		//GameRegistry.addShapelessRecipe(new ItemStack(MythoCraftItems.obsidianShard, 4), Blocks.obsidian);
		GameRegistry.addShapelessRecipe(new ItemStack(MythoCraftItems.duskIron), MythoCraftItems.obsidianShard, Items.iron_ingot);
		GameRegistry.addShapelessRecipe(new ItemStack(MythoCraftItems.dawnGold), Items.quartz, Items.gold_ingot);
	}

	private static void initAltar()//Could move this to AltarRecipes
	{
    	AltarRecipes.getInstance().addShapelessRecipe(new ItemStack(Items.coal, 64), 1, 0, 16, Items.blaze_powder);
    	AltarRecipes.getInstance().addShapelessRecipe(new ItemStack(MythoCraftBlocks.altar), 100, 2, 0, Blocks.stone);
	}
}
