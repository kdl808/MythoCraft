package kdlalp.mod.mythocraft.blocks;

import kdlalp.mod.mythocraft.blocks.shrine.TileEntityShrine;
import kdlalp.mod.mythocraft.core.RegistryHelper;
import net.minecraft.block.Block;
import cpw.mods.fml.common.registry.GameRegistry;


public class MythoCraftBlocks
{
	public static Block altar;
	public static Block liquidIchor;
	
	public static void init()
	{
		altar = RegistryHelper.registerBlock(new BlockShrine(), "shrine");
		GameRegistry.registerTileEntity(TileEntityShrine.class, "Shrine");
	}
}
