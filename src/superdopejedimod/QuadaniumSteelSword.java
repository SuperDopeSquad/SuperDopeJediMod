package superdopesquad.superdopejedimod;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.fml.common.registry.GameRegistry;



public class QuadaniumSteelSword extends BaseMeleeWeapon {

	
	public QuadaniumSteelSword(String unlocalizedName) {
		
		super(unlocalizedName, SuperDopeJediMod.quadaniumSteelToolMaterial);
	}
	
	
	public void registerRecipe() {

    	ItemStack stickStack = new ItemStack(Items.STICK);
    	ItemStack quadaniumSteelIngotStack = new ItemStack(SuperDopeJediMod.quadaniumSteelIngot);
    	GameRegistry.addRecipe(new ItemStack(this), " x", " x", " y", 'x', quadaniumSteelIngotStack, 'y', stickStack);
	}
	
}