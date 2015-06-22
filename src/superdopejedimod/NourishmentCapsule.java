package superdopesquad.superdopejedimod;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemFood;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;


public class NourishmentCapsule extends ItemFood {

	
	public NourishmentCapsule() {
		
		//public ItemFood(int amount, float saturation, boolean isWolfFood)
		super(5, 5, false);
		
		this.setCreativeTab(CreativeTabs.tabFood);
		this.setUnlocalizedName("nourishmentCapsule");
	}
	
	
	public void registerRecipe() {
	
		// potato, carrots, bread, sugar, mushroom, apple
		
		// Recipe for creating a Nourishment Capsule.
		ItemStack potatoStack = new ItemStack(Items.potato);
		ItemStack carrotStack = new ItemStack(Items.carrot);
		ItemStack breadStack = new ItemStack(Items.bread);
		ItemStack sugarStack = new ItemStack(Items.sugar);
		//ItemStack mushroomStack = new ItemStack();
		ItemStack appleStack = new ItemStack(Items.potato);
		ItemStack stoneButtonStack = new ItemStack(Blocks.stone_button);
		ItemStack ironIngotStack = new ItemStack(Items.iron_ingot);
		//GameRegistry.addRecipe(new ItemStack(this), " x", " y", " z", 'x', redPowerCrystalStack, 'y', stoneButtonStack, 'z', ironIngotStack);
	}
}
