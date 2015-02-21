package alpvax.mod.mythocraft.crafting;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.SlotCrafting;
import net.minecraft.item.ItemStack;
import alpvax.mod.mythocraft.core.MythoPlayer;

public class SlotAltarCrafting extends SlotCrafting
{
	private InventoryAltarIn matrix;

	public SlotAltarCrafting(EntityPlayer p_i1823_1_, IInventory p_i1823_2_, IInventory p_i1823_3_, int p_i1823_4_, int p_i1823_5_, int p_i1823_6_)
	{
		super(p_i1823_1_, p_i1823_2_, p_i1823_3_, p_i1823_4_, p_i1823_5_, p_i1823_6_);
		matrix = (InventoryAltarIn)p_i1823_2_;
	}
	
	@Override
	public void onPickupFromSlot(EntityPlayer p_82870_1_, ItemStack p_82870_2_)
    {
		System.out.println(p_82870_2_);//XXX
        IAltarRecipe r = ((InventoryAltarOut)inventory).getRecipe();
        if(r != null)
        {
        	System.out.printf("Tier + (%d): %d -> %d%n", r.getTierIncrease(), MythoPlayer.tier(p_82870_1_), MythoPlayer.tier(p_82870_1_) + r.getTierIncrease());//XXX
        	System.out.printf("Ichor - %d: %d -> %d%n", r.ichorRequired(), matrix.getIchor(), matrix.getIchor() - r.ichorRequired());//XXX
	        MythoPlayer.addTier(p_82870_1_, r.getTierIncrease());
	        matrix.consumeIchor(r.ichorRequired());
        }
        super.onPickupFromSlot(p_82870_1_, p_82870_2_);
    }
}
