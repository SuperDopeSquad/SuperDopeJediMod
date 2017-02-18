package superdopesquad.superdopejedimod;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class CompressedMetalPlate extends BaseItem{
	
public CompressedMetalPlate(String unlocalizedName) {
		
		super(unlocalizedName);
		
		this.setCreativeTab(CreativeTabs.MATERIALS);
	}

public void registerRecipe() {
	
	// Smelting 4 compressedMetalMesh will create 1 CompressedMetalPlate
	GameRegistry.addSmelting(SuperDopeJediMod.compressedMetalMesh, new ItemStack(SuperDopeJediMod.compressedMetalPlate), 1.0F);

	}


}
