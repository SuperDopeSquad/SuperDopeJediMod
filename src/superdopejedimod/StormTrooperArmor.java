package superdopesquad.superdopejedimod;

import net.minecraft.init.Items;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class StormTrooperArmor extends BaseArmor{

public StormTrooperArmor(ArmorMaterial materialIn, int renderIndexIn, EntityEquipmentSlot equipmentSlotIn) {
		
		super(materialIn, renderIndexIn, equipmentSlotIn);

	}
	public StormTrooperArmor(EntityEquipmentSlot equipmentSlotIn, String nameInput) {
		
		super(SuperDopeJediMod.stormTrooperArmorMaterial, equipmentSlotIn, nameInput);
	}
	
	public void registerRecipe() {
		
		ItemStack quartzStack = new ItemStack(Items.QUARTZ);
		ItemStack chromateStack = new ItemStack(SuperDopeJediMod.chromateIngot);
		
		// Helmet.
    	GameRegistry.addRecipe(new ItemStack(SuperDopeJediMod.stormTrooperHelmet), "xxx", "y y", 'x', quartzStack, 'y', chromateStack);		
		// Chest.
    	GameRegistry.addRecipe(new ItemStack(SuperDopeJediMod.stormTrooperChestplate), "x x", "yxy", "xyx", 'x', quartzStack, 'y', chromateStack);
    	// Leggings.
    	GameRegistry.addRecipe(new ItemStack(SuperDopeJediMod.stormTrooperLeggings), "yxy", "x x", "y y", 'x', quartzStack, 'y', chromateStack);
    	// Boots.
    	GameRegistry.addRecipe(new ItemStack(SuperDopeJediMod.stormTrooperBoots), "x x", "y y", 'x', quartzStack, 'y', chromateStack);
    	// Shield.
    	//GameRegistry.addRecipe(new ItemStack(SuperDopeJediMod.mandalorianIronShield), "xxx", "xxx", " x ", 'x', mandalorianIronIngotStack);
	}
	
}
