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
		
		ItemStack itemStackBlasterPartsOne = new ItemStack(SuperDopeJediMod.weaponManager.blasterParts);
		ItemStack itemStackMe = new ItemStack(this);
		ItemStack itemStackBlasterPartsMany = new ItemStack(SuperDopeJediMod.weaponManager.blasterParts, 4);
		
		// Many Blaster Parts create this weapon.
    	GameRegistry.addShapedRecipe(getRegistryName(), null, itemStackMe,  "x x", "xx ", "   ", 'x', itemStackBlasterPartsOne);	
    	
    	// This weapon can be broken down into many Blaster Parts.
    	GameRegistry.addShapedRecipe(getRegistryName(), null, itemStackBlasterPartsMany, "x", 'x', itemStackMe);
	}
}
