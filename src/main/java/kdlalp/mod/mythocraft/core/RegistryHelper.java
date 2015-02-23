package kdlalp.mod.mythocraft.core;

import kdlalp.mod.mythocraft.blocks.BlockLiquid;
import kdlalp.mod.mythocraft.items.ItemBucketFilled;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidRegistry;
import cpw.mods.fml.common.registry.GameRegistry;

public class RegistryHelper
{
	public static Block registerBlock(Block block, String name)
    {
        return registerBlock(block, name, true);
    }
	public static Block registerBlock(Block block, String name, boolean addToCreative)
    {
		if(addToCreative)
		{
			block.setCreativeTab(MythoCraftMod.tabMythoCraftBlocks);
		}
        return GameRegistry.registerBlock(block.setBlockName(name).setBlockTextureName(ModInfo.MODID + ":" + name), name);
    }

	public static Item registerItem(String name)
	{
		return registerItem(null, name);
	}
    public static Item registerItem(Item item, String name)
    {
    	if(item == null)
    	{
    		item = new Item();
    	}
        GameRegistry.registerItem(item.setUnlocalizedName(name).setTextureName(ModInfo.MODID + ":" + name).setCreativeTab(MythoCraftMod.tabMythoCraftItems), name);
        return item;
    }

	public static CompleteFluid registerFluid(Fluid fluid, Material fluidMaterial, String name)
	{
		FluidRegistry.registerFluid(fluid.setUnlocalizedName(name));
		CompleteFluid f = new CompleteFluid();
		f.fluid = fluid;
		f.block = registerBlock(new BlockLiquid(fluid, Material.water), "fluid_" + name, false);
		f.bucket = registerItem(new ItemBucketFilled(fluid), "bucket_" + name);
		FluidContainerRegistry.registerFluidContainer(fluid, new ItemStack(f.bucket), new ItemStack(Items.bucket));
		return f;
	}
    
    public static class CompleteFluid
    {
    	public Fluid fluid;
    	public Block block;
    	public Item bucket;
    }
}
