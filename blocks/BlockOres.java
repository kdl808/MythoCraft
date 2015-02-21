package mod.kdl808.mythocraft.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class BlockOres extends Block {
	
	@SideOnly(Side.CLIENT)
	private IIcon[] texture;
	
	final static String[] subBlocks =  new String[] ("")
	
	protected BlockOres() {
		super(Material.rock);
	}

}
