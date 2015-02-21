package alpvax.mod.mythocraft.core;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.common.IExtendedEntityProperties;

public class MythoPlayer
{
	public static final String PROP_NAME = "MythoCraftPlayer";
	public static final int DW_CHANNEL = 20;
	
	public static void register(Entity player)
	{
		player.registerExtendedProperties(PROP_NAME, new MythoPlayerData());
	}
	private static MythoPlayerData get(EntityPlayer player)
	{
		return (MythoPlayerData)player.getExtendedProperties(PROP_NAME);
	}

	public static int tier(EntityPlayer player)
	{
		return get(player).getTier();
	}
	public static void addTier(EntityPlayer player, int amount)
	{
		MythoPlayerData mpd = get(player);
		mpd.setTier(mpd.getTier() + amount);
	}
	
	private static class MythoPlayerData implements IExtendedEntityProperties
	{
		private static final String KEY_TIER = "Tier";
		
		private EntityPlayer player;
		
		//private int tier;

		private int getTier()
		{
			return player.getDataWatcher().getWatchableObjectInt(DW_CHANNEL);
		}
		private void setTier(int tier)
		{
			if(tier < 0)
			{
				tier = 0;
			}
			player.getDataWatcher().updateObject(DW_CHANNEL, tier);
		}
	
		@Override
		public void saveNBTData(NBTTagCompound compound)
		{
			NBTTagCompound nbt = new NBTTagCompound();
			nbt.setInteger(KEY_TIER, getTier());
			compound.setTag(PROP_NAME, nbt);
		}
	
		@Override
		public void loadNBTData(NBTTagCompound compound)
		{
			NBTTagCompound nbt = compound.getCompoundTag(PROP_NAME);
			setTier(nbt.getInteger(KEY_TIER));
		}
	
		@Override
		public void init(Entity entity, World world)
		{
			player = (EntityPlayer)entity;
			player.getDataWatcher().addObject(DW_CHANNEL, 0);
		}
	}
}