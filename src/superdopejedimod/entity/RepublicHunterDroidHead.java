package superdopesquad.superdopejedimod.entity;

import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;
import superdopesquad.superdopejedimod.SuperDopeJediMod;

public class RepublicHunterDroidHead extends RepublicBaseDroidHead {

	
	public RepublicHunterDroidHead(String unlocalizedName) {
		
		super(unlocalizedName, RepublicHunterDroidEntity.class);
	}
	
	
	public void registerRecipe() {
		
		// An 8-box of DroidParts, plus a mandalorianIron ingot, creates a RepublicHunterDroidHead.	
	    ItemStack itemStackDroidParts = new ItemStack(SuperDopeJediMod.entityManager.droidParts);
	    ItemStack itemStackMandalorianIronIngot = new ItemStack(SuperDopeJediMod.mandalorianIronIngot); 
	    ItemStack itemStackThis = new ItemStack(this);
	    
	    GameRegistry.addRecipe(itemStackThis, "xxx", "xyx", "xxx", 'x', itemStackDroidParts,
	    			'y', itemStackMandalorianIronIngot);	
	}
}
