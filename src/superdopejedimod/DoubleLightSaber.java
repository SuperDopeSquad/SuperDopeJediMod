package superdopejedimod;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class DoubleLightSaber extends BaseItem {
	
	String color;
	
	
	public DoubleLightSaber(String unlocalizedName, String colorInput) {
		
		super(unlocalizedName);
		
		this.setMaxStackSize(1);
		
		this.color = colorInput;
		
		this.setCreativeTab(CreativeTabs.tabCombat);
		//this.setMaxStackSize(64);
		//this.setCreativeTab(CreativeTabs.tabMisc);
		//this.setUnlocalizedName(unlocalizedName);
	}

}
