package kdlalp.mod.mythocraft.core;

import net.minecraftforge.fluids.FluidContainerRegistry;

public class MythoSettings
{
	/** Amount of fluid ichor stored in the altar (mb) */
	public static final int ALTAR_TANK_SIZE = FluidContainerRegistry.BUCKET_VOLUME;
	public static final int ALTAR_NUM_RESULTS = 1;
	/** Amount of fluid ichor produced from a single item (mb) */
	public static final int ICHOR_CONVERT_RATIO = 100;
	public static final int ALTAR_INPUT_WIDTH = 3;
	public static final int ALTAR_INPUT_HEIGHT = 3;
}
