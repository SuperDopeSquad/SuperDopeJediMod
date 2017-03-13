package superdopesquad.superdopejedimod.weapon;

import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;
import superdopesquad.superdopejedimod.SuperDopeJediMod;

public class BlasterSniper extends BaseBlaster {

	
	public BlasterSniper(String unlocalizedName) {
		
		super(unlocalizedName);
		
		// Unique style of this type of blaster:
		this.isInstantWeapon = false;
		this.damageAmount = 6.0F;
		this.range = 30.0F;
	}


	public void registerRecipe() {
		
		// Recipe for creating a GaffiStick.
		ItemStack itemStackBlasterParts = new ItemStack(SuperDopeJediMod.weaponManager.blasterParts);
    	GameRegistry.addRecipe(new ItemStack(this), "x x", "xx ", "   ", 'x', itemStackBlasterParts);	
	}
}
