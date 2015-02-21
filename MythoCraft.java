package mod.kdl808.mythocraft;

import mod.kdl808.mythocraft.core.ModInfo;
import net.minecraft.creativetab.CreativeTabs;
import cpw.mods.fml.common.Mod;

@Mod(modid = ModInfo.MODID, name = ModInfo.NAME, version = ModInfo.VERSION)
public class MythoCraft {
	
	public static CreativeTabs tabMythoCraftItems = new CreativeTabs("MythoCraft Items") {
		@SideOnly(Side.CLIENT)
		public Item getTabIconItems(){
			return Item.getItemFromBlock(Blocks.enchanting_table);
			{
		}
	};
	
	public static CreativeTabs tabMythoCraftBlocks = new CreativeTabs("MythoCraft Blocks") {
		@SideOnly(Side.CLIENT)
		public Item getTabIconItems(){
			return Item.getItemFromBlock(Blocks.quartz);
			{
		}
	};
	
	@Mod.EventHandler
	public void preInit(FMLPreInitializationEvent preEvent) {
	ObsidianShard.init();
	DuskIronIngot.init();
	DawnGoldIngot.init();
	Recipes.init();
	MythoCraftBlocks.init();
	}
	@Mod.EventHandler
	public void Init(FMLInitializationEvent event) {
	  
	}
	@Mod.EventHandler
	public void postInit(FMLPostInitializationEvent postEvent) {
	  
	}
}
