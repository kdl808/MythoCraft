package kdlalp.mod.mythocraft.crafting;

import kdlalp.mod.mythocraft.blocks.altar.InventoryAltarIn;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;

public class AltarCraftingRecipe implements IAltarRecipe
{
	/** Basic crafting IRecipe to wrap */ 
	private IRecipe pattern;
	/** Tier recieved for completing the craft */
	private int rTier;
	/** Tier required to perform the craft */
	private int tier;
	/** amount of Ichor required to complete the craft */
	private int ichor;
	
	public AltarCraftingRecipe(IRecipe crafting, int ichorCost, int tierReward, int tierRequired)
	{
		pattern = crafting;
		ichor = ichorCost;
		rTier = tierReward;
		tier = tierRequired;
	}

	@Override
	public ItemStack[] getResults(InventoryAltarIn craftMatrix)
	{
		return new ItemStack[]{pattern.getRecipeOutput()};
	}

	@Override
	public int getTierIncrease()
	{
		return rTier;
	}

	@Override
	public int tierRequired()
	{
		return tier;
	}

	@Override
	public int ichorRequired()
	{
		return ichor;
	}

	@Override
	public boolean matches(EntityPlayer player, InventoryAltarIn craftMatrix)
	{
		return pattern.matches(craftMatrix, player.worldObj);
	}

}
