package superdopesquad.superdopejedimod;


import java.util.Random;
import net.minecraft.block.material.Material;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.GameRegistry;


public class MandalorianIron extends BaseBlock {

	
	public MandalorianIron(String unlocalizedName) {

		super(Material.IRON, unlocalizedName);
	}
	
	
	public Item getItemDropped(int metadata, Random random, int fortune) {
        
		return Item.getItemFromBlock(SuperDopeJediMod.mandalorianIron);
	}
	
	
	@Override
	public void registerRecipe() {
		
		ItemStack mandalorianIronIngotStackSingle = new ItemStack(SuperDopeJediMod.mandalorianIronIngot);
		ItemStack mandalorianIronIngotStackNine = new ItemStack(SuperDopeJediMod.mandalorianIronIngot, 9);
				
		// 9 MandalorianIronIngots will create 1 MandalorianIron
		GameRegistry.addShapedRecipe(this.getRegistryName(), null, new ItemStack(this), "xxx", "xxx", "xxx", 'x', mandalorianIronIngotStackSingle);
			
		// 1 MandalorianIron will create 9 MandalorinIronIngots.
		GameRegistry.addShapelessRecipe(Utilities.GetRegistryNameRecycler(this), null, mandalorianIronIngotStackNine, Ingredient.fromStacks(new ItemStack(this)));     //(this.getRegistryName(), null, mandalorianIronIngotStackNine, new ItemStack(this));
			
		// Smelting a MandalorianIronOre will create 1 MandalorianIronIngot
		GameRegistry.addSmelting(SuperDopeJediMod.mandalorianIronOre, mandalorianIronIngotStackSingle, 1.0F);		
	
		// 8 Iron Ingots and a Lapis Luzuli will create 9 Mandalorian Iron Ingots.
    	ItemStack lapisLazuliStack = new ItemStack(Items.DYE, 1, 4);
    	ItemStack ironIngotStack = new ItemStack(Items.IRON_INGOT);
    	GameRegistry.addShapedRecipe(Utilities.GetRegistryNameBackdoor(this), null, mandalorianIronIngotStackNine, "xxx", "xyx", "xxx", 'x', ironIngotStack, 'y', lapisLazuliStack);		
	}
}
