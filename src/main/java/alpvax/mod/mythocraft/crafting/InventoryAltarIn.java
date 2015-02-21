package alpvax.mod.mythocraft.crafting;

import net.minecraft.inventory.Container;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import alpvax.mod.mythocraft.items.MythoCraftItems;

public class InventoryAltarIn extends InventoryCrafting
{
    /*/** Name given through use of an anvil
    private String name;*/
    /** Stack of Ichor. */
    private ItemStack ichorStack;
    /** Index of Ichor Slot. */
    private int isIndex;
    /** Class containing the callbacks for the events on_GUIClosed and on_CraftMaxtrixChanged. */
    private Container eventHandler;

    public InventoryAltarIn(Container p_i1807_1_, int p_i1807_2_, int p_i1807_3_)
    {
        super(p_i1807_1_, p_i1807_2_, p_i1807_3_);
        eventHandler = p_i1807_1_;
        isIndex = p_i1807_2_ * p_i1807_3_;
        ichorStack = null;
    }

    /**
     * Returns the number of slots in the inventory.
     */
    public int getSizeInventory()
    {
        return super.getSizeInventory();
    }

    /**
     * Returns the stack in slot i
     */
    public ItemStack getStackInSlot(int p_70301_1_)
    {
        return p_70301_1_ == isIndex ? ichorStack : super.getStackInSlot(p_70301_1_);
    }
    
    /**
     * Returns the amount of ichor
     */
    public int getIchor()
    {
    	return ichorStack != null ? ichorStack.stackSize : 0;
    }

	public void consumeIchor(int i)
	{
		if(ichorStack != null)
		{
			decrStackSize(isIndex, i);
			eventHandler.onCraftMatrixChanged(this);
		}
	}

    /**
     * Returns the name of the inventory
     */
    @Override
    public String getInventoryName()
    {
        return "container.mythoAltar";
    }

    /**
     * Returns if the inventory is named
     */
    @Override
    public boolean hasCustomInventoryName()
    {
        return false;//TODO: Implement custom named Altars
    }

    /**
     * When some containers are closed they call this on each slot, then drop whatever it returns as an EntityItem -
     * like when you close a workbench GUI.
     */
    public ItemStack getStackInSlotOnClosing(int p_70304_1_)
    {
        if(p_70304_1_ == isIndex)
        {
        	if(ichorStack != null)
        	{
	            ItemStack itemstack = ichorStack;
	            ichorStack = null;
            return itemstack;
        	}
        	return null;
        }
        return super.getStackInSlotOnClosing(p_70304_1_);
    }

    /**
     * Removes from an inventory slot (first arg) up to a specified number (second arg) of items and returns them in a
     * new stack.
     */
    public ItemStack decrStackSize(int p_70298_1_, int p_70298_2_)
    {
    	if(p_70298_1_ == isIndex)
        {
        	if(ichorStack != null)
        	{
	            ItemStack itemstack;
	
	            if(ichorStack.stackSize <= p_70298_2_)
	            {
	                itemstack = ichorStack;
	                ichorStack = null;
	                eventHandler.onCraftMatrixChanged(this);
	                return itemstack;
	            }
	            else
	            {
	                itemstack = ichorStack.splitStack(p_70298_2_);
	
	                if(ichorStack.stackSize == 0)
	                {
	                	ichorStack = null;
	                }
	
	                eventHandler.onCraftMatrixChanged(this);
	                return itemstack;
	            }
        	}
        	return null;
        }
        return super.decrStackSize(p_70298_1_, p_70298_2_);
    }

    /**
     * Sets the given item stack to the specified slot in the inventory (can be crafting or armor sections).
     */
    public void setInventorySlotContents(int p_70299_1_, ItemStack p_70299_2_)
    {
        if(p_70299_1_ == isIndex)
        {
        	ichorStack = p_70299_2_;
        	eventHandler.onCraftMatrixChanged(this);
        }
        else
        {
        	super.setInventorySlotContents(p_70299_1_, p_70299_2_);
        }
    }

    /**
     * Returns true if automation is allowed to insert the given stack (ignoring stack size) into the given slot.
     */
    public boolean isItemValidForSlot(int i, ItemStack stack)
    {
        return i == isIndex ? stack.isItemEqual(new ItemStack(MythoCraftItems.ichor)) : super.isItemValidForSlot(i, stack);
    }
}