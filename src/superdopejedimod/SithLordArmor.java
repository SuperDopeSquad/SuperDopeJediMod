package superdopesquad.superdopejedimod;

import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class SithLordArmor extends BaseArmor{
	public SithLordArmor(ArmorMaterial materialIn, int renderIndexIn, EntityEquipmentSlot equipmentSlotIn) {
		
		super(materialIn, renderIndexIn, equipmentSlotIn);

	}
	public SithLordArmor(EntityEquipmentSlot equipmentSlotIn, String nameInput) {
		
		super(SuperDopeJediMod.sithLordArmorMaterial, equipmentSlotIn, nameInput);
	}
	
	public void registerRecipe() {
		
		ItemStack rubyStack = new ItemStack(SuperDopeJediMod.ruby);
		
		// Helmet.
    	GameRegistry.addRecipe(new ItemStack(SuperDopeJediMod.sithLordHelmet), "xxx", "x x", 'x', rubyStack);		
		// Chest.
    	GameRegistry.addRecipe(new ItemStack(SuperDopeJediMod.sithLordChestplate), "x x", "xxx", "xxx", 'x', rubyStack);
    	// Leggings.
    	GameRegistry.addRecipe(new ItemStack(SuperDopeJediMod.sithLordLeggings), "xxx", "x x", "x x", 'x', rubyStack);
    	// Boots.
    	GameRegistry.addRecipe(new ItemStack(SuperDopeJediMod.sithLordBoots), "x x", "x x", 'x', rubyStack);
    	// Shield.
    	//GameRegistry.addRecipe(new ItemStack(SuperDopeJediMod.mandalorianIronShield), "xxx", "xxx", " x ", 'x', mandalorianIronIngotStack);
	}	
	
}
