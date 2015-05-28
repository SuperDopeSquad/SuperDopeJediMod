package superDopeJediMod;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;


public abstract class BaseItem extends Item {

	
	public BaseItem(String unlocalizedName) {
		
		super();
		
		this.setMaxStackSize(64);
		this.setCreativeTab(CreativeTabs.tabMisc);
		this.setUnlocalizedName(unlocalizedName);
	}
}
