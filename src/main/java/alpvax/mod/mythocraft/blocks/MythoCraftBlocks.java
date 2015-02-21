package alpvax.mod.mythocraft.blocks;

import alpvax.mod.mythocraft.core.MythoReference;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import cpw.mods.fml.common.registry.GameRegistry;


public class MythoCraftBlocks
{
	public static Block altar;
	
	public static void init()
	{
		altar = GameRegistry.registerBlock(new BlockAltar().setBlockName("altar").setBlockTextureName(MythoReference.MODID + ":altar").setCreativeTab(CreativeTabs.tabDecorations), "altar");
	}
}
