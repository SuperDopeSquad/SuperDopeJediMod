package superdopesquad.superdopejedimod;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class LightSaber extends BaseMeleeWeapon {
	
	String color;
	

	public LightSaber(String unlocalizedName, String colorInput) {
		
		super(unlocalizedName, SuperDopeJediMod.powerCrystalMaterial);
		
		this.setMaxStackSize(1);
		
		this.color = colorInput;
		
		this.setCreativeTab(CreativeTabs.tabCombat);
		//this.setMaxStackSize(64);
		//this.setCreativeTab(CreativeTabs.tabMisc);
		//this.setUnlocalizedName(unlocalizedName);
		
		
	}
	
	
	public void registerRecipe() {
		
		if (this.color == "Red") {
		
			// Recipe for creating a redLightSaber.
			ItemStack redPowerCrystalStack = new ItemStack(SuperDopeJediMod.redPowerCrystal);
    		ItemStack ironIngotStack = new ItemStack(Items.iron_ingot);
    		ItemStack stoneButtonStack = new ItemStack(Blocks.stone_button);
    		GameRegistry.addRecipe(new ItemStack(this), "x", "y", "z", 'x', redPowerCrystalStack, 'y', stoneButtonStack, 'z', ironIngotStack);
		}
		
		if (this.color == "Blue") {
			
			// Recipe for creating a blueLightSaber.
			ItemStack bluePowerCrystalStack = new ItemStack(SuperDopeJediMod.bluePowerCrystal);
    		ItemStack ironIngotStack = new ItemStack(Items.iron_ingot);
    		ItemStack stoneButtonStack = new ItemStack(Blocks.stone_button);
    		GameRegistry.addRecipe(new ItemStack(this), "x", "y", "z", 'x', bluePowerCrystalStack, 'y', stoneButtonStack, 'z', ironIngotStack);
		}
		
		if (this.color == "Green") {
			
			// Recipe for creating a greenLightSaber.
			ItemStack greenPowerCrystalStack = new ItemStack(SuperDopeJediMod.greenPowerCrystal);
    		ItemStack ironIngotStack = new ItemStack(Items.iron_ingot);
    		ItemStack stoneButtonStack = new ItemStack(Blocks.stone_button);
    		GameRegistry.addRecipe(new ItemStack(this), "x", "y", "z", 'x', greenPowerCrystalStack, 'y', stoneButtonStack, 'z', ironIngotStack);
    		
		}
		
		if (this.color == "Purple") {
			
			// Recipe for creating a purpleLightSaber.
			ItemStack purplePowerCrystalStack = new ItemStack(SuperDopeJediMod.purplePowerCrystal);
    		ItemStack ironIngotStack = new ItemStack(Items.iron_ingot);
    		ItemStack stoneButtonStack = new ItemStack(Blocks.stone_button);
    		GameRegistry.addRecipe(new ItemStack(this), "x", "y", "z", 'x', purplePowerCrystalStack, 'y', stoneButtonStack, 'z', ironIngotStack);
    		
		}
	}

}
