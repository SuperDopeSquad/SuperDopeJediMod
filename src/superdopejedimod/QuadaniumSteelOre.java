package superdopesquad.superdopejedimod;


import java.util.Random;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;


public class QuadaniumSteelOre extends BaseBlock {

	protected QuadaniumSteelOre(String unlocalizedName) {
		
		super(Material.rock, unlocalizedName);
	}
	
	
    public Item getItemDropped(int metadata, Random random, int fortune) {
        
    	return Item.getItemFromBlock(SuperDopeJediMod.quadaniumSteel);
    }
  
    


}
