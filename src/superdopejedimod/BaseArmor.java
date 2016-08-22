package superdopesquad.superdopejedimod;

import net.minecraft.entity.Entity;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;


public class BaseArmor extends ItemArmor {
	
	
	public BaseArmor(ArmorMaterial materialIn, int renderIndexIn, EntityEquipmentSlot equipmentSlotIn) {
		
		super(materialIn, renderIndexIn, equipmentSlotIn);
		
		
	
	}
	
	
//	@Override
//	public String getArmorTexture()
//	
//	public String getArmorTexture(ItemStack stack, Entity entity, int slot, String type)
//	{
//	    return Main.MODID + ":textures/armor/" + this.textureName + "_" + (this.armorType == 2 ? "2" : "1") + ".png";
//	}

}
