package alpvax.mod.mythocraft.items;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.World;
import alpvax.mod.mythocraft.core.MythoPlayer;
import alpvax.mod.mythocraft.core.MythoReference;
import cpw.mods.fml.common.registry.GameRegistry;

public class MythoCraftItems
{
	public static Item ichor;
	
	public static void init()
	{
		ichor = new Item(){
			/**
		     * Callback for item usage. If the item does something special on right clicking, he will have one of those. Return
		     * True if something happen and false if it don't. This is for ITEMS, not BLOCKS
		     */
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
		}.setMaxStackSize(Integer.MAX_VALUE).setUnlocalizedName("ichor").setTextureName(MythoReference.MODID + ":ichor").setCreativeTab(CreativeTabs.tabDecorations);
		GameRegistry.registerItem(ichor, "ichor");
	}
}
