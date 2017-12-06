package superdopesquad.superdopejedimod.weapon;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;
import superdopesquad.superdopejedimod.SuperDopeJediMod;
import superdopesquad.superdopejedimod.Utilities;
import superdopesquad.superdopejedimod.weapon.PlasmaShotEntityBase.PowerLevel;

public class BlasterPistol extends BaseBlaster {

	
	public BlasterPistol(String unlocalizedName) {
		
		super(unlocalizedName);
		
		// Unique style of this type of blaster:
		this.isInstantWeapon = true;
		this.powerLevel = PowerLevel.STANDARD;
		this.range = 10.0F;
	}

	
	@Override
	public boolean IsUseUnfriendlyBanned() {
	
		// By default, all blasters are banned from the unfriendly classes, namely, the force-wielding classes.
		// We let blaster pistols, however, be used by everyone.
		return false;
	}
	

	public void registerRecipe() {
		
		ItemStack itemStackBlasterPartsOne = new ItemStack(SuperDopeJediMod.weaponManager.blasterParts);
		ItemStack itemStackMe = new ItemStack(this);
		ItemStack itemStackBlasterPartsMany = new ItemStack(SuperDopeJediMod.weaponManager.blasterParts, 3);
		
		// Many Blaster Parts create this weapon.
    	GameRegistry.addShapedRecipe(this.getRegistryName(), null, itemStackMe, "xx ", "x  ", "   ", 'x', itemStackBlasterPartsOne);	
    	
    	// This weapon can be broken down into many Blaster Parts.
    	GameRegistry.addShapedRecipe(Utilities.GetRegistryNameRecycler(this), null, itemStackBlasterPartsMany, "x", 'x', itemStackMe);
	}
}
