package kdlalp.mod.mythocraft.blocks.altar;

import kdlalp.mod.mythocraft.api.crafting.IAltarRecipe;
import kdlalp.mod.mythocraft.core.MythoPlayer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;

public class InventoryAltarOut implements IInventory
{
	/** List of the stacks in the crafting matrix. */
    private ItemStack[] stackList;
    /** Player using this Inventory */
    private EntityPlayer thePlayer;
    /** Current IAltarRecipe */
    private IAltarRecipe recipe;
    
    public InventoryAltarOut(EntityPlayer player, int numSlots)
    {
    	stackList = new ItemStack[numSlots];
    	thePlayer = player;
    	recipe = null;
    }

    /**
     * Returns the number of slots in the inventory.
     */
    @Override
    public int getSizeInventory()
    {
        return stackList.length;
    }

    /**
     * Returns the stack in slot i
     */
    @Override
    public ItemStack getStackInSlot(int i)
    {
        return i < stackList.length ? stackList[i] : null;
    }

    /**
     * Returns the name of the inventory
     */
    @Override
    public String getInventoryName()
    {
        return "Result";
    }

    /**
     * Returns if the inventory is named
     */
    @Override
    public boolean hasCustomInventoryName()
    {
        return false;
    }

    /**
     * Removes from an inventory slot (first arg) up to a specified number (second arg) of items and returns them in a
     * new stack.
     */
    @Override
    public ItemStack decrStackSize(int slot, int amount)
    {
        if(stackList[slot] != null)
        {
            ItemStack stack = stackList[slot];
            if(stack.stackSize > amount)
            {
            	stack.stackSize -= amount;
            	ItemStack s1 = stack.copy();
            	s1.stackSize = amount;
            	return s1;
            }
            stackList[slot] = null;
            return stack;
        }
        return null;
    }

    /**
     * When some containers are closed they call this on each slot, then drop whatever it returns as an EntityItem -
     * like when you close a workbench GUI.
     */
    @Override
    public ItemStack getStackInSlotOnClosing(int slot)
    {
        if(stackList[slot] != null)
        {
            ItemStack itemstack = stackList[slot];
            stackList[slot] = null;
            return itemstack;
        }
        return null;
    }

    /**
     * Sets the given item stack to the specified slot in the inventory (can be crafting or armor sections).
     */
    @Override
    public void setInventorySlotContents(int p_70299_1_, ItemStack p_70299_2_)
    {
    	stackList[p_70299_1_] = p_70299_2_;
    }

    /**
     * Returns the maximum stack size for a inventory slot.
     */
    @Override
    public int getInventoryStackLimit()
    {
        return 64;
    }

    /**
     * For tile entities, ensures the chunk containing the tile entity is saved to disk later - the game won't think it
     * hasn't changed and skip it.
     */
    @Override
    public void markDirty() {}

    /**
     * Do not make give this method the name canInteractWith because it clashes with Container
     */
    @Override
    public boolean isUseableByPlayer(EntityPlayer p_70300_1_)
    {
        return true;
    }

    @Override
    public void openInventory() {}

    @Override
    public void closeInventory() {}

    /**
     * Returns true if automation is allowed to insert the given stack (ignoring stack size) into the given slot.
     */
    @Override
    public boolean isItemValidForSlot(int p_94041_1_, ItemStack p_94041_2_)
    {
        return true;
    }
    
    public void setRecipe(IAltarRecipe r, InventoryAltarIn craftMatrix, ItemStack... fallback)
	{
		recipe = r;
		if(recipe != null && MythoPlayer.tier(thePlayer) >= recipe.tierRequired() && craftMatrix.getIchor() >= recipe.ichorRequired())
		{
			setResults(((IAltarRecipe)r).getResults(craftMatrix));
		}
		else if(fallback != null && fallback.length > 0)
		{
			setResults(fallback);
		}
		else
		{
			setResults(new ItemStack[0]);
		}
	}

	public IAltarRecipe getAltarRecipe()
	{
		return recipe instanceof IAltarRecipe ? (IAltarRecipe)recipe : null;
	}
	
	private void setResults(ItemStack[] stacks)
	{
		for(int i = 0; i < stackList.length; i++)
		{
			if(i >= stacks.length || stacks[i] == null)
			{
				stackList[i] = null;
			}
			else
			{
				stackList[i] = stacks[i].copy();
			}
		}
 	}
}
