package superdopesquad.superdopejedimod;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.fml.common.registry.GameRegistry;


public class MandalorianIronSword extends BaseMeleeWeapon {

	
	public MandalorianIronSword(String unlocalizedName) {
		
		super(unlocalizedName, SuperDopeJediMod.mandalorianIronToolMaterial);
	}
	
	
	public void registerRecipe() {

    	ItemStack stickStack = new ItemStack(Items.STICK);
    	ItemStack mandalorianIronIngotStack = new ItemStack(SuperDopeJediMod.mandalorianIronIngot);
    	GameRegistry.addRecipe(new ItemStack(this), " x", " x", " y", 'x', mandalorianIronIngotStack, 'y', stickStack);
	}
}