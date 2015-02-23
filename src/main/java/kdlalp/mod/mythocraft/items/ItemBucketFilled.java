package kdlalp.mod.mythocraft.items;

import java.util.HashMap;
import java.util.Map;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBucket;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.fluids.Fluid;

public class ItemBucketFilled extends ItemBucket
{
	public ItemBucketFilled(Fluid fluid)
	{
		super(fluid.getBlock());
		fluidToBucketMap.put(fluid.getBlock(), new ItemStack(this));
	}

	private static Map<Block, ItemStack> fluidToBucketMap = new HashMap<Block, ItemStack>();
	
	public static ItemStack fillBucket(World world, int x, int y, int z)
	{
		if(world.getBlockMetadata(x, y, z) == 0)
		{
			Block block = world.getBlock(x, y, z);
			ItemStack stack = fluidToBucketMap.get(block);
			if(stack != null)
			{
				world.setBlockToAir(x, y, z);
				return stack;
			}
		}
		return null;
	}
}
