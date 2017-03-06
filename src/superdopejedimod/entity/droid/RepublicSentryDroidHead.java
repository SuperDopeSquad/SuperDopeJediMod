package superdopesquad.superdopejedimod.entity.droid;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;
import superdopesquad.superdopejedimod.SuperDopeJediMod;

public class RepublicSentryDroidHead extends RepublicBaseDroidHead {

	
	public RepublicSentryDroidHead(String unlocalizedName) {
		
        super(unlocalizedName, RepublicSentryDroidEntity.class);
	}
	
	
	public void registerRecipe() {
		
		// An 8-box of DroidParts, plus an Iron ingot, creates a RepublicSentryDroidHead.	
	    ItemStack itemStackDroidParts = new ItemStack(SuperDopeJediMod.entityManager.droidParts);
	    ItemStack itemStackIronIngot = new ItemStack(Items.IRON_INGOT); 
	    ItemStack itemStackThis = new ItemStack(this);
	    
	    GameRegistry.addRecipe(itemStackThis, "xxx", "xyx", "xxx", 'x', itemStackDroidParts,
	    			'y', itemStackIronIngot);	
	}
}
