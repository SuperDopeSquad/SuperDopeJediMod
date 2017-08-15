package superdopesquad.superdopejedimod;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;


public class ChromateShard extends BaseItem{
	
	public ChromateShard(String unlocalizedName) {
		
		super(unlocalizedName);
		
		this.setCreativeTab(CreativeTabs.MATERIALS);
		
	}

	public void registerRecipe() {
		
//		// Recipe for creating a Chromate Shard.
//		ItemStack compressedMetalBitsStack = new ItemStack(SuperDopeJediMod.compressedMetalBits);
//		
//		GameRegistry.addShapedRecipe(getRegistryName(), null, new ItemStack(this, 1), 
//				"***", 
//				"*A*", 
//				"*A*", 
//				'A', compressedMetalBitsStack);
//			
//		GameRegistry.addShapedRecipe(getRegistryName(), null, new ItemStack(this, 1), 
//				"***", 
//				"**A", 
//				"**A", 
//				'A', compressedMetalBitsStack);
//		
//		GameRegistry.addShapedRecipe(getRegistryName(), null, new ItemStack(this, 1), 
//				"*A*", 
//				"*A*", 
//				"***", 
//				'A', compressedMetalBitsStack);
//		
//		GameRegistry.addShapedRecipe(this.getRegistryName(), null, new ItemStack(this, 1), 
//				"**A", 
//				"**A", 
//				"***", 
//				'A', compressedMetalBitsStack);
//		
//		GameRegistry.addShapedRecipe(this.getRegistryName(), null, new ItemStack(this, 1), 
//				"A**", 
//				"A**", 
//				"***", 
//				'A', compressedMetalBitsStack);
//		
//		GameRegistry.addShapedRecipe(this.getRegistryName(), null, new ItemStack(this, 1), 
//				"***", 
//				"A**", 
//				"A**", 
//				'A', compressedMetalBitsStack);
//		
	}	
	
}
