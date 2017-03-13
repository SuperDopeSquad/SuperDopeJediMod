package superdopesquad.superdopejedimod.weapon;

import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;
import superdopesquad.superdopejedimod.SuperDopeJediMod;

public class BlasterRifle extends BaseBlaster {

	
	public BlasterRifle(String unlocalizedName) {
		
		super(unlocalizedName);
		
		// Unique style of this type of blaster:
		this.isInstantWeapon = true;
		this.damageAmount = 4.0F;
		this.range = 20.0F;
	}


	public void registerRecipe() {
		
		// Recipe for creating a GaffiStick.
		ItemStack itemStackBlasterParts = new ItemStack(SuperDopeJediMod.weaponManager.blasterParts);
    	GameRegistry.addRecipe(new ItemStack(this), "xxx", "x  ", "   ", 'x', itemStackBlasterParts);	
	}
}
