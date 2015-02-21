package kdlalp.mod.mythocraft.core;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import cpw.mods.fml.common.registry.GameRegistry;

public class RegistryHelper
{
	public static Block registerBlock(Block block, String name)
    {
        return GameRegistry.registerBlock(block.setBlockName(name).setBlockTextureName(ModInfo.MODID + ":" + name).setCreativeTab(MythoCraftMod.tabMythoCraftBlocks), name);
    }

	public static Item registerItem(String name)
	{
		return registerItem(null, name);
	}
    public static Item registerItem(Item item, String name)
    {
    	if(item == null)
    	{
    		item = new Item();
    	}
        item = item.setUnlocalizedName(name).setTextureName(ModInfo.MODID + ":" + name).setCreativeTab(MythoCraftMod.tabMythoCraftItems);
        GameRegistry.registerItem(item, name);
        return item;
    }
}
