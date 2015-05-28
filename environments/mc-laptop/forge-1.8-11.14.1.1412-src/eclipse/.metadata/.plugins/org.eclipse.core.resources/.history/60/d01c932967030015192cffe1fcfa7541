package tutorial.generic;

import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;


public class GenericOre extends Block {

	public GenericOre(Material material) 
    {
            super(material);
            
            setHardness(4.0F); // 33% harder than diamond
            setStepSound(Block.soundTypePiston); // sounds got renamed, look in Block class for what blocks have what sounds
            //setBlockName("genericOre"); // changed in 1.7
            //setCreativeTab(CreativeTabs.tabBlock);
   
            setHarvestLevel("pickaxe", 3);
    }
	
	
	 //@Override
    public Item getItemDropped(int metadata, Random random, int fortune) {
        
    	//If the block's drop is an item.
    	return Generic.genericIngot;
    	
    	//If the block's drop is a block.
    	//return Item.getItemFromBlock(Generic.genericBlock);
    	
    	//If the block's drop is a vanilla item.
    	//return Item.getItemById(Id);
    	 
    	//If the block's drop is a vanilla block.
        //return Item.getItemFromBlock(Block.getBlockById(id));
    }
}
