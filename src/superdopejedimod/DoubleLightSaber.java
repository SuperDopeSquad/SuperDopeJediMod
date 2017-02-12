package superdopesquad.superdopejedimod;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class DoubleLightSaber extends BaseMeleeWeapon {
	
	String color;
	
	
	public DoubleLightSaber(String unlocalizedName, String colorInput) {
		
		super(unlocalizedName, SuperDopeJediMod.doublePowerCrystalMaterial);
		
		this.setMaxStackSize(1);
		
		this.color = colorInput;
		
		this.setCreativeTab(CreativeTabs.COMBAT);
	}

public void registerRecipe() {
		
		if (this.color == "Red") {
		
			// Recipe for creating a redLightSaber.
			ItemStack redPowerCrystalStack = new ItemStack(SuperDopeJediMod.redPowerCrystal);
    		ItemStack ironIngotStack = new ItemStack(Items.IRON_INGOT);
    		GameRegistry.addRecipe(new ItemStack(this), "x", "z", "x", 'x', redPowerCrystalStack, 'z', ironIngotStack);
		}
		
		if (this.color == "Blue") {
			
			// Recipe for creating a blueLightSaber.
			ItemStack bluePowerCrystalStack = new ItemStack(SuperDopeJediMod.bluePowerCrystal);
    		ItemStack ironIngotStack = new ItemStack(Items.IRON_INGOT);
    		GameRegistry.addRecipe(new ItemStack(this), "x", "z", "x", 'x', bluePowerCrystalStack, 'z', ironIngotStack);
		}
		
		if (this.color == "Green") {
			
			// Recipe for creating a greenLightSaber.
			ItemStack greenPowerCrystalStack = new ItemStack(SuperDopeJediMod.greenPowerCrystal);
    		ItemStack ironIngotStack = new ItemStack(Items.IRON_INGOT);
    		GameRegistry.addRecipe(new ItemStack(this), "x", "z", "x", 'x', greenPowerCrystalStack, 'z', ironIngotStack);
		}
		
		if (this.color == "Purple") {
			
			// Recipe for creating a purpleLightSaber.
			ItemStack purplePowerCrystalStack = new ItemStack(SuperDopeJediMod.purplePowerCrystal);
    		ItemStack ironIngotStack = new ItemStack(Items.IRON_INGOT);
    		GameRegistry.addRecipe(new ItemStack(this), "x", "z", "x", 'x', purplePowerCrystalStack, 'z', ironIngotStack);
		}
		
		if (this.color == "Red") {
			
			// Recipe for creating a redLightSaber.
			ItemStack redLightSaberStack = new ItemStack(SuperDopeJediMod.lightSaberRed);
    		ItemStack redPowerCrystalStack = new ItemStack(SuperDopeJediMod.redPowerCrystal);
    		GameRegistry.addRecipe(new ItemStack(this), "x", "z", 'x', redLightSaberStack, 'z', redPowerCrystalStack);
		}
		
		if (this.color == "Blue") {
			
			// Recipe for creating a redLightSaber.
			ItemStack blueLightSaberStack = new ItemStack(SuperDopeJediMod.lightSaberBlue);
    		ItemStack bluePowerCrystalStack = new ItemStack(SuperDopeJediMod.bluePowerCrystal);
    		GameRegistry.addRecipe(new ItemStack(this), "x", "z", 'x', blueLightSaberStack, 'z', bluePowerCrystalStack);
		}
		
		if (this.color == "Green") {
			
			// Recipe for creating a redLightSaber.
			ItemStack greenLightSaberStack = new ItemStack(SuperDopeJediMod.lightSaberGreen);
    		ItemStack greenPowerCrystalStack = new ItemStack(SuperDopeJediMod.greenPowerCrystal);
    		GameRegistry.addRecipe(new ItemStack(this), "x", "z", 'x', greenLightSaberStack, 'z', greenPowerCrystalStack);
		}
		
		if (this.color == "Purple") {
			
			// Recipe for creating a redLightSaber.
			ItemStack purpleLightSaberStack = new ItemStack(SuperDopeJediMod.lightSaberPurple);
    		ItemStack purplePowerCrystalStack = new ItemStack(SuperDopeJediMod.purplePowerCrystal);
    		GameRegistry.addRecipe(new ItemStack(this), "x", "z", 'x', purpleLightSaberStack, 'z', purplePowerCrystalStack);
		}
	}
}
