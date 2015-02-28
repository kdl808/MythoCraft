package kdlalp.mod.mythocraft.blocks.altar;

import kdlalp.mod.mythocraft.api.crafting.IAltarRecipe;
import kdlalp.mod.mythocraft.core.MythoPlayer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.SlotCrafting;
import net.minecraft.item.ItemStack;

public class SlotAltarCrafting extends SlotCrafting
{
	private InventoryAltarIn matrix;

	public SlotAltarCrafting(EntityPlayer player, InventoryAltarIn input, InventoryAltarOut inv, int index, int x, int y)
	{
		super(player, input, inv, index, x, y);
		matrix = input;
	}
	
	@Override
	public void onPickupFromSlot(EntityPlayer player, ItemStack stack)
    {
		IAltarRecipe recipe = ((InventoryAltarOut)inventory).getAltarRecipe();
        if(recipe != null)
        {
	        MythoPlayer.addTier(player, recipe.getTierIncrease());
	        matrix.consumeIchor(recipe.ichorRequired());
        }
        super.onPickupFromSlot(player, stack);
    }
}
