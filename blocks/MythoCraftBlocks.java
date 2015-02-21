package mod.kdl808.mythocraft.blocks;

import static mod.kdl808.mythocraft.blocks.MythoCraftBlocksDec.blockOres;
public class MythoCraftBlocks {
	
	public static void init() {
		registerBlocks();
	}
	
	public static void registerBlocks() {
		blockOres = new BlockOres().setBlockName("blockOres");
	}

}
