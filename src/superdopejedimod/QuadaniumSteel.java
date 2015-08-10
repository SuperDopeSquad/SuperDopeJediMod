package superdopesquad.superdopejedimod;


import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraftforge.fml.common.registry.GameRegistry;


public class QuadaniumSteel extends BaseBlock {
	
	
	public QuadaniumSteel(String name) {
		
		super(Material.iron, name);
	}
	
	
	public void registerRecipe() {
		
		// 9 QuadaniumSteelIngots will create 1 QuadaniumSteel
		GameRegistry.addShapelessRecipe(new ItemStack(this), new ItemStack(SuperDopeJediMod.quadaniumSteelIngot, 9));
		
		// 1 QuadaniumSteel will create 9 QuadaniumSteelIngots.
		GameRegistry.addShapelessRecipe(new ItemStack(SuperDopeJediMod.mandalorianIronIngot, 9), new ItemStack(this));
		
		// Smelting a QuadaniumSteelOre will create 1 QuadaniumSteelIngot
		GameRegistry.addSmelting(SuperDopeJediMod.quadaniumSteelOre, new ItemStack(SuperDopeJediMod.quadaniumSteelIngot), 1.0F);		
	}
	
}
