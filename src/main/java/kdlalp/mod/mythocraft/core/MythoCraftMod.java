package kdlalp.mod.mythocraft.core;

import kdlalp.mod.mythocraft.blocks.MythoCraftBlocks;
import kdlalp.mod.mythocraft.crafting.MythoCraftRecipes;
import kdlalp.mod.mythocraft.items.MythoCraftItems;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.EntityEvent.EntityConstructing;
import net.minecraftforge.oredict.OreDictionary;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@Mod(modid = ModInfo.MODID, name = ModInfo.NAME, version = ModInfo.VERSION)
public class MythoCraftMod
{
    @Mod.Instance(ModInfo.MODID)
    public static MythoCraftMod instance;
    
    @SidedProxy(clientSide="kdlalp.mod.mythocraft.client.ClientProxy", serverSide="kdlalp.mod.mythocraft.core.CommonProxy")
	public static CommonProxy proxy;
    
    public static CreativeTabs tabMythoCraftItems = new CreativeTabs("MythoCraft Items")
    {
		@SideOnly(Side.CLIENT)
		public Item getTabIconItem()
		{
			return MythoCraftItems.ichor;
		}
	};
	
	public static CreativeTabs tabMythoCraftBlocks = new CreativeTabs("MythoCraft Blocks")
	{
		@SideOnly(Side.CLIENT)
		public Item getTabIconItem()
		{
			return Item.getItemFromBlock(MythoCraftBlocks.altar);
		}
	};
    
    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
    	instance = this;
    	MythoCraftBlocks.init();
    	MythoCraftItems.init();
    	initOreDict();
    	MythoCraftRecipes.init();

        NetworkRegistry.INSTANCE.registerGuiHandler(this, new CommonProxy());
    	MinecraftForge.EVENT_BUS.register(this);
    }
    
    private void initOreDict()
	{
		OreDictionary.registerOre("obsidianShard", MythoCraftItems.obsidianShard);
	}

	@SubscribeEvent
    public void constructEntity(EntityConstructing e)
    {
    	if(e.isCanceled())	return;
    	if(e.entity instanceof EntityPlayer)
    	{
    		MythoPlayer.register(e.entity);
    	}
    }
}
