package superdopesquad.superdopejedimod.weapon;

import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;
import superdopesquad.superdopejedimod.SuperDopeJediMod;
import superdopesquad.superdopejedimod.Utilities;
import superdopesquad.superdopejedimod.weapon.PlasmaShotEntityBase.PowerLevel;

public class BlasterCarbine extends BaseBlaster {

	
	public BlasterCarbine(String unlocalizedName) {
		
		super(unlocalizedName);

		// Unique style of this type of blaster:
		this.isInstantWeapon = true;
		this.powerLevel = PowerLevel.RIFLE;
		this.range = 10.0F;
	}

	
	public void registerRecipe() {
		
		ItemStack itemStackBlasterPartsOne = new ItemStack(SuperDopeJediMod.weaponManager.blasterParts);
		ItemStack itemStackMe = new ItemStack(this);
		ItemStack itemStackBlasterPartsMany = new ItemStack(SuperDopeJediMod.weaponManager.blasterParts, 5);
		
		// Many Blaster Parts create this weapon.
    	GameRegistry.addShapedRecipe(this.getRegistryName(), null, itemStackMe,  "xxx", "xx ", "   ", 'x', itemStackBlasterPartsOne);	
    	
    	// This weapon can be broken down into many Blaster Parts.
    	GameRegistry.addShapedRecipe(Utilities.GetRegistryNameRecycler(this), null, itemStackBlasterPartsMany, "x", 'x', itemStackMe);
	}
}
	