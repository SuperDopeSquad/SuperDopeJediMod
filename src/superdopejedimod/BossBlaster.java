package superdopesquad.superdopejedimod;

import net.minecraft.block.material.Material;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;
import superdopesquad.superdopejedimod.weapon.BaseRangedWeapon;


public class BossBlaster extends BaseRangedWeapon {

	public BossBlaster(String unlocalizedName) {
		
		super(unlocalizedName);
		
		
	}

	
	public void registerRecipe() {
		// Recipe for creating a GaffiStick.
    	ItemStack stickStack = new ItemStack(Items.STICK);
    	ItemStack ironIngotStack = new ItemStack(Items.IRON_INGOT);
    	GameRegistry.addRecipe(new ItemStack(this), "xx", " y", " x", 'x', ironIngotStack, 'y', stickStack);
	}
}
