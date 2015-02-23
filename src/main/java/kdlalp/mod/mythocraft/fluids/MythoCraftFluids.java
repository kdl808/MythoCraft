package kdlalp.mod.mythocraft.fluids;

import kdlalp.mod.mythocraft.blocks.MythoCraftBlocks;
import kdlalp.mod.mythocraft.core.RegistryHelper;
import kdlalp.mod.mythocraft.core.RegistryHelper.CompleteFluid;
import kdlalp.mod.mythocraft.items.MythoCraftItems;
import net.minecraft.block.material.Material;
import net.minecraftforge.fluids.Fluid;
public class MythoCraftFluids
{
	public static Fluid liquidIchor;
	
	public static void init()
	{
		CompleteFluid f = RegistryHelper.registerFluid(new Fluid("ichor").setLuminosity(2).setDensity(500).setViscosity(200).setTemperature(200), Material.water, "ichor");
		liquidIchor = f.fluid;
		MythoCraftBlocks.liquidIchor = f.block;
		MythoCraftItems.bucketIchor = f.bucket;
	}
}
