package superdopesquad.superdopejedimod;

import java.awt.Color;
import javax.swing.plaf.synth.ColorType;
import org.lwjgl.opengl.GL11;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;


@SideOnly(Side.CLIENT)
public class CreditGUI extends GuiScreen{

	int guiWidth = 148;
	int guiHeight = 80;
	int creditCount = -1;
	
	public CreditGUI(int creditCount) {
	
		super();
		
		this.creditCount = creditCount;
	}
	
	
	@Override
	public void drawScreen(int X, int Y, float Ticks) {
		
		String creditCountString = Integer.toString(this.creditCount);
		
		
		int guiX = (width - guiWidth) / 2;
		int guiY = (height - guiHeight) / 2;
		GL11.glColor4f(1, 1, 1, 1);
		drawDefaultBackground();
		mc.renderEngine.bindTexture(new ResourceLocation(SuperDopeJediMod.MODID, "textures/gui/CreditGUI.png"));
		drawTexturedModalRect(guiX, guiY, 0, 0, guiWidth, guiHeight);
		
		// Draw the count of how many credits the user has.
		// We know the number of credits, because it was an input into the class.
		//ScaledResolution res = new ScaledResolution(this.mc, this.mc.displayWidth, this.mc.displayHeight);
		FontRenderer fontRender = mc.fontRenderer;
		//int displayWidthScaled = res.getScaledWidth();
		//int displayHeightScaled = res.getScaledHeight();
		mc.entityRenderer.setupOverlayRendering();
		int textX = guiX + 20;
		int textY = guiY + 20;
		int color = 0x990000;
		
		fontRender.drawStringWithShadow(creditCountString, textX, textY, color);

		super.drawScreen(X, Y, Ticks);
	}

	
}
