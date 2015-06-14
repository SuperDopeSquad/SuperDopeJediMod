package superDopeJediMod;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class LightSaber extends BaseItem {
	
	String color;
	

	public LightSaber(String unlocalizedName, String colorInput) {
		
		super(unlocalizedName);
		
		this.color = colorInput;
		
		this.setCreativeTab(CreativeTabs.tabCombat);
		//this.setMaxStackSize(64);
		//this.setCreativeTab(CreativeTabs.tabMisc);
		//this.setUnlocalizedName(unlocalizedName);
	}
}
