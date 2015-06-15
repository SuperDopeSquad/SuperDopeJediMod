package superDopeJediMod;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;


public class PowerCrystal extends BaseItem {
	
	String color;
	
	public PowerCrystal(String unlocalizedName, String colorInput) {
		
		super(unlocalizedName);
		
		this.color = colorInput;
		
		this.setCreativeTab(CreativeTabs.tabMaterials);
		
		//this.setMaxStackSize(64);
		//this.setCreativeTab(CreativeTabs.tabMisc);
		//this.setUnlocalizedName(unlocalizedName);
	}
	
	
	
	
	
	

}
