package kdlalp.mod.mythocraft.items;

import kdlalp.mod.mythocraft.blocks.MythoCraftBlocks;
import kdlalp.mod.mythocraft.blocks.shrine.TileEntityShrine;
import kdlalp.mod.mythocraft.core.MythoPlayer;
import kdlalp.mod.mythocraft.core.RegistryHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.World;

public class MythoCraftItems
{
	public static Item solidIchor;
	public static Item bucketIchor;
	public static Item obsidianShard;
	public static Item duskIron;
	public static Item dawnGold;
	
	public static void init()
	{
		solidIchor = RegistryHelper.registerItem(new Item(){
			@Override
		    public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float p_77648_8_, float p_77648_9_, float p_77648_10_)
		    {
				boolean flag = false;
				TileEntityShrine altar = null;
				if(world.getBlock(x, y, z) == MythoCraftBlocks.altar)
				{
					altar = (TileEntityShrine)world.getTileEntity(x, y, z);
				}
				if(altar == null && player.isSneaking())
				{
					flag = true;
					MythoPlayer.addTier(player, -1 * MythoPlayer.tier(player));
				}
				if(world.isRemote)
				{
					player.addChatMessage(new ChatComponentText(String.format("%s has a tier of %d", player.getCommandSenderName(), MythoPlayer.tier(player))));
					if(altar != null)
					{
						player.addChatMessage(new ChatComponentText(String.format("Ichor=%d (Fluid=%d; Items=%d)", altar.getTotalIchor(), altar.getFluidIchor(), altar.getItemIchor())));
						flag = true;
					}
				}
		        return flag;
		    }
		}/*.setMaxStackSize(256)*/, "ichor");
		obsidianShard = RegistryHelper.registerItem("obsidianShard");
		duskIron = RegistryHelper.registerItem("duskIron");
		dawnGold = RegistryHelper.registerItem("dawnGold");
	}
}
