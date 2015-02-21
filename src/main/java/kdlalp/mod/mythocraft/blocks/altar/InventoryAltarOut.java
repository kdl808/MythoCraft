package kdlalp.mod.mythocraft.blocks.altar;

import kdlalp.mod.mythocraft.crafting.IAltarRecipe;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;

public class InventoryAltarOut implements IInventory
{
	/** List of the stacks in the crafting matrix. */
    private ItemStack[] stackList;
    /** Current IAltarRecipe */
    private IAltarRecipe recipe;
    
    public InventoryAltarOut(int numSlots)
    {
    	stackList = new ItemStack[numSlots];
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
    public ItemStack decrStackSize(int p_70298_1_, int p_70298_2_)
    {
        if(stackList[p_70298_1_] != null)
        {
            ItemStack itemstack = stackList[p_70298_1_];
            stackList[p_70298_1_] = null;
            return itemstack;
        }
        return null;
    }

    /**
     * When some containers are closed they call this on each slot, then drop whatever it returns as an EntityItem -
     * like when you close a workbench GUI.
     */
    @Override
    public ItemStack getStackInSlotOnClosing(int p_70304_1_)
    {
        if(stackList[p_70304_1_] != null)
        {
            ItemStack itemstack = stackList[p_70304_1_];
            stackList[p_70304_1_] = null;
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
    	System.out.println(p_70299_2_);//XXX
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

	public void setRecipe(IAltarRecipe r)
	{
		recipe = r;
	}

	public IAltarRecipe getRecipe()
	{
		return recipe;
	}
}
