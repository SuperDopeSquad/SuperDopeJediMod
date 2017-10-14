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

public class ChromateOre extends BaseBlock{
	
	public ChromateOre(String unlocalizedName) {
		
		super(Material.IRON, unlocalizedName);
		
		this.setHardness(7.0F);
		
		this.setHarvestLevel("pickaxe", 2);
		
		this.setCreativeTab(CreativeTabs.BUILDING_BLOCKS);
	}

	
	public Item getItemDropped(int metadata, Random random, int fortune) {
	        
		return Item.getItemFromBlock(SuperDopeJediMod.chromateOre);
	}
	
	public void registerRecipe() {
		
		// Smelting a ChromateOre will create 1 Chromate Ingot
		
		GameRegistry.addSmelting(SuperDopeJediMod.chromateOre, new ItemStack(SuperDopeJediMod.chromateIngot), 1.0F);
	
	}
	
public void generateSurface(World world, Random random, int i, int j) {
		
		int maxVeinSize = 6;
		int minY = 0;
		int maxY = 32;
		int chancesPerChunk = 16; // A chunk is 16 blocks wide, 16 blocks long, and 256 blocks deep, which is 65,536 blocks total.
		
		SuperDopeJediMod.superDopeWorldGenerator.addOreSpawn(this, world, random, i, j, 16, 16, maxVeinSize, chancesPerChunk, minY, maxY); 
	}
	
}
