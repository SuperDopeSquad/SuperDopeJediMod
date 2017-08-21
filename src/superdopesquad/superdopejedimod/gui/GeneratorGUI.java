package superdopesquad.superdopejedimod.gui;

import java.lang.ref.Reference;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.resources.I18n;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.util.ResourceLocation;
import superdopesquad.superdopejedimod.SuperDopeJediMod;

public class GeneratorGUI extends GuiContainer {

	private TileEntityGenerator te;
	private IInventory playerInv;
	
	public GeneratorGUI(IInventory playerInv, TileEntityGenerator te) {
		
		super(new ContainerGenerator(playerInv, te));
		
		this.xSize = 175;
		this.ySize = 165;
		
		this.te = te;
		this.playerInv = playerInv;

	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
		
		GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
		this.mc.getTextureManager().bindTexture(new ResourceLocation(SuperDopeJediMod.MODID, "textures/gui/container/generator.png"));
		this.drawTexturedModalRect(this.guiLeft, this.guiTop, 0, 0, this.xSize, this.ySize);
		
	}

//		@Override
	protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
		String s = I18n.format("container.generator"); //Gets the formatted name for the block breaker from the language file
		this.mc.fontRenderer.drawString(s, this.xSize / 2 - this.mc.fontRenderer.getStringWidth(s) / 2, 6, 4210752); //Draws the block breaker name in the center on the top of the gui
		this.mc.fontRenderer.drawString(this.playerInv.getDisplayName().getFormattedText(), 8, 73, 4210752); //The player's inventory name
		
	
		}
	
	}
