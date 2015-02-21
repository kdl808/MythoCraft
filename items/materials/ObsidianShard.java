package mod.kdl808.mythocraft.items.materials;

import mod.kdl808.mythocraft.core.ModRegister;
import mod.kdl808.mythocraft.items.CreativeTab;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.Item;

public class ObsidianShard extends Item {
	public static Item obsidianShard = new CreativeTab().setUnlocalizedName("obsidianShard")
			.setTextureName("mythocraft:obsidianShard");
	public static void init()
	{
		ModRegister.registerItem(obsidianShard);
	}
	public void registerIcons(IIconRegister par1IconRegister)
    {
        itemIcon = par1IconRegister.registerIcon("mythocraft:obsidianShard");
    }
}
