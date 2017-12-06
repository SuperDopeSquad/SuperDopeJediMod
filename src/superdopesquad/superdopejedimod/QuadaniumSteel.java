package superdopesquad.superdopejedimod;


import java.util.Random;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraftforge.fml.common.registry.GameRegistry;


public class QuadaniumSteel extends BaseBlock {
	
	
	public QuadaniumSteel(String name) {
		
		super(Material.IRON, name);
	}
	
	
	 public Item getItemDropped(int metadata, Random random, int fortune) {
	        
	    return Item.getItemFromBlock(SuperDopeJediMod.quadaniumSteel);
	 }
	
	
	public void registerRecipe() {
		
		ItemStack quadaniumSteelIngotStackSingle = new ItemStack(SuperDopeJediMod.quadaniumSteelIngot);
		ItemStack quadaniumSteelIngotStackNine = new ItemStack(SuperDopeJediMod.quadaniumSteelIngot, 9);
		
		// 9 QuadaniumSteelIngots will create 1 QuadaniumSteel
		GameRegistry.addShapedRecipe(this.getRegistryName(), null, new ItemStack(this), "xxx", "xxx", "xxx", 'x', quadaniumSteelIngotStackSingle);
	
		// 1 QuadaniumSteel will create 9 QuadaniumSteelIngots.
		GameRegistry.addShapelessRecipe(Utilities.GetRegistryNameRecycler(this), null, quadaniumSteelIngotStackNine, Ingredient.fromStacks(new ItemStack(this)));
			
		// Smelting a QuadaniumSteelOre will create 1 QuadaniumSteelIngot
		GameRegistry.addSmelting(SuperDopeJediMod.quadaniumSteelOre, quadaniumSteelIngotStackSingle, 1.0F);		
	
		// 8 Iron Ingots and an Ink Sac will create 9 Quadanium Steel Ingots.
    	ItemStack inkSacStack = new ItemStack(Items.DYE, 1, 0);
    	ItemStack ironIngotStack = new ItemStack(Items.IRON_INGOT);
    	GameRegistry.addShapedRecipe(Utilities.GetRegistryNameBackdoor(this), null, quadaniumSteelIngotStackNine, "xxx", "xyx", "xxx", 'x', ironIngotStack, 'y', inkSacStack);
	}
}
