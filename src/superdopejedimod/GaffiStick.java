package superdopesquad.superdopejedimod;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.fml.common.registry.GameRegistry;



public class GaffiStick extends BaseMeleeWeapon {

	
	public GaffiStick(String unlocalizedName) {
		
		super(unlocalizedName, SuperDopeJediMod.gaffiStickMaterial);
		
		setCreativeTab(CreativeTabs.COMBAT);
	}
	
	
	public void registerRecipe() {
		
		// Recipe for creating a GaffiStick.
    	ItemStack stickStack = new ItemStack(Items.STICK);
    	ItemStack ironIngotStack = new ItemStack(Items.IRON_INGOT);
    	GameRegistry.addRecipe(new ItemStack(this), "xx", " y", " x", 'x', ironIngotStack, 'y', stickStack);
    	
    	// Smelting a GaffiStick will create 1 IronIngot.
    	GameRegistry.addSmelting(this, ironIngotStack, 1.0F);		
	}
}
