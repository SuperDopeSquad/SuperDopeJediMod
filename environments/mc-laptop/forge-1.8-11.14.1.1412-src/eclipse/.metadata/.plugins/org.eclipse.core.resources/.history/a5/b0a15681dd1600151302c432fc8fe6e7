package superdopejedimod;

import java.util.Random;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;


public class PowerCrystalOre extends BaseBlock {
	
	String color;
	
	protected PowerCrystalOre(String unlocalizedName, String colorInput) {
		
		super(Material.rock, unlocalizedName);
		
		this.color = colorInput;
		
		// Note to Bryn: i commented the below line out, because the parent class of PowerCrystalOre, 
		// which is BaseBlock, already sets the creative tab for all new blocks to be
		// "tabBlock".  You only need to call setCreativeTab if you want to override this
		// and put the block in a different tab.
		//
		// this.setCreativeTab(CreativeTabs.tabBlock);
	}
	
	
	public Item getItemDropped(int metadata, Random random, int fortune) {
        
		Item obj;
		
		if (this.color == "Red") {
			obj = SuperDopeJediMod.redPowerCrystal;
		}
		else if (this.color == "Green") {
			obj = SuperDopeJediMod.greenPowerCrystal;
		}
		else if (this.color == "Blue") {
			obj = SuperDopeJediMod.bluePowerCrystal;
		}
		else if (this.color == "Purple") {
			obj = SuperDopeJediMod.purplePowerCrystal;
		}
		else {
			obj = SuperDopeJediMod.greenPowerCrystal;
		}
		
    	return obj;
    	
    	
    }
	
	
	

}
