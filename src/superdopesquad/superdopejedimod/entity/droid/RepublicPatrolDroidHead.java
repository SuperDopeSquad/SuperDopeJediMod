package superdopesquad.superdopejedimod.entity.droid;

import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;
import superdopesquad.superdopejedimod.SuperDopeJediMod;

public class RepublicPatrolDroidHead extends RepublicBaseDroidHead  {

	
	public RepublicPatrolDroidHead(String unlocalizedName) {
		
		super(unlocalizedName, RepublicPatrolDroidEntity.class);
	}
	
	
	public void registerRecipe() {
		
		// An 8-box of DroidParts, plus a quadaniumSteel ingot, creates a RepublicPatrolDroidHead.	
	    ItemStack itemStackDroidParts = new ItemStack(SuperDopeJediMod.entityManager.droidParts);
	    ItemStack itemStackQuadaniumSteelIngot = new ItemStack(SuperDopeJediMod.quadaniumSteelIngot); 
	    ItemStack itemStackThis = new ItemStack(this);
	    ItemStack itemStackDroidPartsMany = new ItemStack(SuperDopeJediMod.entityManager.droidParts, 8);
	    
	    GameRegistry.addShapedRecipe(getRegistryName(), null, itemStackThis, "xxx", "xyx", "xxx", 'x', itemStackDroidParts,
	    			'y', itemStackQuadaniumSteelIngot);	
	    GameRegistry.addShapedRecipe(getRegistryName(), null, itemStackDroidPartsMany, "x", 'x', itemStackThis);	
	}
}
