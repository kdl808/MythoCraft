package kdlalp.mod.mythocraft.core;

import kdlalp.mod.mythocraft.blocks.altar.ContainerAltar;
import kdlalp.mod.mythocraft.blocks.altar.TileEntityAltar;
import kdlalp.mod.mythocraft.client.GuiAltar;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import cpw.mods.fml.common.network.IGuiHandler;

public class CommonProxy implements IGuiHandler
{

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
	{
		return new ContainerAltar(player.inventory, (TileEntityAltar)world.getTileEntity(x, y, z));
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
	{
		return new GuiAltar(player.inventory, (TileEntityAltar)world.getTileEntity(x, y, z));
	}

}
