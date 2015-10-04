package superdopesquad.superdopejedimod;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;


public class ChromateShard extends BaseItem{
	
	public ChromateShard(String unlocalizedName) {
		
		super(unlocalizedName);
		
		this.setCreativeTab(CreativeTabs.tabMaterials);
		
	}

	public void registerRecipe() {
		
		// Recipe for creating an Generator.
		ItemStack chromateIngotStack = new ItemStack(SuperDopeJediMod.chromateIngot);
		
		GameRegistry.addRecipe(new ItemStack(this, 1), 
				"***", 
				"*A*", 
				"***", 
				'A', chromateIngotStack);
			
		
		GameRegistry.addRecipe(new ItemStack(this, 1), 
				"***", 
				"***", 
				"*A*", 
				'A', chromateIngotStack);
		
		GameRegistry.addRecipe(new ItemStack(this, 1), 
				"***", 
				"***", 
				"A**", 
				'A', chromateIngotStack);
		
		GameRegistry.addRecipe(new ItemStack(this, 1), 
				"***", 
				"***", 
				"**A", 
				'A', chromateIngotStack);
		
		GameRegistry.addRecipe(new ItemStack(this, 1), 
				"***", 
				"**A", 
				"***", 
				'A', chromateIngotStack);
		
		GameRegistry.addRecipe(new ItemStack(this, 1), 
				"***", 
				"A**", 
				"***", 
				'A', chromateIngotStack);
		
		GameRegistry.addRecipe(new ItemStack(this, 1), 
				"A**", 
				"***", 
				"***", 
				'A', chromateIngotStack);
		
		GameRegistry.addRecipe(new ItemStack(this, 1), 
				"*A*", 
				"***", 
				"***", 
				'A', chromateIngotStack);
		
		GameRegistry.addRecipe(new ItemStack(this, 1), 
				"**A", 
				"***", 
				"***", 
				'A', chromateIngotStack);
		
		
	}	
	
}
