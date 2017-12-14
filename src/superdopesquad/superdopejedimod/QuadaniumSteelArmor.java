package superdopesquad.superdopejedimod;


import net.minecraft.entity.Entity;
import net.minecraft.init.Items;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;


public class QuadaniumSteelArmor extends BaseArmor {
	
	
	public QuadaniumSteelArmor(EntityEquipmentSlot equipmentSlotIn, String nameInput) {
		
		super(SuperDopeJediMod.quadaniumSteelArmorMaterial, equipmentSlotIn, nameInput);
	}
	
	
	public void registerRecipe() {
		
		ItemStack quadaniumSteelIngotStack = new ItemStack(SuperDopeJediMod.quadaniumSteelIngot);
		
		// Helmet.
    	GameRegistry.addShapedRecipe(Utilities.GetRegistryNameHelmet(this), null, new ItemStack(SuperDopeJediMod.quadaniumSteelHelmet), "xxx", "x x", 'x', quadaniumSteelIngotStack);		
		// Chest.
    	GameRegistry.addShapedRecipe(Utilities.GetRegistryNameChestplate(this), null, new ItemStack(SuperDopeJediMod.quadaniumSteelChestplate), "x x", "xxx", "xxx", 'x', quadaniumSteelIngotStack);
    	// Leggings.
    	GameRegistry.addShapedRecipe(Utilities.GetRegistryNameLeggings(this), null, new ItemStack(SuperDopeJediMod.quadaniumSteelLeggings), "xxx", "x x", "x x", 'x', quadaniumSteelIngotStack);
    	// Boots.
    	GameRegistry.addShapedRecipe(Utilities.GetRegistryNameBoots(this), null, new ItemStack(SuperDopeJediMod.quadaniumSteelBoots), "x x", "x x", 'x', quadaniumSteelIngotStack);
	}
}
	