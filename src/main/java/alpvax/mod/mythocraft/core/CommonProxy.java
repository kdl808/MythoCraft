package alpvax.mod.mythocraft.core;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import alpvax.mod.mythocraft.crafting.ContainerAltar;
import alpvax.mod.mythocraft.crafting.GuiAltar;
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
