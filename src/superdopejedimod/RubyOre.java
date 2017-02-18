package superdopesquad.superdopejedimod;

import java.util.Random;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class RubyOre extends BaseBlock{
	
	public RubyOre(String unlocalizedName) {
		
		super(Material.ROCK, unlocalizedName);
		
		this.setHardness(15.0F);
		
		this.setHarvestLevel("pickaxe", 2);
		
		this.setCreativeTab(CreativeTabs.BUILDING_BLOCKS);
		
	}

	
	public Item getItemDropped(int metadata, Random random, int fortune) {
        
		return Item.getItemFromBlock(SuperDopeJediMod.rubyOre);
	}
	
	public void registerRecipe() {
		
		// Smelting a rubyOre will create 1 Ruby
		
		GameRegistry.addSmelting(SuperDopeJediMod.rubyOre, new ItemStack(SuperDopeJediMod.ruby), 1.0F);
	
	}
	
	public void generateSurface(World world, Random random, int i, int j) {
			
			int maxVeinSize = 4;
			int minY = 0;
			int maxY = 12;
			int chancesPerChunk = 5; // A chunk is 16 blocks wide, 16 blocks long, and 256 blocks deep, which is 65,536 blocks total.
			
			SuperDopeJediMod.superDopeWorldGenerator.addOreSpawn(this, world, random, i, j, 16, 16, maxVeinSize, chancesPerChunk, minY, maxY); 
		}
	
}
