package superdopesquad.superdopejedimod;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;



	
	@SideOnly(Side.CLIENT)
	public class GeneratorGUI extends GuiScreen{

		int guiWidth = 120;
		int guiHeight = 120;
		String Generator = "Generator";

		@Override
		public void drawScreen(int X, int Y, float Ticks) {
			
			
			
			int guiX = (width - guiWidth) / 2;
			int guiY = (height - guiHeight) / 2;
			
			GL11.glColor4f(1, 1, 1, 1);
			
			mc.renderEngine.bindTexture(new ResourceLocation(SuperDopeJediMod.MODID, "textures/gui/CreditGUI.png"));
			drawTexturedModalRect(guiX, guiY, 0, 0, guiWidth, guiHeight);
			
			// Draw the count of how many credits the user has.
			// We know the number of credits, because it was an input into the class.
			//ScaledResolution res = new ScaledResolution(this.mc, this.mc.displayWidth, this.mc.displayHeight);
			FontRenderer fontRender = mc.fontRendererObj;
			//int displayWidthScaled = res.getScaledWidth();
			//int displayHeightScaled = res.getScaledHeight();
			mc.entityRenderer.setupOverlayRendering();
			int textX = guiX + 20;
			int textY = guiY + 20;
			int color = 0x990000;
			
			fontRender.drawStringWithShadow(Generator, textX, textY, color);

			super.drawScreen(X, Y, Ticks);
		}
		
		
	}
