package superdopesquad.superdopejedimod;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemFood;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;


public class NourishmentCapsule extends BaseItemFood {

	
	public NourishmentCapsule(String name) {
		
		// String name, int amount, float saturation, boolean isWolfFood
		super(name, 20, 20, false);
		
		this.setCreativeTab(CreativeTabs.tabFood);
		this.setUnlocalizedName("nourishmentCapsule");
	}
	
	
	public void registerRecipe() {
	
		// Recipe for creating a Nourishment Capsule.
		ItemStack potatoStack = new ItemStack(Items.potato);
		ItemStack carrotStack = new ItemStack(Items.carrot);
		ItemStack breadStack = new ItemStack(Items.bread);
		ItemStack sugarStack = new ItemStack(Items.sugar);
		ItemStack mushroomStack = new ItemStack(Blocks.brown_mushroom);
		ItemStack appleStack = new ItemStack(Items.apple);

		GameRegistry.addRecipe(new ItemStack(this, 6), 
				"AB", 
				"CD", 
				"EF", 
				'A', potatoStack, 
				'B', carrotStack, 
				'C', breadStack,
				'D', sugarStack, 
				'E', mushroomStack, 
				'F', appleStack);
	}
}
