package superdopesquad.superdopejedimod.weapon;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;
import superdopesquad.superdopejedimod.SuperDopeJediMod;

public class BlasterPistol extends BaseBlaster {

	
	public BlasterPistol(String unlocalizedName) {
		
		super(unlocalizedName);
		
		// Unique style of this type of blaster:
		this.isInstantWeapon = true;
		this.damageAmount = 2.0F;
		this.range = 10.0F;
	}

	
	@Override
	public boolean IsUseUnfriendlyBanned() {
	
		// By default, all blasters are banned from the unfriendly classes, namely, the force-wielding classes.
		// We let blaster pistols, however, be used by everyone.
		return false;
	}
	

	public void registerRecipe() {
		
		// Recipe for creating a GaffiStick.
		ItemStack itemStackBlasterParts = new ItemStack(SuperDopeJediMod.weaponManager.blasterParts);
    	GameRegistry.addRecipe(new ItemStack(this), "xx ", "x  ", "   ", 'x', itemStackBlasterParts);	
	}
}
