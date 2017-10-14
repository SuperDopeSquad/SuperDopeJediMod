package superdopesquad.superdopejedimod;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class BitsOfCompressedMetalMesh extends BaseItem{

public BitsOfCompressedMetalMesh(String unlocalizedName) {
		
		super(unlocalizedName);
		
		this.setCreativeTab(CreativeTabs.MATERIALS);
	}
	
public void registerRecipe() {
	
//	// Recipe for creating a Compressed metal bit.
//	ItemStack chromateShardStack = new ItemStack(SuperDopeJediMod.chromateShard);
//	GameRegistry.addShapedRecipe(getRegistryName(), null, new ItemStack(this, 1), 
//			"***", 
//			"*A*", 
//			"*A*", 
//			'A', chromateShardStack);
//	
	}

}
