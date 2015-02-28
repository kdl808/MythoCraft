package kdlalp.mod.mythocraft.api.crafting;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;

public interface IAltarRecipe
{
	public ItemStack[] getResults(InventoryCrafting inv);

	public int getTierIncrease();
	
	public int tierRequired();
	
	public int ichorRequired();
	
	public boolean matches(EntityPlayer player, InventoryCrafting craftMatrix);
}
