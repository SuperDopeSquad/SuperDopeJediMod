package superdopesquad.superdopejedimod.faction;


import java.awt.Color;
import java.awt.event.ActionEvent;
import java.io.IOException;
import javax.swing.plaf.synth.ColorType;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.Display;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import superdopesquad.superdopejedimod.SuperDopeJediMod;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.entity.player.EntityPlayer;


@SideOnly(Side.CLIENT)
public class ClassGUI extends GuiScreen {

	int guiWidth = 250;
	int guiHeight = 350;
	int sithWidth = 125;
	int sithHeight = 175;
	int jediWidth = 125;
	int jediHeight = 175;
	private GuiButton _sithButton = null;
	private GuiButton _jediButton = null;
	private EntityPlayer _player = null;
	private static final ClassManager _classManager = SuperDopeJediMod.classManager;

	
	public ClassGUI(EntityPlayer player) {
				
		this._player = player;
	}
	
	
	//draws how big the whole gui is and what texture it uses
	@Override
	public void drawScreen(int X, int Y, float Ticks) {
		
		int guiX = (width - guiWidth) / 2;
		int guiY = (height - guiHeight) / 2;
		GL11.glColor4f(1, 1, 1, 1);
		
		mc.renderEngine.bindTexture(new ResourceLocation(SuperDopeJediMod.MODID, "MDKExample/src/main/resources/assets/superdopejedimod/textures/gui/FactionGUI.png"));
		//draws rectangles
		drawTexturedModalRect(guiX, guiY, 0, 0, guiWidth, guiHeight);
		drawTexturedModalRect(guiX, guiY, 0, 81, sithWidth, sithHeight);
		
		
		//ScaledResolution res = new ScaledResolution(this.mc, this.mc.displayWidth, this.mc.displayHeight);
		FontRenderer fontRender = mc.fontRenderer;
		//int displayWidthScaled = res.getScaledWidth();
		//int displayHeightScaled = res.getScaledHeight();
		mc.entityRenderer.setupOverlayRendering();
		int textX = guiX + 20;
		int textY = guiY + 20;
		int color = 0x990000;

		super.drawScreen(X, Y, Ticks);
	}

	
	//sets the gui size and buttons.
	@Override
	public void initGui() {
		
		int guiX = (width - guiWidth) / 2;
		int guiY = (height - guiHeight) / 2;
		buttonList.clear();
		buttonList.add(this._sithButton = new GuiButton(_classManager.SITH, guiX + 10, guiY + 30, 250, 175, _classManager.SITH_NAME));
		buttonList.add(this._jediButton = new GuiButton(_classManager.JEDI, guiX + 10, guiY + 200, 250, 175, _classManager.JEDI_NAME));
		super.initGui();
	}
	
	
	private void setClass(Integer id) {
		
	}
	
	//what the gui says when you click on one of the two buttons
	@Override
	protected void actionPerformed(GuiButton button) throws IOException {

		// Go ahead and set the faction.  If it fails, we should tell the user we failed.
		int classId = button.id;
		boolean setSuccessfully = _classManager.setPlayerClassById(this._player, classId);
		if (!setSuccessfully) {
			System.out.println("Error setting class through ClassGUI");
			return;
		}
		
		// If you made it this far, you succeeded in setting the faction, and we want to tell the user what happened.
		
		// if you choose the sith
		if (button.id == _classManager.SITH) {
			
			this._sithButton.displayString = "You have chosen the " + _classManager.SITH_NAME + ".";
			this._sithButton.enabled = false;
			this._jediButton.enabled = false;
		}
		
		//if you choose the republic
		else if (button.id == _classManager.JEDI) {
			
			this._jediButton.displayString = "You have chosen the " + _classManager.JEDI_NAME + ".";
			this._jediButton.enabled = false;
			this._sithButton.enabled = false;
		}
		
		super.actionPerformed(button);
	}		
}
