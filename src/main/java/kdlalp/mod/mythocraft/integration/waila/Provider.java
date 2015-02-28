package kdlalp.mod.mythocraft.integration.waila;

import java.util.List;

import kdlalp.mod.mythocraft.blocks.altar.TileEntityAltar;
import mcp.mobius.waila.api.IWailaConfigHandler;
import mcp.mobius.waila.api.IWailaDataAccessor;
import mcp.mobius.waila.api.IWailaDataProvider;
import mcp.mobius.waila.api.IWailaRegistrar;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;

public class Provider implements IWailaDataProvider
{

	@Override
	public ItemStack getWailaStack(IWailaDataAccessor accessor, IWailaConfigHandler config)
	{
		return null;
	}

	@Override
	public List<String> getWailaHead(ItemStack itemStack, List<String> currenttip, IWailaDataAccessor accessor, IWailaConfigHandler config)
	{
		return currenttip;
	}

	@Override
	public List<String> getWailaBody(ItemStack itemStack, List<String> currenttip, IWailaDataAccessor accessor, IWailaConfigHandler config)
	{
		List<String> list = currenttip;
		TileEntity tile = accessor.getTileEntity();
		if (tile instanceof TileEntityAltar)
		{
			TileEntityAltar altar = (TileEntityAltar)tile;
			list.add(String.format(StatCollector.translateToLocal("tooltip.ichorLevel"), altar.getTotalIchor(), altar.getFluidIchor(), altar.getItemIchor()));
		}
		return list;
	}

	@Override
	public List<String> getWailaTail(ItemStack itemStack, List<String> currenttip, IWailaDataAccessor accessor, IWailaConfigHandler config)
	{
		return currenttip;
	}

	@Override
	public NBTTagCompound getNBTData(EntityPlayerMP player, TileEntity te, NBTTagCompound tag, World world, int x, int y, int z)
	{
		return null;
	}
	
	public static void callbackRegister(IWailaRegistrar registrar)
	{
		IWailaDataProvider provider = new Provider();
		registrar.registerBodyProvider(provider, TileEntityAltar.class);
	}
}