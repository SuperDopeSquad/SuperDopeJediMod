package superdopesquad.superdopejedimod;

import java.util.Random;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class CompressedMetalMesh extends BaseItem{

	public CompressedMetalMesh(String unlocalizedName) {
		
			super(unlocalizedName);
		
			this.setCreativeTab(CreativeTabs.MATERIALS);
	}
	
public void registerRecipe() {
		
		// Recipe for creating an Generator.
		ItemStack bitsOfCompressedMetalMeshStack = new ItemStack(SuperDopeJediMod.bitsOfCompressedMetalMesh);
		
		GameRegistry.addRecipe(new ItemStack(this, 1), 
				"***", 
				"***", 
				"AA*", 
				'A', bitsOfCompressedMetalMeshStack);
		
		GameRegistry.addRecipe(new ItemStack(this, 1), 
				"***", 
				"*AA", 
				"***", 
				'A', bitsOfCompressedMetalMeshStack);
		
		GameRegistry.addRecipe(new ItemStack(this, 1), 
				"AA*", 
				"***", 
				"***", 
				'A', bitsOfCompressedMetalMeshStack);
		
		GameRegistry.addRecipe(new ItemStack(this, 1), 
				"***", 
				"AA*", 
				"***", 
				'A', bitsOfCompressedMetalMeshStack);
		
		GameRegistry.addRecipe(new ItemStack(this, 1), 
				"***", 
				"***", 
				"*AA", 
				'A', bitsOfCompressedMetalMeshStack);
		
		GameRegistry.addRecipe(new ItemStack(this, 1), 
				"*AA", 
				"***", 
				"***", 
				'A', bitsOfCompressedMetalMeshStack);
		
	}
	
}
