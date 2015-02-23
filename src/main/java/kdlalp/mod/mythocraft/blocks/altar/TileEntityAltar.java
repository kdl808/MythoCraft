package kdlalp.mod.mythocraft.blocks.altar;

import static kdlalp.mod.mythocraft.core.MythoSettings.ICHOR_CONVERT_RATIO;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import kdlalp.mod.mythocraft.core.MythoSettings;
import kdlalp.mod.mythocraft.fluids.MythoCraftFluids;
import kdlalp.mod.mythocraft.items.MythoCraftItems;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.Constants;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTank;
import net.minecraftforge.fluids.FluidTankInfo;
import net.minecraftforge.fluids.IFluidHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class TileEntityAltar extends TileEntity implements ISidedInventory, IFluidHandler
{    
    /** List of the stacks in the input. */
    private ItemStack[] inputStacks = new ItemStack[MythoSettings.ALTAR_INPUT_WIDTH * MythoSettings.ALTAR_INPUT_HEIGHT];//TODO:Variable input size?
    /** Stack in the ichor item slot */
    private ItemStack ichorStack = null;
    /** Amount of ichor in fluid form */
    private FluidTank ichor = new FluidTank(MythoSettings.ALTAR_TANK_SIZE);
    /** number of ticks until next ichor item is consumed */
    //TODO:Implement gradual liquification: private int nextIchor;
    /** Name given through use of an anvil */
    private String name;
    /** List of Containers listening to this */
    private List<ContainerAltar> listeners = new ArrayList<ContainerAltar>();

    //**********Super Methods**********
	@Override
	public void updateEntity()
    {
		super.updateEntity();
		int i = getIchorAmount(ichorStack);
		if(ichor.getCapacity() - ichor.getFluidAmount() >= i)
		{
			fill(null, new FluidStack(MythoCraftFluids.liquidIchor, i), true);
			decrStackSize(getSizeInventory() - 1, i);
		}
		/*TODO:Implement gradual liquification
		if(nextIchor > 0)
		{
			int filled = ichor.fill(new FluidStack(MythoCraftFluids.liquidIchor, 1), true);
			if(filled > 0)
			{
				nextIchor--;
			}
		}
		int i;
		if(nextIchor <= 0 && (i = getIchorAmount(ichorStack)) > 0 && ichor.fill(new FluidStack(MythoCraftFluids.liquidIchor, i), false) == i)
		{
			decrStackSize(getSizeInventory() - 1, 1);
			nextIchor = i;
		}*/
    }
	
	@Override
    public void readFromNBT(NBTTagCompound tag)
    {
        super.readFromNBT(tag);
        NBTTagList items = tag.getTagList("Items", Constants.NBT.TAG_COMPOUND);
        for(int i = 0; i < items.tagCount(); i++)
        {
            NBTTagCompound nbt = items.getCompoundTagAt(i);
            if(nbt.hasKey("Slot", Constants.NBT.TAG_BYTE))
            {
            	setInventorySlotContents(nbt.getByte("Slot"), ItemStack.loadItemStackFromNBT(nbt));
            }
        }
        if(tag.hasKey("Tank", Constants.NBT.TAG_COMPOUND))
        {
        	ichor.readFromNBT(tag.getCompoundTag("Tank"));
        }
        //TODO:Implement gradual liquification: nextIchor = tag.getInteger("NextIchor");
        if(tag.hasKey("CustomName", Constants.NBT.TAG_STRING))
        {
            name = tag.getString("CustomName");
        }
    }

	@Override
    public void writeToNBT(NBTTagCompound tag)
    {
        super.writeToNBT(tag);
        NBTTagList items = new NBTTagList();
        for(int i = 0; i < getSizeInventory(); i++)
        {
        	ItemStack stack = getStackInSlot(i);
        	if(stack != null)
        	{
        		NBTTagCompound nbt = new NBTTagCompound();
        		nbt.setByte("Slot", (byte)i);
        		stack.writeToNBT(nbt);
        		items.appendTag(nbt);
        	}
        }
        tag.setTag("Items", items);
        NBTTagCompound nbt = new NBTTagCompound();
        ichor.writeToNBT(nbt);
        tag.setTag("Tank", nbt);
        //TODO:Implement gradual liquification: tag.setInteger("NextIchor", nextIchor);
        if(hasCustomInventoryName())
        {
        	tag.setString("CustomName", name);
        }
    }

    //**********IFluidHandler**********
	@Override
	public int fill(ForgeDirection from, FluidStack resource, boolean doFill)
	{
		if(resource != null && resource.isFluidEqual(new FluidStack(MythoCraftFluids.liquidIchor, 1)))
		{
			int i = ichor.fill(resource, doFill);
			if(doFill)
	        {
	        	ichorChanged();
	        }
			return i;
		}
		return 0;
	}

	@Override
	public FluidStack drain(ForgeDirection from, FluidStack resource, boolean doDrain)
	{
		if(resource != null && resource.isFluidEqual(ichor.getFluid()))
        {
			return drain(from, resource.amount, doDrain);
        }
		return null;
	}

    @Override
    public FluidStack drain(ForgeDirection from, int maxDrain, boolean doDrain)
    {
        FluidStack fluid = ichor.drain(maxDrain, doDrain);
        if(doDrain)
        {
        	ichorChanged();
        }
        return fluid;
    }

    @Override
    public boolean canFill(ForgeDirection from, Fluid fluid)
    {
        return fluid.equals(MythoCraftFluids.liquidIchor);
    }

    @Override
    public boolean canDrain(ForgeDirection from, Fluid fluid)
    {
        return true;
    }

    @Override
    public FluidTankInfo[] getTankInfo(ForgeDirection from)
    {
        return new FluidTankInfo[]{ichor.getInfo()};
    }

    //**********ISidedInventory**********
	@Override
	public int getSizeInventory()
	{
		return inputStacks.length + 1;
	}

	@Override
	public ItemStack getStackInSlot(int slot)
	{
		return slot < 0 || slot >= getSizeInventory() ? null : slot < inputStacks.length ? inputStacks[slot] : ichorStack;
	}

	@Override
	public ItemStack decrStackSize(int slot, int amount)
	{
		ItemStack stack = getStackInSlot(slot);
		if(stack != null)
		{
			ItemStack itemstack;
			if(stack.stackSize <= amount)
            {
                itemstack = stack;
                setInventorySlotContents(slot, null);
            }
            else
            {
                itemstack = stack.splitStack(amount);
                if(stack.stackSize == 0)
                {
                	stack = null;
                }
                setInventorySlotContents(slot, stack);
            }
            return itemstack;
		}
		return null;
	}

	@Override
	public ItemStack getStackInSlotOnClosing(int slot)
	{
        ItemStack stack = getStackInSlot(slot);
        if(stack != null)
        {
        	setInventorySlotContents(slot, null);
        }
        return stack;
	}

	@Override
	public void setInventorySlotContents(int slot, ItemStack stack)
	{
		setStackAndUpdate(slot, stack);
	}

	@Override
	public String getInventoryName()
	{
		return hasCustomInventoryName() ? name : "container.mythoAltar";
	}

	@Override
	public boolean hasCustomInventoryName()
	{
		return name != null && name.length() > 0;
	}

	@Override
	public int getInventoryStackLimit()
	{
		return 64;
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer player)
	{
		return worldObj.getTileEntity(xCoord, yCoord, zCoord) == this && player.getDistanceSq((double)xCoord + 0.5D, (double)yCoord + 0.5D, (double)zCoord + 0.5D) <= 64.0D;
	}

	@Override
    public void openInventory() {}

	@Override
    public void closeInventory() {}

    /**
     * Returns true if automation is allowed to insert the given stack (ignoring stack size) into the given slot.
     */@Override
    public boolean isItemValidForSlot(int slot, ItemStack stack)
    {
    	 return slot < 0 || slot >= getSizeInventory() ? false : slot < inputStacks.length ? isInputStack(slot, stack) : isIchor(stack);
    }

	@Override
	public int[] getAccessibleSlotsFromSide(int side)
	{
    	int[] allSlots = new int[getSizeInventory()];
		for(int i = 0; i < allSlots.length; allSlots[i] = i++);
		return allSlots;
	}

	@Override
	public boolean canInsertItem(int slot, ItemStack stack, int side)
	{
		return isItemValidForSlot(slot, stack);
	}

	@Override
	public boolean canExtractItem(int slot, ItemStack stack, int side)
	{
		return false;
	}

	//**********Helper Methods**********
	
	/**
	 * Sets the name to be displayed in the GUI
	 */
	public void setCustomName(String s)
	{
		name = s;
	}
	
	/**
	 * Helper method to fill with fluid already existing, or liquidIchor
	 */
	public int fill(ForgeDirection side, int amount, boolean doFill)
	{
		FluidStack f = ichor.getFluid();
		return fill(side, f != null ? new FluidStack(f, amount) : new FluidStack(MythoCraftFluids.liquidIchor, amount), true);
	}

    @SideOnly(Side.CLIENT)
    /**
     * Used to synchronise the client
     */
	public void setFluidIchor(int amount)
	{
		System.out.println("Setting Fluid Ichor to " + amount);//XXX
		if(amount < 1)
		{
			return;
		}
		int i = getFluidIchor();
		if(amount < i)
		{
			drain(null, i - amount, true);
		}
		if(amount > i)
		{
			fill(null, amount - i, true);
		}
	}
	
	public int getFluidIchor()
	{
		return ichor.getFluidAmount();
	}
	public int getItemIchor()
	{
		return getIchorAmount(ichorStack);
	}
	public int getTotalIchor()
	{
		return getFluidIchor() + getItemIchor();
	}
	
	/**
	 * Consumes the given amount of ichor. Does not check for required amount first
	 */
	public void consumeIchor(int ichorRequired)//TODO:Check for required amount?
	{
		FluidStack fluid = drain(null,ichorRequired, true);
		ichorRequired -= fluid.amount;
		if(ichorRequired > 0)
		{
			ItemStack stack = ichorStack.copy();
			int i = getIchorAmount(stack);
			if(i > 0)
			{
				int j = ichorRequired / i;
				stack.stackSize -= j;
				ichorRequired -= i * j;
				if(ichorRequired > 0)
				{
					stack.stackSize--;
					fill(null, new FluidStack(fluid, i - ichorRequired), true);
					//TODO:Implement gradual liquification: nextIchor += i - ichorRequired;
				}
			}
			setInventorySlotContents(inputStacks.length, stack);//set ichorStack
		}
	}

	private int getIchorAmount(ItemStack stack)
	{
    	//TODO:DEBUG/TEST
		if(stack != null && stack.stackSize > 0)
		{
			if(stack.getItem().equals(MythoCraftItems.solidIchor))
			{
				return ICHOR_CONVERT_RATIO;
			}
			FluidStack f = FluidContainerRegistry.getFluidForFilledItem(stack);
			if(f != null && f.getFluid().equals(MythoCraftFluids.liquidIchor))
			{
				return f.amount;
			}
		}
		return 0;
	}
	
	/**
	 * Checks whether the ItemStack is valid as ichor (is solid Ichor or contains liquid Ichor)
	 */
	public static boolean isIchor(ItemStack stack)
	{
		return stack != null && (stack.isItemEqual(new ItemStack(MythoCraftItems.solidIchor)) || FluidContainerRegistry.containsFluid(stack, new FluidStack(MythoCraftFluids.liquidIchor, 1)));//Contains at least 1 ichor
	}

	private boolean isInputStack(int slot, ItemStack stack)
	{
		ItemStack input = inputStacks[slot];
		return input != null && stack != null && input.stackSize < input.getMaxStackSize() && input.isItemEqual(stack) && ItemStack.areItemStackTagsEqual(input, stack);
	}

	/**
	 * Gets the instance of InventoryAltarIn for the Container
	 */
	public InventoryAltarIn getCraftMatrix(ContainerAltar c)
	{
		return new InventoryAltarIn(c, this, MythoSettings.ALTAR_INPUT_WIDTH, MythoSettings.ALTAR_INPUT_HEIGHT);
	}
	
    private void notifyContainers(int slot, ItemStack newStack, ContainerAltar... exclusions)
	{
		for(ContainerAltar c : listeners)
		{
			if(!Arrays.asList(exclusions).contains(c))
			{
				c.updateContents(slot, newStack);
			}
		}
	}
    
    private void ichorChanged()
    {
    	for(ContainerAltar c : listeners)
		{
			c.onCraftMatrixChanged(this);
		}
    }

	/**
	 * Synchronises the TileEntity Slots with
	 * @param container the container
	 */
	public void updateSlots(ContainerAltar container)
	{
		for(int i = 0; i < getSizeInventory(); i++)
		{
			setStackAndUpdate(i, container.craftMatrix.getStackInSlot(i), container);
		}
	}
	
	/**
	 * Actually set the stack in the slot and notify all listeners not in the list of exclusions
	 */
	private void setStackAndUpdate(int slot, ItemStack stack, ContainerAltar... exclusions)
	{
		if(slot < 0 || slot >= getSizeInventory())
		{
			return;
		}
		if(slot < inputStacks.length)
		{
			inputStacks[slot] = stack;
		}
		else
		{
			ichorStack = stack;
		}
		notifyContainers(slot, stack, exclusions);
	}
}
