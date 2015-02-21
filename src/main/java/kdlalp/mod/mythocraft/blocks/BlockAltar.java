package kdlalp.mod.mythocraft.blocks;

import kdlalp.mod.mythocraft.core.MythoCraftMod;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public class BlockAltar extends Block
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
        player.openGui(MythoCraftMod.instance, 0, world, x, y, z);
        if(world.isRemote)
        {
            return true;
        }
        else
        {
            //player.openGui(MythoCraftMod.instance, 0, world, x, y, z);
            return true;
        }
    }
}
