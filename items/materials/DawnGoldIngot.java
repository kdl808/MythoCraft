package mod.kdl808.mythocraft.items.materials;

import mod.kdl808.mythocraft.core.ModRegister;
import mod.kdl808.mythocraft.items.CreativeTab;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.Item;

public class DawnGoldIngot extends Item {
	public static Item dawngoldIngot = new CreativeTab().setUnlocalizedName("dawngoldIngot")
			.setTextureName("mythocraft:dawngoldIngot");
	public static void init()
	{
		ModRegister.registerItem(dawngoldIngot);
	}
	public void registerIcons(IIconRegister par1IconRegister)
    {
        itemIcon = par1IconRegister.registerIcon("mythocraft:dawngoldIngot");
    }
}