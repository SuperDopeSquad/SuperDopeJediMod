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

public class ChromateOre extends BaseBlock{
	
public ChromateOre(String unlocalizedName) {
		
		super(Material.iron, unlocalizedName);
		this.setCreativeTab(CreativeTabs.tabBlock);
	}

	
	public Item getItemDropped(int metadata, Random random, int fortune) {
	        
		return Item.getItemFromBlock(SuperDopeJediMod.chromateOre);
	}
public void registerRecipe() {
	// Smelting a ChromateOre will create 1 Chromate Ingot
	GameRegistry.addSmelting(SuperDopeJediMod.chromateOre, new ItemStack(SuperDopeJediMod.chromateIngot), 1.0F);
	}
}
