package superdopesquad.superdopejedimod;

import java.util.Random;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;


public class BrownSteelOre extends BaseBlock {

	protected BrownSteelOre(String unlocalizedName) {
		
		super(Material.ROCK, unlocalizedName);
	}
	
	
    public Item getItemDropped(int metadata, Random random, int fortune) {
        
    	return Item.getItemFromBlock(SuperDopeJediMod.brownSteel);
    }
}
