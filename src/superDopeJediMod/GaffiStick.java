package superDopeJediMod;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;


public class GaffiStick extends BaseItem {

	
	public GaffiStick(String unlocalizedName) {
		
		super(unlocalizedName);
		
		//this.setTextureName("superDopeJediMod:gaffiStick");
		//this.setMaxStackSize(64);
		//this.setCreativeTab(CreativeTabs.tabMisc);
		//this.setUnlocalizedName(unlocalizedName);
	}
	
	
	public void registerRecipe() {
		
		// Recipe for creating a GaffiStick.
    	ItemStack stickStack = new ItemStack(Items.stick);
    	ItemStack ironIngotStack = new ItemStack(Items.iron_ingot);
    	GameRegistry.addRecipe(new ItemStack(this), "xx", " y", " x", 'x', ironIngotStack, 'y', stickStack);
	}
	
}
