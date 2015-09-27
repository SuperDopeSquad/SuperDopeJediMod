package superdopesquad.superdopejedimod;

import java.util.Random;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;


public class Engine extends BaseBlock {

public Engine(String unlocalizedName) {
		
		super(Material.iron, unlocalizedName);
		this.setCreativeTab(CreativeTabs.tabRedstone);
	}

	
	public Item getItemDropped(int metadata, Random random, int fortune) {
	        
		return Item.getItemFromBlock(SuperDopeJediMod.engine);
	}
	
	public void registerRecipe() {
		
		// Recipe for creating an Engine.
		ItemStack chromateIngotStack = new ItemStack(SuperDopeJediMod.chromateIngot);
		ItemStack redstoneStack = new ItemStack(Items.redstone);
		ItemStack compressedMetalPlateStack = new ItemStack(SuperDopeJediMod.compressedMetalPlate);
		ItemStack quadaniumSteelStack = new ItemStack(SuperDopeJediMod.quadaniumSteel);
		
		GameRegistry.addRecipe(new ItemStack(this, 1), 
				"ADA", 
				"CBC", 
				"ADA", 
				'A', compressedMetalPlateStack, 
				'B', redstoneStack, 
				'C', quadaniumSteelStack,
				'D', chromateIngotStack);
				
	}
	
}
	

