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


public class ElectricTransmitter extends BaseBlock{

	public ElectricTransmitter(String unlocalizedName) {
		
		super(Material.iron, unlocalizedName);
		
		this.setCreativeTab(CreativeTabs.tabRedstone);
		
	}
	
	public Item getItemDropped(int metadata, Random random, int fortune) {
        
		return Item.getItemFromBlock(SuperDopeJediMod.electricTransmitter);
	}
	
public void registerRecipe() {
		
		// Recipe for creating an ElectricTransmitter.
		ItemStack chromateIngotStack = new ItemStack(SuperDopeJediMod.chromateIngot);
		ItemStack redstoneStack = new ItemStack(Items.redstone);
		ItemStack compressedMetalPlateStack = new ItemStack(SuperDopeJediMod.compressedMetalPlate);
		ItemStack diamondStack = new ItemStack(Items.diamond);
		
		GameRegistry.addRecipe(new ItemStack(this, 1), 
				"ADA", 
				"CBC", 
				"ADA", 
				'A', compressedMetalPlateStack, 
				'B', redstoneStack, 
				'C', diamondStack,
				'D', chromateIngotStack);
				
	}
	
}
