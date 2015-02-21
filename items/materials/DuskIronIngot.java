package mod.kdl808.mythocraft.items.materials;

import mod.kdl808.mythocraft.core.ModRegister;
import mod.kdl808.mythocraft.items.CreativeTab;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.Item;

public class DuskIronIngot extends Item {
	public static Item duskironIngot = new CreativeTab().setUnlocalizedName("duskironIngot")
			.setTextureName("mythocraft:duskironIngot");
	public static void init()
	{
		ModRegister.registerItem(duskironIngot);
	}
	public void registerIcons(IIconRegister par1IconRegister)
    {
        itemIcon = par1IconRegister.registerIcon("mythocraft:duskironIngot");
    }
}