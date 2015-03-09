package kdlalp.mod.mythocraft.api.crafting;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;

/**
 * Wrapper around a standard IRecipe to convert it to an IAltarRecipe
 */
public class ShrineCraftingRecipe implements IShrineRecipe
{
	/** Basic crafting IRecipe to wrap */ 
	private IRecipe pattern;
	/** The results */ 
	private ItemStack[] results;
	/** Tier recieved for completing the craft */
	private int rTier;
	/** Tier required to perform the craft */
	private int tier;
	/** amount of Ichor required to complete the craft */
	private int ichor;

	public ShrineCraftingRecipe(IRecipe crafting, int ichorCost, int tierReward, int tierRequired)
	{
		pattern = crafting;
		ichor = ichorCost;
		rTier = tierReward;
		tier = tierRequired;
	}
	public ShrineCraftingRecipe(IRecipe crafting, ItemStack[] results, int ichorCost, int tierReward, int tierRequired)
	{
		this(crafting,ichorCost, tierReward, tierRequired);
		
	}

	@Override
	public ItemStack[] getResults(InventoryCrafting craftMatrix)
	{
		return results != null ? results : new ItemStack[]{pattern.getRecipeOutput()};
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
	public boolean matches(EntityPlayer player, InventoryCrafting craftMatrix)
	{
		return pattern.matches(craftMatrix, player.worldObj);
	}

}
