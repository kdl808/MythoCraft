package kdlalp.mod.mythocraft.blocks.shrine;

import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;

public class InventoryShrineIn extends InventoryCrafting
{
	/** Shrine instance */
	private TileEntityShrine shrine;
    /** Stack of Ichor. */
    private ItemStack ichorStack;
    /** Index of Ichor Slot. */
    private int ichorIndex;
    /** Class containing the callbacks for the events on_GUIClosed and on_CraftMaxtrixChanged. */
    private ContainerShrine eventHandler;

    public InventoryShrineIn(ContainerShrine c, TileEntityShrine tile, int width, int height)
    {
        super(c, width, height);
        eventHandler = c;
        shrine = tile;
        ichorIndex = width * height;
        ichorStack = null;
        eventHandler.ignoreChanges = true;
        for(int i = 0; i < getSizeInventory(); i++)
        {
        	setInventorySlotContents(i, shrine.getStackInSlot(i));
        }
        eventHandler.ignoreChanges = false;
    }

    @Override
    public int getSizeInventory()
    {
        return super.getSizeInventory();
    }

    @Override
    public ItemStack getStackInSlot(int slot)
    {
        return slot == ichorIndex ? ichorStack : super.getStackInSlot(slot);
    }
    
    /**
     * Returns the amount of ichor
     */
    public int getIchor()
    {
    	return shrine.getTotalIchor();
    }

	public void consumeIchor(int amount)
	{
		shrine.consumeIchor(amount);
	}

    @Override
    public String getInventoryName()
    {
        return "Input";
    }

    @Override
    public ItemStack getStackInSlotOnClosing(int slot)
    {
        if(slot == ichorIndex)
        {
        	if(ichorStack != null)
        	{
	            ItemStack itemstack = ichorStack;
	            ichorStack = null;
            return itemstack;
        	}
        	return null;
        }
        return super.getStackInSlotOnClosing(slot);
    }

    @Override
    public ItemStack decrStackSize(int slot, int amount)
    {
    	if(slot == ichorIndex)
        {
        	if(ichorStack != null)
        	{
	            ItemStack itemstack;
	
	            if(ichorStack.stackSize <= amount)
	            {
	                itemstack = ichorStack;
	                ichorStack = null;
	                eventHandler.onCraftMatrixChanged(this);
	                return itemstack;
	            }
	            else
	            {
	                itemstack = ichorStack.splitStack(amount);
	
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
        return super.decrStackSize(slot, amount);
    }

    @Override
    public void setInventorySlotContents(int slot, ItemStack stack)
    {
        if(slot == ichorIndex)
        {
        	ichorStack = stack;
        	eventHandler.onCraftMatrixChanged(this);
        }
        else
        {
        	super.setInventorySlotContents(slot, stack);
        }
    }

    @Override
    public boolean isItemValidForSlot(int i, ItemStack stack)
    {
        return i == ichorIndex ? TileEntityShrine.isIchor(stack) : super.isItemValidForSlot(i, stack);
    }
}