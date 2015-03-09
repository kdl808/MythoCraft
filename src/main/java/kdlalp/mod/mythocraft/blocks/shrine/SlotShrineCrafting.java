package kdlalp.mod.mythocraft.blocks.shrine;

import kdlalp.mod.mythocraft.api.crafting.IShrineRecipe;
import kdlalp.mod.mythocraft.core.MythoPlayer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.SlotCrafting;
import net.minecraft.item.ItemStack;

public class SlotShrineCrafting extends SlotCrafting
{
	private InventoryShrineIn matrix;

	public SlotShrineCrafting(EntityPlayer player, InventoryShrineIn input, InventoryShrineOut inv, int index, int x, int y)
	{
		super(player, input, inv, index, x, y);
		matrix = input;
	}
	
	@Override
	public void onPickupFromSlot(EntityPlayer player, ItemStack stack)
    {
		IShrineRecipe recipe = ((InventoryShrineOut)inventory).getShrineRecipe();
        if(recipe != null)
        {
	        MythoPlayer.addTier(player, recipe.getTierIncrease());
	        matrix.consumeIchor(recipe.ichorRequired());
        }
        super.onPickupFromSlot(player, stack);
    }
}
