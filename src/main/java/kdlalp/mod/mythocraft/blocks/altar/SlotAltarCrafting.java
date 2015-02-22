package kdlalp.mod.mythocraft.blocks.altar;

import kdlalp.mod.mythocraft.core.MythoPlayer;
import kdlalp.mod.mythocraft.crafting.IAltarRecipe;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.SlotCrafting;
import net.minecraft.item.ItemStack;

public class SlotAltarCrafting extends SlotCrafting
{
	private InventoryAltarIn matrix;

	public SlotAltarCrafting(EntityPlayer p_i1823_1_, IInventory p_i1823_2_, IInventory p_i1823_3_, int p_i1823_4_, int p_i1823_5_, int p_i1823_6_)
	{
		super(p_i1823_1_, p_i1823_2_, p_i1823_3_, p_i1823_4_, p_i1823_5_, p_i1823_6_);
		matrix = (InventoryAltarIn)p_i1823_2_;
	}
	
	@Override
	public void onPickupFromSlot(EntityPlayer player, ItemStack stack)
    {
        IAltarRecipe r = ((InventoryAltarOut)inventory).getRecipe();
        if(r != null)
        {
	        MythoPlayer.addTier(player, r.getTierIncrease());
	        matrix.consumeIchor(r.ichorRequired());
        }
        super.onPickupFromSlot(player, stack);
    }
}
