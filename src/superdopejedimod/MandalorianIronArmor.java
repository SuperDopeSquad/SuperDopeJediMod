package superdopesquad.superdopejedimod;


import net.minecraft.entity.Entity;
import net.minecraft.init.Items;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;


public class MandalorianIronArmor extends BaseArmor {
	
	
	public MandalorianIronArmor(EntityEquipmentSlot equipmentSlotIn, String nameInput) {
		
		super(SuperDopeJediMod.mandalorianIronArmorMaterial, equipmentSlotIn, nameInput);
	}
	
	
	public void registerRecipe() {
		
		ItemStack mandalorianIronIngotStack = new ItemStack(SuperDopeJediMod.mandalorianIronIngot);
		
		// Helmet.
    	GameRegistry.addRecipe(new ItemStack(SuperDopeJediMod.mandalorianIronHelmet), "xxx", "x x", 'x', mandalorianIronIngotStack);		
		// Chest.
    	GameRegistry.addRecipe(new ItemStack(SuperDopeJediMod.mandalorianIronChestplate), "x x", "xxx", "xxx", 'x', mandalorianIronIngotStack);
    	// Leggings.
    	GameRegistry.addRecipe(new ItemStack(SuperDopeJediMod.mandalorianIronLeggings), "xxx", "x x", "x x", 'x', mandalorianIronIngotStack);
    	// Boots.
    	GameRegistry.addRecipe(new ItemStack(SuperDopeJediMod.mandalorianIronBoots), "x x", "x x", 'x', mandalorianIronIngotStack);
    	// Shield.
    	//GameRegistry.addRecipe(new ItemStack(SuperDopeJediMod.mandalorianIronShield), "xxx", "xxx", " x ", 'x', mandalorianIronIngotStack);
	}
	
}
	
	