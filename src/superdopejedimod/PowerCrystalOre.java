package superdopesquad.superdopejedimod;

import java.util.Random;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;


public class PowerCrystalOre extends BaseBlock {
	
	String color;
	
	
	protected PowerCrystalOre(String unlocalizedName, String colorInput) {
		
		super(Material.ROCK, unlocalizedName);
		
		this.color = colorInput;
		
		this.setHardness(27.0F);
		
		this.setHarvestLevel("pickaxe", 3);
		
		this.setLightLevel(0.9F);
		
		//this.setStepSound(soundTypeMetal);
		this.setSoundType(blockSoundType.METAL);
	}
	
	
	private Item getCorrectCrystal(String color) {
		
		Item obj = null;
		
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
	
	
	public Item getItemDropped(int metadata, Random random, int fortune) {
        
		return this.getCorrectCrystal(this.color);
//		Item obj = null;
//		
//		if (this.color == "Red") {
//			obj = SuperDopeJediMod.redPowerCrystal;
//		}
//		else if (this.color == "Green") {
//			obj = SuperDopeJediMod.greenPowerCrystal;
//		}
//		else if (this.color == "Blue") {
//			obj = SuperDopeJediMod.bluePowerCrystal;
//		}
//		else if (this.color == "Purple") {
//			obj = SuperDopeJediMod.purplePowerCrystal;
//		} 
//		else {
//			obj = SuperDopeJediMod.greenPowerCrystal;
//		}
//		
//    	return obj;	
    }
	
	
	public void registerRecipe() {
		
		Item obj = this.getCorrectCrystal(this.color);
		if (obj != null) {
			GameRegistry.addSmelting(this, new ItemStack(obj), 1.0F);
		}
		
		// Smelting a PowerCrystalOre will create 1 PowerCrystal
		//GameRegistry.addSmelting(SuperDopeJediMod.redPowerCrystalOre, new ItemStack(SuperDopeJediMod.redPowerCrystal), 1.0F);
		////GameRegistry.addSmelting(SuperDopeJediMod.bluePowerCrystalOre, new ItemStack(SuperDopeJediMod.bluePowerCrystal), 1.0F);
		//GameRegistry.addSmelting(SuperDopeJediMod.greenPowerCrystalOre, new ItemStack(SuperDopeJediMod.greenPowerCrystal), 1.0F);
		//GameRegistry.addSmelting(SuperDopeJediMod.purplePowerCrystalOre, new ItemStack(SuperDopeJediMod.purplePowerCrystal), 1.0F);
	}
	
	
	public void generateSurface(World world, Random random, int i, int j) {
	
		int maxVeinSize = 3;
		int minY = 0;
		int maxY = 12;
		int chancesPerChunk = 5; // A chunk is 16 blocks wide, 16 blocks long, and 256 blocks deep, which is 65,536 blocks total.
	
		SuperDopeJediMod.superDopeWorldGenerator.addOreSpawn(this, world, random, i, j, 16, 16, maxVeinSize, chancesPerChunk, minY, maxY); 
	}	
}
