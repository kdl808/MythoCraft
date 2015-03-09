package kdlalp.mod.mythocraft.core;

import kdlalp.mod.mythocraft.blocks.shrine.ContainerShrine;
import kdlalp.mod.mythocraft.blocks.shrine.TileEntityShrine;
import kdlalp.mod.mythocraft.client.GuiShrine;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import cpw.mods.fml.common.network.IGuiHandler;

public class CommonProxy implements IGuiHandler
{

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
	{
		return new ContainerShrine(player.inventory, (TileEntityShrine)world.getTileEntity(x, y, z));
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
	{
		return new GuiShrine(player.inventory, (TileEntityShrine)world.getTileEntity(x, y, z));
	}

}
