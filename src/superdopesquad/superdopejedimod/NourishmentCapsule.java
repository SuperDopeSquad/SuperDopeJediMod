package superdopesquad.superdopejedimod;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemFood;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;


public class NourishmentCapsule extends BaseItemFood {

	static int healAmount = 20;
	static float saturationModifier = 2;
	static boolean isWolfFood = false;
	
	
	public NourishmentCapsule(String name) {
		
		// String name, int amount, float saturation, boolean isWolfFood
		super(name, healAmount, saturationModifier, isWolfFood);
		
		this.setCreativeTab(CreativeTabs.FOOD);
		this.setUnlocalizedName("nourishmentCapsule");
	}
	
	
	public void registerRecipe() {
	
		// Recipe for creating a Nourishment Capsule.
		ItemStack potatoStack = new ItemStack(Items.POTATO);
		ItemStack carrotStack = new ItemStack(Items.CARROT);
		ItemStack wheatStack = new ItemStack(Items.WHEAT);
		ItemStack sugarStack = new ItemStack(Items.SUGAR);

		GameRegistry.addShapedRecipe(this.getRegistryName(), null, new ItemStack(this, 4), 
				"AB", 
				"CD", 
				'A', potatoStack, 
				'B', carrotStack, 
				'C', wheatStack,
				'D', sugarStack);
	}
}
