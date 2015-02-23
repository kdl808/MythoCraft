package kdlalp.mod.mythocraft.blocks;

import kdlalp.mod.mythocraft.blocks.altar.TileEntityAltar;
import kdlalp.mod.mythocraft.core.MythoCraftMod;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockAltar extends BlockContainer
{

	protected BlockAltar()
	{
		super(Material.rock);
	}

    /**
     * Called upon block activation (right click on the block.)
     */
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int p_149727_6_, float p_149727_7_, float p_149727_8_, float p_149727_9_)
    {
    	TileEntityAltar altar = (TileEntityAltar)world.getTileEntity(x, y, z);
    	if(altar.isUseableByPlayer(player))
    	{
            player.openGui(MythoCraftMod.instance, 0, world, x, y, z);
    	}
        return true;
    }
    /**
     * Called when the block is placed in the world.
     */
    @Override
    public void onBlockPlacedBy(World world, int i, int j, int k, EntityLivingBase entity, ItemStack stack)
    {
    	super.onBlockPlacedBy(world, i, j, k, entity, stack);
        if(stack.hasDisplayName())
        {
            ((TileEntityAltar)world.getTileEntity(i, j, k)).setCustomName(stack.getDisplayName());
        }
    }

	@Override
	public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_)
	{
		return new TileEntityAltar();
	}
}
