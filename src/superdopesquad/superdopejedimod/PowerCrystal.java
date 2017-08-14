package superdopesquad.superdopejedimod;


import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;


public class PowerCrystal extends BaseItem {
	
	String color;
	
	public PowerCrystal(String unlocalizedName, String colorInput) {
		
		super(unlocalizedName);
		
		this.color = colorInput;
		
		this.setCreativeTab(CreativeTabs.MATERIALS);
		
		//this.setMaxStackSize(64);
		//this.setCreativeTab(CreativeTabs.tabMisc);
		//this.setUnlocalizedName(unlocalizedName);
	}
	
	public void registerRecipe() {
		
		if (this.color == "Red") {
		
			// Recipe for creating a redLightSaber.
			ItemStack bluePowerCrystalStack = new ItemStack(SuperDopeJediMod.bluePowerCrystal);
    		ItemStack slimeballStack = new ItemStack(Items.SLIME_BALL);
    		ItemStack redstoneStack = new ItemStack(Items.REDSTONE);
    		ItemStack diamondStack = new ItemStack(Items.DIAMOND);
    		ItemStack bookStack = new ItemStack(Items.BOOK);
    		ItemStack greenPowerCrystalStack = new ItemStack(SuperDopeJediMod.greenPowerCrystal);
    		ItemStack purplePowerCrystalStack = new ItemStack(SuperDopeJediMod.purplePowerCrystal);
    		ItemStack redPowerCrystalStack = new ItemStack(SuperDopeJediMod.redPowerCrystal);

    		GameRegistry.addShapedRecipe(this.getRegistryName(), null, new ItemStack(this), "yyy", "yxy", "yyy", 'x', bluePowerCrystalStack, 'y', redstoneStack);
    		GameRegistry.addShapedRecipe(this.getRegistryName(), null, new ItemStack(this), "yyy", "yxy", "yyy", 'x', greenPowerCrystalStack, 'y', redstoneStack);
    		GameRegistry.addShapedRecipe(this.getRegistryName(), null, new ItemStack(this), "yyy", "yxy", "yyy", 'x', purplePowerCrystalStack, 'y', redstoneStack);

    		
		} 
	
		if (this.color == "Blue") {
			
			// Recipe for creating a redLightSaber.
			ItemStack bluePowerCrystalStack = new ItemStack(SuperDopeJediMod.bluePowerCrystal);
    		ItemStack slimeballStack = new ItemStack(Items.SLIME_BALL);
    		ItemStack redstoneStack = new ItemStack(Items.REDSTONE);
    		ItemStack diamondStack = new ItemStack(Items.DIAMOND);
    		ItemStack bookStack = new ItemStack(Items.BOOK);
    		ItemStack greenPowerCrystalStack = new ItemStack(SuperDopeJediMod.greenPowerCrystal);
    		ItemStack purplePowerCrystalStack = new ItemStack(SuperDopeJediMod.purplePowerCrystal);
    		ItemStack redPowerCrystalStack = new ItemStack(SuperDopeJediMod.redPowerCrystal);

    		GameRegistry.addShapedRecipe(this.getRegistryName(), null, new ItemStack(this), "yyy", "yxy", "yyy", 'x', redPowerCrystalStack, 'y', diamondStack);
    		GameRegistry.addShapedRecipe(this.getRegistryName(), null, new ItemStack(this), "yyy", "yxy", "yyy", 'x', greenPowerCrystalStack, 'y', diamondStack);
    		GameRegistry.addShapedRecipe(this.getRegistryName(), null, new ItemStack(this), "yyy", "yxy", "yyy", 'x', purplePowerCrystalStack, 'y', diamondStack);

    		
		} 
	
		
		if (this.color == "Green") {
			
			// Recipe for creating a redLightSaber.
			ItemStack bluePowerCrystalStack = new ItemStack(SuperDopeJediMod.bluePowerCrystal);
    		ItemStack slimeballStack = new ItemStack(Items.SLIME_BALL);
    		ItemStack redstoneStack = new ItemStack(Items.REDSTONE);
    		ItemStack diamondStack = new ItemStack(Items.DIAMOND);
    		ItemStack bookStack = new ItemStack(Items.BOOK);
    		ItemStack greenPowerCrystalStack = new ItemStack(SuperDopeJediMod.greenPowerCrystal);
    		ItemStack purplePowerCrystalStack = new ItemStack(SuperDopeJediMod.purplePowerCrystal);
    		ItemStack redPowerCrystalStack = new ItemStack(SuperDopeJediMod.redPowerCrystal);

    		GameRegistry.addShapedRecipe(getRegistryName(), null, new ItemStack(this), "yyy", "yxy", "yyy", 'x', bluePowerCrystalStack, 'y', slimeballStack);
    		GameRegistry.addShapedRecipe(getRegistryName(), null, new ItemStack(this), "yyy", "yxy", "yyy", 'x', redPowerCrystalStack, 'y', slimeballStack);
    		GameRegistry.addShapedRecipe(getRegistryName(), null, new ItemStack(this), "yyy", "yxy", "yyy", 'x', purplePowerCrystalStack, 'y', slimeballStack);

    		
		} 
		
		
		if (this.color == "Purple") {
			
			// Recipe for creating a redLightSaber.
			ItemStack bluePowerCrystalStack = new ItemStack(SuperDopeJediMod.bluePowerCrystal);
    		ItemStack slimeballStack = new ItemStack(Items.SLIME_BALL);
    		ItemStack redstoneStack = new ItemStack(Items.REDSTONE);
    		ItemStack diamondStack = new ItemStack(Items.DIAMOND);
    		ItemStack bookStack = new ItemStack(Items.BOOK);
    		ItemStack greenPowerCrystalStack = new ItemStack(SuperDopeJediMod.greenPowerCrystal);
    		ItemStack purplePowerCrystalStack = new ItemStack(SuperDopeJediMod.purplePowerCrystal);
    		ItemStack redPowerCrystalStack = new ItemStack(SuperDopeJediMod.redPowerCrystal);

    		GameRegistry.addShapedRecipe(this.getRegistryName(), null, new ItemStack(this), "yyy", "yxy", "yyy", 'x', bluePowerCrystalStack, 'y', bookStack);
    		GameRegistry.addShapedRecipe(this.getRegistryName(), null, new ItemStack(this), "yyy", "yxy", "yyy", 'x', greenPowerCrystalStack, 'y', bookStack);
    		GameRegistry.addShapedRecipe(this.getRegistryName(), null, new ItemStack(this), "yyy", "yxy", "yyy", 'x', redPowerCrystalStack, 'y', bookStack);

    		
		} 
		
	}

}
