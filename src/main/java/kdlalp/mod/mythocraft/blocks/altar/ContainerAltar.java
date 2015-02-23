package kdlalp.mod.mythocraft.blocks.altar;

import kdlalp.mod.mythocraft.core.MythoSettings;
import kdlalp.mod.mythocraft.crafting.AltarRecipes;
import kdlalp.mod.mythocraft.crafting.IAltarRecipe;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;

public class ContainerAltar extends Container
{
	private TileEntityAltar altar;
    /** The crafting matrix inventory (3x3). */
    public InventoryAltarIn craftMatrix;
    public InventoryAltarOut craftResult;
    private EntityPlayer player;

    /** Used to initialise the InventoryAltarIn */
    protected boolean ignoreChanges = false;

    public ContainerAltar(InventoryPlayer inventory, TileEntityAltar tile)
    {
        player = inventory.player;
        altar = tile;
        craftMatrix = altar.getCraftMatrix(this);
        craftResult = new InventoryAltarOut(player, MythoSettings.ALTAR_NUM_RESULTS);
        int i;
        int j;
        for(i = 0; i < 3; i++)
        {
        	for(j = 0; j < 3; j++)
        	{
        		addSlotToContainer(new Slot(craftMatrix, j + i * 3, 30 + j * 18, 17 + i * 18));//Matrix slots 0 -> 8
        	}
        }
        addSlotToContainer(new Slot(craftMatrix, 9, 93, 53){
        	@Override
        	public boolean isItemValid(ItemStack stack)
        	{
        		return TileEntityAltar.isIchor(stack);
        	}
        });//Ichor slot 9
        //for(i = 0; i < altar.numOutPuts)//TODO:Position multiple slots
        addSlotToContainer(new SlotAltarCrafting(inventory.player, craftMatrix, craftResult, 0, 124, 35));//Output slots 10 -> (9+numOutputs)

        for(i = 0; i < 3; i++)
        {
        	for(j = 0; j < 9; j++)
        	{
        		addSlotToContainer(new Slot(inventory, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));//Inventory slots (11 -> 38) + nO
        	}
        }
        for(j = 0; j < 9; j++)
        {
        	addSlotToContainer(new Slot(inventory, j, 8 + j * 18, 142));//Hotbar slots (39 -> 47) + nO
        }
        onCraftMatrixChanged(craftMatrix);
    }

    /**
     * Callback for when automation changes something in the TileEntity
     */
	public void updateContents(int slot, ItemStack newStack)
	{
		craftMatrix.setInventorySlotContents(slot, newStack);
	}

    /**
     * Callback for when the crafting matrix is changed.
     */
    @Override
    public void onCraftMatrixChanged(IInventory inventory)
    {
    	if(!ignoreChanges)
    	{
			if(inventory == craftMatrix)
			{
				altar.updateSlots(this);
			}
			IAltarRecipe recipe = AltarRecipes.getInstance().findMatchingRecipe(craftMatrix, player);
		    craftResult.setRecipe(recipe, craftMatrix, CraftingManager.getInstance().findMatchingRecipe(craftMatrix, altar.getWorldObj()));
    	}
    }

    @Override
    public boolean canInteractWith(EntityPlayer player)
    {
        return altar.isUseableByPlayer(player);
    }

    /**
     * Called when a player shift-clicks on a slot. You must override this or you will crash when someone does that.
     */
    @Override
    public ItemStack transferStackInSlot(EntityPlayer player, int slotIndex)
    {
        ItemStack itemstack = null;
        Slot slot = (Slot)inventorySlots.get(slotIndex);

        if (slot != null && slot.getHasStack())
        {
        	ItemStack itemstack1 = slot.getStack();
            itemstack = itemstack1.copy();
            int numOut = craftResult.getSizeInventory() - 1;
            if(slotIndex >= 10 && slotIndex < 10 + numOut)//Result
            {
                if(!mergeItemStack(itemstack1, 11 + numOut, 47 + numOut, true))//Move to Inventory
                {
                    return null;
                }
                slot.onSlotChange(itemstack1, itemstack);
            }
            else if(slotIndex >= 10)//Not Input or Ichor
            {
            	for(int i = 0; i < altar.getSizeInventory(); i++)
            	{
            		if(altar.isItemValidForSlot(i, itemstack1) && !mergeItemStack(itemstack1, i, i, false))
	            	{
	            		return null;
	            	}
            	}
                if (slotIndex >= 11 + numOut && slotIndex < 38 + numOut)//Inventory
                {
                    if(!this.mergeItemStack(itemstack1, 38 + numOut, 47 + numOut, false))
                    {
                        return null;
                    }
                }
                else if (slotIndex >= 38 + numOut && slotIndex < 47 + numOut && !this.mergeItemStack(itemstack1, 11 + numOut, 30 + numOut, false))//Hotbar
                {
                    return null;
                }
            }
            else if(!this.mergeItemStack(itemstack1, 11 + numOut, 47 + numOut, false))//Inventory and Hotbar
            {
                return null;
            }

            if(itemstack1.stackSize == 0)
            {
                slot.putStack((ItemStack)null);
            }
            else
            {
                slot.onSlotChanged();
            }

            if(itemstack1.stackSize == itemstack.stackSize)
            {
                return null;
            }
            slot.onPickupFromSlot(player, itemstack1);
        }

        return itemstack;
    }

    @Override
    public boolean func_94530_a(ItemStack stack, Slot slot)
    {
        return slot.inventory != craftResult && super.func_94530_a(stack, slot);
    }
}