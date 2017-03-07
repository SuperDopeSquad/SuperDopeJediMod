package superdopesquad.superdopejedimod.weapon;

import java.util.List;

import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;
import superdopesquad.superdopejedimod.SuperDopeJediMod;
import superdopesquad.superdopejedimod.faction.ClassInfo;

public class LightSaber extends BaseMeleeWeapon {
	
	String color;
	

	public LightSaber(String unlocalizedName, String colorInput) {
		
		super(unlocalizedName, SuperDopeJediMod.powerCrystalMaterial);
		
		this.setMaxStackSize(1);
		
		this.color = colorInput;
		
		this.setCreativeTab(CreativeTabs.COMBAT);
		//this.setMaxStackSize(64);
		//this.setCreativeTab(CreativeTabs.tabMisc);
		//this.setUnlocalizedName(unlocalizedName);
		
		
	}
	
	

	public void registerRecipe() {
		
		if (this.color == "Red") {
		
			// Recipe for creating a redLightSaber.
			ItemStack redPowerCrystalStack = new ItemStack(SuperDopeJediMod.redPowerCrystal);
    		ItemStack ironIngotStack = new ItemStack(Items.IRON_INGOT);
    		ItemStack stoneButtonStack = new ItemStack(Blocks.STONE_BUTTON);
    		GameRegistry.addRecipe(new ItemStack(this), "x", "y", "z", 'x', redPowerCrystalStack, 'y', stoneButtonStack, 'z', ironIngotStack);
		} 
		
		if (this.color == "Blue") {
			
			// Recipe for creating a blueLightSaber.
			ItemStack bluePowerCrystalStack = new ItemStack(SuperDopeJediMod.bluePowerCrystal);
    		ItemStack ironIngotStack = new ItemStack(Items.IRON_INGOT);
    		ItemStack stoneButtonStack = new ItemStack(Blocks.STONE_BUTTON);
    		GameRegistry.addRecipe(new ItemStack(this), "x", "y", "z", 'x', bluePowerCrystalStack, 'y', stoneButtonStack, 'z', ironIngotStack);
		}
		
		if (this.color == "Green") {
			
			// Recipe for creating a greenLightSaber.
			ItemStack greenPowerCrystalStack = new ItemStack(SuperDopeJediMod.greenPowerCrystal);
    		ItemStack ironIngotStack = new ItemStack(Items.IRON_INGOT);
    		ItemStack stoneButtonStack = new ItemStack(Blocks.STONE_BUTTON);
    		GameRegistry.addRecipe(new ItemStack(this), "x", "y", "z", 'x', greenPowerCrystalStack, 'y', stoneButtonStack, 'z', ironIngotStack);
    		
		}
		
		if (this.color == "Purple") {
			
			// Recipe for creating a purpleLightSaber.
			ItemStack purplePowerCrystalStack = new ItemStack(SuperDopeJediMod.purplePowerCrystal);
    		ItemStack ironIngotStack = new ItemStack(Items.IRON_INGOT);
    		ItemStack stoneButtonStack = new ItemStack(Blocks.STONE_BUTTON);
    		GameRegistry.addRecipe(new ItemStack(this), "x", "y", "z", 'x', purplePowerCrystalStack, 'y', stoneButtonStack, 'z', ironIngotStack);
    		
		} 
		if (this.color == "Red") {
			
			// Recipe for creating a new redLightSaber.
			ItemStack redstoneStack = new ItemStack(Items.REDSTONE);
    		
    		ItemStack lightSaberRedStack = new ItemStack(SuperDopeJediMod.lightSaberRed);
    		GameRegistry.addRecipe(new ItemStack(this), "xxx", "xyx", "xxx", 'x', redstoneStack, 'y', lightSaberRedStack);
		} 
		if (this.color == "Blue") {
			
			// Recipe for creating a new blueLightSaber.
			ItemStack redstoneStack = new ItemStack(Items.REDSTONE);
    		
    		ItemStack lightSaberBlueStack = new ItemStack(SuperDopeJediMod.lightSaberBlue);
    		GameRegistry.addRecipe(new ItemStack(this), "xxx", "xyx", "xxx", 'x', redstoneStack, 'y', lightSaberBlueStack);
		} 
		if (this.color == "Green") {
	
			// Recipe for creating a new greenLightSaber.
			ItemStack redstoneStack = new ItemStack(Items.REDSTONE);
    		
    		ItemStack lightSaberGreenStack = new ItemStack(SuperDopeJediMod.lightSaberGreen);
    		GameRegistry.addRecipe(new ItemStack(this), "xxx", "xyx", "xxx", 'x', redstoneStack, 'y', lightSaberGreenStack);
		} 
		if (this.color == "Purple") {
			
			// Recipe for creating a new PurpleLightSaber.
			ItemStack redstoneStack = new ItemStack(Items.REDSTONE);
    		
    		ItemStack lightSaberPurpleStack = new ItemStack(SuperDopeJediMod.lightSaberPurple);
    		GameRegistry.addRecipe(new ItemStack(this), "xxx", "xyx", "xxx", 'x', redstoneStack, 'y', lightSaberPurpleStack);
		} 	
	}
	
	@Override
	public List<ClassInfo> GetFriendlyClasses() {
	
		// By default, we only let force-wielding classes use Light Sabers.
		return SuperDopeJediMod.classManager.getForceWieldingClasses();
	}
	
	
	@Override
	public List<ClassInfo> GetUnfriendlyClasses() {
	
		// By default, we do not let force-wielding classes use blasters.
		return SuperDopeJediMod.classManager.getNonForceWieldingClasses();
	}
	
	@Override
	public boolean IsUseUnfriendlyBanned() {
	
		// By default, all blasters are banned from the unfriendly classes, namely, the force-wielding classes.
		return true;
	}
	
}
