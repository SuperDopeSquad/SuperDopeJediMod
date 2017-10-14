package superdopesquad.superdopejedimod;

import java.util.Random;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;


public class CompressedMetalBits extends BaseItem{
	
public CompressedMetalBits(String unlocalizedName) {
		
		super(unlocalizedName);
		
		this.setCreativeTab(CreativeTabs.MATERIALS);
	}





public void registerRecipe() {

	// MC: Note to Bryn: in the latest version of Forge,  using "*" in your recipe pattern crashes. 
	// I'm commenting it out for now, so these recipes won't be generated until we fix this.

//	// Recipe for creating a Compressed metal bit.
//	ItemStack chromateIngotStack = new ItemStack(SuperDopeJediMod.chromateIngot);
//	ItemStack mandalorianIronIngotStack = new ItemStack(SuperDopeJediMod.mandalorianIronIngot);
//	ItemStack quadaniumSteelIngotStack = new ItemStack(SuperDopeJediMod.quadaniumSteelIngot);
//	GameRegistry.addShapedRecipe(this.getRegistryName(), null, new ItemStack(this, 1), 
//			"***", 
//			"*A*", 
//			"***", 
//			'A', chromateIngotStack);
//
//	GameRegistry.addShapedRecipe(this.getRegistryName(), null, new ItemStack(this, 1), 
//			"***", 
//			"*B*", 
//			"***", 
//			'B', mandalorianIronIngotStack);
//
//	GameRegistry.addShapedRecipe(this.getRegistryName(), null, new ItemStack(this, 1), 
//			"***", 
//			"*C*", 
//			"***", 
//			'C', quadaniumSteelIngotStack);
	}
}