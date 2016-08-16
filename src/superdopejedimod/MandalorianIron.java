package superdopesquad.superdopejedimod;


import java.util.Random;

import net.minecraft.block.material.Material;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;


public class MandalorianIron extends BaseBlock {

	
	public MandalorianIron(String unlocalizedName) {
		super(Material.IRON, unlocalizedName);
	}
	
	
	public Item getItemDropped(int metadata, Random random, int fortune) {
        
		return Item.getItemFromBlock(SuperDopeJediMod.mandalorianIron);
	}
	
	
	public void registerRecipe() {
		
		// 9 MandalorianIronIngots will create 1 MandalorianIron
		//GameRegistry.addRecipe(new ItemStack(this, 1), "x", 'x', new ItemStack(SuperDopeJediMod.mandalorianIronIngot, 9));
		GameRegistry.addShapelessRecipe(new ItemStack(this), new ItemStack(SuperDopeJediMod.mandalorianIronIngot, 9));
		
		// 1 MandalorianIron will create 9 MandalorinIronIngots.
		//GameRegistry.addRecipe(new ItemStack(SuperDopeJediMod.mandalorianIronIngot, 9), "x", 'x', new ItemStack(this, 1));
		GameRegistry.addShapelessRecipe(new ItemStack(SuperDopeJediMod.mandalorianIronIngot, 9), new ItemStack(this));
		
		// Smelting a MandalorianIronOre will create 1 MandalorianIronIngot
		GameRegistry.addSmelting(SuperDopeJediMod.mandalorianIronOre, new ItemStack(SuperDopeJediMod.mandalorianIronIngot), 1.0F);		
	}
}

