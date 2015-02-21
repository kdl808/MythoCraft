package kdlalp.mod.mythocraft.items;

import kdlalp.mod.mythocraft.core.MythoPlayer;
import kdlalp.mod.mythocraft.core.RegistryHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.World;

public class MythoCraftItems
{
	public static Item ichor;
	public static Item obsidianShard;
	public static Item duskIron;
	public static Item dawnGold;
	
	public static void init()
	{
		ichor = RegistryHelper.registerItem(new Item(){
			@Override
		    public boolean onItemUse(ItemStack p_77648_1_, EntityPlayer p_77648_2_, World p_77648_3_, int p_77648_4_, int p_77648_5_, int p_77648_6_, int p_77648_7_, float p_77648_8_, float p_77648_9_, float p_77648_10_)
		    {
				boolean flag = false;
				if(p_77648_2_.isSneaking())
				{
					flag = true;
					MythoPlayer.addTier(p_77648_2_, -1 * MythoPlayer.tier(p_77648_2_));
				}
				if(p_77648_3_.isRemote)
				{
					p_77648_2_.addChatMessage(new ChatComponentText(String.format("%s has a tier of %d", p_77648_2_.getCommandSenderName(), MythoPlayer.tier(p_77648_2_))));
					p_77648_2_.addChatMessage(new ChatComponentText("Item in slot 9 = " + p_77648_2_.inventory.getStackInSlot(9)));
				}
		        return flag;
		    }
		}.setMaxStackSize(512), "ichor");
		obsidianShard = RegistryHelper.registerItem("obsidianShard");
		duskIron = RegistryHelper.registerItem("duskIron");
		dawnGold = RegistryHelper.registerItem("dawnGold");
	}
}
