package kdlalp.mod.mythocraft.blocks;

import kdlalp.mod.mythocraft.core.RegistryHelper;
import net.minecraft.block.Block;


public class MythoCraftBlocks
{
	public static Block altar;
	
	public static void init()
	{
		altar = RegistryHelper.registerBlock(new BlockAltar(), "altar");
	}
}
