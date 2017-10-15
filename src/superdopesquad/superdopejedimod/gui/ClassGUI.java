package superdopesquad.superdopejedimod.gui;

import java.io.IOException;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.text.TextComponentString;
import superdopesquad.superdopejedimod.SuperDopeJediMod;
import superdopesquad.superdopejedimod.faction.ClassManager;

public class ClassGUI extends GuiScreen {

	GuiButton sith;
	GuiButton jedi;
	GuiButton neutral;
	GuiButton bountyhunter;
	GuiButton smuggler;
	GuiButton stormtroopercommander;
	GuiButton sithpilot;
	GuiButton republicpilot;
	GuiButton tunneler;
	GuiButton teamjude;
	
	EntityPlayer player;
	
	
	
	
	public ClassGUI(EntityPlayer player) {
		
		super();
		this.player = player;
		
	}
	
	//draws the default background
	
	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
	    this.drawDefaultBackground();
	    super.drawScreen(mouseX, mouseY, partialTicks);
	}
	
	//makes sure when the gui pops up the game doesnt pause
	
	@Override
	public boolean doesGuiPauseGame() {
	    return false;
	}
	
	//draws buttons on screen
	
	public void initGui() {
	   
		this.buttonList.add(this.sith = new GuiButton(0, this.width / 2 - 200, this.height / 2 - 75, "Sith"));
	    this.buttonList.add(this.bountyhunter = new GuiButton(1, this.width / 2 - 200, this.height / 2 - 47, "Bounty Hunter"));
	    this.buttonList.add(this.jedi = new GuiButton(2, this.width / 2, this.height / 2 - 75, "Jedi"));
	    this.buttonList.add(this.neutral = new GuiButton(3, this.width / 2, this.height / 2 - 47, "Neutral"));
	    this.buttonList.add(this.smuggler = new GuiButton(4, this.width / 2, this.height / 2 - 18, "Smuggler"));
	    this.buttonList.add(this.stormtroopercommander = new GuiButton(5, this.width / 2 - 200, this.height / 2 - 18, "Storm Trooper Commander"));
	    this.buttonList.add(this.tunneler = new GuiButton(6, this.width / 2 - 200, this.height / 2 + 10, "Tunneler"));
	    this.buttonList.add(this.teamjude = new GuiButton(7, this.width / 2, this.height / 2 + 10, "Team Jude"));
	    
	}
	
	//Closes gui when a button is clicked
	
	@Override
	protected void actionPerformed(GuiButton button) throws IOException {
	    if (button == this.sith) {
	        
	        
	    	//sets the class
	    	String inputClassName = "sith";
	    	boolean classSetSuccessfully = SuperDopeJediMod.classManager.setPlayerClassByName(player, inputClassName);
	    	
	    	
	    	this.mc.displayGuiScreen(null);
	        if (this.mc.currentScreen == null)
	            this.mc.setIngameFocus();
	        
	        player.sendMessage(new TextComponentString("Congratulations! " + SuperDopeJediMod.classManager.getPlayerClassLongDescription(player)));
	        
	    }
	    if (button == this.jedi){
	        
	    	
	    	String inputClassName = "jedi";
	    	boolean classSetSuccessfully = SuperDopeJediMod.classManager.setPlayerClassByName(player, inputClassName);
	    	
	    	
	        this.mc.displayGuiScreen(null);
	        if (this.mc.currentScreen == null)
	            this.mc.setIngameFocus();
	        
	        player.sendMessage(new TextComponentString("Congratulations! " + SuperDopeJediMod.classManager.getPlayerClassLongDescription(player)));
	        
	    }
	    
	    if (button == this.neutral){
	    	
	    	
	    	String inputClassName = "unaffiliated";
	    	boolean classSetSuccessfully = SuperDopeJediMod.classManager.setPlayerClassByName(player, inputClassName);
	        
	    	
	        this.mc.displayGuiScreen(null);
	        if (this.mc.currentScreen == null)
	            this.mc.setIngameFocus();
	        
	        player.sendMessage(new TextComponentString("Congratulations! " + SuperDopeJediMod.classManager.getPlayerClassLongDescription(player)));
	        
	    }
	    
	    if (button == this.bountyhunter){
	    	
	    	
	    	String inputClassName = "bountyhunter";
	    	boolean classSetSuccessfully = SuperDopeJediMod.classManager.setPlayerClassByName(player, inputClassName);
	        
	    	
	        this.mc.displayGuiScreen(null);
	        if (this.mc.currentScreen == null)
	            this.mc.setIngameFocus();
	        
	        player.sendMessage(new TextComponentString("Congratulations! " + SuperDopeJediMod.classManager.getPlayerClassLongDescription(player)));
	        
	    }
	    
	    if (button == this.smuggler){
	    	
	    	
	    	String inputClassName = "smuggler";
	    	boolean classSetSuccessfully = SuperDopeJediMod.classManager.setPlayerClassByName(player, inputClassName);
	        
	    	
	        this.mc.displayGuiScreen(null);
	        if (this.mc.currentScreen == null)
	            this.mc.setIngameFocus();
	        
	        player.sendMessage(new TextComponentString("Congratulations! " + SuperDopeJediMod.classManager.getPlayerClassLongDescription(player)));
	        
	    }
	    
	    
	    if (button == this.stormtroopercommander){
	    	
	    	
	    	String inputClassName = "stormtroopercommander";
	    	boolean classSetSuccessfully = SuperDopeJediMod.classManager.setPlayerClassByName(player, inputClassName);
	        
	    	
	        this.mc.displayGuiScreen(null);
	        if (this.mc.currentScreen == null)
	            this.mc.setIngameFocus();
	        
	        player.sendMessage(new TextComponentString("Congratulations! " + SuperDopeJediMod.classManager.getPlayerClassLongDescription(player)));
	        
	    }
	    
	    if (button == this.tunneler){
	    	
	    	
	    	String inputClassName = "tunneler";
	    	boolean classSetSuccessfully = SuperDopeJediMod.classManager.setPlayerClassByName(player, inputClassName);
	        
	    	
	        this.mc.displayGuiScreen(null);
	        if (this.mc.currentScreen == null)
	            this.mc.setIngameFocus();
	        
	        player.sendMessage(new TextComponentString("Congratulations! " + SuperDopeJediMod.classManager.getPlayerClassLongDescription(player)));
	        
	    }
	    
	    if (button == this.teamjude){
	    	
	    	
	    	String inputClassName = "teamjude";
	    	boolean classSetSuccessfully = SuperDopeJediMod.classManager.setPlayerClassByName(player, inputClassName);
	        
	    	
	        this.mc.displayGuiScreen(null);
	        if (this.mc.currentScreen == null)
	            this.mc.setIngameFocus();
	        
	        player.sendMessage(new TextComponentString("Congratulations! " + SuperDopeJediMod.classManager.getPlayerClassLongDescription(player)));
	        
	    }
	    
	}
	
}
