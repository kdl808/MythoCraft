package alpvax.mod.mythocraft.crafting;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public interface IAltarRecipe
{
	public ItemStack getResult(InventoryAltarIn inv);

	public int getTierIncrease();
	
	public int tierRequired();
	
	public int ichorRequired();
	
	public boolean matches(EntityPlayer player, InventoryAltarIn craftMatrix);
}
