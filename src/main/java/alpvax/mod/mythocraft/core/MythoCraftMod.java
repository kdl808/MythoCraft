package alpvax.mod.mythocraft.core;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.EntityEvent.EntityConstructing;
import alpvax.mod.mythocraft.blocks.MythoCraftBlocks;
import alpvax.mod.mythocraft.crafting.AltarRecipes;
import alpvax.mod.mythocraft.items.MythoCraftItems;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.network.NetworkRegistry;

@Mod(modid = MythoReference.MODID, version = MythoReference.VERSION)
public class MythoCraftMod
{
    @Mod.Instance(MythoReference.MODID)
    public static MythoCraftMod instance;
    
    @SidedProxy(clientSide="alpvax.mod.mythocraft.client.ClientProxy", serverSide="alpvax.mod.mythocraft.core.CommonProxy")
	public static CommonProxy proxy;
    
    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
    	instance = this;
    	MythoCraftBlocks.init();
    	MythoCraftItems.init();
    	AltarRecipes.getInstance().addShapelessRecipe(new ItemStack(Items.coal, 64), 1, 0, 16, Items.blaze_powder);
    	AltarRecipes.getInstance().addShapelessRecipe(new ItemStack(MythoCraftBlocks.altar), 100, 2, 0, Blocks.stone);

        NetworkRegistry.INSTANCE.registerGuiHandler(this, new CommonProxy());
    	MinecraftForge.EVENT_BUS.register(this);
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
