package mod.kdl808.mythocraft.core;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import cpw.mods.fml.common.registry.GameRegistry;

public class ModRegister {
	public static void registerBlock(Block block)
    {
        GameRegistry.registerBlock(block, ModInfo.MODID + block.getUnlocalizedName());
    }

    public static void registerItem(Item item)
    {
        GameRegistry.registerItem(item, ModInfo.MODID + item.getUnlocalizedName());
    }
}
