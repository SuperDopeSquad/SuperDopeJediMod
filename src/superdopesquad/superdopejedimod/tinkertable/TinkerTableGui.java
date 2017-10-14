package superdopesquad.superdopejedimod.tinkertable;


import org.lwjgl.opengl.GL11;
import com.mojang.realmsclient.gui.ChatFormatting;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.gui.recipebook.IRecipeShownListener;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import scala.reflect.internal.Trees.This;


@SideOnly(value = Side.CLIENT)
public class TinkerTableGui extends GuiContainer implements IRecipeShownListener
{
    //public TinkerTableContainer container;
    private String _blockName;

    
    public TinkerTableGui(EntityPlayer player, World world, String blockName, int x, int y, int z) {
    	
        super(new TinkerTableContainer(player, world, new BlockPos(x, y, z)));
        
        //System.out.println("x:" + x + " y:" + y + "z:" + z);
    	
        //this.container = (TinkerTableContainer) inventorySlots;
        this._blockName = blockName;
    }
    
    
    @Override
    public void initGui() {
    	
    	//System.out.println("inside GeneratorGui:initGui()");
    }

    @Override
    public void actionPerformed(GuiButton button) {
    }

    
    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks)
    {
        super.drawScreen(mouseX, mouseY, partialTicks);
    }

//    @Override
//    protected void drawGuiContainerForegroundLayer(int par1, int par2)
//    {
//        GL11.glDisable(GL11.GL_LIGHTING);
//
//        fontRenderer.drawString(blockName, xSize / 2 - 
//              fontRenderer.getStringWidth(blockName) / 2 + 1, 5, 4210752);
//        fontRenderer.drawString(I18n.format("container.inventory"), 6, ySize - 96 + 2, 
//              4210752);
//
//        String string = container.resultString;
//        if(string != null)
//        {
//            State msgType = container.deconstructingState;
//            ChatFormatting format = ChatFormatting.GREEN;
//            ChatFormatting shadowFormat = ChatFormatting.DARK_GRAY;
//            if(msgType == GeneratorContainer.State.ERROR)
//            {
//                format = ChatFormatting.WHITE;
//                shadowFormat = ChatFormatting.DARK_RED;
//            }
//
//            fontRenderer.drawString(shadowFormat + string + ChatFormatting.RESET, 
//                  6 + 1, ySize - 95 + 2 - fontRenderer.FONT_HEIGHT, 0);
//
//            fontRenderer.drawString(format + string + ChatFormatting.RESET, 6, 
//                  ySize - 96 + 2 - fontRenderer.FONT_HEIGHT, 0);
//        }
//
//        GL11.glEnable(GL11.GL_LIGHTING);
//    }

//    @Override
//    protected void drawGuiContainerBackgroundLayer(float f, int i, int j)
//    {
////        GL11.glPushMatrix();
////        GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
////
////        mc.renderEngine.bindTexture(
////              new ResourceLocation("blocksmith:textures/gui/container/deconstructor.png"));
////
////        int k = width / 2 - xSize / 2;
////        int l = height / 2 - ySize / 2;
////        drawTexturedModalRect(k, l, 0, 0, xSize, ySize);
////        GL11.glPopMatrix();
//    }
    
    
	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
		
		// figure out where the dialog should start drawing.  Let's center this!
		this.guiLeft = (this.width - this.xSize) / 2;
		this.guiTop = (this.height - this.ySize) / 2;
		
		// draw the background texture.
		GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);
		this.mc.getTextureManager().bindTexture(new ResourceLocation("superdopejedimod:textures/gui/tinkertableguibackground.png"));
		this.drawTexturedModalRect(this.guiLeft, this.guiTop, 0, 0, this.xSize, this.ySize);
	}

	
	@Override
	protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
		
		//System.out.println("DEBUG in drawGuiContainerForegroundLayer:" + this.width + "," + this.height);
		//this.drawRect(0, 0, this.width, this.height, 4210752);
		
		// Title at the top of the dialog.
		String dialogTitle = I18n.format("tile.tinkerTable.name"); //Gets the formatted name for the block breaker from the language file
		this.mc.fontRenderer.drawString(dialogTitle, this.xSize / 2 - this.mc.fontRenderer.getStringWidth(dialogTitle) / 2, 6, 4210752); //Draws the block breaker name in the center on the top of the gui
		
//		// Testing.
//		//String testingString = I18n.format("Testing!"); //Gets the formatted name for the block breaker from the language file
//		this.mc.fontRenderer.drawString(
//				testingString, // string
//				this.xSize / 2 - this.mc.fontRenderer.getStringWidth(testingString) / 2, // x
//				6 + 30, // y
//				4210752); // color
//				
		//this.mc.fontRenderer.drawString(this.playerInv.getDisplayName().getFormattedText(), 8, 73, 4210752); //The player's inventory name
	}


	@Override
	public void recipesUpdated() {
		// TODO Auto-generated method stub
		
	}
}
