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


public class ElectricFluxOre extends BaseBlock{

	public ElectricFluxOre(String unlocalizedName) {
		
		super(Material.ROCK, unlocalizedName);
		
		this.setHardness(7.0F);
		
		this.setHarvestLevel("pickaxe", 2);
		
		//this.setStepSound(soundTypeStone);
		this.setSoundType(blockSoundType.STONE);
	}
	
	public Item getItemDropped(int metadata, Random random, int fortune) {
        
		return Item.getItemFromBlock(SuperDopeJediMod.electricFluxOre);
	}
	
public void registerRecipe() {
		
		// Smelting a electricFluxOre will create 1 electricFluxIngot.
		GameRegistry.addSmelting(SuperDopeJediMod.electricFluxOre, new ItemStack(SuperDopeJediMod.electricFluxIngot), 1.0F);
	}
	
	public void generateSurface(World world, Random random, int i, int j) {
		
		int maxVeinSize = 6;
		int minY = 0;
		int maxY = 40;
		int chancesPerChunk = 14; // A chunk is 16 blocks wide, 16 blocks long, and 256 blocks deep, which is 65,536 blocks total.
		
		SuperDopeJediMod.superDopeWorldGenerator.addOreSpawn(this, world, random, i, j, 16, 16, maxVeinSize, chancesPerChunk, minY, maxY); 
	}
	
}
