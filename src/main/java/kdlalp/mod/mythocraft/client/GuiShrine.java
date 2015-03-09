package kdlalp.mod.mythocraft.client;

import kdlalp.mod.mythocraft.api.crafting.IShrineRecipe;
import kdlalp.mod.mythocraft.blocks.shrine.ContainerShrine;
import kdlalp.mod.mythocraft.blocks.shrine.TileEntityShrine;
import kdlalp.mod.mythocraft.core.MythoPlayer;
import kdlalp.mod.mythocraft.core.MythoSettings;
import kdlalp.mod.mythocraft.fluids.MythoCraftFluids;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiShrine extends GuiContainer
{
    private static final ResourceLocation shrineGuiTextures = new ResourceLocation("mythocraft:textures/gui/container/shrine.png");
    
    private TileEntityShrine shrine;

    public GuiShrine(InventoryPlayer inv, TileEntityShrine tile)
    {
        super(new ContainerShrine(inv, tile));
        shrine = tile;
    }

    /**
     * Draw the foreground layer for the GuiContainer (everything in front of the items)
     */
    protected void drawGuiContainerForegroundLayer(int p_146979_1_, int p_146979_2_)
    {
    	GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        mc.getTextureManager().bindTexture(shrineGuiTextures);
        int k = (width - xSize) / 2;
        int l = (height - ySize) / 2;
        drawTexturedModalRect(k + 11, l + 18, 11, 18, 1, 1);//IchorBar tlpix
        drawTexturedModalRect(k + 20, l + 18, 20, 18, 1, 1);//IchorBar trpix
        drawTexturedModalRect(k + 11, l + 67, 11, 67, 1, 1);//IchorBar blpix
        drawTexturedModalRect(k + 20, l + 67, 20, 67, 1, 1);//IchorBar brpix
    	String s = shrine.hasCustomInventoryName() ? shrine.getInventoryName() : StatCollector.translateToLocal(shrine.getInventoryName());
        fontRendererObj.drawString(s, xSize / 2 - fontRendererObj.getStringWidth(s) / 2, 6, 4210752);
    	s = StatCollector.translateToLocal(MythoCraftFluids.liquidIchor.getUnlocalizedName());
    	int i = fontRendererObj.getStringWidth(s) / 2;
        fontRendererObj.drawString(s, 15 - i + 3, 8, 4210752);
        s = StatCollector.translateToLocal("label.tier");
        fontRendererObj.drawString(s, 117 - fontRendererObj.getStringWidth(s) / 2, 18, 0xf0f0f0);
        fontRendererObj.drawString(StatCollector.translateToLocal("container.inventory"), 8, ySize - 96 + 2, 4210752);
    }

    protected void drawGuiContainerBackgroundLayer(float p_146976_1_, int p_146976_2_, int p_146976_3_)
    {
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        mc.getTextureManager().bindTexture(shrineGuiTextures);
        int k = (width - xSize) / 2;
        int l = (height - ySize) / 2;
        drawTexturedModalRect(k, l, 0, 0, xSize, ySize);//MainGui
        int i = shrine.getFluidIchor() * 50 / MythoSettings.SHRINE_TANK_SIZE;
		drawTexturedModelRectFromIcon(k + 11, l + 67 - i, MythoCraftFluids.liquidIchor.getStillIcon(), 9, i);//IchorBar TODO:Fix IchorBar
        IShrineRecipe recipe = ((ContainerShrine)inventorySlots).craftResult.getShrineRecipe();
        if(recipe != null)
        {
        	int tierReq = recipe.tierRequired();
        	drawTexturedModalRect(k + 92, l + 18, 176, 0, tierReq > 0 ? MythoPlayer.tier(mc.thePlayer) * 50 / tierReq : 50, 7);//TierBar
        }
    }
}