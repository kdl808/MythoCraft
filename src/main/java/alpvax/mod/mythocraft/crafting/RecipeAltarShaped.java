package alpvax.mod.mythocraft.crafting;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public class RecipeAltarShaped implements IAltarRecipe
{

	@Override
	public ItemStack getResult(InventoryAltarIn craftMatrix)
	{
		return null;
	}

	@Override
	public int getTierIncrease()
	{
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean matches(EntityPlayer player, InventoryAltarIn craftMatrix)
	{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int tierRequired()
	{
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int ichorRequired()
	{
		// TODO Auto-generated method stub
		return 0;
	}

}
