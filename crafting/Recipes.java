package mod.kdl808.mythocraft.crafting;

import mod.kdl808.mythocraft.items.materials.DawnGoldIngot;
import mod.kdl808.mythocraft.items.materials.DuskIronIngot;
import mod.kdl808.mythocraft.items.materials.ObsidianShard;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.common.registry.GameRegistry;

public class Recipes {
	public static void init()
	{
		GameRegistry.addShapedRecipe(new ItemStack(Blocks.obsidian), new Object[] {"AA ", "AA ", "   ", 'A', ObsidianShard.obsidianShard});
		GameRegistry.addShapelessRecipe(new ItemStack(ObsidianShard.obsidianShard, 4), new Object[] {Blocks.obsidian});
		GameRegistry.addShapelessRecipe(new ItemStack(DuskIronIngot.duskironIngot), new Object[] {ObsidianShard.obsidianShard, Items.iron_ingot});
		GameRegistry.addShapelessRecipe(new ItemStack(DawnGoldIngot.dawngoldIngot), new Object[] {Items.quartz, Items.gold_ingot});

	}

}
