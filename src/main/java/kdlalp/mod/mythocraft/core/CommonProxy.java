package kdlalp.mod.mythocraft.core;

import kdlalp.mod.mythocraft.blocks.altar.ContainerAltar;
import kdlalp.mod.mythocraft.blocks.altar.GuiAltar;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import cpw.mods.fml.common.network.IGuiHandler;

public class CommonProxy implements IGuiHandler
{

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
	{
		// TODO Auto-generated method stub
		return new ContainerAltar(player.inventory, world, x, y, z);
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
	{
		// TODO Auto-generated method stub
		return new GuiAltar(player.inventory, world, x, y, z);
	}

}
