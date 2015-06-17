package superdopejedimod;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemFood;


public class NourishmentCapsule extends ItemFood {

	
	public NourishmentCapsule() {
		
		//public ItemFood(int amount, float saturation, boolean isWolfFood)
		super(5, 5, false);
		
		this.setCreativeTab(CreativeTabs.tabFood);
		this.setUnlocalizedName("nourishmentCapsule");
	}
}
