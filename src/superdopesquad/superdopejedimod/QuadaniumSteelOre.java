package superdopesquad.superdopejedimod;


import java.util.Random;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.world.World;


public class QuadaniumSteelOre extends BaseBlock {

	
	protected QuadaniumSteelOre(String unlocalizedName) {
		
		super(Material.ROCK, unlocalizedName);
		
		this.setHardness(5.0F);
		
		this.setHarvestLevel("pickaxe", 2);
	}
	
	
    public Item getItemDropped(int metadata, Random random, int fortune) {
        
    	return Item.getItemFromBlock(SuperDopeJediMod.quadaniumSteelOre);
    }
  
    
    public void generateSurface(World world, Random random, int i, int j) {
		
		int maxVeinSize = 16;
		int minY = 0;
		int maxY = 60;
		int chancesPerChunk = 64; // A chunk is 16 blocks wide, 16 blocks long, and 256 blocks deep, which is 65,536 blocks total.
		
		//SuperDopeJediMod.superDopeWorldGenerator.
		SuperDopeJediMod.superDopeWorldGenerator.addOreSpawn(this, world, random, i, j, 16, 16, maxVeinSize, chancesPerChunk, minY, maxY); 
	}
}
